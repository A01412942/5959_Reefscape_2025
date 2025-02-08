package com.team5959.commands;

import com.team5959.subsystems.ArmIntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;

public class ArmIntakeCommand extends Command{

    //INITIALIZATION
    private final ArmIntakeSubsystem armIntakeSubsystem;

    private BooleanSupplier armButtonIsPressedSupplier;

    private int setSwitchArm = 0;
    //CONSTRUCTOR
    public ArmIntakeCommand(ArmIntakeSubsystem armSubsystem, BooleanSupplier armButtonIsPressedSupplier){
        this.armIntakeSubsystem = armSubsystem;
        this.armButtonIsPressedSupplier = armButtonIsPressedSupplier;
     
        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
      // ALTERING VALUES

      //Joystick buttons -> boolean
      boolean armButtonIsPressed = armButtonIsPressedSupplier.getAsBoolean();

      if (armButtonIsPressed){
        if (setSwitchArm == 0){
          setSwitchArm = 1; 
          armIntakeSubsystem.moveToInPosition();
        }
        else if (setSwitchArm == 1){
        setSwitchArm = 0;
        armIntakeSubsystem.moveToOutPosition();
        }
      }
    } 

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}