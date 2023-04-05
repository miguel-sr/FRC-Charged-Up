// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftFrontDriveMotorPort  = 1;
    public static final int kLeftRearDriveMotorPort = 2;
    public static final int kRightFrontDriveMotorPort = 3;
    public static final int kRightRearDriveMotorPort = 4;

    public static final class Encoders {
      public static final int[] kLeftEncoderPorts = new int[] {1, 2};
      public static final int[] kRightEncoderPorts = new int[] {5, 6};

      private static final double kWheelDiameterMeters = 0.1524;
      private static final double kPulsesPerRevolution = 2048;
      public final static double kEncodersDistancePerPulse = (kWheelDiameterMeters * Math.PI) / kPulsesPerRevolution;
    }
    
    public static final double kYLowSpeedLimiter = 0.5;
    public static final double kTurningLowSpeedLimiter = 0.6;
    public static final double kYHighSpeedLimiter = 1.0;
    public static final double kTurningHighSpeedLimiter = 0.75;
  }

  public static final class ArmConstants {
    public static final int kMotorPort = 4;

    public static final double kP = 1;

    // These are fake gains; in actuality these must be determined individually for each robot
    public static final double kSVolts = 1;
    public static final double kGVolts = 1;
    public static final double kVVoltSecondPerRad = 0.5;
    public static final double kAVoltSecondSquaredPerRad = 0.1;

    public static final double kMaxVelocityRadPerSecond = 3;
    public static final double kMaxAccelerationRadPerSecSquared = 10;

    public static final int[] kEncoderPorts = new int[] {4, 5};
    public static final int kEncoderPPR = 256;
    public static final double kEncoderDistancePerPulse = 2.0 * Math.PI / kEncoderPPR;

    // The offset of the arm from the horizontal in its neutral position,
    // measured from the horizontal
    public static final double kArmOffsetRads = 0.5;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kArmControllerPort = 0;

    public static final class Axis {
      public static final int kLeftStickX = 0;
      public static final int kLeftStickY = 1;
      public static final int kRightStickX = 4;
      public static final int kRightStickY = 5;
      public static final int kLeftTrigger = 2;
      public static final int kRightTrigger = 3;
    }

    public static final class Buttons {
      public static final int kA = 1;
      public static final int kB = 2;
      public static final int kX = 3;
      public static final int kY = 4;
      public static final int kLeftBumper = 5;
      public static final int kRightBumper = 6;
      public static final int kBack = 7;
      public static final int kStart = 8;
    }
  }
}
