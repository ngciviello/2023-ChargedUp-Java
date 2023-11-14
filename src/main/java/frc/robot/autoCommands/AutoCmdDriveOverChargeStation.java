package frc.robot.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;

public class AutoCmdDriveOverChargeStation extends CommandBase {
    private final SubDriveTrain subDriveTrain;
    private final Timer timer = new Timer();
    private boolean onChargeStation = false;
    private boolean overChargeStation = false;
    private boolean pitchedDown = false;
    private boolean finished = false;
    private float levelPitchValue = 0;

    public AutoCmdDriveOverChargeStation(SubDriveTrain subDriveTrain) {
        this.subDriveTrain = subDriveTrain;
        addRequirements(subDriveTrain);
    }

    @Override
    public void initialize() {
        // reset variables
        onChargeStation = false;
        finished = false;
        timer.reset();
        overChargeStation = false;
        pitchedDown = false;
        levelPitchValue = subDriveTrain.getPitch();
        subDriveTrain.setPitchLevelValue(levelPitchValue);
        subDriveTrain.setStraightYawValue(subDriveTrain.getYaw());
    }

    @Override
    public void execute() {
        double speed = 0;
        double currentPitch = subDriveTrain.getPitch();
        if(!onChargeStation && currentPitch < levelPitchValue-5) {
            onChargeStation = true;
        }
        else if(onChargeStation && !pitchedDown && currentPitch > levelPitchValue+5) {
            pitchedDown = true;
        }
        else if(onChargeStation && pitchedDown && currentPitch > levelPitchValue-0.7 && currentPitch < levelPitchValue+0.7) {
            overChargeStation = true;
        }
        if(!overChargeStation && !onChargeStation) {
            speed = -0.6;
        }
        else if(!overChargeStation) {
            if(!pitchedDown) {
                speed = -0.428571429;
            }
            else {
                speed = -0.48;
            }
        }
        else {
            timer.start();
            if(!timer.hasElapsed(0.33)) {
                speed = -0.531428571;
            }
            else {
                speed = 0;
                finished = true;
            }
        }
        subDriveTrain.drive(speed, 0);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
