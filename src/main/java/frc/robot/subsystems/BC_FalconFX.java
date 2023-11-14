package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import static frc.robot.Constants.DriveTrainConstants.*;


public class BC_FalconFX implements MotorController {
    public BC_FalconFX(int device) {
        falconFX = new TalonFX(device);
    };

    // Overrides for MotorController Class
    @Override
    public void set(double speed) {
        if(controlMode == ControlMode.Velocity) {
            speed = speed * maxSpeed;
        }
        falconFX.set(controlMode, speed);
        this.speed = speed;
    }

    @Override
    public double get() {
        return speed;
    }

    @Override
    public void setInverted(boolean isInverted) {
        if(isInverted) {
            falconFX.setInverted(TalonFXInvertType.Clockwise);
        }
        else {
            falconFX.setInverted((TalonFXInvertType.CounterClockwise));
        }
    }

    @Override
    public boolean getInverted() {
        return falconFX.getInverted();
    }

    @Override
    public void disable() {
        falconFX.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void stopMotor() {
        falconFX.setNeutralMode(NeutralMode.Coast);
    }

    public void setControlMode(ControlMode mode) {
        controlMode = mode;
    }

    public void clearStickyFaults() {
        falconFX.clearStickyFaults();
    }

    public void setConfig_kF(int profileSlot, double value) {
        falconFX.config_kF(profileSlot, value, 0);
    }

    public void setConfig_kP(int profileSlot, double value) {
        falconFX.config_kP(profileSlot, value, 0);
    }

    public void setConfig_kI(int profileSlot, double value) {
        falconFX.config_kI(profileSlot, value, 0);
    }

    public void setConfig_kD(int profileSlot, double value) {
        falconFX.config_kD(profileSlot, value, 0);
    }

    public void setFollower(TalonFX motor) {
        falconFX.follow(motor);
        falconFX.setInverted(TalonFXInvertType.FollowMaster);
    }

    public TalonFX getMotorController() {
        return falconFX;
    }

    public void setSelectedFeedbackSensor(FeedbackDevice device) {
        falconFX.configSelectedFeedbackSensor(device);
    }

    public void setStatorCurrentLimit(boolean enable, double limit, double trigger, double triggerTime) {
        falconFX.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(enable, limit, trigger, triggerTime));
    }

    public void setSupplyCurrentLimit(boolean enable, double limit, double trigger, double triggerTime) {
        falconFX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(enable, limit, trigger, triggerTime));
    }

    public void setNominalPeakOutput(int profileSlot) {
        falconFX.selectProfileSlot(profileSlot, 0);
        falconFX.configNominalOutputForward(0,0);
        falconFX.configNominalOutputReverse(0,0);
        falconFX.configPeakOutputForward(1.0,0);
        falconFX.configPeakOutputReverse(-1.0,0);
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void driveWithMotionMagic(double position) {
        falconFX.set(TalonFXControlMode.MotionMagic, position);
    }

    public void setRamp(double ramp) {
        falconFX.configClosedloopRamp(ramp, 0);
    }

    public void configureMotionMagic() {
        falconFX.configMotionAcceleration(8192, 0);
        falconFX.configMotionCruiseVelocity(8192, 0);
    }

    public double getEncoderPosition() {
        return falconFX.getSelectedSensorPosition(0);
    }

    public double getError() {
        return falconFX.getClosedLoopError(0);
    }

    public void resetEncoder() {
        falconFX.setSelectedSensorPosition(0, 0, 0);
    }

    public void setNeutralDeadBand(double deadBand) {
        falconFX.configNeutralDeadband(deadBand);
    }

    // Privates
    private TalonFX falconFX;
    private ControlMode controlMode = ControlMode.Velocity;
    private double speed = 0;
    private double maxSpeed = VELOCITY_SP_MAX_LG;
}
