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
import frc.robot.commands.DriveForwardOnly;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.Lift;
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
  private final Lift lift = new Lift();
  
  private final  XboxController controller = new XboxController(0);

  private final DoNothing doNothing = new DoNothing();
  private final DriveForwardOnly driveForwardHalfSec = new DriveForwardOnly(driveTrain, 0.5);

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
        controller.getY(Hand.kRight)), driveTrain
      )
    );

    // Set default intake command to stop
    intakeRollers.setDefaultCommand(
      new RunCommand(() -> intakeRollers.stop(), intakeRollers)
    );

    conveyor.setDefaultCommand(
      new RunCommand(() -> conveyor.stop(), conveyor)
    );

    // Set default flywheel command to spin at max power
    flywheel.setDefaultCommand(new RunFlywheel(flywheel));

    // Set default indexer command to stop
    indexer.setDefaultCommand(
      new RunCommand(() -> indexer.stop(), indexer)
    );

    // lift.setDefaultCommand(
    //   new RunCommand(() -> lift.stopWinch(), lift)
    // );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new Trigger(() -> controller.getTriggerAxis(Hand.kLeft) > 0.75)
      .whileActiveContinuous(new RunCommand(() -> conveyor.spin(1), conveyor));

    new JoystickButton(controller, Button.kB.value)
      .whileHeld(new RunCommand(() -> conveyor.spin(-1), conveyor));

    new Trigger(() ->  controller.getTriggerAxis(Hand.kRight) > 0.75)
      .whileActiveContinuous(new RunCommand(() -> intakeRollers.spin(1), intakeRollers));

    new JoystickButton(controller, Button.kY.value)
      .whileHeld(new RunCommand(() -> intakeRollers.spin(-0.5), intakeRollers));

    new JoystickButton(controller, Button.kX.value)
      .whenPressed(new InstantCommand(() -> intakeRollers.deploy(), intakeRollers));

    new JoystickButton(controller, Button.kBumperRight.value)
      .whileHeld(new RunCommand(() -> {
        indexer.spin(0.5);
        conveyor.spin(0.75);
      }, indexer, conveyor));

    new JoystickButton(controller, Button.kBumperLeft.value)
      .whenPressed(new InstantCommand(() -> {
        if(flywheel.getState() == FlywheelStates.OFF || flywheel.getState() == FlywheelStates.LOWGOAL) {
          flywheel.setState(FlywheelStates.HIGHGOAL);
        } else {
          flywheel.setState(FlywheelStates.OFF);
      }}, flywheel));

    new JoystickButton(controller, Button.kStart.value)
      .whenPressed(new InstantCommand(() -> {
        if(flywheel.getState() == FlywheelStates.OFF || flywheel.getState() == FlywheelStates.HIGHGOAL) {
          flywheel.setState(FlywheelStates.LOWGOAL);
        } else {
          flywheel.setState(FlywheelStates.OFF);
        }}, flywheel));

    // new Trigger(() -> controller.getPOV() == 0)
    //   .whileActiveContinuous(new RunCommand(() -> lift.winchUp(), lift));

    // new Trigger(() -> controller.getPOV() == 180)
    //   .whileActiveContinuous(new RunCommand(() -> lift.winchDown(), lift));

    new Trigger(() -> controller.getPOV() == 90)
      .whileActiveContinuous(new RunCommand(() -> lift.pullPiston(), lift));

    new Trigger(() -> controller.getPOV() == 270)
      .whileActiveContinuous(new RunCommand(() -> lift.pushPiston(), lift));
}


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return driveForwardHalfSec;
  }
}
