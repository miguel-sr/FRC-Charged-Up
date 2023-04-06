package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoBalance extends CommandBase {
    private final PIDController pid = new PIDController(DriveConstants.Gyro.kP, 0.0, 0.0);
    private final DriveSubsystem driveSubsystem;
    private int m_stable_done;

    public AutoBalance(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        
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
            pid.calculate(driveSubsystem.getPitch(), 0), 
            -DriveConstants.Gyro.kMaxAutoBalanceOutput, 
            DriveConstants.Gyro.kMaxAutoBalanceOutput);

        if (pid.atSetpoint()) {
            output = 0;
            m_stable_done++;
        } else {
            m_stable_done = 0;
            driveSubsystem.curvatureDrive(output, 0, true);
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
