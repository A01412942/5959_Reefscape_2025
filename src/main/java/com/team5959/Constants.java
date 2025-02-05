package com.team5959;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
//  =================
//       Red CAN
//  =================
//   0 · roboRIO
//   1 · PDH

//   2 · frontLeftRotation
//   3 · frontLeftDrive
//   4 · frontRightRotation
//   5 · frontRightDrive
//   6 · rearRightRotation
//   7 · rearRightDrive
//   8 · rearLeftRotation
//   9 · rearLeftDrive

//   16 · elevatorLeft
//   17 · elevatorRight
//  =================
public class Constants {
    public static class ControllerConstants{
        public static final int kDriverControllerPort = 0; //port for driver's control
        public static final int kMecanismsControllerPort = 1; //port for the mecanism's controller.
    }

    public static class SwerveConstants{
        //SDS L2 
        public static final boolean ROTATION_ENCODER_DIRECTION = false;

        //MEASUREMENTS
        public static final double WHEEL_DIAMETER = 4 * 2.5 / 100;
        public static final double TRACK_WIDTH = 0.7366;
        public static final double WHEEL_BASE = 0.7366;
      
        public static final double DRIVE_GEAR_RATIO = 8.14 / 1;
        public static final double ROTATION_GEAR_RATIO = 150 / 7;
        
        public static final double VOLTAGE = 7.2; //FIXME check this out

        // KINEMATICS FOR SWERVE DRIVE
        // ORDER IS ALWAYS FL, FR, RR, RL 
        //pos x is out in front, pos y is to the left 
        public static final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(
          
          // FRONT LEFT
          new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2),
          // FRONT RIGHT
          new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2),
          // REAR RIGHT
          new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2),
          // REAR LEFT
          new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2));
          
        //FRONT LEFT
        public static class FrontLeft {
            public static final int DRIVE_PORT = 3;
            public static final int ROTATION_PORT = 2;
            public static final int ABSOLUTE_ENCODER_PORT = 10;
            public static final double OFFSET = (-0.3986 * 90); //80.95; (-0.3986 * 90)//este ya está bien-143.87
            public static final boolean DRIVE_INVERTED = false; 
            public static final boolean ROTATION_INVERTED = true; 
      
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(DRIVE_PORT, ROTATION_PORT, ABSOLUTE_ENCODER_PORT, OFFSET, DRIVE_INVERTED, ROTATION_INVERTED);
          }

        //FRONT RIGHT
        public static class FrontRight {
            public static final int DRIVE_PORT = 5;
            public static final int ROTATION_PORT = 4;
            public static final int ABSOLUTE_ENCODER_PORT = 11;
            public static final double OFFSET = 20; //-25.31 - 2;(0.4321 * 55)
            public static final boolean DRIVE_INVERTED = true; 
            public static final boolean ROTATION_INVERTED = true; 
      
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(DRIVE_PORT, ROTATION_PORT, ABSOLUTE_ENCODER_PORT, OFFSET, DRIVE_INVERTED, ROTATION_INVERTED);
          }
        //REAR RIGHT
        public static class RearRight {
            public static final int DRIVE_PORT = 7;
            public static final int ROTATION_PORT = 6;
            public static final int ABSOLUTE_ENCODER_PORT = 12;
            public static final double OFFSET = -100; //(-0.2290 * )-28.92 + 6;
            public static final boolean DRIVE_INVERTED = true; 
            public static final boolean ROTATION_INVERTED = true; 
      
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(DRIVE_PORT, ROTATION_PORT, ABSOLUTE_ENCODER_PORT, OFFSET, DRIVE_INVERTED, ROTATION_INVERTED);
          }

        //REAR LEFT
        public static class RearLeft {
            public static final int DRIVE_PORT = 9;
            public static final int ROTATION_PORT = 8;
            public static final int ABSOLUTE_ENCODER_PORT = 13;
            public static final double OFFSET = 220; //(-0.0927 * 90)-101.60 + 6;
            public static final boolean DRIVE_INVERTED = false; 
            public static final boolean ROTATION_INVERTED = true; 
      
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(DRIVE_PORT, ROTATION_PORT, ABSOLUTE_ENCODER_PORT, OFFSET, DRIVE_INVERTED, ROTATION_INVERTED);
          }
        //CONVERSIONS FOR ENCODERS

        //velocity in meters per sec instead of RPM 
        public static final double DRIVE_ENCODER_POSITION_CONVERSION = ((2 * Math.PI * (WHEEL_DIAMETER/2))) / DRIVE_GEAR_RATIO; //drive enc rotation
        //velocity in meters instead of rotations 
        public static final double DRIVE_ENCODER_VELOCITY_CONVERSION = DRIVE_ENCODER_POSITION_CONVERSION / 60; //drive enc speed 
     
        //PID VALUES FOR TURNING MOTOR PID
        public static final double KP_TURNING = 0.0048;
        public static final double KI_TURNING = 0.0002;
        public static final double KD_TURNING = 0.0001;
    
        public static final double KP_AUTO_TRANSLATION = 0.285;
        public static final double KI_AUTO_TRANSLATION = 0.001;
        public static final double KD_AUTO_TRANSLATION = 0.0002;
        public static final double TRANSLATION_TOLLERANCE = 0.025; // tolerance in meters
    
        public static final double KP_AUTO_ROTATION = 0.0011;
        public static final double KI_AUTO_ROTATION = 0.000;
        public static final double KD_AUTO_ROTATION = 0.0005;
        public static final double ROTATION_TOLLERANCE = 1.5; // tolerance in dergrees
    
    
        //MAX
        public static final double MAX_SPEED = 3.6576; //12.0 ft/s 
        public static final double MAX_ROTATION = MAX_SPEED / Math.hypot(TRACK_WIDTH / 2.0, WHEEL_BASE / 2.0);
        }

        //ELEVATOR CONSTANTS
        public static class ElevatorConstants{
        public static final int elevatorRightID = 18;
        public static final int elevatorLeftID= 17;
        public static final boolean elevatorRightInverted = false;
        public static final boolean elevatorLeftInverted = true;
        
        //PID VALUES
        public static final double KP_ELEVATOR = 0.01;
        public static final double KI_ELEVATOR = 0.000; //FIXME adjust pid values for elevator
        public static final double KD_ELEVATOR = 0.00;
        //POSITION VALUES (in encoder units)
        public static final double elevatorStartingPosition = 0.00;
        public static final double elevatorPositionOne = 20.00;
        public static final double elevatorPositionTwo = 40.00;

        public static final int elevatorLimitSwitchUpID = 0;
        public static final int elevatorLimitSwitchDownID = 1;

      }

      //ARM CONSTANTS
      public static class ArmConstants{
        public static final int armMotorID = 16;

        //PID VALUES
        public static final double KP_ARM = 0.01;
        public static final double KI_ARM = 0.0008; //FIXME adjust pid values for arm
        public static final double KD_ARM = 0.008;
        //POSITION VALUES (in encoder units)
        public static final double armStartingPosition = 0.00;
        public static final double armScoringPosition = 20.00;
      }
    }
