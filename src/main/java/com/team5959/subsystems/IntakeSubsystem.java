package com.team5959.subsystems;

import com.team5959.Constants;
import com.team5959.Constants.IntakeConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;


public class IntakeSubsystem extends SubsystemBase {
    //INIZIALIZATION

    //initialize motors
    private final SparkMax coralIntakeMotor;
    private final SparkMax algaeIntakeMotor;

    //initialize motor configuration
    private final SparkBaseConfig coralIntakeMotorConfig;
    private final SparkBaseConfig algaeIntakeMotorConfig;

    //initialize encoder
    DutyCycleEncoder pivotEncoder = new DutyCycleEncoder(IntakeConstants.absoluteEncoderPort);
    double pivotePosition;
    double pivotePositionGrados;

    public IntakeSubsystem (){

        //instatiate motors and config
        coralIntakeMotor = new SparkMax(IntakeConstants.coralIntakeMotorID, MotorType.kBrushless);
        algaeIntakeMotor = new SparkMax(IntakeConstants.algaeIntakeMotorID, MotorType.kBrushless);

        coralIntakeMotorConfig = new SparkMaxConfig();
        algaeIntakeMotorConfig = new SparkMaxConfig();

        coralIntakeMotorConfig.idleMode(IdleMode.kBrake);
        algaeIntakeMotorConfig.idleMode(IdleMode.kBrake);

        coralIntakeMotor.configure(coralIntakeMotorConfig, null,null);
        algaeIntakeMotor.configure(algaeIntakeMotorConfig, null,null);
    }

    public void runCoralIntake(double speed){
        coralIntakeMotor.set(speed);
    }

    public void runAlgaeIntake(double speed){
        algaeIntakeMotor.set(speed);
    }

    public void stopCoralIntake(){
        coralIntakeMotor.set(0);
    }

    public void stopAlgaeIntake(){
        algaeIntakeMotor.set(0);
    }

    public void periodic (){
        pivotePosition = pivotEncoder.get();
        pivotePositionGrados = pivotePosition * 360;
        SmartDashboard.putNumber("Pivote Position", pivotePositionGrados);
    }
}
