package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    private final WPI_VictorSPX leftFront =
        new WPI_VictorSPX(DriveConstants.kLeftFrontDriveMotorPort);

    private final WPI_VictorSPX leftRear =
        new WPI_VictorSPX(DriveConstants.kLeftRearDriveMotorPort);

    private final WPI_VictorSPX rightFront =
        new WPI_VictorSPX(DriveConstants.kRightFrontDriveMotorPort);

    private final WPI_VictorSPX rightRear =
        new WPI_VictorSPX(DriveConstants.kRightRearDriveMotorPort);

    private final MotorControllerGroup leftMotors = 
        new MotorControllerGroup(leftFront, leftRear);
    
    private final MotorControllerGroup rightMotors = 
        new MotorControllerGroup(rightFront, rightRear);

    private final DifferentialDrive drive = 
        new DifferentialDrive(leftMotors, rightMotors);

    public DriveSubsystem() {
        leftFront.setNeutralMode(NeutralMode.Brake);
        leftRear.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightRear.setNeutralMode(NeutralMode.Brake);
    
        leftMotors.setInverted(false);
        rightMotors.setInverted(true);
    }

    @Override
    public void periodic() {}

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
        drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }
}
