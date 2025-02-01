package com.team5959.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.team5959.Constants;
import com.team5959.Constants.ArmConstants;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmSubsystem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax intake;


    //initialize encoder
    private final RelativeEncoder intakeEncoder;

    private final PIDController intakePID;

    private double armPosition;

    private boolean controlMode = false;

    private double intakevelocity;

    public ArmSubsystem(){
        //instatiate motors, config and encoder
        intake = new SparkMax(ArmConstants.armMotorID, MotorType.kBrushless);

        intakeEncoder = intake.getEncoder();

        intakeEncoder.setPosition(Constants.ElevatorConstants.elevatorStartingPosition);
        intakePID = new PIDController(ArmConstants.KP_ARM, ArmConstants.KI_ARM, ArmConstants.KD_ARM);
    }

    // Method to set a target position
    public void moveArmPosition(double position) {
        armPosition = position;
    }

    // Preset positions
    public void moveToStartingPosition() {
        controlMode = false;
        moveArmPosition(ArmConstants.armStartingPosition);  // Move to Starting position
    }

    public void moveToCoralPosition() {
        controlMode = false;
        
        moveArmPosition(ArmConstants.armCoralPosition);  // Move to Coral position
    }

    public void moveManualIntake(double triggerIn) {
        controlMode = true;
        intakevelocity = triggerIn - ArmConstants.ARM_SPEED_REDUCER;
    }

    public void moveInvertedManualIntake(double triggerIn) {
        controlMode = true;
        intakevelocity = -triggerIn + ArmConstants.ARM_SPEED_REDUCER;
    }


    @Override
    public void periodic() {
        if(!controlMode) { // If not in manual control mode
       
        // PID control mode
        double pidOutput = intakePID.calculate(intakeEncoder.getPosition(), armPosition); // Calculate PID output
        intake.set(pidOutput); // Set the motor to the calculated PID output
        } else {
            intake.set(intakevelocity);
        }
       
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return intakePID.atSetpoint();
    }
}