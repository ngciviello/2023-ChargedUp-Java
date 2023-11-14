package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubHorizontalElevator;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubTurret;
import frc.robot.subsystems.SubVerticalElevator;

public class SeqCmdTurretAndElevsHome extends SequentialCommandGroup {
    public SeqCmdTurretAndElevsHome(SubIntakeWrist subIntakeWrist, SubHorizontalElevator subHorizontalElevator, SubTurret subTurret, SubVerticalElevator subVerticalElevator, CommandXboxController auxController) {
        addCommands(
                new CmdIntakeWristHome(subIntakeWrist),
                new CmdHorizontalElevHome(subHorizontalElevator, auxController),
                new CmdTurretHome(subTurret, auxController),
                new CmdVerticalElevHome(subVerticalElevator, auxController)
        );
    }
}
