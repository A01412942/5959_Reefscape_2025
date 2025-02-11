package com.team5959.commands;

import edu.wpi.first.wpilibj2.command.Command; 
import java.util.function.BooleanSupplier; 

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
   //     this.lbButtonSupplier = lbBooleanSupplier;
  //      this.rbButtonSupplier = rbBooleanSupplier;

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
     // boolean lbButton = lbButtonSupplier.getAsBoolean();
      //boolean rbButton= rbButtonSupplier.getAsBoolean();
      //boolean isManualMode = false;
        
      
    /*    if (buttonAIsPressed || buttonXIsPressed || buttonYIsPressed || buttonBIsPressed) {
            isManualMode = false;
        }
        if (lbButton || rbButton) {
            isManualMode = true;
        }

        if (isManualMode) {
            if (lbButton) {
                isManualMode = true;
                elevatorSubsytem.elevatorManualMode(-0.4);
            } else if (rbButton) {
                isManualMode = true;
                elevatorSubsytem.elevatorManualMode(0.4);
            } else {
                isManualMode = true;
                elevatorSubsytem.stopElevator();
            }
    } else {*/ 

            if (buttonAIsPressed) {
                elevatorSubsytem.moveToStartingPosition(); // Replace with actual position
            } else if (buttonXIsPressed) {
                elevatorSubsytem.moveToL1Position(); // Replace with actual position
            } else if (buttonYIsPressed) {
                elevatorSubsytem.moveToL2Position(); // Replace with actual position
            } else if (buttonBIsPressed) {
                elevatorSubsytem.moveToL3Position();// Replace with actual position
            } 
        //}
    

        
        
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
}
