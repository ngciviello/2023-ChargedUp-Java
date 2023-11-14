package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;

import static frc.robot.Constants.IntakeWristConstants.*;

public class CmdIntakeWristPickUpFromShelf extends CommandBase {
    private final SubIntakeWrist subIntakeWrist;
    private final SubRobotGlobals subRobotGlobals;
    private final CommandXboxController auxController;
    private boolean finished = false;

    public CmdIntakeWristPickUpFromShelf(SubIntakeWrist subIntakeWrist, SubRobotGlobals subRobotGlobals, CommandXboxController auxController) {
        this.subIntakeWrist = subIntakeWrist;
        this.subRobotGlobals = subRobotGlobals;
        this.auxController = auxController;

        addRequirements(subIntakeWrist);
    }

    @Override
    public void initialize() {
        finished = false;
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_SHELF);
        }
        else {
            subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_CUBE_OFF_SHELF);
        }
    }

    @Override
    public void execute() {
        if(auxController.getHID().getBackButton()) {
            finished = true;
        }
        else if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            subIntakeWrist.intakeSpin(-0.475);
            if(subIntakeWrist.getIntakeOutput() > 45) {
                finished = true;
            }
        }
        else if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            subIntakeWrist.intakeSpin(-0.2);
            if(subIntakeWrist.getIntakeOutput() > 9.5) {
                finished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        subIntakeWrist.wristServoToPosition(WRIST_CLAW_MIN_LIMIT);
        subIntakeWrist.intakeSpin(0.0);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
