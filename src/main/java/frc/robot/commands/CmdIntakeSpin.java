package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubIntakeWrist;
import frc.robot.subsystems.SubRobotGlobals;

import static frc.robot.Constants.IntakeWristConstants.*;

public class CmdIntakeSpin extends CommandBase {
    private final SubIntakeWrist subIntakeWrist;
    private final SubRobotGlobals subRobotGlobals;
    private final CommandXboxController auxController;
    private boolean finished = false;
    private boolean pullIn = false;
    private boolean eject = false;
    private boolean superEject = false;
    private double speed = 0.0;

    public CmdIntakeSpin(SubIntakeWrist subIntakeWrist, SubRobotGlobals subRobotGlobals, CommandXboxController auxController) {
        this.subIntakeWrist = subIntakeWrist;
        this.subRobotGlobals = subRobotGlobals;
        this.auxController = auxController;
    }

    @Override
    public void initialize() {
        finished = false;
        pullIn = auxController.povUp().getAsBoolean();
        eject = auxController.povRight().getAsBoolean();
        superEject = auxController.povLeft().getAsBoolean();
        speed = 0;
    }

    @Override
    public void execute() {

        // Conditions for finishing
        if(!auxController.getHID().getLeftBumper()) {
            finished = true;
        }
        else if(pullIn && subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece && subIntakeWrist.getWristPosition() < WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR+3 && subIntakeWrist.getWristPosition() > WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR-1 && subIntakeWrist.getIntakeOutput() > 12) {
            finished = true;
        }
        else if(pullIn && subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece && subIntakeWrist.getIntakeOutput() > 19) {
            finished = true;
        }
        else if(pullIn && subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece && subIntakeWrist.getIntakeOutput() > 9.5) {
            finished = true;
        }

        // Speeds for Cones
        if (subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            if(pullIn) {
                if(subIntakeWrist.getWristPosition() < WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR+3 && subIntakeWrist.getWristPosition() > WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR-1) {
                    speed = -0.175;
                }
                else {
                    speed = -0.455;
                }
            }
            else if (eject) {
                speed = 0.4;
            }
            else if(superEject) {
                speed = 1;
            }
            else {
                speed = 0.18;
            }
        }

        // Speed for Cubes
        else if (subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            if(pullIn) {
                speed = -0.2;
            }
            else if (eject) {
                speed = 0.4;
            }
            else if(superEject) {
                speed = 0.64;
            }
            else {
                speed = 0.15;
            }
        }
        subIntakeWrist.intakeSpin(speed);
    }

    @Override
    public void end(boolean interrupted) {
        subIntakeWrist.intakeSpin(0.0);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
