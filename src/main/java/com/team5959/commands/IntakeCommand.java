package com.team5959.commands;

import java.util.function.BooleanSupplier;

import com.team5959.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCommand extends Command{
    //INITIALIZATION

    private final IntakeSubsystem intakeSubsystem;
    private final BooleanSupplier squareButtonSupplier, invertedIntakeSupplier;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, BooleanSupplier squareButtonSupplier, BooleanSupplier invertedIntakeSupplier){
        this.intakeSubsystem = intakeSubsystem;
        this.squareButtonSupplier = squareButtonSupplier;
        this.invertedIntakeSupplier = invertedIntakeSupplier;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean squareButton = squareButtonSupplier.getAsBoolean();
        boolean invertedIntake = invertedIntakeSupplier.getAsBoolean();

        //set different positons of the elevator
        if (invertedIntake) {
            intakeSubsystem.runIntake(-0.6);
        } else if (squareButton) {
            intakeSubsystem.runIntake(0.6);
        } else {
            intakeSubsystem.stopIntake();}
        }

    
      
            @Override
    public boolean isFinished() {
        return false; // Command never finishes on its own
    }

  
    
}
