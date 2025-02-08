package com.team5959.commands;

import edu.wpi.first.wpilibj2.command.Command; 
import java.util.function.BooleanSupplier; 

import com.team5959.Constants.ElevatorConstants;
import com.team5959.RobotContainer;
import com.team5959.subsystems.ElevatorSubsytem;

public class ElevatorCommand extends Command{

    //INITIALIZATION
    private final ElevatorSubsytem elevatorSubsytem;

    private final BooleanSupplier buttonAIsPressedSupplier, buttonXIsPressedSupplier, buttonYIsPressedSupplier, buttonBIsPressedSupplier;

    //CONSTRUCTOR
    public ElevatorCommand(ElevatorSubsytem elevatorSubsytem, BooleanSupplier buttonAIsPressedSupplier, BooleanSupplier buttonXIsPressedSupplier, BooleanSupplier buttonYIsPressedSupplier, BooleanSupplier buttonBIsPressedSupplier){
        this.elevatorSubsytem = elevatorSubsytem;
        this.buttonAIsPressedSupplier = buttonAIsPressedSupplier;
        this.buttonXIsPressedSupplier= buttonXIsPressedSupplier;
        this.buttonYIsPressedSupplier = buttonYIsPressedSupplier;
        this.buttonBIsPressedSupplier = buttonBIsPressedSupplier;

        addRequirements(elevatorSubsytem);
    }

    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean buttonAIsPressed = buttonAIsPressedSupplier.getAsBoolean();
        boolean buttonXIsPressed = buttonXIsPressedSupplier.getAsBoolean();
        boolean buttonYIsPressed = buttonYIsPressedSupplier.getAsBoolean();
        boolean buttonBIsPressed = buttonBIsPressedSupplier.getAsBoolean();


        //set different positons of the elevator
        if (buttonAIsPressed) {
            elevatorSubsytem.moveToStartingPosition();
        }
        if (buttonXIsPressed) {
            elevatorSubsytem.moveToL1Position();
        }
        if (buttonYIsPressed) {
            elevatorSubsytem.moveToL2Position();
        }
        if(buttonBIsPressed){
            elevatorSubsytem.moveToL3Position();
        }
    
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
}
