package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubLimelightUpper;

public class CmdCycleUpperLimelightPipelineAndPrint extends CommandBase {

    private final SubLimelightUpper subLimelightUpper;

    private final CommandXboxController auxController;

    private boolean isFinished = false;

    public CmdCycleUpperLimelightPipelineAndPrint(SubLimelightUpper subLimelightUpper, CommandXboxController auxController) {
        this.subLimelightUpper = subLimelightUpper;
        this.auxController = auxController;
        addRequirements(subLimelightUpper);
    }

    @Override
    public void initialize() {
        int pipeline = (int) subLimelightUpper.getActivePipeLine();
        pipeline++;
        subLimelightUpper.selectPipeline(pipeline);
        if(subLimelightUpper.getActivePipeLine() > 9) {
            subLimelightUpper.selectPipeline(0);
            System.out.println("Caught Illegal Pipeline");
        }
        System.out.println("Active Pipeline: " + subLimelightUpper.getActivePipeLine());
        System.out.println("Old Pipeline Int: " + pipeline);
        isFinished = false;
    }

    @Override
    public void execute() {
        if(subLimelightUpper.getTarget()) {
            System.out.println("Found Target!");
        }
        if(!auxController.a().getAsBoolean()) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Finished!");
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
