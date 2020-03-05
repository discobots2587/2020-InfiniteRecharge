/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Flywheel.FlywheelStates;

public class RunFlywheel extends CommandBase {
  private final Flywheel flywheel;
  private FlywheelStates state;

  /**
   * Creates a new RunFlywheel.
   */
  public RunFlywheel(Flywheel iFlywheel) {
    flywheel = iFlywheel;
    addRequirements(iFlywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = flywheel.getState();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (state) {
      case OFF:
        flywheel.stop();
        break;
      
      case LOWGOAL:
        flywheel.spin(FlywheelConstants.klowGoalPower);
        break;

      case HIGHGOAL:
        flywheel.spin(FlywheelConstants.khighGoalPower);
        break;

      default:
        flywheel.stop();
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
