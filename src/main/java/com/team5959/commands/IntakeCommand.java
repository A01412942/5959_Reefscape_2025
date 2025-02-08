package com.team5959.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.team5959.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCommand extends Command{
    //INITIALIZATION

    private final IntakeSubsystem intakeSubsystem;
    private final DoubleSupplier  ltAxisSupplier, rtAxisSupplier;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, DoubleSupplier ltAxisSupplier, DoubleSupplier rtAxisSupplier){
        this.intakeSubsystem = intakeSubsystem;
        this.ltAxisSupplier = ltAxisSupplier;
        this.rtAxisSupplier = rtAxisSupplier;
        addRequirements(intakeSubsystem);
    }
    
    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        double ltAxis = ltAxisSupplier.getAsDouble();
        double rtAxis = rtAxisSupplier.getAsDouble();

        //set different positons of the elevator
        if (ltAxis > 0.5) {
            intakeSubsystem.runCoralIntake(-0.6);
        } else if (rtAxis > 0.5) {
            intakeSubsystem.runCoralIntake(0.6);
        } else {
            intakeSubsystem.stopCoralIntake();
        }
    }
    
      
    @Override
    public boolean isFinished() {
        return false; // Command never finishes on its own
    }

  
    
}
