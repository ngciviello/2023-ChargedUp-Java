// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.GlobalConstants.nodeTypes.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class GlobalConstants {
        public enum pieceTypes {ConePiece, CubePiece, NoPiece};
        public enum nodeTypes {ConeNode, CubeNode, HybridNode, NoNode};

        // Field Measurements (inches)
//    When facing/looking directly at center point of april tag ...
//      x = horizontal (left, right) distance from center point of april tag
//      y = depth (in, out) distance from center point of april tag
//      z = vertical (up, down) distance from center point of april tag
        public static class fieldpos { public double x, y, z; public nodeTypes nodeType;

            public fieldpos(double x, double y, double z, nodeTypes nodeType) {
            }
        };

        public static class game_state {
            public pieceTypes selectedPieceType = pieceTypes.ConePiece;
        };
    }
  public static final class ControllerConstants {

// Controllers
    public static final int DRIVER_CONTROLLER = 0;
    public static final int AUX_CONTROLLER = 1;
/*
   Game controller button and joystick addressing.
   Access in code by including Constants.h and using BUTTON_A
   */
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_L_BUMP = 5;
    public static final int BUTTON_R_BUMP = 6; // Drive gear sifting
    public static final int BUTTON_SELECT = 7;
    public static final int BUTTON_START = 8;
    public static final int BUTTON_L3 = 9;
    public static final int BUTTON_R3 = 10;

    public static final int AXIS_LX = 0; // Steer left/right
    public static final int AXIS_LY = 1;
    public static final int AXIS_L_TRIG = 2; // Forward driving
    public static final int AXIS_R_TRIG = 3; // Reverse driving
    public static final int AXIS_RX = 4;
    public static final int AXIS_RY = 5;

    public static final double JOYSTICK_AXIS_BUFFER = 0.055;

/*
   CONTROLS --

      Primary

         R Trigger - drive forward (toward turret)
         L Trigger - drive backward (toward battery)
         L Joystick X axis - drive rotation left/right
         L Joystick Y axis - 

         A - Select cube/cone
         B - 
         X -
         Y - pickup off floor

         R Joystick X axis -
         R Joystick Y axis -

         L Bumper - reset navX yaw
         R Bumper -

         Start -
         Back -

         DPAD -

      Auxiliary

         R Trigger -
         L Trigger -

         L Joystick X axis - turret rotation
         L Joystick Y axis - move vertical elevator
      
         A -
         B - Prep for grid piece placement (uses d-pad) (semi-redundant)
         X - grid piece placement (uses d-pad)
         Y -

         R Joystick X axis - move turret slowly
         R Joystick Y axis - move horizontal elevator

         L Bumper - wrist positions (uses d-pad)
         R Bumper - spin intake (uses d-pad)

         Start - pickup off of shelf
         Back - zero wrist, turret, and elevators (hold)

         DPAD - Node Selection (defined below in DPAD_VALUE_XXX constants)
*/

// aux controller D-pad values
    public static final int DPAD_VALUE_MIDDLE_CENTER = -1;
    public static final int DPAD_VALUE_MIDDLE_UP = 0;
    public static final int DPAD_VALUE_RIGHT_UP = 45;
    public static final int DPAD_VALUE_RIGHT_CENTER = 90;
    public static final int DPAD_VALUE_RIGHT_DOWN = 135;
    public static final int DPAD_VALUE_MIDDLE_DOWN = 180;
    public static final int DPAD_VALUE_LEFT_DOWN = 225;
    public static final int DPAD_VALUE_LEFT_CENTER = 270;
    public static final int DPAD_VALUE_LEFT_UP = 315;
  }
  public static final class DriveTrainConstants {
    // Motor Addressing
    public static final int MOTOR_RIGHT_MASTER = 1;
    public static final int MOTOR_LEFT_MASTER = 2;
    public static final int MOTOR_RIGHT_FOLLOWER = 3;
    public static final int MOTOR_LEFT_FOLLOWER = 4;

    // Maximum velocity in units/100ms
    public static final int VELOCITY_MAX = 17500; // 20000
    public static final int VELOCITY_SP_MAX_HG = 17500;  // 20000/ Maximum velocity in actual high gear
    public static final int VELOCITY_SP_MAX_LG = 17500;  // 20000 Maximum velocity in actual low gear
    public static final int VELOCITY_SP_MAX_LL = 4500;   // Maximum velocity in low low virtual gear

    // Continuous current limit for Talons in amps
    public static final int CONTINUOUS_CURRENT_LIMIT = 20;
    // Peak current limit for the Talons in amps
    public static final int PEAK_CURRENT_LIMIT = 30;
    // Peak current duration for Talons in ms
    public static final int DURATION_CURRENT_LIMIT = 500;

    // PID constants PID[0] Used for low speed right side
    // public static final double RIGHT_KF_0 = 0.042; // 0.0465; //0.13; // Kf = ((percent of output used for control)*1023) / (max encoder units)/100ms
    // public static final double RIGHT_KP_0 = 0.048;// 0.123; // Kp = ((percent of output used for control)*1023) / Error
    public static final double RIGHT_KF_0 = 0.06138;//0.046035; // 0.0465; //0.13; // Kf = ((percent of output used for control)*1023) / (max encoder units)/100ms
    public static final double RIGHT_KP_0 = 0.137179;// 0.123; // Kp = ((percent of output used for control)*1023) / Error
    public static final double RIGHT_KI_0 = 0.000;
    public static final double RIGHT_KD_0 = 0.0;

    // PID constants PID[0] Used for low speed right side
    // public static final double LEFT_KF_0 = 0.041; // 0.0465; //0.13; // Kf = ((percent of output used for control)*1023) / (max encoder units)/100ms
    // public static final double LEFT_KP_0 = 0.0455;// 0.123; // Kp = ((percent of output used for control)*1023) / Error
    public static final double LEFT_KF_0 = 0.06138; //0.046035; // 0.06138 0.0465; //0.13; // Kf = ((percent of output used for control)*1023) / (max encoder units)/100ms
    public static final double LEFT_KP_0 = 0.1292;// 0.123; // Kp = ((percent of output used for control)*1023) / Error
    public static final double LEFT_KI_0 = 0.000;
    public static final double LEFT_KD_0 = 0;

  // PID constants PID[1] used for high speed left side
    public static final double RIGHT_KF_1 = 0.041;
    public static final double RIGHT_KP_1 = 0.0;
    public static final double RIGHT_KI_1 = 0;
    public static final double RIGHT_KD_1 = 0;

  // PID constants PID[1] used for high speed left side
    public static final double LEFT_KF_1 = 0.041;
    public static final double LEFT_KP_1 = 0.0;
    public static final double LEFT_KI_1 = 0;
    public static final double LEFT_KD_1 = 0;

    // Marauder encode measurements ....
    //
    // DRIVE TRAIN:
    // we measured moving a straight line for 75 feet
    //  right side : 12,161 ticks per foot
    //  left side : 12,153 ticks per foot
    //
    //  right side: 1013.4 ticks per inch
    //  left side: 1012.75 ticks per inch
    //
    //  pitch: is negative with the back (battery end) lifted up

    public static final double DRIVE_TRAIN_TICKS_PER_INCH_RIGHT_SIDE = 1013.4;
    public static final double DRIVE_TRAIN_TICKS_PER_INCH_LEFT_SIDE = 1012.75;

    public static final double DRIVE_TRAIN_ROTATIONS_PER_INCH_LEFT_SIDE = DRIVE_TRAIN_TICKS_PER_INCH_LEFT_SIDE / 2048.0;
    public static final double DRIVE_TRAIN_ROTATIONS_PER_INCH_RIGHT_SIDE = DRIVE_TRAIN_TICKS_PER_INCH_RIGHT_SIDE / 2048.0;

    public static final int NAVX_CHARGED_UP_RAMP_PITCH = 11;

    // 0.75 to 1.3 with on floor depending upon drive train tipped forward or back
    public static final double NAVX_CHARGED_UP_ON_FLOOR_PITCH = 0.12;
  }
  public static final class IntakeWristConstants {
    /*
    // OLD POSITIONS FOR CLAW
    // Claw Wrist motor limits
    public static final double WRIST_CLAW_MAX_LIMIT = 24; // this is down hitting the floor when elevator is down, not pointing all the way down
    public static final double WRIST_CLAW_MIN_LIMIT = 0;
    
    // Claw Wrist positions
    public static final double WRIST_CLAW_FORWARD_POSITION = 16.5;
    public static final double WRIST_CLAW_DROP_FOR_PLACEMENT_POSITION = 16.5;
    public static final double WRIST_CLAW_UP_A_BIT_POSITION = 7;
    public static final double WRIST_CLAW_DOWN_FOR_PICKING_UP_POSITION = 22.5;
    */
    
    // NEW WRIST POSITIONS FOR INTAKE
    /*
    CUBE PICKUP: 22
    CONE UP-RIGHT PICKUP: 19
    CONE TIPPED-OVER PICKUP: 22.5
    TILTED DOWN: 27
    STRAIGHT DOWN: 34.5
    CONE PLACEMENT: 9
    CUBE PLACEMENT: 10
    */
    public static final double WRIST_CLAW_MAX_LIMIT = 34.5; // this is pointing all the way down
    public static final double WRIST_CLAW_MIN_LIMIT = 0;
    public static final double WRIST_CLAW_TILTED_DOWN = 27; // this is down hitting the floor when elevator is down, not pointing all the way down

    public static final double WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_SHELF = 13;
    public static final double WRIST_CLAW_PICKUP_UPRIGHT_CONE_OFF_FLOOR = 16.95;//17.1;
    public static final double WRIST_CLAW_PICKUP_TIPPED_OVER_CONE = 23.5;
    public static final double WRIST_CLAW_PLACE_CONE = 8;

    public static final double WRIST_CLAW_PICKUP_CUBE_OFF_SHELF = 15.0;
    public static final double WRIST_CLAW_PICKUP_CUBE_OFF_FLOOR = 18.625;//21.875;
    public static final double WRIST_CLAW_PLACE_CUBE = 9;


    // Claw Wrist motor address
    public static final int MOTOR_WRIST_CLAW = 12;
    public static final int MOTOR_INTAKE = 16;
  }
  public static final class HorizontalElevatorConstants {
      // Horizontal elevator limits
      public static final double HORIZONTAL_ELEV_MAX_LIMIT = 57;
      public static final double HORIZONTAL_ELEV_MIN_LIMIT = 0.25;

      // Horizontal elevator motor address
      public static final int MOTOR_HORIZONTAL_ELEVATOR = 9;
  }
  public static final class LimelightConstants {
      // angle up upper LimeLightUpper: -28.2209
      // angle of LimeLightLower: 23.8122
      // Height of middle of cube: 4.5 inches
      // Height of middle of cone: 6.5 inches
      // Height of Upper Pole: 43.75 inches
      // Height of Upper LimeLight: 51.5 inches
      // Height of Lower LimeLight: 8.25 inches

      // Limelight IDs:
      public static final String LIMELIGHT_LOWER_ID = "limelight-lower";
      public static final String LIMELIGHT_UPPER_ID = "limelight-upper";

// Limelight related Constants
      public static final int LL_PIPELINE_APRILTAG_1n8 = 1;  // This pipeline will have 1 and 8 aprilTags selected
      public static final int LL_PIPELINE_APRILTAG_2n7 = 2;  // This pipeline will have 2 and 7 aprilTags selected
      public static final int LL_PIPELINE_APRILTAG_3n6 = 3;  // This pipeline will have 3 and 6 aprilTags selected
      public static final int LL_PIPELINE_APRILTAG_4n5 = 4;  // This pipeline will have 4 and 5 aprilTags selected

      public static final int LL_PIPELINE_TARGET_UPPER_CONE = 5;
      public static final int LL_PIPELINE_TARGET_LOWER_CONE = 6;

      public static final int LL_PIPELINE_PURPLE_CUBE_NUMBER = 8;
      public static final int LL_PIPELINE_YELLOW_CONE_NUMBER = 9;
      // Limelight camera specifics
      public static final double LL_LIMELIGHT_UPPER_ANGLE = -25.8262;
      public static final double LL_LIMELIGHT_LOWER_ANGLE =  22.1038;
      public static final double LL_LIMELIGHT_UPPER_HEIGHT = 51.5;
      public static final double LL_LIMELIGHT_LOWER_HEIGHT = 8.25;  // Quick calculations says it should be 8.0 inches
      // Game piece specifics
      public static final double LL_YELLOW_CONE_CENTER_HEIGHT = 6.5;
      public static final double LL_PURPLE_CUBE_CENTER_HEIGHT = 4.5;
      // Game grid placement specifics
      public static final double TARGET_CONE_POLE_UPPER_HEIGHT = 43.875;
      public static final double TARGET_CONE_POLE_LOWER_HEIGHT = 24;

      // Substation shelf height
      public static final double LL_SUBSTATION_SHELF_HEIGHT = 37.375;


      // Target ID int values
      public static final int TID_YELLOW_CONE_ID = 1;
      public static final int TID_PURPLE_CUBE_ID = 2;
      public static final int TID_CONE_POLE_UPPER_ID = 3;
      public static final int TID_CONE_POLE_LOWER_ID = 4;

      // Target heights
      public static final double TARGET_APRILTAG_GRID_HEIGHT = 18.25;
      public static final double TARGET_APRILTAG_SUBSTATION_HEIGHT = 27.375;

      //  Grid has two cone nodes on left, two cone nodes on right, two shelves in center
      //   ________________________________     ^
      //  |          |         |           |    |
      //  |     O    |         |     O     |    |
      //  |          | ------- |           |   (y)
      //  |     O    |         |     O     |    |
      //  |          | ---t--- |           |    |    (z) is distance above ground
      //  |          |         |           |    v
      //  |________________________________|     <------- (x) ------>
      //
      // Constants for offset distances from april tag center
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_SHELF_MIDDLE = new GlobalConstants.fieldpos(0.0, 8.655, 5.25, CubeNode);
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_SHELF_TOP = new GlobalConstants.fieldpos (0.0, 23.035, 17.25, CubeNode);

      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_FLOOR_CENTER = new GlobalConstants.fieldpos ( 0.0,  7.14, -18.24, HybridNode );
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_FLOOR_RIGHT = new GlobalConstants.fieldpos ( 16.75, 7.14, -18.24, HybridNode );
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_FLOOR_LEFT = new GlobalConstants.fieldpos ( -16.75, 7.14, -18.24, HybridNode );

      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_LOWER = new GlobalConstants.fieldpos ( 18.875, 8.42, 16.75, ConeNode );
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_RIGHT_UPPER = new GlobalConstants.fieldpos ( 18.875, 25.45, 27.75, ConeNode );
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_LOWER = new GlobalConstants.fieldpos ( -18.875, 8.42, 16.75, ConeNode );
      public static final GlobalConstants.fieldpos FIELD_POS_OFFSET_FROM_TAG_CONE_NODE_LEFT_UPPER = new GlobalConstants.fieldpos ( -18.875, 25.45, 27.75, ConeNode );

      // Cone node from AprilTag 22, 8.5, 18.875
      //                                  27.875

  }
  public static final class TurretConstants {
      //
      //  TURRET:
      //    180-degree rotation = 2768 encoder ticks
      //    1 degree = 15.4 encoder ticks
      //
      // Turret setup and addressing
      public static final int MOTOR_TURRET = 7;
      public static final int TURRET_MIN_ENCODER = -3000; // clockwise (if looking down on robot)
      public static final int TURRET_MAX_ENCODER = 3000;
      public static final int TURRET_KF_0 = 0;
      public static final int TURRET_KP_0 = 2;
      public static final int TURRET_KI_0 = 0;
      public static final int TURRET_KD_0 = 100;
      public static final double TURRET_ENCODER_TICS_PER_DEGREE = 15.37778;
      public static final int TURRET_HOME_POSITION = 0;
      public static final int TURRET_POSITION_HOLD_TOLERANCE = 15;
  }
  public static final class VerticalElevatorConstants {
      // Vertical Elevator limits
      public static final double VERTICAL_ELEV_TOP_VALUE = 96000;
      public static final double VERTICAL_ELEV_MAX_LIMIT = 94000;
      public static final double VERTICAL_ELEV_MIN_LIMIT = 1200;

      // Vertical Elevator motor address
      public static final int MOTOR_VERTICAL_ELEVATOR = 8;

      public static final double VERTICAL_ELEV_POS_CONE_NODE_LOWER = 50000;
      public static final double VERTICAL_ELEV_POS_CONE_NODE_UPPER = 90000;
      public static final double VERTICAL_ELEV_POS_CUBE_NODE_LOWER = 53000;
      public static final double VERTICAL_ELEV_POS_CUBE_NODE_UPPER = 84000;
      public static final double VERTICAL_ELEV_POS_HYBRID_NODE = VERTICAL_ELEV_MIN_LIMIT;
      public static final double VERTICAL_ELEV_POS_SUBSTATION_SHELF = 80000; //90000; //83700;
      public static final double VERTICAL_ELEV_POS_SWITCH_LIMELIGHT_THRESHOLD = 20000;
  }
}
