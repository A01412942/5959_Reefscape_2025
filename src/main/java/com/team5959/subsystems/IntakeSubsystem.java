package com.team5959.subsystems;

import com.team5959.Constants.IntakeConstants;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    //INIZIALIZATION

    //initialize motors
    private final SparkMax intakeMotor;

    //initialize motor configuration
    
    private final SparkBaseConfig intakeMotorConfig;

    public IntakeSubsystem (){
        //instatiate motors and config
        intakeMotor = new SparkMax(IntakeConstants.intakeMotorID, MotorType.kBrushless);

        intakeMotorConfig = new SparkMaxConfig();

        intakeMotorConfig.idleMode(IdleMode.kBrake);

        intakeMotor.configure(intakeMotorConfig, null,null);

    }

    public void runIntake(double speed){
        intakeMotor.set(speed);
    }

    public void runIntakeInverse(double speed){
        intakeMotor.set(-speed);
    }

    public void stopIntake(){
        intakeMotor.set(0);
    }
}
