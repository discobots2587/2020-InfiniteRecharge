/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

public class Lift extends SubsystemBase {
  private final TalonSRX winch = new TalonSRX(LiftConstants.kwinchID);

  private final Solenoid leftSolenoid = new Solenoid(LiftConstants.kleftSolenoidChannel);
  private final Solenoid rightSolenoid = new Solenoid(LiftConstants.krightSolenoidChannel);

  /**
   * Creates a new Lift.
   */
  public Lift() {
    // Nothing to do
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
