package com.team5959.commands;

import edu.wpi.first.wpilibj2.command.Command; 
import java.util.function.BooleanSupplier; 

import com.team5959.Constants.ElevatorConstants;
import com.team5959.RobotContainer;
import com.team5959.subsystems.ElevatorSubsytem;

public class ElevatorCommand extends Command{

    //INITIALIZATION
    private final ElevatorSubsytem elevatorSubsytem;

    private final BooleanSupplier buttonAIsPressedSupplier, buttonXIsPressedSupplier, buttonYIsPressedSupplier, buttonBIsPressedSupplier, lbButtonSupplier,rbButtonSupplier;


    //CONSTRUCTOR
    public ElevatorCommand(ElevatorSubsytem elevatorSubsytem, BooleanSupplier buttonAIsPressedSupplier, BooleanSupplier buttonXIsPressedSupplier, BooleanSupplier buttonYIsPressedSupplier, BooleanSupplier buttonBIsPressedSupplier, BooleanSupplier lbBooleanSupplier, BooleanSupplier rbBooleanSupplier){
        this.elevatorSubsytem = elevatorSubsytem;
        this.buttonAIsPressedSupplier = buttonAIsPressedSupplier;
        this.buttonXIsPressedSupplier= buttonXIsPressedSupplier;
        this.buttonYIsPressedSupplier = buttonYIsPressedSupplier;
        this.buttonBIsPressedSupplier = buttonBIsPressedSupplier;
        this.lbButtonSupplier = lbBooleanSupplier;
        this.rbButtonSupplier = rbBooleanSupplier;

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
        boolean lbButton = lbButtonSupplier.getAsBoolean();
        boolean rbButton= rbButtonSupplier.getAsBoolean();
        boolean isManualMode = true;
        
        if(isManualMode = false) {
        if (buttonAIsPressed) {
            isManualMode = false;
            elevatorSubsytem.moveToStartingPosition();
        } else if (buttonXIsPressed) {
            isManualMode = false;
            elevatorSubsytem.moveToL1Position();
        } else if (buttonYIsPressed) {
            isManualMode = false;
            elevatorSubsytem.moveToL2Position();
        } else if(buttonBIsPressed){
            isManualMode = false;
            elevatorSubsytem.moveToL3Position();
        } else {
            isManualMode = true;
        }}
    
        if (isManualMode){
            if (lbButton) {
                isManualMode = true;
                elevatorSubsytem.elevatorManualMode(-0.4);
            } else if (rbButton){
                isManualMode = true;
                elevatorSubsytem.elevatorManualMode(0.4);
            } else  if (buttonAIsPressed) {
                isManualMode = false;
            } else if (buttonXIsPressed) {
                isManualMode = false;
            } else if (buttonYIsPressed) {
                isManualMode = false;
            } else if(buttonBIsPressed){
                isManualMode = false;
            }else{
                isManualMode = true;
                elevatorSubsytem.elevatorManualMode(0);
            } 
        }
        
    
                
                
                

        
        
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
}
