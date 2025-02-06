package com.team5959.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.team5959.Constants;
import com.team5959.Constants.ElevatorConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput; //0 is pressed, 1 is not pressed
import edu.wpi.first.wpilibj.PowerDistribution;
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

    private final DigitalInput elevatorLimitSwitchUp;
    private final DigitalInput elevatorLimitSwitchDown;

    private boolean PIDOn;
    
    public ElevatorSubsytem(){
        //instatiate motors, config and encoder
        elevatorRight = new SparkMax(ElevatorConstants.elevatorRightID, MotorType.kBrushless);
        elevatorLeft = new SparkMax(ElevatorConstants.elevatorLeftID, MotorType.kBrushless);

        elevatorEncoder = elevatorRight.getEncoder();
        
        elevatorRightConfig = new SparkMaxConfig();
        elevatorLeftConfig = new SparkMaxConfig();

        elevatorLimitSwitchUp = new DigitalInput(ElevatorConstants.elevatorLimitSwitchUpID);
        elevatorLimitSwitchDown = new DigitalInput(ElevatorConstants.elevatorLimitSwitchDownID);

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
        
        targetPosition = elevatorEncoder.getPosition(); // Set target to current position
    }

    public boolean LimitSwitchUpState() {
        return !elevatorLimitSwitchUp.get();
    }

    public boolean LimitSwitchDownState() {
        return !elevatorLimitSwitchDown.get();
    }

    // Preset positions
    public void moveToPositionCero() {
        PIDOn = true;
        setTargetPosition(Constants.ElevatorConstants.elevatorStartingPosition);  // Move to preset position 0
    }

    public void moveToPositionOne() {
        PIDOn = true;
        setTargetPosition(Constants.ElevatorConstants.elevatorPositionOne);  // Move to preset position 1
    }

    public void moveToPositionTwo() {
        PIDOn = true;
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
        // Clamp the PID out
        if (LimitSwitchUpState()){
        pidOutput = MathUtil.clamp(pidOutput, -1.0, 0);
        } else if (LimitSwitchDownState()){
        pidOutput = MathUtil.clamp(pidOutput, 0, 1); 
        }else {
        pidOutput = MathUtil.clamp(pidOutput, -1.0, 1.0);
        }

        elevatorRight.set(pidOutput);
    }

    public void stopElevator(){
        elevatorRight.set(0);
    }
    // Method to check if the motor has reached the target position
    public boolean atTargetPosition() {
        return elevatorPID.atSetpoint();
    }
}