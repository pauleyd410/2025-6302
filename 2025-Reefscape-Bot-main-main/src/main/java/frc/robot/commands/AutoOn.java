package frc.robot.commands;


import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoOn extends Command {
    private final SwerveSubsystem swerve;

    public AutoOn(SwerveSubsystem swerve) {
        this.swerve = swerve;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        swerve.drive(new Translation2d(-2, 0), 0, false);
    }

    @Override
    public void execute() {
        // Add execution logic if needed
    }

    @Override
    public void end(boolean interrupted) {
        swerve.drive(new Translation2d(0, 0), 0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
