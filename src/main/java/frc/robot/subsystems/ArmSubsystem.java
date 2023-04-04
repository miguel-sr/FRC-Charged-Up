package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    private final CANSparkMax m_motor = new CANSparkMax(ArmConstants.kMotorPort, MotorType.kBrushless);

    private final Encoder m_encoder =
        new Encoder(ArmConstants.kEncoderPorts[0], ArmConstants.kEncoderPorts[1]);

    private final ArmFeedforward m_feedforward =
        new ArmFeedforward(
            ArmConstants.kSVolts, ArmConstants.kGVolts,
            ArmConstants.kVVoltSecondPerRad, ArmConstants.kAVoltSecondSquaredPerRad);
            
    public ArmSubsystem() {
        super(
            new ProfiledPIDController(ArmConstants.kP, 0, 0, 
                new TrapezoidProfile.Constraints(
                    ArmConstants.kMaxVelocityRadPerSecond, 
                    ArmConstants.kMaxAccelerationRadPerSecSquared)), 
                0
        );

        m_encoder.setDistancePerPulse(ArmConstants.kEncoderDistancePerPulse);

        // Start arm at rest in neutral position
        setGoal(ArmConstants.kArmOffsetRads);
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
        double feedforward = m_feedforward.calculate(setpoint.position, setpoint.velocity);
        m_motor.setVoltage(output + feedforward);        
    }

    @Override
    public double getMeasurement() {
        return m_encoder.getDistance() + ArmConstants.kArmOffsetRads;
    }
   
}
