/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
  private final DriveTrain driveTrain;
  private final DoubleSupplier left;
  private final DoubleSupplier right;

  /**
   * Creates a new ArcadeDrive.
   */
  public TankDrive(DriveTrain subsystem, DoubleSupplier ileft, DoubleSupplier iright) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = subsystem;
    left = ileft;  //Y-value from left joystick
    right = iright;   //Y-value from right joystick
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.getDrive().tankDrive(left.getAsDouble(), right.getAsDouble());
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
