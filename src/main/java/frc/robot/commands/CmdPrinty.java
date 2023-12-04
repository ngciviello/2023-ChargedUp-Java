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
        System.out.println("NavX Yaw: " + subDriveTrain.getYaw());
    }

    @Override
    public void end(boolean interrupted) {
        int selectedPipeline = upperLimelight.getPipeline();
        if(selectedPipeline == -1) {
            System.out.println("*** Error reading pipeline! ***");
        } else {
            selectedPipeline++;
            if (selectedPipeline > 9) {
                selectedPipeline = 0;
            }
            upperLimelight.setPipeline(selectedPipeline);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
