package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ServoSubsystem;
import java.util.function.DoubleSupplier;

public class ServoControl extends CommandBase {
  private final ServoSubsystem m_servoSubsystem;
  private final DoubleSupplier m_position;

  /**
   * Creates a servo control command
   *
   * @param servoSubsystem ServoSubsustem instance
   * @param position The servo's position [0..1.0]. 1.0 corresponds to max clockwise position.
   */
  public ServoControl(ServoSubsystem servoSubsystem, DoubleSupplier position) {
    m_servoSubsystem = servoSubsystem;
    m_position = position;

    addRequirements(servoSubsystem);
  }

  @Override
  public void execute() {
    m_servoSubsystem.set(m_position.getAsDouble());
  }
}
