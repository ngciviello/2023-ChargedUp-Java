package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubHorizontalElevator;

public class CmdHorizontalElevGridPiecePlacement extends CommandBase {
    private final SubHorizontalElevator subHorizontalElevator;
    private final CommandXboxController auxController;

    public CmdHorizontalElevGridPiecePlacement(SubHorizontalElevator subHorizontalElevator, CommandXboxController auxController) {
        this.subHorizontalElevator = subHorizontalElevator;
        this.auxController = auxController;

        addRequirements(subHorizontalElevator);
    }

    @Override
    public void initialize() {
        if(auxController.povUp().getAsBoolean() || auxController.povUpLeft().getAsBoolean() || auxController.povUpRight().getAsBoolean()) {
            subHorizontalElevator.servoToPosition(36);
        }
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
