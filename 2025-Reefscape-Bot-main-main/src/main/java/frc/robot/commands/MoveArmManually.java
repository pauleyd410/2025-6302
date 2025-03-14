package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

/** An example command that uses an example subsystem. */
public class MoveArmManually extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ArmSubsystem manSubArm;
    private final double power;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
    public MoveArmManually(ArmSubsystem manSubArm, double power) {
    this.manSubArm = manSubArm;
    this.power = power;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(manSubArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    manSubArm.setPower(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    manSubArm.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}