package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubTurret;

import static frc.robot.Constants.ControllerConstants.JOYSTICK_AXIS_BUFFER;

public class CmdTurretMove extends CommandBase {
    private final SubTurret subTurret;
    private final CommandXboxController auxController;

    public CmdTurretMove(SubTurret subTurret, CommandXboxController auxController) {
        this.subTurret = subTurret;
        this.auxController = auxController;

        addRequirements(subTurret);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double rotationSpeed = 0.0;
        if(auxController.getLeftX() > JOYSTICK_AXIS_BUFFER || auxController.getLeftX() < -JOYSTICK_AXIS_BUFFER) {
            // This is controlling the speed of the turret
            rotationSpeed = auxController.getLeftX()*-0.84;
            if(rotationSpeed < 0) {
                rotationSpeed = rotationSpeed*-rotationSpeed;
            }
            else {
                rotationSpeed = rotationSpeed*rotationSpeed;
            }
            subTurret.enableHoldPosition(false);
            subTurret.rotateBySpeed(rotationSpeed);
        }
        else if(((auxController.getRightX() > JOYSTICK_AXIS_BUFFER) || (auxController.getRightX() < -JOYSTICK_AXIS_BUFFER)) && (Math.abs(auxController.getRightX()) > Math.abs(auxController.getRightY()))) {
            // This is controlling the speed of the turret
            rotationSpeed = auxController.getRightX()*-0.4;
            if(rotationSpeed < 0) {
                rotationSpeed = rotationSpeed*-rotationSpeed;
            }
            else {
                rotationSpeed = rotationSpeed*rotationSpeed;
            }
            subTurret.enableHoldPosition(false);
            subTurret.rotateBySpeed(rotationSpeed);
        }
        else {
            subTurret.enableHoldPosition(true);
            //m_subTurret->RotateManual(0.0);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
