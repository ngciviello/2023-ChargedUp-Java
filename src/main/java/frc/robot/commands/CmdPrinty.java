package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;
import frc.robot.subsystems.SubLimelightUpper;

public class CmdPrinty extends CommandBase {

    private final SubDriveTrain subDriveTrain;

    public CmdPrinty(SubDriveTrain subDriveTrain) {
        this.subDriveTrain = subDriveTrain;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        System.out.println("NavX Yaw: " + subDriveTrain.getYaw());
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
