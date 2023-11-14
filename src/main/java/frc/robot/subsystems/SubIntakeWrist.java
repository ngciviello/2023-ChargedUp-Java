package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IntakeWristConstants.*;

public class SubIntakeWrist extends SubsystemBase {
    public SubIntakeWrist() {}

    //@Override
    public void periodic() {
        if(enableWristHoldPosition) {
            clawWristController.setReference(wristHoldPosition, CANSparkMax.ControlType.kPosition);
        }
    }

    public void configureMotors() {
        // ** Set up the wrist motor **
        // set up the limits
        clawWristMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, (float) wristMinLimit);
        clawWristMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float) wristMaxLimit);
        clawWristMotor.setSmartCurrentLimit(20);
        clawWristMotor.setClosedLoopRampRate(0.09);

        // set PID coefficients
        clawWristController.setP(0.045);
        clawWristController.setI(0.000004);
        clawWristController.setD(2.0);
        clawWristController.setIZone(0.0);
        clawWristController.setFF(0.0);
        clawWristController.setOutputRange(-1.0, 1.0);

        // ** Set up the intake motor **

        intakeMotor.clearStickyFaults();
        // Select the feedback device (encoder) for the motor
        intakeMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        intakeMotor.setInverted(TalonFXInvertType.CounterClockwise);

        // set PID coefficients
        intakeMotor.config_kF(0, 0.0, 0);
        intakeMotor.config_kP(0, 0.01, 0);
        intakeMotor.config_kI(0, 0.0, 0);
        intakeMotor.config_kD(0, 0.0, 0);
        intakeMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.configNeutralDeadband(0.001);
        // set output limit
        intakeMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 12, 100));
    }

    public double getWristPosition() {
        return clawWristEncoder.getPosition();
    }

    public void wristServoToPosition(double position) {
        if(position > wristMaxLimit) {
            position = wristMaxLimit;
        }
        else if(position < wristMinLimit) {
            position = wristMinLimit;
        }
        setWristHoldPosition(position);
    }

    public void setWristHoldPosition(double position) {
        if(position > wristMaxLimit) {
            position = wristMaxLimit;
        }
        else if(position < wristMinLimit) {
            position = wristMinLimit;
        }
        wristHoldPosition = position;
    }

    public void enableWristHoldPosition(boolean enable) {
        enableWristHoldPosition = enable;
    }

    public void intakeSpin(double speed) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public double getIntakeOutput() {
        return intakeMotor.getSupplyCurrent();
    }

    // Privates

    // Set up the spark max for the wrist motor
    private CANSparkMax clawWristMotor = new CANSparkMax(MOTOR_WRIST_CLAW, CANSparkMaxLowLevel.MotorType.kBrushless);
    private SparkMaxPIDController clawWristController = clawWristMotor.getPIDController();
    private SparkMaxRelativeEncoder clawWristEncoder = (SparkMaxRelativeEncoder) clawWristMotor.getEncoder();

    // Set up the falcon for the intake
    private TalonFX intakeMotor = new TalonFX(MOTOR_INTAKE);

    private double wristMinLimit = WRIST_CLAW_MIN_LIMIT;
    private double wristMaxLimit = WRIST_CLAW_MAX_LIMIT;

    private double wristHoldPosition = clawWristEncoder.getPosition();

    private boolean enableWristHoldPosition = true;
}
