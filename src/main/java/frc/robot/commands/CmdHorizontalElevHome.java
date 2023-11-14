package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubHorizontalElevator;

public class CmdHorizontalElevHome extends CommandBase {
    private final SubHorizontalElevator subHorizontalElevator;
    private final CommandXboxController auxController;
    private boolean finished = false;
    private boolean hElevIn = false;

    public CmdHorizontalElevHome(SubHorizontalElevator subHorizontalElevator, CommandXboxController auxController) {
        this.subHorizontalElevator = subHorizontalElevator;
        this.auxController = auxController;

        addRequirements(subHorizontalElevator);
    }

    @Override
    public void initialize() {
        finished = false;
        hElevIn = false;
    }

    @Override
    public void execute() {
        if(auxController.getHID().getBackButton()) {
            subHorizontalElevator.servoToPosition(0.25);
            if (subHorizontalElevator.getPosition() < 8) {
                hElevIn = true;
                finished = true;
            }
        }
        else {
            finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(!hElevIn) {
            subHorizontalElevator.servoToPosition(subHorizontalElevator.getPosition());
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
