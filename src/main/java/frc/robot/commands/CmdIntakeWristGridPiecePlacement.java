package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.IntakeWristConstants.WRIST_CLAW_PLACE_CONE;
import static frc.robot.Constants.IntakeWristConstants.WRIST_CLAW_PLACE_CUBE;

public class CmdIntakeWristGridPiecePlacement extends CommandBase {
    private final SubRobotGlobals subRobotGlobals;
    private final SubIntakeWrist subIntakeWrist;

    public CmdIntakeWristGridPiecePlacement(SubRobotGlobals subRobotGlobals, SubIntakeWrist subIntakeWrist) {
        this.subRobotGlobals = subRobotGlobals;
        this.subIntakeWrist = subIntakeWrist;

        addRequirements(subIntakeWrist);
    }

    @Override
    public void initialize() {
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            subIntakeWrist.wristServoToPosition(WRIST_CLAW_PLACE_CONE);
        }
        else if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            subIntakeWrist.wristServoToPosition(WRIST_CLAW_PLACE_CUBE);
        }
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
