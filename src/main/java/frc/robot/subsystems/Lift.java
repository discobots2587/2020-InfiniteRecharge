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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

public class Lift extends SubsystemBase {
  private final TalonSRX winch = new TalonSRX(LiftConstants.kwinchID);

  private final Solenoid solenoid = new Solenoid(LiftConstants.ksolenoidChannel);

  /**
   * Creates a new Lift.
   */
  public Lift() {
    winch.setNeutralMode(NeutralMode.Brake);
    winch.setInverted(false);
    solenoid.set(true);
  }

  public void pullPiston() {
    solenoid.set(false);
  }

  public void pushPiston() {
    solenoid.set(true);
  }

  public void winchUp() {
    winch.set(ControlMode.PercentOutput, LiftConstants.winchUpPower);
  }

  public void winchDown() {
    winch.set(ControlMode.PercentOutput, LiftConstants.winchDownPower);
  }

  public void stopWinch() {
    winch.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
