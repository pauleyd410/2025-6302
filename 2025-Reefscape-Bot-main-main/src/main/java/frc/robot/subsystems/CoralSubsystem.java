package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralSubsystem extends SubsystemBase {
    private final SparkFlex coralMotor;
    private final SparkFlexConfig motorConfig;

    public CoralSubsystem() {
        coralMotor = new SparkFlex(Constants.CORAL_INTAKE_MOTOR_ID, MotorType.kBrushless);
        motorConfig = new SparkFlexConfig();

        motorConfig.idleMode(IdleMode.kBrake);
        motorConfig.smartCurrentLimit(Constants.SmartCoralLimit);
        coralMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void setPower(double power) {
        coralMotor.set(power);
    }

    @Override
    public void periodic(){}
    
}
