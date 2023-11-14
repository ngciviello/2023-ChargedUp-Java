package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.VerticalElevatorConstants.*;

public class SubVerticalElevator extends SubsystemBase {
    public SubVerticalElevator() {}

    @Override
    public void periodic() {
        if(enableHoldPosition) {
            vElevMotor.set(ControlMode.Position, holdPosition);
        }
        else {
            holdPosition = vElevMotor.getSelectedSensorPosition();
        }
    }

    public void configureMotor() {
        // clear the sticky faults on the motor
        vElevMotor.clearStickyFaults();

        // set whether the motor is inverted
        vElevMotor.setInverted(TalonFXInvertType.CounterClockwise);

        // configure the feedback sensor (encoder)
        vElevMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        // set the current limit
        vElevMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 5, 10, 100));

        // set up the soft limits
        vElevMotor.configForwardSoftLimitThreshold(topSoftLimit);
        vElevMotor.configReverseSoftLimitThreshold(bottomSoftLimit);
        vElevMotor.configForwardSoftLimitEnable(true);
        vElevMotor.configReverseSoftLimitEnable(true);

        // set the ramp
        vElevMotor.configClosedloopRamp(0.2);

        vElevMotor.configClosedLoopPeakOutput(0, 0.6, 0);
        vElevMotor.setNeutralMode(NeutralMode.Brake);
        vElevMotor.configNeutralDeadband(0.001);

        // Set the motor PID coefficients
        vElevMotor.config_kF(0, 0.0, 0);
        vElevMotor.config_kP(0, 0.01, 0);
        vElevMotor.config_kI(0, 0.0, 0);
        vElevMotor.config_kD(0, 0.0, 0);
    }

    public void moveBySpeed(double speed) {
        vElevMotor.set(TalonFXControlMode.PercentOutput, speed);
    }

    public void servoToPosition(double position) {
        setHoldPosition(position);
    }

    public double getPosition() {
        return vElevMotor.getSelectedSensorPosition();
    }

    public void resetPositionToZero() {
        vElevMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void resetPositionToTop() {
        vElevMotor.setSelectedSensorPosition(VERTICAL_ELEV_TOP_VALUE, 0, 0);
    }

    public void enableHoldPosition(boolean enable) {
        enableHoldPosition = enable;
    }

    public double getHoldPosition() {
        return holdPosition;
    }

    private void setHoldPosition(double position) {
        if(position < bottomSoftLimit){
            position = bottomSoftLimit;
        }
        else if(position > topSoftLimit){
            position = topSoftLimit;
        }
        holdPosition = position;
    }

    private final TalonFX vElevMotor = new TalonFX(MOTOR_VERTICAL_ELEVATOR);

    private final double topSoftLimit = VERTICAL_ELEV_MAX_LIMIT;
    private final double bottomSoftLimit = VERTICAL_ELEV_MIN_LIMIT;

    private double holdPosition = vElevMotor.getSelectedSensorPosition();
    private boolean enableHoldPosition = true;
}
