/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveTrainConstants {
        public static final int kleftMasterID = 10;
        public static final int krightMasterID = 11;
        public static final int kleftSlaveID = 12;
        public static final int krightSlaveID = 13;

        public static final int kshifterChannel = 0;
        
        public static final int kmotorCurrentLimit = 35;
    }

    public static final class IntakeRollersConstants {
        public static final int kRollersID = 20;

        public static final int kdeployChannel = 1;

    }
    
    public static final class FlywheelConstants {
        public static final int kleftFlywheelID = 30;
        public static final int krightFlywheelID = 31;

        public static final double klowGoalPower = 0.2;
        public static final double khighGoalPower = 1.0;
    }
    
    public static final class ConveyorConstants {
        public static final int kConveyorID = 40;
    }

    public static final class IndexerConstants {
        public static final int kindexerID = 50;
    }

    public static final class LiftConstants {
        public static final int kwinchID = 60;
        public static final double winchUpPower = 0.8;
        public static final double winchDownPower = -0.2;

        public static final int ksolenoidChannel = 2;
    }
}
