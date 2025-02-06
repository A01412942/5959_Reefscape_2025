package com.team5959.commands;

import com.team5959.Constants.ArmConstants;
import com.team5959.RobotContainer;
import com.team5959.subsystems.ArmSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command; 

public class ArmCommand extends Command{

    //INITIALIZATION

    private final ArmSubsystem armSubsystem;

    private BooleanSupplier bottonAIsPressedSupplier, bottonBIsPressedSupplier;
    

    public ArmCommand(ArmSubsystem armSubsystem, BooleanSupplier bottonAIsPressedSupplier , BooleanSupplier bottonBIsPressedSupplier){
        this.armSubsystem = armSubsystem;
        this.bottonAIsPressedSupplier = bottonAIsPressedSupplier;
        this.bottonBIsPressedSupplier = bottonBIsPressedSupplier;
     
        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean bottonAIsPressed = bottonAIsPressedSupplier.getAsBoolean();
        boolean bottonBIsPressed = bottonBIsPressedSupplier.getAsBoolean();

        

        //set different positons of the elevator
        if (bottonAIsPressed) {
        armSubsystem.moveToPositionCero();
        }
        if (bottonBIsPressed) {
        armSubsystem.moveToScoringPosition();
        }

        
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
}