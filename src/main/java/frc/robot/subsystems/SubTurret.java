package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.TurretConstants.*;

public class SubTurret extends SubsystemBase {
    public SubTurret() {}

    @Override
    public void periodic() {
        if(enableHoldPosition) {
            turretMotor.set(ControlMode.Position, holdPosition);
        }
        else {
            holdPosition = turretMotor.getSelectedSensorPosition();
        }
    }

    public void configureTurret() {
        // Set the PID coefficients for the turret motor
        turretMotor.config_kF(0, TURRET_KF_0);
        turretMotor.config_kP(0, TURRET_KP_0);
        turretMotor.config_kI(0, TURRET_KI_0);
        turretMotor.config_kD(0, TURRET_KD_0);

        // Set up the soft limits for the turret motor
        turretMotor.configForwardSoftLimitThreshold(TURRET_MAX_ENCODER);
        turretMotor.configReverseSoftLimitThreshold(TURRET_MIN_ENCODER);
        turretMotor.configForwardSoftLimitEnable(true);
        turretMotor.configReverseSoftLimitEnable(true);

        // Set up the feedback sensor (encoder)
        turretMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        turretMotor.setSensorPhase(true);
        turretMotor.setInverted(false);

        turretMotor.configPeakCurrentDuration(20, 0);
        turretMotor.configPeakCurrentLimit(15, 0);
        turretMotor.configContinuousCurrentLimit(7, 0);
        turretMotor.enableCurrentLimit(true);

        turretMotor.configClosedloopRamp(0.28);
        turretMotor.configOpenloopRamp(0);

        //motor->SetSelectedSensorPosition(0,0,0);
        holdPosition = turretMotor.getSelectedSensorPosition(0);
    }

    public double getPositionInTicks() {
        return turretMotor.getSelectedSensorPosition();
    }

    public double getPositionInDegrees() {
        return (turretMotor.getSelectedSensorPosition() / TURRET_ENCODER_TICS_PER_DEGREE);
    }

    public void rotateToAngle(double angle) {
        double position = (angle*TURRET_ENCODER_TICS_PER_DEGREE);
        setHoldPosition(position);
    }

    public void rotateBySpeed(double speed) {
        turretMotor.set(ControlMode.PercentOutput, speed);
    }

    public void rotateToPosition(double position) {
        setHoldPosition(position);
    }

    public void enableHoldPosition(boolean enable) {
        enableHoldPosition = enable;
    }

    // Privates
    private void setHoldPosition(double position) {
        // Check that turret is within operating parameters
        if(position < TURRET_MIN_ENCODER){
            position = TURRET_MIN_ENCODER;
        }
        else if(position > TURRET_MAX_ENCODER){
            position = TURRET_MAX_ENCODER;
        }
        holdPosition = position;
    }

    private final TalonSRX turretMotor = new TalonSRX(MOTOR_TURRET);

    private double holdPosition = turretMotor.getSelectedSensorPosition();
    private boolean enableHoldPosition = true;
}
