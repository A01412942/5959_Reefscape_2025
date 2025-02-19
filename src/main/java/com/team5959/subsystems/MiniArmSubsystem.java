package com.team5959.subsystems;

import com.team5959.Constants.MiniArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class MiniArmSubsystem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax miniArmMotor;

    //Encoder Absolute Position
    DutyCycleEncoder miniArmAbsoluteEncoder;
    double miniArmPosition;
    double miniArmPositionMultiplication;
    int miniArmPositionDegrees;


    //initialize PID controller
    private final PIDController miniArmPID;

    //Target position
    private double miniArmTargetPosition = 145;

    public MiniArmSubsystem(){
        //instatiate motors, config and encoder
        miniArmMotor = new SparkMax(MiniArmConstants.miniArmMotorID, MotorType.kBrushless);

        miniArmPID = new PIDController(MiniArmConstants.KP_MINI_ARM, MiniArmConstants.KI_MINI_ARM, MiniArmConstants.KD_MINI_ARM);
        
        //Absolute Encoder
        miniArmAbsoluteEncoder = new DutyCycleEncoder(MiniArmConstants.absoluteEncoderPort);
    }

    public void actualPosition(){
        miniArmPosition = miniArmAbsoluteEncoder.get();
        miniArmPositionMultiplication = miniArmPosition * 360;
        miniArmPositionDegrees = (int)miniArmPositionMultiplication;
    }
    // Method to set a target position
    public void setMiniArmTargetPosition(double position) {
        miniArmTargetPosition = position;
    }

    public void moveToStartingPosition(){
        setMiniArmTargetPosition(MiniArmConstants.miniArmStartingPosition);
    }

    public void moveToDropAlgaePosition(){
        setMiniArmTargetPosition(MiniArmConstants.miniArmDropAlgaePosition);
    }
    public void moveToDownPosition(){
        setMiniArmTargetPosition(MiniArmConstants.miniArmDownPosition);
    }
   
    @Override
    public void periodic() {  
        actualPosition();
        SmartDashboard.putNumber("Mini Arm Position", miniArmPositionDegrees);

        // Calculate PID output
        double pidOutput = miniArmPID.calculate(miniArmPositionDegrees, miniArmTargetPosition);
        
        // Set the motor to the calculated PID output
        miniArmMotor.set(pidOutput);
        
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return miniArmPID.atSetpoint();
    }
}