package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CmdTimer extends CommandBase {
    private final Timer timer;
    private double time;

    public CmdTimer(Timer timer, double time) {
        this.timer = timer;
        this.time = time;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(time);
    }
}
