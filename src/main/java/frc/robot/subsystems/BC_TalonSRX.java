package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import static frc.robot.Constants.DriveTrainConstants.*;

public class BC_TalonSRX implements MotorController {

    private TalonSRX talonSRX;
    private ControlMode controlMode = ControlMode.Velocity;
    private double speed = 0;
    private double maxSpeed = VELOCITY_SP_MAX_LG;


    public BC_TalonSRX(int device) {
        talonSRX = new TalonSRX(device);
    }

    // Overrides for the motor controller class
    @Override
    public void set(double speed) {
        if(controlMode == ControlMode.Velocity) {
            speed = speed * maxSpeed;
        }
        talonSRX.set(controlMode, speed);
        this.speed = speed;
    }

    @Override
    public double get() {
        return this.speed;
    }

    @Override
    public void setInverted(boolean isInverted) {
        talonSRX.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return talonSRX.getInverted();
    }

    @Override
    public void disable() {
        talonSRX.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void stopMotor() {
        talonSRX.setNeutralMode(NeutralMode.Coast);
    }

    public void setMotionMagic(double position) {
        talonSRX.set(TalonSRXControlMode.MotionMagic, position);
    }

    public double getError() {
        return talonSRX.getClosedLoopError(0);
    }

    public double getTarget() {
        return talonSRX.getClosedLoopTarget(0);
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void resetEncoderPosition() {
        talonSRX.setSelectedSensorPosition(0, 0,0);
    }

    public void setConfig_kF(int profileSlot, double value) {
        talonSRX.config_kF(profileSlot, value, 0);
    }

    public void setConfig_kP(int profileSlot, double value) {
        talonSRX.config_kP(profileSlot, value, 0);
    }

    public void setConfig_kI(int profileSlot, double value) {
        talonSRX.config_kI(profileSlot, value, 0);
    }

    public void setConfig_kD(int profileSlot, double value) {
        talonSRX.config_kD(profileSlot, value, 0);
    }

    public void clearStickyFaults() {
        talonSRX.clearStickyFaults();
    }

    public void setFollower(TalonSRX motor, boolean inverted) {
        talonSRX.follow(motor);
        talonSRX.setInverted(inverted);
    }

    public TalonSRX getMotorController() {
        return talonSRX;
    }

    public void setSelectedFeedbackSensor(FeedbackDevice device) {
        talonSRX.configSelectedFeedbackSensor(device, 0, 0);
    }

    public void setSupplyCurrentLimit(boolean enable, double limit, double trigger, double triggerTimer) {
        talonSRX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(enable, limit, trigger, triggerTimer));
    }

    public void setNominalPeakOutput(int profileSlot) {
        talonSRX.selectProfileSlot(profileSlot, 0);
        talonSRX.configNominalOutputForward(0, 0);
        talonSRX.configNominalOutputReverse(0, 0);
        talonSRX.configPeakOutputForward(1.0, 0);
        talonSRX.configPeakOutputReverse(-1.0, 0);
    }

    public void configureMotionMagic() {
        talonSRX.configMotionAcceleration(8192, 0);
        talonSRX.configMotionCruiseVelocity(8192, 0);
    }

    public void driveWithMotionMagic(double position) {
        talonSRX.set(ControlMode.MotionMagic, position);
    }

    public double getEncoderPosition() {
        return talonSRX.getSelectedSensorPosition(0);
    }

    public void resetEncoder() {
        talonSRX.setSelectedSensorPosition(0, 0, 0);
    }

    public void setRamp(double ramp) {
        talonSRX.configClosedloopRamp(ramp, 0);
    }

    public void setControlMode(ControlMode mode) {
        this.controlMode = mode;
    }

    public void configureMotionVelocity(double velocity) {
        talonSRX.configMotionCruiseVelocity(velocity, 0);
    }

    public void configureMotionAcceleration(double acceleration) {
        talonSRX.configMotionAcceleration(acceleration, 0);
    }

    public void configureMotionCurveStrength(int curveStrength) {
        talonSRX.configMotionSCurveStrength(curveStrength, 0);
    }
}
