package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubDriveTrain;

import static frc.robot.Constants.ControllerConstants.JOYSTICK_AXIS_BUFFER;

public class CmdDriveWithController extends CommandBase {
    private final SubDriveTrain subDriveTrain;
    private final CommandXboxController driverController;

    public CmdDriveWithController(SubDriveTrain subDriveTrain, CommandXboxController driverController) {
        this.subDriveTrain = subDriveTrain;
        this.driverController = driverController;

        addRequirements(subDriveTrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // Reset speed to 0 every execution
        double speed = 0.0;
        // Reset rotation to 0 every execution
        double rotation = 0.0;

        // Booleans for detecting if we want the robot to move
        boolean isMoveDesired = (driverController.getRightTriggerAxis() > 0 || driverController.getLeftTriggerAxis() > 0);
        boolean isTurnDesired = (driverController.getLeftX() > JOYSTICK_AXIS_BUFFER || driverController.getLeftX() < -JOYSTICK_AXIS_BUFFER);

        if(isMoveDesired) {
            // Set speed to value of triggers
            if(driverController.getRightTriggerAxis() > 0) {
                speed = -1*driverController.getRightTriggerAxis();
            }
            else {
                speed = driverController.getLeftTriggerAxis();
            }
        }
        if (isTurnDesired) {
            rotation = 0.6*driverController.getLeftX();
        }

        subDriveTrain.drive(speed, rotation);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
