package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubVerticalElevator;

import static frc.robot.Constants.ControllerConstants.JOYSTICK_AXIS_BUFFER;

public class CmdVerticalElevMove extends CommandBase {
    private final SubVerticalElevator subVerticalElevator;
    private final CommandXboxController auxController;

    public CmdVerticalElevMove(SubVerticalElevator subVerticalElevator, CommandXboxController auxController) {
        this.subVerticalElevator = subVerticalElevator;
        this.auxController = auxController;

        addRequirements(subVerticalElevator);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // Reset speed to 0 every execution
        double speed = 0.0;
        if(auxController.getLeftY() > JOYSTICK_AXIS_BUFFER || auxController.getLeftY() < -JOYSTICK_AXIS_BUFFER) {
            speed = -auxController.getLeftY()*0.7; // Actually only 49% speed because we square it
            if(speed < 0) {
                speed = -speed*speed;
            }
            else {
                speed = speed*speed;
            }
            subVerticalElevator.enableHoldPosition(false);
            subVerticalElevator.moveBySpeed(speed);
        }
        else {
            subVerticalElevator.enableHoldPosition(true);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
