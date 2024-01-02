// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.ControllerConstants;
import frc.robot.autoCommands.AutoCmdAutonomousDoNothing;
import frc.robot.autoCommands.AutoCmdMobility;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
  private final SubRobotGlobals subRobotGlobals = new SubRobotGlobals();
  private final SubDriveTrain subDriveTrain = new SubDriveTrain();
  private final SubLimelightUpper subLimelightUpper = new SubLimelightUpper();
  private final SubLimelightLower subLimelightLower = new SubLimelightLower();
  private final SubTurret subTurret = new SubTurret();
  private final SubVerticalElevator subVerticalElevator = new SubVerticalElevator();
  private final SubHorizontalElevator subHorizontalElevator = new SubHorizontalElevator();
  private final SubIntakeWrist subIntakeWrist = new SubIntakeWrist();

  private final Timer timer = new Timer();

  private final AutoCmdAutonomousDoNothing autoCmdAutonomousDoNothing = new AutoCmdAutonomousDoNothing();
  private final AutoCmdMobility autoCmdMobility = new AutoCmdMobility(subDriveTrain);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverController = new CommandXboxController(ControllerConstants.DRIVER_CONTROLLER);
  private final CommandXboxController auxController = new CommandXboxController(ControllerConstants.AUX_CONTROLLER);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*
    new Trigger(exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    driverController.b().whileTrue(exampleSubsystem.exampleMethodCommand());
    */

    // Driver Controller buttons
    driverController.y().onTrue(new CmdIntakeWristPickupOffFloor(subIntakeWrist, subRobotGlobals, driverController));
    driverController.a().onTrue(new CmdSelectPieceType(subRobotGlobals));

    // Auxiliary Controller buttons
    auxController.leftBumper().onTrue(new CmdIntakeSpin(subIntakeWrist, subRobotGlobals, auxController));
    auxController.rightBumper().onTrue(new CmdIntakeWristPositioning(subIntakeWrist, subRobotGlobals, auxController));

    auxController.x().onTrue(new SeqCmdPlaceGamePieceOnGrid(subRobotGlobals, subVerticalElevator, subTurret, subHorizontalElevator, subLimelightLower, subLimelightUpper, subDriveTrain, subIntakeWrist, auxController, timer));

    auxController.back().onTrue(new SeqCmdTurretAndElevsHome(subIntakeWrist, subHorizontalElevator, subTurret, subVerticalElevator, auxController));

    auxController.leftStick().onTrue(new CmdVerticalElevResetPositionToTop(subVerticalElevator));

    auxController.a().onTrue(new CmdPrinty(subDriveTrain));

    // Subsystem default commands
    subDriveTrain.setDefaultCommand(new CmdDriveWithController(subDriveTrain, driverController));

    subTurret.setDefaultCommand(new CmdTurretMove(subTurret, auxController));

    subVerticalElevator.setDefaultCommand(new CmdVerticalElevMove(subVerticalElevator, auxController));

    subHorizontalElevator.setDefaultCommand(new CmdHorizontalElevMove(subHorizontalElevator, auxController, subTurret));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoCmdMobility;
  }

  public void configureSubsystems() {
    subDriveTrain.driveTrainConfigure();
    subHorizontalElevator.configureMotor();
    subTurret.configureTurret();
    subVerticalElevator.configureMotor();
    subIntakeWrist.configureMotors();
  }
}
