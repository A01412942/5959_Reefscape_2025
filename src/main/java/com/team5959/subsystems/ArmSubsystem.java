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

    private double armTargetPosition;

    public ArmSubsystem(){
        //instatiate motors, config and encoder
        armMotor = new SparkMax(ArmConstants.armMotorID, MotorType.kBrushless);

        armEncoder = armMotor.getEncoder();

        armEncoder.setPosition(Constants.ElevatorConstants.elevatorStartingPosition);
        armPID = new PIDController(ArmConstants.KP_ARM, ArmConstants.KI_ARM, ArmConstants.KD_ARM);
    }

    // Method to set a target position
    public void setArmTargetPosition(double position) {
        armTargetPosition = position;
    }

    public void moveToPositionCero(){
        setArmTargetPosition(ArmConstants.armStartingPosition);
    }

    public void moveToScoringPosition(){
        setArmTargetPosition(ArmConstants.armScoringPosition);
    }

    @Override
    public void periodic() {
        double pidOutput = armPID.calculate(armEncoder.getPosition(), armTargetPosition); // Calculate PID output
        armMotor.set(pidOutput); // Set the motor to the calculated PID output
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return armPID.atSetpoint();
    }
}