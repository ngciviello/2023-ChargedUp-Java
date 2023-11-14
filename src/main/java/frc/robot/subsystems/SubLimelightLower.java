package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class SubLimelightLower extends SubLimelightBase{
    public SubLimelightLower() {
        tblLimelight = NetworkTableInstance.getDefault().getTable("limelight-lower");
    }
}
