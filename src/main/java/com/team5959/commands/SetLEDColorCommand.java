package com.team5959.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.team5959.subsystems.LEDSubsystem;

public class SetLEDColorCommand extends InstantCommand{

    public SetLEDColorCommand(LEDSubsystem ledSubsystem, int r, int g, int b) {
        super(() -> ledSubsystem.setLEDColor(r, g, b));
        addRequirements(ledSubsystem);
    }
    

    public void execute() {
        // The rainbow effect is handled in the subsystem's periodic method
        // when robot is disabled, so this command can remain empty
    }

    @Override
    public boolean isFinished() {
        return false; // Run until interrupted
    }
}