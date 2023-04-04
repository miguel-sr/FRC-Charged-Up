// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();

  private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
  private final Joystick armController = new Joystick(OIConstants.kArmControllerPort);

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
    new JoystickButton(armController, OIConstants.Buttons.kA)
    .onTrue(Commands.runOnce(
      () -> {
        armSubsystem.setGoal(2);
        armSubsystem.enable();
      }, 
      armSubsystem));

    // Move the arm to neutral position when the 'B' button is pressed.
    new JoystickButton(armController, OIConstants.Buttons.kB)
      .onTrue(Commands.runOnce(
        () -> {
          armSubsystem.setGoal(ArmConstants.kArmOffsetRads);
          armSubsystem.enable();
        }, 
        armSubsystem));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
