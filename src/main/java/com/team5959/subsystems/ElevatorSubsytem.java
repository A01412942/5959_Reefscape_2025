package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.ElevatorConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

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

    //initialize PID controller
    private final PIDController elevatorPID;

    //Target position
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

        elevatorPID = new PIDController(Constants.ElevatorConstants.KP_ELEVATOR, Constants.ElevatorConstants.KI_ELEVATOR, Constants.ElevatorConstants.KD_ELEVATOR);
    }
    public void holdCurrentPosition() {
        // Set target to current position
        targetPosition = elevatorEncoder.getPosition(); 
    }

    //PRESET POSITIONS
    public void moveToStartingPosition() {
        // Move to preset position 0
        setTargetPosition(Constants.ElevatorConstants.elevatorStartingPosition);  
    }

    public void moveToL1Position() {     
        // Move to preset L1 position
        setTargetPosition(Constants.ElevatorConstants.elevatorL1Position);  
    }

    public void moveToL2Position() {
        // Move to preset L2 position
        setTargetPosition(Constants.ElevatorConstants.elevatorL2Position);  
    }

    public void moveToL3Position() {
        // Move to preset L3 position
        setTargetPosition(Constants.ElevatorConstants.elevatorL3Position);  
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

