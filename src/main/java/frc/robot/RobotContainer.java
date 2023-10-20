// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Rotate;
import frc.robot.commands.ServoControl;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.ServoSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();

  private final CommandXboxController m_controller = new CommandXboxController(0);

  private final ServoSubsystem m_servo0 = new ServoSubsystem(3);
  private final ServoSubsystem m_servo1 = new ServoSubsystem(2);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_romiDrivetrain.setDefaultCommand(
        new DriveCommand(
            m_romiDrivetrain, () -> -m_controller.getLeftY(), () -> -m_controller.getLeftX()));

    m_servo0.setDefaultCommand(
        new ServoControl(m_servo0, () -> (0.5 + m_controller.getRightY() / 2)));

    m_servo1.setDefaultCommand(
        new ServoControl(m_servo1, () -> (0.5 + -m_controller.getRightX() / 2)));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_controller
        .povLeft()
        .onTrue(new Rotate(m_romiDrivetrain, -90).until(() -> m_controller.getHID().getPOV() == 0));
    m_controller
        .povRight()
        .onTrue(new Rotate(m_romiDrivetrain, 90).until(() -> m_controller.getHID().getPOV() == 0));
    m_controller
        .povDown()
        .onTrue(new Rotate(m_romiDrivetrain, 180).until(() -> m_controller.getHID().getPOV() == 0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
