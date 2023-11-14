package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubVerticalElevator;

public class CmdVerticalElevHome extends CommandBase {
    private final SubVerticalElevator subVerticalElevator;
    private final CommandXboxController auxController;
    private  boolean finished = false;
    private boolean isVertElevHome = false;

    public CmdVerticalElevHome(SubVerticalElevator subVerticalElevator, CommandXboxController auxController) {
        this.subVerticalElevator = subVerticalElevator;
        this.auxController = auxController;

        addRequirements(subVerticalElevator);
    }

    @Override
    public void initialize() {
        finished = false;
        isVertElevHome = false;
    }

    @Override
    public void execute() {
        finished = !auxController.getHID().getBackButton();
        if(!finished) {
            subVerticalElevator.servoToPosition(25000);
            if(subVerticalElevator.getPosition() < 40000) {
                finished = true;
                isVertElevHome = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(!isVertElevHome) {
            subVerticalElevator.servoToPosition(subVerticalElevator.getPosition() - 500);
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
