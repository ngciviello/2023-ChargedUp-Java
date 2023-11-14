package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubVerticalElevator;

public class CmdVerticalElevResetPositionToTop extends CommandBase {
    private final SubVerticalElevator subVerticalElevator;

    public CmdVerticalElevResetPositionToTop(SubVerticalElevator subVerticalElevator) {
        this.subVerticalElevator = subVerticalElevator;

        addRequirements(subVerticalElevator);
    }

    @Override
    public void initialize() {
        subVerticalElevator.resetPositionToTop();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
