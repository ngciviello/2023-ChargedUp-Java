package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;

import static frc.robot.Constants.IntakeWristConstants.*;

public class CmdIntakeWristPositioning extends CommandBase {
    private final SubIntakeWrist subIntakeWrist;
    private final SubRobotGlobals subRobotGlobals;
    private final CommandXboxController auxController;

    public CmdIntakeWristPositioning(SubIntakeWrist subIntakeWrist, SubRobotGlobals subRobotGlobals, CommandXboxController auxController) {
        this.subIntakeWrist = subIntakeWrist;
        this.subRobotGlobals = subRobotGlobals;
        this.auxController = auxController;

        addRequirements(subIntakeWrist);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(auxController.povUp().getAsBoolean()) {
            subIntakeWrist.wristServoToPosition(WRIST_CLAW_MIN_LIMIT);
        } else if (subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            if(auxController.povCenter().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PLACE_CONE);
            } else if(auxController.povDown().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_MAX_LIMIT);
            } else if(auxController.povRight().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_SHELF);
            } else if(auxController.povLeft().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_TIPPED_OVER_CONE);
            } else if(auxController.povUpLeft().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR);
            }
        } else if (subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            if(auxController.povCenter().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PLACE_CUBE);
            } else if(auxController.povDown().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_CUBE_OFF_FLOOR);
            } else if(auxController.povRight().getAsBoolean()) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_CUBE_OFF_SHELF);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
