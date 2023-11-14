package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubIntakeWrist;

import static frc.robot.Constants.IntakeWristConstants.WRIST_CLAW_MIN_LIMIT;

public class CmdIntakeWristHome extends CommandBase {
    private final SubIntakeWrist subIntakeWrist;

    public CmdIntakeWristHome(SubIntakeWrist subIntakeWrist) {
        this.subIntakeWrist = subIntakeWrist;

        addRequirements(subIntakeWrist);
    }

    @Override
    public void initialize() {
        subIntakeWrist.wristServoToPosition(WRIST_CLAW_MIN_LIMIT);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
