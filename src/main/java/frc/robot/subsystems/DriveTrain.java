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

public class DriveTrain extends SubsystemBase {
  private TalonSRX leftMaster = new TalonSRX(10);
  private TalonSRX rightMaster = new TalonSRX(11);
  // private VictorSPX leftSlave = new VictorSPX(12);
  private TalonSRX leftSlave = new TalonSRX(14);
  private VictorSPX rightSlave = new VictorSPX(13);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.leftSlave.follow(this.leftMaster);
    this.rightSlave.follow(this.rightMaster);
  }

  /**
   * Moves the drive forward at a given power
   * 
   * @param power the power to move forward at from -1 to 1
   */
  public void forward(double power) {
    this.rightMaster.set(ControlMode.PercentOutput, power);
    this.leftMaster.set(ControlMode.PercentOutput, power);
  }
}
