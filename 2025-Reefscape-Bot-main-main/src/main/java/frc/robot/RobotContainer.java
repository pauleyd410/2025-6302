// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AlignWithOffset;
import frc.robot.commands.AutoOn;
import frc.robot.commands.Autos;
import frc.robot.commands.Climb;
import frc.robot.commands.IntakeAlgae;
import frc.robot.commands.IntakeCoral;
import frc.robot.commands.ScoreAlgae;
import frc.robot.commands.ScoreCoral;
import frc.robot.subsystems.AlgaeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem driveBase = new SwerveSubsystem();
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();
  private final CoralSubsystem coral = new CoralSubsystem();
  private final AlgaeSubsystem algae = new AlgaeSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final AutoOn  autoOn = new AutoOn(driveBase);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverXbox =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandXboxController operatorXbox =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /**
   * Converts driver input into a field-relative ChassisSpeeds that is controlled by angular velocity.
  */

  //BLUE TEAM NEEDS POSITIVE //RED NEGATIVE
  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(driveBase.getSwerveDrive(),
                                                                () -> driverXbox.getLeftY() * 1,
                                                                () -> driverXbox.getLeftX() * 1)
                                                            .withControllerRotationAxis(driverXbox::getRightX)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .allianceRelativeControl(false);

   /**
   * Clone's the angular velocity input stream and converts it to a fieldRelative input stream.
   */
  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(driverXbox::getRightX,
                                                                                             driverXbox::getRightY)
                                                           .headingWhile(true);


  /**
   * Clone's the angular velocity input stream and converts it to a robotRelative input stream.
   */
  SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true)
                                                             .allianceRelativeControl(false);

  // Half speed working
  SwerveInputStream halfSpeed = SwerveInputStream.of(driveBase.getSwerveDrive(),
                                                                () -> driverXbox.getLeftY() * .30,
                                                                () -> driverXbox.getLeftX() * .30)
                                                            .withControllerRotationAxis(driverXbox::getRightX)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .allianceRelativeControl(false);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    DriverStation.silenceJoystickConnectionWarning(true);
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // Driver Controls
    Command driveFieldOrientedAnglularVelocity = driveBase.driveFieldOriented(driveAngularVelocity);
    driveBase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
    driverXbox.a().onTrue(elevator.setGoal(.03)); //home elevator
    driverXbox.b().onTrue(elevator.setGoal(.612)); //L3
    driverXbox.y().onTrue(elevator.setGoal(1.5)); //L4

    
    // Half Speed command and controller binding
    Command driveHalfSpeed = driveBase.driveFieldOriented(halfSpeed);
    driverXbox.rightBumper().whileTrue(driveHalfSpeed);

    //Reef alignment
    driverXbox.povRight().onTrue(new AlignWithOffset(true, driveBase).withTimeout(3));
    driverXbox.povLeft().onTrue(new AlignWithOffset(false, driveBase).withTimeout(3));
    
    // Operator Controls
    operatorXbox.a().onTrue(arm.setGoal(0)); //home arm
    operatorXbox.b().onTrue(arm.setGoal(-50)); // L3
    operatorXbox.y().onTrue(arm.setGoal(-30)); // L4
    operatorXbox.x().onTrue(arm.setGoal(-220)); // Load Coral

    // Algae intake angle
    operatorXbox.povLeft().onTrue(arm.setGoal(-98));
    operatorXbox.povRight().onTrue(arm.setGoal(-152));
    
    
    // Algae controls adjust motor speed as needed
    operatorXbox.leftBumper().whileTrue(new IntakeAlgae(algae, 1));
    operatorXbox.rightBumper().whileTrue(new ScoreAlgae(algae, 1));
    // Coral controls adjust motor speed as needed
    operatorXbox.leftTrigger().whileTrue(new IntakeCoral(coral, -.5));
    operatorXbox.rightTrigger().whileTrue(new ScoreCoral(coral, -.5));
    // Climb controls 
    operatorXbox.povUp().whileTrue(new Climb(climber, 1));
    operatorXbox.povDown().whileTrue(new Climb(climber, -1));
   // Elevator manual
	 //operatorXbox.povUp().whileTrue(new MoveArmManually(arm,-0.3));
	 //operatorXbox.povDown().whileTrue(new MoveArmManually(arm, 0.3));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoOn.withTimeout(3);
  }
}
