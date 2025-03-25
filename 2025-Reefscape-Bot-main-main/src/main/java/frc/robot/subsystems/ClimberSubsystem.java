package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
    private final SparkMax climberMotor;
    private final SparkFlexConfig motorConfig;
    
    public ClimberSubsystem() {
        climberMotor = new SparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
        motorConfig = new SparkFlexConfig();

        motorConfig.smartCurrentLimit(Constants.SmartClimberLimit);
        motorConfig.idleMode(IdleMode.kBrake);
        climberMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void setPower(double power) {
        climberMotor.set(power);
    }

    @Override
    public void periodic() {}
}
