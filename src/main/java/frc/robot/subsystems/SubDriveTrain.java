package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveTrainConstants.*;
import static frc.robot.Constants.ControllerConstants.*;

public class SubDriveTrain extends SubsystemBase {
    public SubDriveTrain() {}

    @Override
    public void periodic() {}

    // configure the drivetrain
    public void driveTrainConfigure() {
        // set whether the drivetrain motors are inverted
        rightDriveMotor.setInverted(true);
        leftDriveMotor.setInverted(false);

        // set the right and left followers
        rightFollowMotor.setFollower(rightDriveMotor.getMotorController());
        leftFollowMotor.setFollower(leftDriveMotor.getMotorController());

        // set the current limits for the motors
        rightDriveMotor.setSupplyCurrentLimit(true, CONTINUOUS_CURRENT_LIMIT, PEAK_CURRENT_LIMIT, DURATION_CURRENT_LIMIT);
        leftDriveMotor.setSupplyCurrentLimit(true, CONTINUOUS_CURRENT_LIMIT, PEAK_CURRENT_LIMIT, DURATION_CURRENT_LIMIT);

        // clear sticky faults
        rightDriveMotor.clearStickyFaults();
        rightFollowMotor.clearStickyFaults();
        leftDriveMotor.clearStickyFaults();
        leftFollowMotor.clearStickyFaults();

        // set the neutral deadband on the drive motors
        rightDriveMotor.setNeutralDeadBand(0.001);
        leftDriveMotor.setNeutralDeadBand(0.001);

        // set the nominal and peak outputs for the motors
        rightDriveMotor.setNominalPeakOutput(0);
        rightDriveMotor.setNominalPeakOutput(1);
        leftDriveMotor.setNominalPeakOutput(0);
        leftDriveMotor.setNominalPeakOutput(1);

        // set the ramp
        rightDriveMotor.setRamp(0.15);
        leftDriveMotor.setRamp(0.15);

        // configure motion magic
        rightDriveMotor.configureMotionMagic();
        leftDriveMotor.configureMotionMagic();

        // set max speed
        rightDriveMotor.setMaxSpeed(maxSpeed);
        leftDriveMotor.setMaxSpeed(maxSpeed);

        driveTrain.setSafetyEnabled(true);

        // set the PID constants for the motors
        rightDriveMotor.setConfig_kF(0, RIGHT_KF_0);
        rightDriveMotor.setConfig_kP(0, RIGHT_KP_0);
        rightDriveMotor.setConfig_kI(0, RIGHT_KI_0);
        rightDriveMotor.setConfig_kD(0, RIGHT_KD_0);
        rightDriveMotor.setConfig_kF(1, RIGHT_KF_1);
        rightDriveMotor.setConfig_kP(1, RIGHT_KP_1);
        rightDriveMotor.setConfig_kI(1, RIGHT_KI_1);
        rightDriveMotor.setConfig_kD(1, RIGHT_KD_1);

        leftDriveMotor.setConfig_kF(0, LEFT_KF_0);
        leftDriveMotor.setConfig_kP(0, LEFT_KP_0);
        leftDriveMotor.setConfig_kI(0, LEFT_KI_0);
        leftDriveMotor.setConfig_kD(0, LEFT_KD_0);
        leftDriveMotor.setConfig_kF(1, LEFT_KF_1);
        leftDriveMotor.setConfig_kP(1, LEFT_KP_1);
        leftDriveMotor.setConfig_kI(1, LEFT_KI_1);
        leftDriveMotor.setConfig_kD(1, LEFT_KD_1);
    }

    // reset the safety timer on the drivetrain
    public void resetSafetyTimer() {
        driveTrain.feed();
        rightDriveMotor.get();
    }

    // Get the speed in encoder ticks/100ms
    public double getRightSideSpeed() {
        return rightDriveMotor.get();
    }
    public double getLeftSideSpeed() {
        return leftDriveMotor.get();
    }

    // Drive the drivetrain with arcade drive given speed and rotation
    public void drive(double speed, double rotation) {
        driveTrain.setDeadband(JOYSTICK_AXIS_BUFFER);
        driveTrain.arcadeDrive(speed, rotation, true);
    }

    public void setMaxSpeed(double maxSpeed)  {
        this.maxSpeed = maxSpeed;
        rightDriveMotor.setMaxSpeed(maxSpeed);
        leftDriveMotor.setMaxSpeed(maxSpeed);
    }

    public void rotateDrivetrain(double rotation) {
        driveTrain.arcadeDrive(0, rotation);
    }

    // get the encoder position of the motors
    public double getLeftEncoderValue() {
        return leftDriveMotor.getEncoderPosition();
    }
    public double getRightEncoderValue() {
        return rightDriveMotor.getEncoderPosition();
    }

    // get the error of the motors
    public double getLeftError() {
        return leftDriveMotor.getError();
    }
    public double getRightError() {
        return rightDriveMotor.getError();
    }

    // reset the encoder positions of the motors
    public void resetEncoderPositions() {
        rightDriveMotor.resetEncoder();
        leftDriveMotor.resetEncoder();
    }

    public void setRamp(double ramp) {
        rightDriveMotor.setRamp(ramp);
        leftDriveMotor.setRamp(ramp);
    }

    public void calibrateNavX() {
        ahrs.calibrate();
    }

    public float getYaw() {
        return ahrs.getYaw();
    }

    public void zeroYaw() {
        ahrs.zeroYaw();
    }

    public float getStraightYawValue() {
        return ahrsYawStraight;
    }

    public void setStraightYawValue(float yawValue) {
        ahrsYawStraight = yawValue;
    }

    public void setPitchLevelValue(float pitchValue) {
        ahrsPitchLevel = pitchValue;
    }

    public float getPitchLevelValue() {
        return ahrsPitchLevel;
    }

    public float getPitch() {
        return ahrs.getPitch();
    }

    public float getRoll() {
        return ahrs.getRoll();
    }

    public void setDirectedRotation(double rotation) {
        directedRotation = rotation;
    }

    public double getDirectedRotation() {
        return directedRotation;
    }

    // Privates

    // Create drivetrain motors
    private BC_FalconFX leftDriveMotor = new BC_FalconFX(MOTOR_LEFT_MASTER);
    private BC_FalconFX rightDriveMotor = new BC_FalconFX(MOTOR_RIGHT_MASTER);
    private BC_FalconFX leftFollowMotor = new BC_FalconFX(MOTOR_LEFT_FOLLOWER);
    private BC_FalconFX rightFollowMotor = new BC_FalconFX(MOTOR_RIGHT_FOLLOWER);

    private DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMotor, rightDriveMotor);

    private double maxSpeed = VELOCITY_SP_MAX_LG;

    // Navx setup and increasing the update rate default is 60 range is 4-200. Loading of the RoboRio should be monitored
    private final AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte) 100);

    private float ahrsYawStraight = 0;
    private float ahrsPitchLevel = 0;

    private double directedRotation = 0;
}
