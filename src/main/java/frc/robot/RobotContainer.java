/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DoNothing;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.Flywheel.FlywheelStates;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveTrain driveTrain = new DriveTrain();
  private final IntakeRollers intakeRollers = new IntakeRollers();
  private final Conveyor conveyor = new Conveyor();
  private final Flywheel flywheel = new Flywheel();
  private final Indexer indexer = new Indexer();
  
  private final  XboxController controller = new XboxController(0);

  private final DoNothing doNothing = new DoNothing();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set default drive comand to split-stick arcade
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain.tankDrive(
        controller.getY(Hand.kLeft),
        controller.getY(Hand.kRight)), driveTrain));

    // Set default intake command to stop
    intakeRollers.setDefaultCommand(
      new RunCommand(() -> intakeRollers.stop(), intakeRollers));

    conveyor.setDefaultCommand(
      new RunCommand(() -> conveyor.stop(), conveyor)
    );

    // Set default flywheel command to spin at max power
    flywheel.setDefaultCommand(new RunFlywheel(flywheel));

    // Set default indexer command to stop
    indexer.setDefaultCommand(
      new RunCommand(() -> indexer.stop(), indexer));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new Trigger(() -> controller.getTriggerAxis(Hand.kLeft) > 0.75)
      .whileActiveContinuous(new RunCommand(() -> conveyor.spin(0.5), conveyor));

    new JoystickButton(controller, Button.kB.value)
      .whileHeld(new RunCommand(() -> conveyor.spin(-0.5), conveyor));

    new Trigger(() ->  controller.getTriggerAxis(Hand.kRight) > 0.75)
      .whileActiveContinuous(new RunCommand(() -> intakeRollers.spin(1), intakeRollers));

    new JoystickButton(controller, Button.kY.value)
      .whileHeld(new RunCommand(() -> intakeRollers.spin(-0.5), intakeRollers));

    new JoystickButton(controller, Button.kBumperRight.value)
      .whileHeld(new RunCommand(() -> indexer.spin(0.5), indexer));

    new JoystickButton(controller, Button.kStart.value)
      .whenPressed(new InstantCommand(() -> {
        if(flywheel.getState() == FlywheelStates.OFF) {
          flywheel.setState(FlywheelStates.LOWGOAL);
        } else {
          flywheel.setState(FlywheelStates.OFF);
        }}, flywheel));
}


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return doNothing;
  }
}
