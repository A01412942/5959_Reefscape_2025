package com.team5959.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.team5959.Constants;
import com.team5959.Constants.ArmConstants;
import com.team5959.Constants.ElevatorConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput; //0 is pressed, 1 is not pressed
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.spark.config.SoftLimitConfig;



public class ElevatorSubsytem extends SubsystemBase{
    //INITIALIZATION

    //initialize motors
    private final SparkMax elevatorRight;
    private final SparkMax elevatorLeft;

    //initialize motor configuration
    private final SparkBaseConfig elevatorLeftConfig;
    private final SparkBaseConfig elevatorRightConfig;

    private final SoftLimitConfig elevatorSoftLimitConfig;

    //initialize encoder
    private final RelativeEncoder elevatorEncoder;

    private final PIDController elevatorPID;

    private double targetPosition;

    private double elevatorOutput;

    private final DigitalInput elevatorLimitSwitchUp, elevatorLimitSwitchDown;
    
    public ElevatorSubsytem(){
        //instatiate motors, config and encoder
        elevatorRight = new SparkMax(ElevatorConstants.elevatorRightID, MotorType.kBrushless);
        elevatorLeft = new SparkMax(ElevatorConstants.elevatorLeftID, MotorType.kBrushless);

        elevatorEncoder = elevatorRight.getEncoder();
        
        elevatorRightConfig = new SparkMaxConfig();
        elevatorLeftConfig = new SparkMaxConfig();

        elevatorSoftLimitConfig = new SoftLimitConfig();

        elevatorLimitSwitchUp = new DigitalInput(ElevatorConstants.elevatorLimitSwitchUpID);
        elevatorLimitSwitchDown = new DigitalInput(ElevatorConstants.elevatorLimitSwitchDownID);

        elevatorLeftConfig.follow(elevatorRight, ElevatorConstants.elevatorLeftInverted);
        elevatorLeftConfig.idleMode(IdleMode.kBrake);
        elevatorRightConfig.idleMode(IdleMode.kBrake);
        elevatorRightConfig.inverted(ElevatorConstants.elevatorRightInverted);
    
    
        elevatorRightConfig.apply(elevatorSoftLimitConfig);
        
        
        
        elevatorLeft.configure(elevatorLeftConfig, null, null);
        elevatorRight.configure(elevatorRightConfig, null, null);


        elevatorEncoder.setPosition(Constants.ElevatorConstants.elevatorStartingPosition);
        elevatorPID = new PIDController(Constants.ElevatorConstants.KP_ELEVATOR, Constants.ElevatorConstants.KI_ELEVATOR, Constants.ElevatorConstants.KD_ELEVATOR);
    }
    public void holdCurrentPosition() {
        
        targetPosition = elevatorEncoder.getPosition(); // Set target to current position
    }

    public boolean LimitSwitchUpState() {
        return elevatorLimitSwitchUp.get();
    }

    public boolean LimitSwitchDownState() {
        return elevatorLimitSwitchDown.get();
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
    public void periodic() { //still wont work,, wondering how to make it work
       
        // PID control mode
        double pidOutput = elevatorPID.calculate(elevatorEncoder.getPosition(), targetPosition);
        if (!LimitSwitchUpState()) {
           elevatorSoftLimitConfig.forwardSoftLimit(elevatorEncoder.getPosition());
           elevatorSoftLimitConfig.forwardSoftLimitEnabled(true);
           elevatorSoftLimitConfig.reverseSoftLimitEnabled(false);
        } else if (!LimitSwitchDownState()){
            elevatorSoftLimitConfig.reverseSoftLimit(elevatorEncoder.getPosition());
            elevatorSoftLimitConfig.forwardSoftLimitEnabled(false);
            elevatorSoftLimitConfig.reverseSoftLimitEnabled(true);
        } else {
            elevatorOutput = pidOutput;
            elevatorSoftLimitConfig.forwardSoftLimitEnabled(false);
            elevatorSoftLimitConfig.reverseSoftLimitEnabled(false);
        }

        elevatorRight.set(elevatorOutput);
    }

    public void stopElevator(){
        elevatorRight.set(0);
    }
    
    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return elevatorPID.atSetpoint();
    }

}