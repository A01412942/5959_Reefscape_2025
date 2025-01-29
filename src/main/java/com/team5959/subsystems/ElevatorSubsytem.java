package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.ElevatorConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsytem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax elevatorRight;
    private final SparkMax elevatorLeft;

    //initialize motor configuration
    private final SparkBaseConfig elevatorLeftConfig;
    private final SparkBaseConfig elevatorRightConfig;

    //initialize encoder
    private final RelativeEncoder elevatorEncoder;

    private final PIDController elevatorPID;

    private double targetPosition;
    
    public ElevatorSubsytem(){
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


        elevatorEncoder.setPosition(Constants.ElevatorConstants.elevatorStartingPosition);
        elevatorPID = new PIDController(Constants.ElevatorConstants.KP_ELEVATOR, Constants.ElevatorConstants.KI_ELEVATOR, Constants.ElevatorConstants.KP_ELEVATOR);
    }
    public void holdCurrentPosition() {
        
        targetPosition = elevatorEncoder.getPosition(); // Set target to current position
    }



    // Preset positions
    public void moveToPositionCero() {
        
        setTargetPosition(Constants.ElevatorConstants.elevatorStartingPosition);  // Move to preset position 0
    }

    public void moveToPositionOne() {
        
        setTargetPosition(Constants.ElevatorConstants.elevatorPositionOne);  // Move to preset position 1
    }

    public void moveToPositionTwo() {
        
        setTargetPosition(Constants.ElevatorConstants.elevatorPositionTwo);  // Move to preset position 2
    }

    // Method to set a target position
    public void setTargetPosition(double position) {
        targetPosition = position;
    }

    @Override
    public void periodic() {
       
        // PID control mode
        double pidOutput = elevatorPID.calculate(elevatorEncoder.getPosition(), targetPosition);
        elevatorRight.set(pidOutput); // Set the motor to the calculated PID output
        
       
    }

    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return elevatorPID.atSetpoint();
    }
}

