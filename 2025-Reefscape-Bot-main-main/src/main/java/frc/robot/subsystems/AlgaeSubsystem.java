package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeSubsystem extends SubsystemBase {
    private SparkFlex algaeMotor;
    private SparkFlexConfig motorConfig;

    public AlgaeSubsystem(){
        algaeMotor = new SparkFlex(Constants.ALGAE_MOTOR_ID, MotorType.kBrushless);
        motorConfig = new SparkFlexConfig();

        motorConfig.idleMode(IdleMode.kBrake);
        motorConfig.smartCurrentLimit(Constants.SmartAlgaeLimit);
    }

    public void setPower(double power) {
        algaeMotor.set(power);
    }
    
    @Override
    public void periodic(){}
}
