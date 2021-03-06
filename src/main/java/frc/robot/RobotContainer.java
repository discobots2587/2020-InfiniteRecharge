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
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Flywheel.FlywheelStates;
import frc.robot.subsystems.IntakeRollers.IntakeRollersStates;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
  private final DriveForwardOnly driveForwardHalfSec = new DriveForwardOnly(driveTrain, 5);
  private final SequentialCommandGroup auto = new SequentialCommandGroup(
    new InstantCommand(() -> intakeRollers.deploy(), intakeRollers),
    driveForwardHalfSec
  );

  private final SequentialCommandGroup lowgoal = new SequentialCommandGroup(
    new InstantCommand(() -> intakeRollers.deploy(), intakeRollers),
    new DriveForwardOnly(driveTrain, 7),
    new InstantCommand(() -> flywheel.setState(FlywheelStates.LOWGOAL), flywheel),
    new ParallelRaceGroup(
      new RunCommand(() -> {
        conveyor.spin(1);
        indexer.spin(1);
      }, conveyor, indexer),
      new WaitCommand(3)
    ),
    new InstantCommand(() -> {
      conveyor.spin(0);
      indexer.spin(0);
      flywheel.setState(FlywheelStates.LOWGOAL);
    }, conveyor, indexer, flywheel)
  );
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set default drive comand to split-stick arcade
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain.arcadeDrive(
        controller.getY(Hand.kLeft),
        -controller.getX(Hand.kRight)), driveTrain
      )
    );

    intakeRollers.setDefaultCommand(new RunIntake(intakeRollers));

    conveyor.setDefaultCommand(
      new RunCommand(() -> conveyor.stop(), conveyor)
    );

    flywheel.setDefaultCommand(new RunFlywheel(flywheel));

    // Set default indexer command to stop
    indexer.setDefaultCommand(
      new RunCommand(() -> indexer.stop(), indexer)
    );

    lift.setDefaultCommand(
      new RunCommand(() -> lift.stopWinch(), lift)
    );
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
      .whileActiveOnce(new InstantCommand(() -> {
        if(intakeRollers.getState() == IntakeRollersStates.OFF || intakeRollers.getState() == IntakeRollersStates.OUT) {
          intakeRollers.setState(IntakeRollersStates.IN);
        } else {
          intakeRollers.setState(IntakeRollersStates.OFF);
        }
      }, intakeRollers));

    new JoystickButton(controller, Button.kBumperRight.value)
      .whenPressed(new InstantCommand(() -> intakeRollers.setState(IntakeRollersStates.OUT), intakeRollers))
      .whenReleased(new InstantCommand(() -> intakeRollers.setState(IntakeRollersStates.OFF), intakeRollers));

    new JoystickButton(controller, Button.kX.value)
      .whenPressed(new SequentialCommandGroup(
        new InstantCommand(() -> driveTrain.shift(), driveTrain),
        new WaitCommand(0.5)));

    new JoystickButton(controller, Button.kBumperLeft.value)
      .whileHeld(new RunCommand(() -> {
        indexer.spin(0.5);
        conveyor.spin(0.75);
      }, indexer, conveyor));

    new JoystickButton(controller, Button.kY.value)
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

    new Trigger(() -> controller.getPOV() == 0)
      .whileActiveContinuous(new RunCommand(() -> lift.winchUp(), lift));

    // new Trigger(() -> controller.getPOV() == 180)
    //   .whileActiveContinuous(new RunCommand(() -> lift.winchDown(), lift));

    new Trigger(() -> controller.getPOV() == 90)
      .whileActiveContinuous(new SequentialCommandGroup(
        new InstantCommand(() -> lift.toggle(), lift),
        new WaitCommand(0.5)));

    new Trigger(() -> controller.getPOV() == 270)
      .whileActiveContinuous(new SequentialCommandGroup(
        new InstantCommand(() -> intakeRollers.deploy(), intakeRollers),
        new WaitCommand(0.5)));
}


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return auto;
  }
}
