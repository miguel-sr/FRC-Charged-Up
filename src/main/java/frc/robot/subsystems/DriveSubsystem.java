package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
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

    private final Encoder leftEncoder = new Encoder(
        DriveConstants.Encoders.kLeftEncoderPorts[0], 
        DriveConstants.Encoders.kLeftEncoderPorts[1], 
        false, 
        EncodingType.k4X);

    private final Encoder rightEncoder = new Encoder(
        DriveConstants.Encoders.kRightEncoderPorts[0], 
        DriveConstants.Encoders.kRightEncoderPorts[1], 
        true, 
        EncodingType.k4X);

    private final AHRS gyro =
        new AHRS(SPI.Port.kMXP);

    public DriveSubsystem() {
        leftFront.setNeutralMode(NeutralMode.Brake);
        leftRear.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightRear.setNeutralMode(NeutralMode.Brake);
    
        leftMotors.setInverted(false);
        rightMotors.setInverted(true);

        leftEncoder.setDistancePerPulse(DriveConstants.Encoders.kEncodersDistancePerPulse);
        rightEncoder.setDistancePerPulse(DriveConstants.Encoders.kEncodersDistancePerPulse);
    }

    @Override
    public void periodic() {}

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getAverageEncoderDistance() {
        return Math.abs((leftEncoder.getDistance() + rightEncoder.getDistance()) / 2);
    }

    public void resetGyro() {
        gyro.reset();
        gyro.calibrate();
    }

    public double getAngle() {
        return -gyro.getAngle();
    }

    public float getPitch() {
        return gyro.getPitch();
    }

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
        drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }

    public void stop() {
        drive.stopMotor();
    }
}
