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
import frc.robot.Constants.IndexerConstants;

public class Indexer extends SubsystemBase {

  private final TalonSRX indexer = new TalonSRX(IndexerConstants.kindexerID);

  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    indexer.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Spins the indexer at a given power
   * 
   * @param power the power to spin the indexer at [-1, 1]. Negative values spin outwards.
   */
  public void spin(final double power) {
    indexer.set(ControlMode.PercentOutput, power);
  }

  /**
   * Stop the indexer
   */
  public void stop() {
    indexer.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
