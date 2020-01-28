/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//SEE https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/RobotContainer.java FOR REF

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.ArcadeDrive;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Robot Subsystems
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain m_robotDrive = new DriveTrain();

  // Robot Commands
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //Robot Controller
  private final XboxController m_driverController = new XboxController(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set default drive command --> arcade drive
    m_robotDrive.setDefaultCommand(
      
      //Arcade Drive
      new ArcadeDrive(
        m_robotDrive, 
        () -> m_driverController.getY(GenericHID.Hand.kLeft),
        () -> m_driverController.getX(GenericHID.Hand.kRight)
      )
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //PUT JOYSTICK BUTTON --> COMMAND HERE
    // Grab the hatch when the 'A' button is pressed.
    /*
    new JoystickButton(m_driverController, Button.kA.value)
        .whenPressed(new GrabHatch(m_hatchSubsystem));
    // Release the hatch when the 'B' button is pressed.
    new JoystickButton(m_driverController, Button.kB.value)
        .whenPressed(new ReleaseHatch(m_hatchSubsystem));
    // While holding the shoulder button, drive at half speed
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenHeld(new HalveDriveSpeed(m_robotDrive));
    */
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //later return m_chooser.getSelected();
    return m_autoCommand;
  }

  public DriveTrain getDrive() {
    return m_robotDrive;
  }

  public XboxController getController() {
    return m_driverController;
  }
}
