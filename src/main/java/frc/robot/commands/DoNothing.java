/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoNothing extends CommandBase {
  /**
   * Creates a new DoNothing.
   */
  public DoNothing() {
    // Use addRequirements() here to declare subsystem dependencies.
    // Don't do anything
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Don't do anything
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Don't do anything
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Don't do anything
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
