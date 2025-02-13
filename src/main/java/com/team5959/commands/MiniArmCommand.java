package com.team5959.commands;

import java.util.function.BooleanSupplier;

import com.team5959.subsystems.MiniArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
public class MiniArmCommand extends Command {

    //INITIALIZATION
    private final MiniArmSubsystem miniArmSubsystem;

    private final BooleanSupplier miniArmButtonIsPressedSupplier, miniArmButtonIsPressedSupplier2;
   
    private int setSwitchMiniArm = 0;

    //CONSTRUCTOR
    public MiniArmCommand(MiniArmSubsystem miniArmSubsystem, BooleanSupplier miniArmButtonIsPressedSupplier, BooleanSupplier miniArmButtonIsPressedSupplier2){
        this.miniArmSubsystem = miniArmSubsystem;
        this.miniArmButtonIsPressedSupplier = miniArmButtonIsPressedSupplier;
        this.miniArmButtonIsPressedSupplier2 = miniArmButtonIsPressedSupplier2;

        addRequirements(miniArmSubsystem);
    }

    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean miniArmButtonIsPressed = miniArmButtonIsPressedSupplier.getAsBoolean();
        boolean miniArmButtonIsPressed2 = miniArmButtonIsPressedSupplier2.getAsBoolean();
        
        if (miniArmButtonIsPressed){
            if (setSwitchMiniArm == 0){
            setSwitchMiniArm = 1; 
            miniArmSubsystem.moveToStartingPosition();
            }
            else if (setSwitchMiniArm == 1){
            setSwitchMiniArm = 0;
            miniArmSubsystem.moveToDownPosition();
            }
        }
        
        if (miniArmButtonIsPressed2){
            miniArmSubsystem.moveToDropAlgaePosition();
        }
    }
}
