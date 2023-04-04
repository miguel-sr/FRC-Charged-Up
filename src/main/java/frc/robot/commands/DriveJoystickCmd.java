package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveJoystickCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final Supplier<Double> leftTriggerFunction, rightTriggerFunction, turningSpdFunction;
    private final Supplier<Boolean> changeSpeedModeFunction;
    private String speedMode = "off";

    public DriveJoystickCmd(DriveSubsystem driveSubsystem, Supplier<Double> leftTriggerFunction, Supplier<Double> rightTriggerFunction, 
                            Supplier<Double> turningSpdFunction, Supplier<Boolean> changeSpeedModeFunction) {

        this.driveSubsystem = driveSubsystem;
        this.leftTriggerFunction = leftTriggerFunction;
        this.rightTriggerFunction = rightTriggerFunction;
        this.turningSpdFunction = turningSpdFunction;
        this.changeSpeedModeFunction = changeSpeedModeFunction;

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (changeSpeedModeFunction.get()) {
            switch (speedMode) {
              case "off":
                speedMode = "on";
                break;
      
              case "on":
                speedMode = "off";
                break;
            }
          }
      
          switch (speedMode) {
            case "off":
              changeSpeed(DriveConstants.kYLowSpeedLimiter, DriveConstants.kTurningLowSpeedLimiter);
              break;           
      
            case "on":
              changeSpeed(DriveConstants.kYHighSpeedLimiter, DriveConstants.kTurningHighSpeedLimiter);
              break;
          }
    }

    public void changeSpeed(double xLimiter, double turningLimiter) {
        if (leftTriggerFunction.get() != 0) {
            driveSubsystem.curvatureDrive(leftTriggerFunction.get() * xLimiter, turningSpdFunction.get() * turningLimiter, true);
        } else if (rightTriggerFunction.get() != 0) {
            driveSubsystem.curvatureDrive(rightTriggerFunction.get() * xLimiter, turningSpdFunction.get() * turningLimiter, true);
        } else {
            driveSubsystem.curvatureDrive(0, 0 * turningLimiter, true);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }

}
