package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.ArmConstants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmSubsystem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax armMotor;


    //initialize encoder
    private final RelativeEncoder armEncoder;

    private final PIDController armPID;

    private double armPosition;

    private boolean controlMode = false;

    private double armVelocity;

    public ArmSubsystem(){
        //instatiate motors, config and encoder
        armMotor = new SparkMax(ArmConstants.armMotorID, MotorType.kBrushless);

        armEncoder = armMotor.getEncoder();

        armEncoder.setPosition(Constants.ElevatorConstants.elevatorStartingPosition);
        armPID = new PIDController(ArmConstants.KP_ARM, ArmConstants.KI_ARM, ArmConstants.KD_ARM);
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

    public void moveManualArm(double triggerIn) {
        controlMode = true;
        armVelocity = triggerIn - ArmConstants.ARM_SPEED_REDUCER;
    }

    public void moveInvertedManualArm(double triggerIn) {
        controlMode = true;
        armVelocity = -triggerIn + ArmConstants.ARM_SPEED_REDUCER;
    }


    @Override
    public void periodic() {

        if(controlMode) { // If not in manual control mode
        // PID control mode
        double pidOutput = armPID.calculate(armEncoder.getPosition(), armPosition); // Calculate PID output
        armMotor.set(pidOutput); // Set the motor to the calculated PID output
        } else {
            armMotor.set(armVelocity);
        }       
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return armPID.atSetpoint();
    }
}