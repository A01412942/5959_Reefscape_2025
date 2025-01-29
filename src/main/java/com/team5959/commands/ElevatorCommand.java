package com.team5959.commands;

import com.team5959.Constants.ElevatorConstants;
import com.team5959.RobotContainer;
import com.team5959.subsystems.ElevatorSubsytem;

import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorCommand extends Command{
    private final ElevatorSubsytem elevatorSubsytem;

    public ElevatorCommand(ElevatorSubsytem elevatorSubsytem){
        this.elevatorSubsytem = elevatorSubsytem;
        addRequirements(elevatorSubsytem);
    }

    /* @Override
    public void execute(){
         if (RobotContainer.control.getCrossButtonPressed()) {
    elevatorSubsytem.moveToPositionCero();
    }
        if (RobotContainer.control.getCircleButtonPressed()) {
    elevatorSubsytem.moveToPositionOne();
    }
        if (RobotContainer.control.getTriangleButton()) {
        elevatorSubsytem.moveToPositionTwo();
    }
    } */
          // Returns true when the command should end.
          @Override
          public boolean isFinished() {
            return false;
          }
        }
