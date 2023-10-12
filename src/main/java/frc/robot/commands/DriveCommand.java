package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
  private final RomiDrivetrain m_drive;
  private final DoubleSupplier m_speed;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a DriveCommand instance
   *
   * @param drive Drivetrain subsystem instance
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Counterclockwise is
   *     positive.
   */
  public DriveCommand(RomiDrivetrain drive, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    m_drive = drive;
    m_speed = xSpeed;
    m_rotation = zRotation;

    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed.getAsDouble(), m_rotation.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
