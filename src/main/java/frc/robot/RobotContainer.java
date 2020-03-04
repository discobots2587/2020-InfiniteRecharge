/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain driveTrain = new DriveTrain();
  private final IntakeRollers intakeRollers = new IntakeRollers();
  private final Flywheel flywheel = new Flywheel();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final  XboxController controller = new XboxController(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set default drive comand to split-stick arcade
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain.arcadeDrive(
        controller.getY(GenericHID.Hand.kLeft),
        controller.getX(GenericHID.Hand.kRight)), driveTrain));

    // Set default intake command to stop
    intakeRollers.setDefaultCommand(
      new RunCommand(() -> intakeRollers.stop(), intakeRollers));

    // Set default flywheel command to spin at max power
    flywheel.setDefaultCommand(
      new RunCommand(() -> flywheel.spin(1), flywheel)); //TODO: Change default flywheel to toggle default

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kBumperRight.value)
      .whileHeld(new RunCommand(() -> intakeRollers.spin(1), intakeRollers));

    new JoystickButton(controller, Button.kBumperLeft.value)
      .whileHeld(new RunCommand(() -> intakeRollers.spin(-0.5), intakeRollers));

    //Change flywheel state
    new JoystickButton(controller, Button.kA.value)
      .whenPressed(new RunCommand(() -> flywheel.spin(1), flywheel)); //TODO: Change flywheel function to toggle when pressed
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
