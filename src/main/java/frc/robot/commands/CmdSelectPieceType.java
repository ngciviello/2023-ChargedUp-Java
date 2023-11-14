package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SubRobotGlobals;


public class CmdSelectPieceType extends CommandBase {
    private final SubRobotGlobals subRobotGlobals;

    public CmdSelectPieceType(SubRobotGlobals subRobotGlobals) {
        this.subRobotGlobals = subRobotGlobals;
    }

    @Override
    public void initialize() {

        switch (subRobotGlobals.game_state.selectedPieceType) {
            case ConePiece:
                subRobotGlobals.game_state.selectedPieceType = Constants.GlobalConstants.pieceTypes.CubePiece;
                break;
            case CubePiece:
            case NoPiece:
                subRobotGlobals.game_state.selectedPieceType = Constants.GlobalConstants.pieceTypes.ConePiece;
                break;
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
