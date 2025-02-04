package com.team5959.commands;

import com.team5959.Constants.ElevatorConstants;
import com.team5959.RobotContainer;
import com.team5959.subsystems.ElevatorSubsytem;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command; 
public class ElevatorCommand extends Command{

    //INITIALIZATION

    private final ElevatorSubsytem elevatorSubsytem;

    private final BooleanSupplier crossButtonIsPressedSupplier, circleButtonIsPressedSupplier, triangleButtonIsPressedSupplier;


    public ElevatorCommand(ElevatorSubsytem elevatorSubsytem, BooleanSupplier crossButtonIsPressedSupplier, BooleanSupplier circleButtonIsPressedSupplier, BooleanSupplier triangleButtonIsPressedSupplier){
        this.elevatorSubsytem = elevatorSubsytem;
        this.crossButtonIsPressedSupplier= crossButtonIsPressedSupplier;
        this.circleButtonIsPressedSupplier = circleButtonIsPressedSupplier;
        this.triangleButtonIsPressedSupplier = triangleButtonIsPressedSupplier;
     
        addRequirements(elevatorSubsytem);
    }

    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean crossButtonIsPressed = crossButtonIsPressedSupplier.getAsBoolean();
        boolean circleButtonIsPressed = circleButtonIsPressedSupplier.getAsBoolean();
        boolean triangleButtonIsPressed = triangleButtonIsPressedSupplier.getAsBoolean();
   

        //set different positons of the elevator
        if (crossButtonIsPressed) {
        elevatorSubsytem.moveToPositionCero();
        }
        if (circleButtonIsPressed) {
        elevatorSubsytem.moveToPositionOne();
        }
        if (triangleButtonIsPressed) {
        elevatorSubsytem.moveToPositionTwo();
        }
    
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
    @Override
    public void end(boolean interrupted) {
        elevatorSubsytem.stopElevator();
    }
}