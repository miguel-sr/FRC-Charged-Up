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

    public static final double kYLowSpeedLimiter = 0.5;
    public static final double kTurningLowSpeedLimiter = 0.6;
    public static final double kYHighSpeedLimiter = 1.0;
    public static final double kTurningHighSpeedLimiter = 0.75;
  }

  public static final class OIConstants {
    public static final int kDriverJoystick = 0;

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
