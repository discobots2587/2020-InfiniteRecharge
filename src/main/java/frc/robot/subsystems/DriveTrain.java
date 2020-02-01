/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private TalonSRX left; 
  private TalonSRX right;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.left = new TalonSRX(1);
    this.right = new TalonSRX(2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double throttle, double turn) {
    double leftPower = throttle + turn;
    double rightPower = throttle - turn;

    left.set(ControlMode.PercentOutput, leftPower);
    right.set(ControlMode.PercentOutput, rightPower);
  }
}
