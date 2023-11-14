package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.HorizontalElevatorConstants.*;

public class SubHorizontalElevator extends SubsystemBase {
    public SubHorizontalElevator() {}

    @Override
    public void periodic() {
        if(enableHoldPosition) {
            hElevatorController.setReference(holdPosition, CANSparkMax.ControlType.kPosition);
        }
        else {
            holdPosition = hElevatorEncoder.getPosition();
        }
    }

    public void configureMotor() {
        // set the soft limits
        hElevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float) maxSoftLimit);
        hElevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, (float) minSoftLimit);

        // set the PID coefficients
        hElevatorController.setP(0.015);
        hElevatorController.setI(0.0);
        hElevatorController.setD(0.0);
        hElevatorController.setFF(0.0);
        hElevatorController.setIZone(0.0);

        // set the output range
        hElevatorController.setOutputRange(-0.4, 0.4);
    }

    public double getPosition() {
        return hElevatorEncoder.getPosition();
    }

    public void servoToPosition(double position) {
        setHoldPosition(position);
        enableHoldPosition = true;
    }

    public void moveBySpeed(double speed) {
        hElevatorMotor.set(speed);
    }

    public void enableHoldPosition(boolean enable) {
        enableHoldPosition = enable;
    }

    public void setMaxExtension(double maxLimit) {
        if(maxLimit > HORIZONTAL_ELEV_MAX_LIMIT){
            maxLimit = HORIZONTAL_ELEV_MAX_LIMIT;
        }
        maxSoftLimit = maxLimit;
        hElevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float) maxSoftLimit);
    }

    // Privates
    private void setHoldPosition(double position) {
        if(position < minSoftLimit) {
            position = minSoftLimit;
        }
        else if(position > maxSoftLimit) {
            position = maxSoftLimit;
        }
        holdPosition = position;
    }

    private final CANSparkMax hElevatorMotor = new CANSparkMax(MOTOR_HORIZONTAL_ELEVATOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final SparkMaxPIDController hElevatorController = hElevatorMotor.getPIDController();
    private final SparkMaxRelativeEncoder hElevatorEncoder = (SparkMaxRelativeEncoder) hElevatorMotor.getEncoder();

    private double maxSoftLimit = HORIZONTAL_ELEV_MAX_LIMIT;
    private double minSoftLimit = HORIZONTAL_ELEV_MIN_LIMIT;

    private double holdPosition = hElevatorEncoder.getPosition();
    private boolean enableHoldPosition = true;
}
