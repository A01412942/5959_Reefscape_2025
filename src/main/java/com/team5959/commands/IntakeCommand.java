package com.team5959.commands;

import com.team5959.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

public class IntakeCommand extends Command{

    //INITIALIZATION
    private final IntakeSubsystem intakeSubsystem;
    private final DoubleSupplier  ltAxisSupplier, rtAxisSupplier;

    //CONSTRUCTOR
    public IntakeCommand(IntakeSubsystem intakeSubsystem, DoubleSupplier ltAxisSupplier, DoubleSupplier rtAxisSupplier){
        this.intakeSubsystem = intakeSubsystem;
        this.ltAxisSupplier = ltAxisSupplier;
        this.rtAxisSupplier = rtAxisSupplier;
        
        addRequirements(intakeSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick Axis -> Double
        double ltAxis = ltAxisSupplier.getAsDouble();
        double rtAxis = rtAxisSupplier.getAsDouble();

        //if left trigger is pressed, run coral intake in reverse if right trigger is pressed, run coral intake forward, else stop Coral intake
        if (ltAxis > 0.5) {
            intakeSubsystem.runAlgaeIntake(0.7); //changed it to algae
        } else if (rtAxis > 0.5) {
            intakeSubsystem.runAlgaeIntake(-0.7);
        } else {
            intakeSubsystem.stopAlgaeIntake();
        }
    }
      
    @Override
    public boolean isFinished() {
        return false; // Command never finishes on its own
    }
}
