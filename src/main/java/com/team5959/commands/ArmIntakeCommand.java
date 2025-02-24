package com.team5959.commands;

import com.team5959.subsystems.ArmIntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;

public class ArmIntakeCommand extends Command{

    //INITIALIZATION
    private final ArmIntakeSubsystem armIntakeSubsystem;

    private BooleanSupplier armButtonIsPressedSupplier, armCrossButtonIsPressedSupplier;

    boolean isArmOut;

    //CONSTRUCTOR
    public ArmIntakeCommand(ArmIntakeSubsystem armSubsystem, BooleanSupplier armButtonIsPressedSupplier, BooleanSupplier armCrossButtonIsPressedSupplier){
        this.armIntakeSubsystem = armSubsystem;
        this.armButtonIsPressedSupplier = armButtonIsPressedSupplier;
        this.armCrossButtonIsPressedSupplier = armCrossButtonIsPressedSupplier;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
      // ALTERING VALUES

      //Joystick buttons -> boolean
      boolean armButtonIsPressed = armButtonIsPressedSupplier.getAsBoolean();
      boolean armCrossButtonIsPressed = armCrossButtonIsPressedSupplier.getAsBoolean();

      if (armButtonIsPressed){
        if (!isArmOut){
          armIntakeSubsystem.moveToInPosition();
          isArmOut = true; 
        } else {
        isArmOut = false;
        armIntakeSubsystem.moveToOutPosition();
        }
      }

      if(armCrossButtonIsPressed){
        armIntakeSubsystem.moveToInPerimeterPosition();
      }
    } 

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}