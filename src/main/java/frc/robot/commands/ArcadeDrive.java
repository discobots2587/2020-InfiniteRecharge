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

public class ArcadeDrive extends CommandBase {
  private final DriveTrain driveTrain;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;

  /**
   * Creates a new ArcadeDrive.
   */
  public ArcadeDrive(DriveTrain subsystem, DoubleSupplier iforward, DoubleSupplier irotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = subsystem;
    forward = iforward;
    rotation = irotation;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // driveTrain.getDrive().arcadeDrive(forward.getAsDouble() * 127, rotation.getAsDouble() * 127);
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
