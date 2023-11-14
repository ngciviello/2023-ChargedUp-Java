package frc.robot.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;

public class AutoCmdBalanceOnChargeStation extends CommandBase {
    private final SubDriveTrain subDriveTrain;
    private final Timer timer;
    private boolean finished = false;
    private double levelPitchValue = 0.0;

    public AutoCmdBalanceOnChargeStation(SubDriveTrain subDriveTrain, Timer timer) {
        this.subDriveTrain = subDriveTrain;
        this.timer = timer;
        addRequirements(subDriveTrain);
    }

    @Override
    public void initialize() {
        timer.stop();
        timer.reset();

        levelPitchValue = subDriveTrain.getPitchLevelValue();

        finished = false;
    }

    @Override
    public void execute() {
        double currentPitch = subDriveTrain.getPitch();
        double pitchTolerance = 1.5;
        double speed = 0.0;

        subDriveTrain.setRamp(0.5);
        if(currentPitch > levelPitchValue+pitchTolerance) {
            //std::cout << "steadying forward current pitch: " << currentPitch << std::endl;
            speed = 0.145714286;
        }
        else if(currentPitch < levelPitchValue-pitchTolerance) {
            //std::cout << "steadying backward, current pitch: " << currentPitch << std::endl;
            speed = -0.145714286;
        }
        else {
            timer.start();
        }
        if (timer.hasElapsed(2)) {
            finished = true;
        }
        // Pass speed to the drive train to make it go
        subDriveTrain.drive(speed, 0);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
