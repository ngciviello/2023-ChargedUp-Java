package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubTurret;

public class CmdTurretHome extends CommandBase {
    private final SubTurret subTurret;
    private final CommandXboxController auxController;
    private boolean isTurretHome = false;
    private boolean finished = false;
    private boolean goingClockwise = false;

    public CmdTurretHome(SubTurret subTurret, CommandXboxController auxController) {
        this.subTurret = subTurret;
        this.auxController = auxController;

        addRequirements(subTurret);
    }

    @Override
    public void initialize() {
        finished = false;
        isTurretHome = false;
        goingClockwise = subTurret.getPositionInDegrees() > 0;
    }

    @Override
    public void execute() {
        if(auxController.getHID().getBackButton()) {
            subTurret.rotateToAngle(0);
            if (subTurret.getPositionInDegrees() < 9 && subTurret.getPositionInDegrees() > -9) {
                finished = true;
                isTurretHome = true;
            }
        }
        else {
            finished = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(!isTurretHome && goingClockwise) {
            subTurret.rotateToAngle(subTurret.getPositionInDegrees() - 8);
        }
        else if(!isTurretHome) {
            subTurret.rotateToAngle(subTurret.getPositionInDegrees() + 8);
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
