package com.team5959.commands;

import com.team5959.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

public class IntakeCommand extends Command{

    //INITIALIZATION
    private final IntakeSubsystem intakeSubsystem;
    private final DoubleSupplier  ltCoralAxisSupplier, rtCoralAxisSupplier, l2AlgaeAxisSupplier, r2AlgaeAxisSupplier;

    //CONSTRUCTOR
    public IntakeCommand(IntakeSubsystem intakeSubsystem, DoubleSupplier ltCoralAxisSupplier, DoubleSupplier rtCoralAxisSupplier,DoubleSupplier l2AlgaeAxisSupplier, DoubleSupplier r2AlgaeAxisSupplier){
        this.intakeSubsystem = intakeSubsystem;
        this.ltCoralAxisSupplier = ltCoralAxisSupplier;
        this.rtCoralAxisSupplier = rtCoralAxisSupplier;
        this.l2AlgaeAxisSupplier = l2AlgaeAxisSupplier;
        this.r2AlgaeAxisSupplier = r2AlgaeAxisSupplier;
        
        addRequirements(intakeSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick Axis -> Double
        double ltCoralAxis = ltCoralAxisSupplier.getAsDouble();
        double rtCoralAxis = rtCoralAxisSupplier.getAsDouble();
        double l2AlgaeAxis = l2AlgaeAxisSupplier.getAsDouble();
        double r2AlgaeAxis = r2AlgaeAxisSupplier.getAsDouble();

        if (l2AlgaeAxis > 0.5) {
            intakeSubsystem.runAlgaeIntake(0.7); //Coral goes into the robot
        } else if (r2AlgaeAxis > 0.5) {
            intakeSubsystem.runAlgaeIntake(-0.7); //Coral goes out of the robot
        } else {
            intakeSubsystem.stopAlgaeIntake();
        }

        if (ltCoralAxis > 0.5) {
            intakeSubsystem.runCoralIntake(0.75);
         } else if (rtCoralAxis > 0.5) {
            intakeSubsystem.runCoralIntake(-0.75);
         } else {
            intakeSubsystem.stopCoralIntake();
         }
    }
      
    @Override
    public boolean isFinished() {
        return false; // Command never finishes on its own
    }
}
