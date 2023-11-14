package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;
import frc.robot.subsystems.SubVerticalElevator;

public class SeqCmdPickUpFromShelf extends SequentialCommandGroup {
    public SeqCmdPickUpFromShelf(SubRobotGlobals subRobotGlobals, SubVerticalElevator subVerticalElevator, SubIntakeWrist subIntakeWrist, CommandXboxController auxController) {
        addCommands(
                new CmdVerticalElevPickUpOffShelf(subVerticalElevator),
                new CmdIntakeWristPickUpFromShelf(subIntakeWrist, subRobotGlobals, auxController)
        );
    }
}
