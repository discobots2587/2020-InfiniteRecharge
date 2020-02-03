/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;;

public class DriveTrain extends SubsystemBase {
  private TalonSRX leftMaster = new TalonSRX(DriveTrainConstants.kleftMasterID);
  private TalonSRX rightMaster = new TalonSRX(DriveTrainConstants.krightMasterID);
  // private VictorSPX leftSlave = new VictorSPX(DriveTrainConstants.kleftSlaveID);
  private TalonSRX leftSlave = new TalonSRX(DriveTrainConstants.kleftSlaveTEMPID);
  private VictorSPX rightSlave = new VictorSPX(DriveTrainConstants.krightSlaveID);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.leftSlave.follow(this.leftMaster);
    this.rightSlave.follow(this.rightMaster);
  }

  /**
   * Moves the drive forward at a given power.
   * 
   * @param power the power to move forward at from -1 to 1
   */
  public void forward(double power) {
    this.leftMaster.set(ControlMode.PercentOutput, power);
    this.rightMaster.set(ControlMode.PercentOutput, -power);
  }

  /**
   * Rotates the robot at a given power.
   * 
   * @param power the power to rotate at from -1 to 1
   */
  public void rotate(double power) {
    this.leftMaster.set(ControlMode.PercentOutput, power);
    this.rightMaster.set(ControlMode.PercentOutput, power);
  }

  /**
   * Drives the robot using arcade controls.
   * 
   * @param forward the forward power
   * @param turn the turning power
   */
  public void arcadeDrive(double forward, double turn) {
    double leftPower = forward + turn;
    double rightPower = forward - turn;

    this.leftMaster.set(ControlMode.PercentOutput, leftPower);
    this.rightMaster.set(ControlMode.PercentOutput, rightPower);
  }
}
