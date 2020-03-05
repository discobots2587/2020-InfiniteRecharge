/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FlywheelConstants;

public class Flywheel extends SubsystemBase {

  private final TalonSRX leftFlywheel = new TalonSRX(FlywheelConstants.kleftFlywheelID);
  private final TalonSRX rightFlywheel = new TalonSRX(FlywheelConstants.krightFlywheelID);

  /**
   * Creates a new Flywheel.
   */
  public Flywheel() {
    leftFlywheel.setNeutralMode(NeutralMode.Coast);
    rightFlywheel.setNeutralMode(NeutralMode.Coast);

    rightFlywheel.setInverted(true);  //might be rightFlywheel, but need to spin in same dir
  }

  /**
   * Spins the flywheel at a given power
   * 
   * @param power the power to spin the flywheel at [-1, 1]. Negative values spin outwards.
   */
  public void spin(final double power) {
    leftFlywheel.set(ControlMode.PercentOutput, power);
    rightFlywheel.set(ControlMode.PercentOutput, power);
  }

  /**
   * Toggle the current speed of flywheel
   * 
   * @param defaultState the default state of the flywheel. 0 for low speed and 1 for high speed. 
   */
  public void toggle(final double defaultState) {
    //TODO: Create smart toggle function 
  }

  /**
   * Stop the flywheel
   */
  public void stop() {
    leftFlywheel.set(ControlMode.PercentOutput, 0);
    rightFlywheel.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
