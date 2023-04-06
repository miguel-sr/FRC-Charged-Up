package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class RotateToAngle extends CommandBase {
    private final PIDController pid = new PIDController(DriveConstants.Gyro.kP, 0.0, 0.0);
    private final DriveSubsystem driveSubsystem;
    private final double angle;
    private int m_stable_done;

    public RotateToAngle(DriveSubsystem driveSubsystem, double angle) {
        this.driveSubsystem = driveSubsystem;
        this.angle = angle;
        
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetGyro();
        m_stable_done = 0;
    }

    double output = 0;

    @Override
    public void execute() {

        pid.setTolerance(DriveConstants.Gyro.kAngleTolerance);
        
        output = MathUtil.clamp(
            pid.calculate(driveSubsystem.getAngle(), angle), 
            -DriveConstants.Gyro.kMaxTurnOutput, 
            DriveConstants.Gyro.kMaxTurnOutput);

        if (pid.atSetpoint()) {
            output = 0;
            m_stable_done++;
        } else {
            m_stable_done = 0;
            driveSubsystem.curvatureDrive(0, -output, true);
        }

    }

    @Override
    public void end(boolean interrupted) {
        pid.close();
    }

    @Override
    public boolean isFinished() {
        return m_stable_done >= 20;
    }
}
