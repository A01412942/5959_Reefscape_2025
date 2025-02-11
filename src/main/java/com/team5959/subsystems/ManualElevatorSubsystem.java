package com.team5959.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.team5959.Constants.ElevatorConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ManualElevatorSubsystem extends SubsystemBase {
    //INITIALIZATION

    //initialize motors
    private final SparkMax elevatorRight;
    private final SparkMax elevatorLeft;

    //initialize motor configuration
    private final SparkBaseConfig elevatorLeftConfig;
    private final SparkBaseConfig elevatorRightConfig;

    //initialize encoder
    private final RelativeEncoder elevatorEncoder;

    //initialize PID controller

    public ManualElevatorSubsystem(){
        //instatiate motors, config and encoder
        elevatorRight = new SparkMax(ElevatorConstants.elevatorRightID, MotorType.kBrushless);
        elevatorLeft = new SparkMax(ElevatorConstants.elevatorLeftID, MotorType.kBrushless);

        elevatorEncoder = elevatorRight.getEncoder();

        elevatorRightConfig = new SparkMaxConfig();
        elevatorLeftConfig = new SparkMaxConfig();

        elevatorLeftConfig.follow(elevatorRight, ElevatorConstants.elevatorLeftInverted);
        elevatorLeftConfig.idleMode(IdleMode.kBrake);
        elevatorRightConfig.idleMode(IdleMode.kBrake);
        elevatorRightConfig.inverted(ElevatorConstants.elevatorRightInverted);
        
        elevatorLeft.configure(elevatorLeftConfig, null, null);
        elevatorRight.configure(elevatorRightConfig, null, null);
    }

    public void elevatorManualMode(double speed){
        elevatorRight.set(speed);
    }

    public void stopElevator(){
        elevatorRight.set(0);
    }

    public void getElevatorPosition(){
        elevatorEncoder.getPosition();
    }

  /*   @Override
    public void periodic(){
        SmartDashboard.putNumber("Elevator Pos", getElevatorPosition());
    } */
}
