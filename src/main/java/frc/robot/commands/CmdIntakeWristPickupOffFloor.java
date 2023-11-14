package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;

import static frc.robot.Constants.IntakeWristConstants.*;

public class CmdIntakeWristPickupOffFloor extends CommandBase {
    private final SubIntakeWrist subIntakeWrist;
    private final SubRobotGlobals subRobotGlobals;
    private final CommandXboxController driverController;
    private boolean finished = false;
    public CmdIntakeWristPickupOffFloor(SubIntakeWrist subIntakeWrist, SubRobotGlobals subRobotGlobals, CommandXboxController driverController) {
        this.subIntakeWrist = subIntakeWrist;
        this.subRobotGlobals = subRobotGlobals;
        this.driverController = driverController;

        addRequirements(subIntakeWrist);
    }

    @Override
    public void initialize() {
        finished = false;
    }

    @Override
    public void execute() {
        if(driverController.getHID().getYButton()) {
            if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece && subIntakeWrist.getIntakeOutput() > 11) {
                finished = true;
            }
            else if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece && subIntakeWrist.getIntakeOutput() > 9.5) {
                finished = true;
            }
            if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR);
                subIntakeWrist.intakeSpin(-0.175);
            }
            else if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
                subIntakeWrist.wristServoToPosition(WRIST_CLAW_PICKUP_CUBE_OFF_FLOOR);
                subIntakeWrist.intakeSpin(-0.2);
            }
        }
        else {
            finished = true;
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
