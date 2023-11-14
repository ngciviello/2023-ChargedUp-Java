package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubHorizontalElevator;
import frc.robot.subsystems.SubTurret;

import static frc.robot.Constants.ControllerConstants.JOYSTICK_AXIS_BUFFER;

public class CmdHorizontalElevMove extends CommandBase {
    private final SubHorizontalElevator subHorizontalElevator;
    private final CommandXboxController auxController;
    private final SubTurret subTurret;

    public CmdHorizontalElevMove(SubHorizontalElevator subHorizontalElevator, CommandXboxController auxController, SubTurret subTurret) {
        this.subHorizontalElevator = subHorizontalElevator;
        this.auxController = auxController;
        this.subTurret = subTurret;

        addRequirements(subHorizontalElevator);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // Updating the horizontal elevator max extension based on turret angle, so we don't over extend
        double turretAngle = Math.abs(subTurret.getPositionInDegrees());
        if(turretAngle > 0 && turretAngle <= 120){
            double maxValue = -0.00019*turretAngle*turretAngle*turretAngle + 0.05838*turretAngle*turretAngle -5.8506*turretAngle + 233.99285;
            subHorizontalElevator.setMaxExtension(maxValue);
        }
        if(turretAngle > 120){
            double maxValue = -0.30*turretAngle +90.0;
            subHorizontalElevator.setMaxExtension(maxValue);
        }

        // reset speed to 0 every execution
        double speed = 0.0;

        // Set speed to joystick value if it's outside buffer zone, and we're not also trying to move to turret
        if(((auxController.getRightY() > JOYSTICK_AXIS_BUFFER) || (auxController.getRightY() < - JOYSTICK_AXIS_BUFFER)) && (Math.abs(auxController.getRightY()) > Math.abs(auxController.getRightX()))) {
            speed = -0.3*auxController.getRightY();
            subHorizontalElevator.enableHoldPosition(false);
            subHorizontalElevator.moveBySpeed(speed);
        }
        else {
            subHorizontalElevator.enableHoldPosition(true);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
