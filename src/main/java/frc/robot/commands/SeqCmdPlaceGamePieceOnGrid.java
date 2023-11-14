package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.*;

public class SeqCmdPlaceGamePieceOnGrid extends SequentialCommandGroup {
    public SeqCmdPlaceGamePieceOnGrid(SubRobotGlobals subRobotGlobals, SubVerticalElevator subVerticalElevator, SubTurret subTurret, SubHorizontalElevator subHorizontalElevator, SubLimelightLower subLimelightLower, SubLimelightUpper subLimelightUpper, SubDriveTrain subDriveTrain, SubIntakeWrist subIntakeWrist, CommandXboxController auxController, Timer timer) {
        addCommands(
                new SeqCmdVertElevAndTurretPrepForPlacement(subRobotGlobals, subVerticalElevator, subTurret, subDriveTrain, subIntakeWrist, auxController),
                new CmdHorizontalElevGridPiecePlacement(subHorizontalElevator, auxController),
                new CmdTimer(timer, 0.25),
                new CmdTurretGridPiecePlacement(subRobotGlobals, subTurret, auxController, subLimelightLower, subLimelightUpper, timer)
        );
    }
}
