package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.*;

public class SeqCmdVertElevAndTurretPrepForPlacement extends SequentialCommandGroup {
    public SeqCmdVertElevAndTurretPrepForPlacement(SubRobotGlobals subRobotGlobals, SubVerticalElevator subVerticalElevator, SubTurret subTurret, SubDriveTrain subDriveTrain, SubIntakeWrist subIntakeWrist, CommandXboxController auxController) {
        addCommands(
                new CmdVerticalElevGridPiecePlacement(subVerticalElevator, subRobotGlobals, auxController),
                new CmdIntakeWristGridPiecePlacement(subRobotGlobals, subIntakeWrist),
                new CmdTurretServoToPerpendicularToGrid(subTurret, subDriveTrain, auxController)
        );
    }
}
