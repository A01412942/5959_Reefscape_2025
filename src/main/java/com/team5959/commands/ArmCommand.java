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

    private BooleanSupplier L1IsPressedSupplier, R1IsPressedSupplier;

    private DoubleSupplier L2IsPressedSupplier, R2IsPressedSupplier;


    public ArmCommand(ArmSubsystem armSubsystem, BooleanSupplier L1IsPressedSupplier, BooleanSupplier R1IsPressedSupplier, DoubleSupplier L2IsPressedSupplier, DoubleSupplier R2IsPressedSupplier){
        this.armSubsystem = armSubsystem;
        this.L1IsPressedSupplier = L1IsPressedSupplier;
        this.R1IsPressedSupplier = R1IsPressedSupplier;
        this.L2IsPressedSupplier = L2IsPressedSupplier;
        this.R2IsPressedSupplier = R2IsPressedSupplier;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        // ALTERING VALUES

        //Joystick buttons -> boolean
        boolean L1IsPressed = L1IsPressedSupplier.getAsBoolean();
        boolean R1IsPressed = R1IsPressedSupplier.getAsBoolean();

        //Joystick buttons -> double
        double L2IsPressed = L2IsPressedSupplier.getAsDouble();
        double R2IsPressed = R2IsPressedSupplier.getAsDouble();


        //set different positons of the elevator
        if (L1IsPressed) {
        armSubsystem.moveToStartingPosition();
        }
        if (R1IsPressed) {
        armSubsystem.moveToCoralPosition();
        }

        if (L2IsPressed > 0.5) {
            armSubsystem.moveManualIntake(L2IsPressed);
        }
        if (R2IsPressed > 0.5) {
            armSubsystem.moveManualIntake(R2IsPressed);
        }
    } 
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    return false;
    }
}