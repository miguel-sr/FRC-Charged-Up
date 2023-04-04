// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final Joystick driverJoystick = new Joystick(OIConstants.kDriverJoystick);

  public RobotContainer() {
    driveSubsystem.setDefaultCommand(new DriveJoystickCmd(
      driveSubsystem, 
      () -> driverJoystick.getRawAxis(OIConstants.Axis.kLeftTrigger), 
      () -> -driverJoystick.getRawAxis(OIConstants.Axis.kRightTrigger), 
      () -> driverJoystick.getRawAxis(OIConstants.Axis.kLeftStickX), 
      () -> driverJoystick.getRawButton(OIConstants.Buttons.kLeftBumper)));

    configureBindings();
  }

  private void configureBindings() {

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
