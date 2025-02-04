package com.team5959.commands;

import java.util.function.BooleanSupplier;

import com.team5959.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCommand extends Command{
    //INITIALIZATION

    private final IntakeSubsystem intakeSubsystem;
    private final BooleanSupplier squareButtonisPressedSupplier, squareInversedisPressedSupplier;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, BooleanSupplier squareButtonisPressedSupplier, BooleanSupplier squareInversedBooleanSupplier){
        this.intakeSubsystem = intakeSubsystem;
        this.squareButtonisPressedSupplier = squareButtonisPressedSupplier;
        this.squareInversedisPressedSupplier = squareInversedBooleanSupplier;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean squareButtonisPressed = squareButtonisPressedSupplier.getAsBoolean();
        boolean squareInversedBooleanSupplier = squareInversedisPressedSupplier.getAsBoolean();

        //set different positons of the elevator
        if (squareButtonisPressed) {
            intakeSubsystem.runIntake(0.6);
        } else if (squareInversedBooleanSupplier) {
            intakeSubsystem.runIntake(-0.6);
        } else{
            intakeSubsystem.stopIntake();
        }
    }
}
