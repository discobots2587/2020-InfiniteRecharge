/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.IntakeRollers.IntakeRollersStates;

public class RunIntake extends CommandBase {
  private final IntakeRollers rollers;
  private IntakeRollersStates state;

  /**
   * Creates a new RunIntake.
   */
  public RunIntake(IntakeRollers irollers) {
    // Use addRequirements() here to declare subsystem dependencies.
    rollers = irollers;
    addRequirements(irollers);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = rollers.getState();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    state = rollers.getState();
    switch (state) {
      case OFF:
        rollers.stop();
        break;

      case IN:
        rollers.spin(1);
        break;

      case OUT:
        rollers.spin(-1);
        break;

      default:
        rollers.stop();
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
