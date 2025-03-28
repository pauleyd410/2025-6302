// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import frc.robot.RobotMath.Arm;
import frc.robot.RobotMath.Elevator;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final double MAX_SPEED = 4.5; // m/s


   //Auto Constants
   public static final double X_REEF_ALIGNMENT_P = 2;
   public static final double Y_REEF_ALIGNMENT_P = 4;
   public static final double ROT_REEF_ALIGNMENT_P = 0.5;

   public static final double ROT_SETPOINT_REEF_ALIGNMENT = 0;
   public static final double ROT_TOLERANCE_REEF_ALIGNMENT = .5;
   public static final double X_SETPOINT_REEF_ALIGNMENT = -.04;
   public static final double X_TOLERANCE_REEF_ALIGNMENT = .005;
   public static final double Y_SETPOINT_REEF_ALIGNMENT = .67;
   public static final double Y_TOLERANCE_REEF_ALIGNMENT = .005;

   public static final double DONT_SEE_TAG_WAIT_TIME = 1;
   public static final double POSE_VALIDATION_TIME = 0.3; 

   // Coral Subsystem constants
   public static final int CORAL_INTAKE_MOTOR_ID = 21;
   public static final int SmartCoralLimit = 40;

   // Algae Subsystem constants
   public static final int ALGAE_MOTOR_ID = 20;
   public static final int SmartAlgaeLimit = 40;

   // Climber subsystem constants
   public static final int CLIMBER_MOTOR_ID = 41;
   public static final int SmartClimberLimit = 40;

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double DEADBAND = 0.2;
  }

  public static class ElevatorConstants {
   
    public static final double kElevatorKp = 6.5; 
    public static final double kElevatorKi = 0.0;
    public static final double kElevatorKd = 0.01;

    public static final double kElevatorkS = 0.0; // volts (V)
    public static final double kElevatorkG = 0.0; // volts (V)
    public static final double kElevatorkV = 0.0; // volt per velocity (V/(m/s))
    public static final double kElevatorkA = 0.0; // volt per acceleration (V/(m/s^2))

    
    public static final double kElevatorGearing = 5; 
    public static final double kElevatorDrumRadius = Units.inchesToMeters(2.160);
    public static final double kCarriageMass = 4; // kg

    //Encoder is reset to measure 0 at the bottom, so minimum height is 0.
    public static final Distance kStartingHeightSim = Meters.of(0); 
    public static final Distance kMinElevatorHeight = Meters.of(0.0); // Adjust for sim
    public static final Distance kMaxElevatorHeight = Meters.of(1.0); // Adjust for sim

    public static double kElevatorRampRate = 1;
    public static int kElevatorCurrentLimit = 60;
    public static double kMaxVelocity = Elevator.convertDistanceToRotations(Meters.of(0.01)).per(Second).in(RPM);
    public static double kMaxAcceleration = Elevator.convertDistanceToRotations(Meters.of(0.01)).per(Second).per(Second).in(RPM.per(Second));
  }

  public static class ArmConstants {
    //Adjust PID
    public static final double kArmKp = 0.65; 
    public static final double kArmKi = 0.0;
    public static final double kArmKd = 0.01;

    //Adjust FF
    public static final double kArmkS = 0.0; // volts (V)
    public static final double kArmkG = 0.0; // volts (V)
    public static final double kArmkV = 0.0; // volts per velocity (V/RPM)
    public static final double kArmkA = 0.0; // volts per acceleration (V/(RPM/s))

    public static final Angle   kArmOffsetToHorizantalZero = Rotations.of(0);
    public static final Angle  kArmAllowedClosedLoopError = Arm.convertAngleToSensorUnits(Degrees.of(0.01));
    public static final double kArmReduction = 240; // 240:1 reduction
    public static final double kArmRampRate = 0.5; // seconds from 0 to full
    public static final Angle   kArmStartingAngle = Degrees.of(0);
    public static final Angle   kMinAngle = Degrees.of(-100);
    public static final Angle   kMaxAngle = Degrees.of(2);
    public static int kArmCurrentLimit = 40;
    public static double kArmMaxVelocityRPM = Arm.convertAngleToSensorUnits(Degrees.of(90)).per(Second).in(RPM);
    public static final double  kArmMaxAccelerationRPMperSecond = Arm.convertAngleToSensorUnits(Degrees.of(180)).per(Second).per(Second).in(RPM.per(Second));
    public static final int kArmStallCurrentLimitAmps = 40; 
  }
}
