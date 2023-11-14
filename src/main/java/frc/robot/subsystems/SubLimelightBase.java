package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static java.lang.Math.atan;
import static java.lang.Math.tan;

public class SubLimelightBase extends SubsystemBase {

    public SubLimelightBase() {}

    @Override
    public void periodic() {}

    public boolean getTarget() {
        return tblLimelight.getEntry("tv").getBoolean(false);
    }

    public double getHorizontalOffset() {
        return tblLimelight.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffset() {
        return tblLimelight.getEntry("ty").getDouble(0.0);
    }

    public double getTargetArea() {
        return tblLimelight.getEntry("ta").getDouble(0.0);
    }

    public double getTargetLatency() {
        return tblLimelight.getEntry("tl").getDouble(0.0);
    }

    public double getShortestSideOfTargetBoundingBox() {
        return tblLimelight.getEntry("tshort").getDouble(0.0);
    }

    public double getLongestSideOfTargetBoundingBox() {
        return tblLimelight.getEntry("tlong").getDouble(0.0);
    }

    public double getHorizontalSideLengthOfTargetBoundingBox() {
        return tblLimelight.getEntry("thor").getDouble(0.0);
    }

    public double getVerticalSideLengthOfTargetBoundingBox() {
        return tblLimelight.getEntry("tvert").getDouble(0.0);
    }

    public double getActivePipeLine() {
        return tblLimelight.getEntry("getPipe").getDouble(0);
    }

    public void setCropValues(double X0, double X1, double Y0, double Y1) {
        cropValues[0] = X0;
        cropValues[1] = X1;
        cropValues[2] = Y0;
        cropValues[3] = Y1;
        tblLimelight.getEntry("crop").setDoubleArray(cropValues);
    }

    public int getAprilTagID() {
        return (int) tblLimelight.getEntry("tid").getInteger(0);
    }

    public double[] getBotPosTargetSpace() {
        return tblLimelight.getEntry("botpose_targetspace").getDoubleArray(new double[6]);
    }

    public double[] getTargetPosRobotSpace() {
        return tblLimelight.getEntry("targetpose_robotspace").getDoubleArray(new double[6]);
    }

    public double[] getTargetPoseCameraSpace() {
        return tblLimelight.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
    }

    public double[] getCameraPosTargetSpace() {
        return tblLimelight.getEntry("camerapose_targetspace").getDoubleArray(new double[6]);
    }

    public double getDistanceToTarget(double h1_heightOfCamera, double h2_heightOfCenterOfTarget, double a1_angleOfCamera) {
        double d = 0.0;

        double a2 = tblLimelight.getEntry("ty").getDouble(0);

        d = (h2_heightOfCenterOfTarget-h1_heightOfCamera) / (tan((a1_angleOfCamera + a2)*3.1416/180));

        return d;
    }

    public double getSkew() {
        return tblLimelight.getEntry("ts").getDouble(0.0);
    }

    // Give distance and heights in the same unit
    public double getCameraMountAngle(double distance, double limelightHeight, double targetHeight) {
        double a1 = 0.0; // degrees
        double a2 = tblLimelight.getEntry("ty").getDouble(0);

        a1 = (atan((targetHeight-limelightHeight)/distance)-(a2*3.1416/180))*180/3.1416;

        return a1;
    }

    /**
     Set the LimeLightBase LED state.

     0 - Use the LED Mode set in the current pipeline
     1 - Force LEDS off
     2 - Force LEDs to blink
     3 - Force LEDs on

     @param mode the mode to set for the LEDs
     */
    public void setLEDState(int mode) {
        tblLimelight.getEntry("ledMode").setNumber(mode);
    }

    /** Set the LimeLightBase camera mode.

     0 - Use Vision processing
     1 - Use Limeligt as Driver Camera (Increases exposure, disables vision processing)

     @param mode the mode to set for the Camera
     */
    public void setCameraMode(int mode) {
        tblLimelight.getEntry("camMode").setNumber(mode);
    }

    /** Select the vision pipline to use for targeting.

     0 - 9 Vision pipeline to use

     @param pipeline the pipeline to select for the vision targeting
     */
    public void selectPipeline(int pipeline) {
        tblLimelight.getEntry("pipeline").setNumber(pipeline);
    }

    /** Select LimeLightBase's streaming mode.

     0 - Standard Side-by-Side stream if a webcam is attached to LimeLightBase.
     1 - PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
     2 - PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream

     @param mode the streaming mode selected
     */
    public void selectStreamMode(int mode) {
        tblLimelight.getEntry("stream").setNumber(mode);

    }

    /** Select LimeLightBase's snapshot mode.

     0 - Stop taking snapshots.
     1 - Take two snapshots per second

     @param mode the streaming mode selected
     */
    public void selectSnapshotMode(int mode) {
        tblLimelight.getEntry("snapshot").setNumber(mode);
    }

    protected NetworkTable tblLimelight;

    protected double[] cropValues = new double[4];
}
