package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveWithEncoders extends CommandBase {
  private final DriveSubsystem driveSubsystem;
  private final double distance, speed;

  public DriveWithEncoders(DriveSubsystem driveSubsystem, double distance, double speed) {
    this.driveSubsystem = driveSubsystem;
    this.distance = distance;
    this.speed = speed;

    addRequirements(driveSubsystem);
  }

  @Override
  public void initialize() {
    driveSubsystem.resetEncoders();
  }

  @Override
  public void execute() {    
    driveSubsystem.curvatureDrive(-speed, 0, true);
  }
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return driveSubsystem.getAverageEncoderDistance() > distance;
  }
}
