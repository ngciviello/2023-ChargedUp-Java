package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.LimelightConstants.*;
import frc.robot.subsystems.SubDriveTrain;
import frc.robot.subsystems.SubLimelight;

public class CmdPrinty extends CommandBase {

    private final SubDriveTrain subDriveTrain;
    private final SubLimelight upperLimelight;

    public CmdPrinty(SubDriveTrain subDriveTrain) {
        this.subDriveTrain = subDriveTrain;
        this.upperLimelight = new SubLimelight(LIMELIGHT_UPPER_ID, LL_LIMELIGHT_UPPER_HEIGHT, LL_LIMELIGHT_UPPER_ANGLE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        int selectedPipeline = upperLimelight.getPipeline();
        if (selectedPipeline == -1) {
            System.out.println("*** Error reading pipeline! ***");
        } else {
            System.out.println("Old Pipeline: " + selectedPipeline);
            selectedPipeline++;
            if (selectedPipeline > 9) {
                selectedPipeline = 0;
            }
            System.out.println("New Pipeline: " + selectedPipeline);
            System.out.println("Got Target? " + upperLimelight.getTarget() + " Apriltag ID: " + upperLimelight.getAprilTagId());
            upperLimelight.setPipeline(selectedPipeline);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
