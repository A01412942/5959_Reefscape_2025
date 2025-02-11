package com.team5959.commands;

import java.util.function.BooleanSupplier;

import com.team5959.subsystems.ElevatorSubsytem;

import edu.wpi.first.wpilibj2.command.Command;

public class ManualElevatorCommand extends Command {
        private final ElevatorSubsytem elevatorSubsytem;
        private final BooleanSupplier lbButtonSupplier,rbButtonSupplier;

    public ManualElevatorCommand(ElevatorSubsytem elevatorSubsytem, BooleanSupplier lbBooleanSupplier, BooleanSupplier rbBooleanSupplier){
        this.elevatorSubsytem = elevatorSubsytem;
        this.lbButtonSupplier = lbBooleanSupplier;
        this.rbButtonSupplier = rbBooleanSupplier;

        addRequirements(elevatorSubsytem);
    }
    @Override
    public void execute(){
        boolean lbButton = lbButtonSupplier.getAsBoolean();
        boolean rbButton= rbButtonSupplier.getAsBoolean();

        if (lbButton) {
            elevatorSubsytem.elevatorManualMode(-0.4);
        } else if (rbButton) {
            elevatorSubsytem.elevatorManualMode(0.4);
        } else {
            elevatorSubsytem.stopElevator();
        }
    }
    
    @Override
    public boolean isFinished() {
    return false;
    }
}
