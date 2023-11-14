package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;

public class SubLimelightUpper extends SubLimelightBase {
    public SubLimelightUpper() {
        tblLimelight = NetworkTableInstance.getDefault().getTable("limelight-upper");
    }
}
