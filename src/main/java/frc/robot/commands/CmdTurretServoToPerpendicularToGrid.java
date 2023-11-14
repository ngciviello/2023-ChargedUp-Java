package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SubDriveTrain;
import frc.robot.subsystems.SubTurret;

import static frc.robot.Constants.ControllerConstants.JOYSTICK_AXIS_BUFFER;

public class CmdTurretServoToPerpendicularToGrid extends CommandBase {
    private final SubTurret subTurret;
    private final SubDriveTrain subDriveTrain;
    private final CommandXboxController auxController;
    private boolean finished = false;
    private boolean goingClockwise = false;
    private double robotYaw = 0.0;
    private double newTurretAngle = 0.0;

    public CmdTurretServoToPerpendicularToGrid(SubTurret subTurret, SubDriveTrain subDriveTrain, CommandXboxController auxController) {
        this.subTurret = subTurret;
        this.subDriveTrain = subDriveTrain;
        this.auxController = auxController;

        addRequirements(subTurret);
    }

    @Override
    public void initialize() {
        finished = false;
        goingClockwise = false;
        robotYaw = -subDriveTrain.getYaw();
        if(robotYaw <= 10 && robotYaw >= -10) {
            if(auxController.povLeft().getAsBoolean() || auxController.povDownLeft().getAsBoolean() || auxController.povUpLeft().getAsBoolean()) {
                goingClockwise = false;
            }
            else if(auxController.povRight().getAsBoolean() || auxController.povDownRight().getAsBoolean() || auxController.povUpRight().getAsBoolean()) {
                goingClockwise = true;
            }
            else {
                goingClockwise = subTurret.getPositionInDegrees() <= 0;
            }
        }
        else {
            goingClockwise = robotYaw < -10;
        }
        if(goingClockwise) {
            newTurretAngle = -180 - robotYaw;
        }
        else {
            newTurretAngle = 180 - robotYaw;
        }
        if(newTurretAngle > 190) {
            System.out.println("New Turret Angle Was More than 190 " + newTurretAngle);
            newTurretAngle = 190;
        }
        else if(newTurretAngle < -190) {
            System.out.println("New Turret Angle Was Less than -190 " + newTurretAngle);
            newTurretAngle = -190;
        }
        System.out.println("New Turret Angel: " + newTurretAngle);
        if(!(auxController.getLeftX() > JOYSTICK_AXIS_BUFFER || auxController.getLeftX() < -JOYSTICK_AXIS_BUFFER) && !(auxController.getRightX() > JOYSTICK_AXIS_BUFFER || auxController.getRightX() < -JOYSTICK_AXIS_BUFFER)) {
            subTurret.rotateToAngle(newTurretAngle);
        }
    }

    @Override
    public void execute() {
        if((auxController.getLeftX() > JOYSTICK_AXIS_BUFFER || auxController.getLeftX() < -JOYSTICK_AXIS_BUFFER) && !(auxController.getRightX() > JOYSTICK_AXIS_BUFFER || auxController.getRightX() < -JOYSTICK_AXIS_BUFFER)) {
            subTurret.rotateToAngle(newTurretAngle);
        }
        double currentTurretAngle = subTurret.getPositionInDegrees();
        if(goingClockwise && currentTurretAngle < newTurretAngle+2) {
            finished = true;
        }
        else if(!goingClockwise && currentTurretAngle > newTurretAngle-2) {
            finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
