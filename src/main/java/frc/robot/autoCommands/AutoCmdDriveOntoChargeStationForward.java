package frc.robot.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;

public class AutoCmdDriveOntoChargeStationForward extends CommandBase {
    private final SubDriveTrain subDriveTrain;
    private final Timer timer;
    private float levelPitchValue = 0;
    private double wellOntoChargeStationPitchValue = 0;
    private boolean onChargeStation = false;
    private boolean finished = false;

    public AutoCmdDriveOntoChargeStationForward(SubDriveTrain subDriveTrain, Timer timer) {
        this.subDriveTrain = subDriveTrain;
        this.timer = timer;
        addRequirements(subDriveTrain);
    }

    @Override
    public void initialize() {
        onChargeStation = false;
        finished = false;
        timer.reset();
        wellOntoChargeStationPitchValue = -999;
        levelPitchValue = subDriveTrain.getPitchLevelValue();
        subDriveTrain.setPitchLevelValue(levelPitchValue);
        subDriveTrain.setStraightYawValue(subDriveTrain.getYaw());
    }

    @Override
    public void execute() {
        double currentPitch = subDriveTrain.getPitch();
        double speed = 0;

        // If we aren't on the charge station yet, go forward until we start tilting
        if(!onChargeStation && (currentPitch > levelPitchValue-5) && (currentPitch < levelPitchValue + 1)) {
            speed = -0.6;
        }
        else {
            if(!onChargeStation) {
                onChargeStation = true;
                timer.start();
            }
            // wait until we "see" the ramp start dropping
            // but only consider a drop in pitch as the ramp dropping after some time has elapsed
            // (so that the initial "bump" and "recoil" of hitting the ramp isn't caught)
            if(currentPitch > wellOntoChargeStationPitchValue+3) {
                if(!timer.hasElapsed(1.6)) {
                    speed = -0.411428571;
                }
                else {
                    if(currentPitch > wellOntoChargeStationPitchValue) {
                        wellOntoChargeStationPitchValue = currentPitch;
                    }
                    speed = -0.265714286;
                }
            }
            else {
                finished = true;
                speed = 0;
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
