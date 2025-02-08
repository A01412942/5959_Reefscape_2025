// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// TITANIUM RAMS 5959, FRC 2025
// Authors: 5959 Programming Team (Beatriz Marún, Jorge Pineda, Danna Hernández, Denis Cerón)

package com.team5959;
import com.team5959.Constants.ControllerConstants;
import com.team5959.commands.SwerveDrive;
import com.team5959.subsystems.IntakeSubsystem;
import com.team5959.subsystems.SwerveChassis;
import com.team5959.subsystems.ArmIntakeSubsystem;
import com.team5959.commands.ArmIntakeCommand;
import com.team5959.commands.IntakeCommand;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;

public class RobotContainer {
  //subsystems
  private final SwerveChassis swerveChassis = new SwerveChassis();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ArmIntakeSubsystem armIntakeSubsystem = new ArmIntakeSubsystem();

  //controllers
  private final PS4Controller control = new PS4Controller(ControllerConstants.kDriverControllerPort);
  private final GenericHID controlOp = new GenericHID(ControllerConstants.kOperatorControllerPort);

  //drive buttons
  private final JoystickButton resetNavxButton = new JoystickButton(control, 10);

  //AXIS
  
  public RobotContainer() {

    swerveChassis.setDefaultCommand(new SwerveDrive(swerveChassis, () -> -control.getLeftY(), () -> -control.getLeftX(), () -> control.getRightX(), true));
    intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem, ()-> controlOp.getRawAxis(2), ()-> controlOp.getRawAxis(3)));
    armIntakeSubsystem.setDefaultCommand(new ArmIntakeCommand(armIntakeSubsystem, ()-> controlOp.getRawButtonPressed(1) && controlOp.getRawButton(9)));
    configureBindings();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
  }

  private void configureBindings() { 

    resetNavxButton.onTrue(new InstantCommand(() -> swerveChassis.resetNavx()));
  }
  
  public void periodic(){
    
  }
  
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}

