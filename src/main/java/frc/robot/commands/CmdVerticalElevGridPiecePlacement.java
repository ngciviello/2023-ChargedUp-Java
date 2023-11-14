package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubRobotGlobals;
import frc.robot.subsystems.SubVerticalElevator;

import static frc.robot.Constants.VerticalElevatorConstants.*;

public class CmdVerticalElevGridPiecePlacement extends CommandBase {
    private final SubVerticalElevator subVerticalElevator;
    private final SubRobotGlobals subRobotGlobals;
    private final CommandXboxController auxController;
    private boolean finished = false;
    private double position = 0.0;

    public CmdVerticalElevGridPiecePlacement(SubVerticalElevator subVerticalElevator, SubRobotGlobals subRobotGlobals, CommandXboxController auxController) {
        this.subVerticalElevator = subVerticalElevator;
        this.subRobotGlobals = subRobotGlobals;
        this.auxController = auxController;

        addRequirements(subVerticalElevator);
    }

    @Override
    public void initialize() {
        finished = false;
        position = 0;
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece && (auxController.povUp().getAsBoolean() || auxController.povUpLeft().getAsBoolean() || auxController.povUpRight().getAsBoolean())) {
            position=VERTICAL_ELEV_POS_CONE_NODE_UPPER;
        }
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece && (auxController.povCenter().getAsBoolean() || auxController.povLeft().getAsBoolean() || auxController.povRight().getAsBoolean())) {
            position=VERTICAL_ELEV_POS_CONE_NODE_LOWER;
        }
        if((subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece || subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) && (auxController.povDown().getAsBoolean() || auxController.povDownLeft().getAsBoolean() || auxController.povDownRight().getAsBoolean())) {
            position=VERTICAL_ELEV_POS_HYBRID_NODE;
        }
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece && (auxController.povUp().getAsBoolean() || auxController.povUpLeft().getAsBoolean() || auxController.povUpRight().getAsBoolean())) {
            position=VERTICAL_ELEV_POS_CUBE_NODE_UPPER;
        }
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece && (auxController.povCenter().getAsBoolean() || auxController.povLeft().getAsBoolean() || auxController.povRight().getAsBoolean())) {
            position=VERTICAL_ELEV_POS_CUBE_NODE_LOWER;
        }
        if(position != 0.0) {
            subVerticalElevator.servoToPosition(position);
            System.out.println("Moving to set position");
        }
        else {
            System.out.println("ERROR: Position Not Set!");
            finished = true;
        }
    }

    @Override
    public void execute() {
        if(!finished) {
            if(subVerticalElevator.getPosition() > (position-10000) && subVerticalElevator.getPosition() < (position+10000)) {
                finished = true;
                System.out.println("At Position!");
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
