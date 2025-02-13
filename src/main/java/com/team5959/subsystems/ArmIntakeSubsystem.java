package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.ArmConstants;
import com.team5959.Constants.IntakeConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class ArmIntakeSubsystem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax armMotor;

    //initialize encoder
    private final RelativeEncoder armEncoder;

    //initialize PID controller
    private final PIDController armPID;

    //Target position
    private double armTargetPosition;

    //Encoder Absolute Position
    DutyCycleEncoder armAbsoluteEncoder;
    double armPosition;
    double armPositionDegrees;

    public ArmIntakeSubsystem(){
        //instatiate motors, config and encoder
        armMotor = new SparkMax(ArmConstants.armMotorID, MotorType.kBrushless);

        armEncoder = armMotor.getEncoder();
        armEncoder.setPosition(ArmConstants.armIntakeInStartingPosition);

        armPID = new PIDController(ArmConstants.KP_ARM, ArmConstants.KI_ARM, ArmConstants.KD_ARM);

        //Encoder Absolute
        armAbsoluteEncoder = new DutyCycleEncoder(IntakeConstants.absoluteEncoderPort);
    }

    // Method to set a target position
    public void setArmTargetPosition(double position) {
        armTargetPosition = position;
    }

    public void moveToInPosition(){
        setArmTargetPosition(ArmConstants.armIntakeInStartingPosition);
    }

    public void moveToOutPosition(){
        setArmTargetPosition(ArmConstants.armIntakeOutPosition);
    }
    
    @Override
    public void periodic() {
        // Calculate PID output
        double pidOutput = armPID.calculate(armEncoder.getPosition(), armTargetPosition);
        
        // Set the motor to the calculated PID output
        armMotor.set(pidOutput);
        
        //Abosulte Encoder
        armPosition = armAbsoluteEncoder.get();
        armPositionDegrees = armPosition * 360;
        SmartDashboard.putNumber("Pivote Position", armPositionDegrees);
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return armPID.atSetpoint();
    }
}