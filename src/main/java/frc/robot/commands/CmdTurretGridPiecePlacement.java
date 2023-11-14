package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.SubLimelightLower;
import frc.robot.subsystems.SubLimelightUpper;
import frc.robot.subsystems.SubRobotGlobals;
import frc.robot.subsystems.SubTurret;

import static frc.robot.Constants.LimelightConstants.*;

public class CmdTurretGridPiecePlacement extends CommandBase {
    private final SubRobotGlobals subRobotGlobals;
    private final SubTurret subTurret;
    private final CommandXboxController auxController;
    private final SubLimelightLower subLimelightLower;
    private final SubLimelightUpper subLimelightUpper;
    private final Timer timer;
    private double nodeXOffsetFromTag = 0;
    private double nodeYOffsetFromTag = 0;
    private int aprilTagCycle = 0;
    private boolean finished = false;

    public CmdTurretGridPiecePlacement(SubRobotGlobals subRobotGlobals, SubTurret subTurret, CommandXboxController auxController, SubLimelightLower subLimelightLower, SubLimelightUpper subLimelightUpper, Timer timer) {
        this.subRobotGlobals = subRobotGlobals;
        this.subTurret = subTurret;
        this.auxController = auxController;
        this.subLimelightLower = subLimelightLower;
        this.subLimelightUpper = subLimelightUpper;
        this.timer = timer;

        addRequirements(subLimelightLower);
        addRequirements(subTurret);
    }

    @Override
    public void initialize() {
        finished = false;
        nodeXOffsetFromTag = 0;
        nodeYOffsetFromTag = 0;
        timer.reset();
        timer.start();
        if(subRobotGlobals.game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            if (auxController.povCenter().getAsBoolean()) {
                System.out.println("****** Placing on middle shelf ******");
                nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_SHELF_MIDDLE.x;
                nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_SHELF_MIDDLE.y;
            } else if (auxController.povUp().getAsBoolean()) {
                System.out.println("****** Placing on top shelf ******");
                nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_SHELF_TOP.x;
                nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_SHELF_TOP.y;
            }
        }
        else {
             if (auxController.povUpLeft().getAsBoolean()) {
                System.out.println("****** Placing on top right cone node ******");
                nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_UPPER.x;
                nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_UPPER.y;
             }
             else if (auxController.povUpRight().getAsBoolean()) {
                 System.out.println("****** Placing on top left cone node ******");
                 nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_UPPER.x;
                 nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_UPPER.y;
             }
             else if (auxController.povLeft().getAsBoolean()) {
                 System.out.println("****** Placing on middle right cone node ******");
                 nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_LOWER.x;
                 nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_LOWER.y;
             }
             else if (auxController.povRight().getAsBoolean()) {
                 System.out.println("****** Placing on middle left cone node ******");
                 nodeXOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_LOWER.x;
                 nodeYOffsetFromTag = FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_LOWER.y;
             }
        }
        if(nodeXOffsetFromTag == 0) {
            System.out.println("****** ERROR: Where do I place??? ******");
            finished = true;
        }
    }

    @Override
    public void execute() {
        if(!finished) {
            /*********************** PIECE PLACEMENT ***************************
             Configure the limelight for the aprilTag and place the  piece that is
             selected by the DPad.  Work down through the aprilTag selections until
             we find a target over several execution of the robot code.
             */

            System.out.println("AprilTagCycle: " + aprilTagCycle);

            if (timer.hasElapsed(0.25) && aprilTagCycle == 0) {
                subLimelightLower.selectPipeline(LL_PIPELINE_APRILTAG_1n8);
                subLimelightUpper.selectPipeline(LL_PIPELINE_APRILTAG_1n8);
                System.out.println("AprilTag 1 or 8 Selected");
                aprilTagCycle = 1;
            } else if (timer.hasElapsed(0.5) && aprilTagCycle == 1) {
                subLimelightLower.selectPipeline(LL_PIPELINE_APRILTAG_2n7);
                subLimelightUpper.selectPipeline(LL_PIPELINE_APRILTAG_2n7);
                System.out.println("AprilTag 1 or 7 Selected");
                aprilTagCycle = 2;
            } else if (timer.hasElapsed(0.75) && aprilTagCycle == 2) {
                subLimelightLower.selectPipeline(LL_PIPELINE_APRILTAG_3n6);
                subLimelightUpper.selectPipeline(LL_PIPELINE_APRILTAG_3n6);
                System.out.println("AprilTag 3 or 6 Selected");
                aprilTagCycle = 3;
            }

            // Set the april tag positioning variables if we see a target
            if (subLimelightLower.getTarget()) {
                double distance = subLimelightLower.getDistanceToTarget(LL_LIMELIGHT_LOWER_HEIGHT, TARGET_APRILTAG_GRID_HEIGHT, LL_LIMELIGHT_LOWER_ANGLE);
                System.out.println("Lower LL Distance to april tag: " + distance);
                double angleFromCenter = subLimelightLower.getHorizontalOffset();
                System.out.println("Lower LL angle to april tag: " + angleFromCenter);
                double xOffset = Math.sin(angleFromCenter * 3.14 / 180) * distance;
                System.out.println("Lower LL X offset: " + xOffset);
                double yOffset = Math.cos(angleFromCenter * 3.14 / 180) * distance;
                System.out.println("Lower LL Y offset: " + yOffset);

                // Calculate the new target X offset
                double newXOffset = nodeXOffsetFromTag + xOffset;
                System.out.println("Lower LL New X offset: " + newXOffset);
                // Calculate the new target Y offset
                double newYOffset = nodeYOffsetFromTag + yOffset;
                System.out.println("Lower LL New Y offset: " + newYOffset);

                // Calculate new target angle
                double turretOffsetAngle = Math.atan(newXOffset/newYOffset)*180/3.14;
                System.out.println("Lower LL Turret angle offset: " + turretOffsetAngle);

                // Calculate new turret angle
                double newTurretPosition = subTurret.getPositionInDegrees() - turretOffsetAngle;
                System.out.println("Current turret angle: " + subTurret.getPositionInDegrees());
                System.out.println("New Turret angle: " + newTurretPosition);

                subTurret.rotateToAngle(newTurretPosition);
                finished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
