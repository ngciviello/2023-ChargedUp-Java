package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubLimelight extends SubsystemBase {
    private NetworkTable tblLimelight;

    private double[] cropValues = new double[4];

    public SubLimelight() {
        tblLimelight = NetworkTableInstance.getDefault().getTable(Constants.LimelightConstants.LIMELIGHT_LOWER_ID);
    }

    @Override
    public void periodic() {}

    /*
    * Our code for reading/writing to a limelight's networktable, for more documentation see:
    * https://docs.limelightvision.io/docs/docs-limelight/apis/complete-networktables-api
    */

    // Sets the id for network table to look for, must match the id of the limelight you want to use
    public void setLimelightId(String id) {
        tblLimelight = NetworkTableInstance.getDefault().getTable(id);
    }

    // Sets the Limelight's led mode:
    // 0 = led mode set in pipeline
    // 1 = force off
    // 2 = force blink
    // 3 = force on
    public void setLedMode(int mode) {
        tblLimelight.getEntry("ledMode").setNumber(mode);
    }

    // Sets the Limelight's operation mode:
    // 0 = vision processing
    // 1 = driver camera (no vision processing
    public void setCamMode(int mode) {
        tblLimelight.getEntry("camMode").setNumber(mode);
    }

    // Sets the pipeline 0-9
    public void setPipeline(int pipeline) {
        tblLimelight.getEntry("pipeline").setNumber(pipeline);
    }

    // Sets the stream mode for a secondary camera plugged into the limelight
    // 0 = side by side video feed
    // 1 = secondary camera is in lower left of main camera
    // 2 = main camera is in lower left of secondary camera
    public void setStreamMode(int mode) {
        tblLimelight.getEntry("stream").setNumber(mode);
    }

    // Set the crop, must be a double array with 4 entries [x0, x1, y0, y1]
    public void setCrop(double[] crop) {
        tblLimelight.getEntry("crop").setDoubleArray(crop);
    }

    // Set the limelight's position in robot space, must be a double array with 6 entries [x, y, z, roll, pitch, yaw]
    public void setLimelightPoseRobotSpace(double[] pose) {
        tblLimelight.getEntry("camerapose_robotspace_set").setDoubleArray(pose);
    }

    // Returns true if the limelight sees a target
    public boolean getTarget() {
        return tblLimelight.getEntry("tv").getDouble(0) == 1;
    }

    // Returns the horizontal offset of the target from the crosshair in degrees
    public double getHorizontalOffset() {
        return tblLimelight.getEntry("tx").getDouble(0);
    }

    // Returns the vertical offset of the target from the crosshair in degrees
    public double getVerticalOffset() {
        return tblLimelight.getEntry("ty").getDouble(0);
    }

    // Returns the percentage of the picture the target takes up
    public double getArea() {
        return tblLimelight.getEntry("ta").getDouble(0);
    }

    // Returns the length of the shortest side of the fitted bounding box in pixels
    public double getShortSide() {
        return tblLimelight.getEntry("tshort").getDouble(0);
    }

    // Returns the length of the longest side of the fitted bounding box in pixels
    public double getLongSide() {
        return tblLimelight.getEntry("tlong").getDouble(0);
    }

    // Returns the length of the horizontal side of the rough bounding box (0-320 pixels)
    public double getHorizontalSide() {
        return tblLimelight.getEntry("thor").getDouble(0);
    }

    // Returns the length of the vertical side of the rough bounding box (0-320 pixels)
    public double getVerticalSide() {
        return tblLimelight.getEntry("tvert").getDouble(0);
    }

    // Returns the active pipeline index of the limelight 0-9, returns -1 if it fails
    public double getPipeline() {
        return tblLimelight.getEntry("getpipe").getDouble(-1);
    }

    // Returns the robot position in field-space [x, y, z, roll, pitch, yaw]
    public double[] getBotPose() {
        return tblLimelight.getEntry("botpose").getDoubleArray(new double[6]);
    }

    // Returns the robot position in field-space (blue driverstation origin) [x, y, z, roll, pitch, yaw]
    public double[] getBotPoseBlue() {
        return tblLimelight.getEntry("botpose_wpiblue").getDoubleArray(new double[6]);
    }

    // Returns the robot position in field-space (red driverstation origin) [x, y, z, roll, pitch, yaw]
    public double[] getBotPoseRed() {
        return tblLimelight.getEntry("botpose_wpired").getDoubleArray(new double[6]);
    }

    // Returns the limelight position in target space [x, y, z, roll, pitch, yaw]
    public double[] getLimelightPoseTargetSpace() {
        return tblLimelight.getEntry("camerapose_targetspace").getDoubleArray(new double[6]);
    }

    // Returns the target position in limelight space [x, y, z, roll, pitch, yaw]
    public double[] getTargetPoseLimelightSpace() {
        return tblLimelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
    }

    // Returns the target position in robot space [x, y, z, roll, pitch, yaw]
    public double[] getTargetPoseRobotSpace() {
        return tblLimelight.getEntry("targetpose_robotspace").getDoubleArray(new double[6]);
    }

    // Returns the robot position in target space [x, y, z, roll, pitch, yaw]
    public double[] getRobotPoseTargetSpace() {
        return tblLimelight.getEntry("robotpose_targetspace").getDoubleArray(new double[6]);
    }

    // Returns the limelight position in robot space [x, y, z, roll, pitch, yaw]
    public double[] getLimelightPoseRobotSpace() {
        return tblLimelight.getEntry("camerapose_robotspace").getDoubleArray(new double[6]);
    }

    // Returns the ID of the targeted april tag
    public double getAprilTagId() {
        return tblLimelight.getEntry("tid").getDouble(0);
    }
}
