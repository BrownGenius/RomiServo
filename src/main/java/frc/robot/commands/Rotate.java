package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class Rotate extends CommandBase {
  public static final int MAX_ERROR = 5;
  public static final double MAX_SPEED = 0.35;

  private final RomiDrivetrain m_drive;
  private final double m_degrees;
  private double m_targetYaw;

  /**
   * @param drive Drivetrain subsystem instance
   * @param degrees The number of degrees to rotate. Positive value is clockwise when viewing the
   *     robot from the top
   */
  public Rotate(RomiDrivetrain drive, double degrees) {
    m_drive = drive;
    m_degrees = degrees;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    m_targetYaw = m_drive.getYaw() + m_degrees;
  }

  @Override
  public void execute() {
    double zRotation = 0.0;
    double yawError = m_targetYaw - m_drive.getYaw();

    if (yawError > MAX_ERROR) {
      zRotation = -MAX_SPEED;
    } else if (yawError < -MAX_ERROR) {
      zRotation = MAX_SPEED;
    }

    m_drive.arcadeDrive(0, zRotation);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    double currentYaw = m_drive.getYaw();
    double yawError = m_targetYaw - currentYaw;

    if (Math.abs(yawError) <= MAX_ERROR) return true;

    return false;
  }
}
