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
  private TalonSRX leftMaster;
  private TalonSRX rightMaster;
  // private VictorSPX leftSlave;
  private TalonSRX leftSlave;
  private VictorSPX rightSlave;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.leftMaster = new TalonSRX(10);
    this.rightMaster = new TalonSRX(11);
    // this.leftSlave = new VictorSPX(12);
    this.leftSlave = new TalonSRX(14);
    this.rightSlave = new VictorSPX(13);

    this.leftSlave.follow(this.leftMaster);
    this.rightSlave.follow(this.rightMaster);
  }

  public void forward(double power) {
    this.rightMaster.set(ControlMode.PercentOutput, power);
    this.leftMaster.set(ControlMode.PercentOutput, power);
  }
}
