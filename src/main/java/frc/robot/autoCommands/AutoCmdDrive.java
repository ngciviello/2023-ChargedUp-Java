package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;

import static frc.robot.Constants.DriveTrainConstants.DRIVE_TRAIN_TICKS_PER_INCH_LEFT_SIDE;
import static frc.robot.Constants.DriveTrainConstants.DRIVE_TRAIN_TICKS_PER_INCH_RIGHT_SIDE;

public class AutoCmdDrive extends CommandBase {
    private final SubDriveTrain subDriveTrain;

    private double speed = 0.0;
    private double distance = 0.0;
    private double rightFinished = 0.0;
    private double leftFinished = 0.0;
    private boolean finished = false;

    public AutoCmdDrive(SubDriveTrain subDriveTrain, double speed, double distance) {
        this.subDriveTrain = subDriveTrain;
        this.speed = speed;
        this.distance = distance;

        addRequirements(subDriveTrain);
    }

    @Override
    public void initialize() {
        finished = false;
        leftFinished = distance*DRIVE_TRAIN_TICKS_PER_INCH_LEFT_SIDE + subDriveTrain.getLeftEncoderValue();
        rightFinished = distance*DRIVE_TRAIN_TICKS_PER_INCH_RIGHT_SIDE + subDriveTrain.getRightEncoderValue();
        subDriveTrain.setStraightYawValue(subDriveTrain.getStraightYawValue());
    }

    @Override
    public void execute() {
        double rightPosition = subDriveTrain.getRightEncoderValue();
        double leftPosition = subDriveTrain.getLeftEncoderValue();
        if(speed < 0 && ((rightPosition < rightFinished) || (leftPosition < leftFinished))) {
            finished = true;
            subDriveTrain.drive(0.0, 0.0);
            System.out.println("Done Going Negative");
        }
        else if(speed > 0 && ((rightPosition > rightFinished) || (leftPosition > leftFinished))) {
            finished = true;
            subDriveTrain.drive(0.0, 0.0);
            System.out.println("Done Going Positive");
        }
        else {
            System.out.println("Moving, Currently At: " + rightPosition + " To: " + rightFinished);
            subDriveTrain.drive(speed, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return finished;
    }
}
