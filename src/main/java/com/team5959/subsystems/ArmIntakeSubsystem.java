package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.ArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
//import com.revrobotics.RelativeEncoder;

public class ArmIntakeSubsystem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax armMotor;

    //initialize encoder
 //   private final RelativeEncoder armEncoder;

    //initialize PID controller
    private final PIDController armPID;

    //Target position
    double armTargetPosition;

    //Encoder Absolute Position
    DutyCycleEncoder armAbsoluteEncoder;
    double armPosition;
    double armPositionMultiplication;
    int armPositionDegrees;
    RelativeEncoder positionSpark;


    public ArmIntakeSubsystem(){
        //instatiate motors, config and encoder
        armMotor = new SparkMax(ArmConstants.armMotorID, MotorType.kBrushless);

    //    armEncoder = armMotor.getEncoder();
    //    armEncoder.setPosition(ArmConstants.armIntakeInStartingPosition);

        armPID = new PIDController(ArmConstants.KP_ARM, ArmConstants.KI_ARM, ArmConstants.KD_ARM);

        //Encoder Absolute
        //armAbsoluteEncoder = new DutyCycleEncoder(ArmConstants.absoluteEncoderPort);

        positionSpark = armMotor.getEncoder();
        SmartDashboard.putNumber("Pivote Position", armPositionDegrees);
    }
/*
    public void actualPosition(){
        armPosition = armAbsoluteEncoder.get();
        armPositionMultiplication = armPosition * 360;
        armPositionDegrees = (int)armPositionMultiplication;
    }
*/
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
        //actualPosition();
        SmartDashboard.putNumber("Arm Position", armPositionDegrees);

        // Calculate PID output
        double pidOutput = armPID.calculate(positionSpark.getPosition(), armTargetPosition);
        
        // Set the motor to the calculated PID output
        armMotor.set(pidOutput);
        

    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return armPID.atSetpoint();
    }
}