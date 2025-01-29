// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team5959;

import com.team5959.commands.ElevatorCommand;
import com.team5959.commands.SwerveDrive;
import com.team5959.subsystems.ElevatorSubsytem;
import com.team5959.subsystems.SwerveChassis;


import com.team5959.Constants.ControllerConstants;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class RobotContainer {
  //subsystems
  private final SwerveChassis swerveChassis = new SwerveChassis();
  private final ElevatorSubsytem elevatorSubsytem = new ElevatorSubsytem();

  //controllers
  private final PS4Controller control = new PS4Controller(ControllerConstants.kDriverControllerPort);
  private final GenericHID controlOp = new GenericHID(ControllerConstants.kMecanismsControllerPort);

  //drive buttons
  private final JoystickButton resetNavxButton = new JoystickButton(control, 10);
  private final JoystickButton resetPosButton = new JoystickButton(control, 0);
  //AXIS
  private final int joystickAxis = PS4Controller.Axis.kRightY.value;
  
  public RobotContainer() {

    //swerveSubs.setDefaultCommand(new S_DriveCommand(swerveSubs, () -> -.getLeftY(), () -> -xbox.getLeftX(), () -> -xbox.getRightX(), true));
    swerveChassis.setDefaultCommand(new SwerveDrive(swerveChassis, () -> -control.getLeftY(), () -> -control.getLeftX(), () -> control.getRightX(), true));
    elevatorSubsytem.setDefaultCommand(new ElevatorCommand(elevatorSubsytem, () -> control.getCrossButtonPressed(), ()-> control.getCircleButtonPressed(), ()-> control.getTriangleButtonPressed()));
    // Configure the trigger bindings   
    configureBindings();
  }

  private void configureBindings() {
    
    resetNavxButton.onTrue(new InstantCommand(() -> swerveChassis.resetNavx()));
    resetPosButton.onTrue(new InstantCommand(() -> swerveChassis.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)))));
    
  }
  
  public void periodic(){
    
  }
  
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}

