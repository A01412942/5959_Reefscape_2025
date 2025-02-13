package com.team5959;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
//   6 · rearRightRotation   //FIXME check CAN ID's out
//   7 · rearRightDrive
//   8 · rearLeftRotation
//   9 · rearLeftDrive

//   16 · elevatorRight
//   17 · elevatorLeft

//   18 · armIntakeMotor
//   19 · algaeIntakeMotor

//   20 · coralIntakeMotorRight
//   21 · coralIntakeMotorLeft

//   22 · miniArmMotor
//  =================
public class Constants {
    public static class ControllerConstants{
        public static final int kDriverControllerPort = 0; //port for driver's control
        public static final int kOperatorControllerPort = 1; //port for the mecanism's controller.
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
      public static final int elevatorRightID = 16;
      public static final int elevatorLeftID= 17;
      public static final boolean elevatorRightInverted = false;
      public static final boolean elevatorLeftInverted = true;
        
      //PID VALUES
      public static final double KP_ELEVATOR = 0.035;
      public static final double KI_ELEVATOR = 0.000; //FIXME adjust pid values for elevator
      public static final double KD_ELEVATOR = 0.0004;
      //POSITION VALUES (in encoder units)
      public static final double elevatorStartingPosition = 0.00;
      public static final double elevatorL1Position = 20.00;
      public static final double elevatorL2Position = 40.00;
      public static final double elevatorL3Position = 60.00;
    }

    //ARM CONSTANTS
    public static class ArmConstants{
      //ID's
      public static final int armMotorID = 18;
      public static final int absoluteEncoderPort = 5;
      //PID VALUES
      public static final double KP_ARM = 0.035;
      public static final double KI_ARM = 0.000; //FIXME adjust pid values for arm
      public static final double KD_ARM = 0.0004;

      //POSITION VALUES (in encoder units)
      public static final double armIntakeInStartingPosition = 0.00;
      public static final double armIntakeOutPosition = 20.00;
    }

    //INTAKE CONSTANTS
    public static class IntakeConstants{ 
      //ID's
      public static final int coralIntakeMotorRightID = 20;
      public static final int coralIntakeMotorLeftID = 21;
      public static final int algaeIntakeMotorID = 19;
      //INVERTED
      public static final boolean coralIntakeMotorRightInverted = false;
      public static final boolean coralIntakeMotorLeftInverted = true;
            
      
    }
    
    public static class MiniArmConstants{
      //ID's
      public static final int miniArmMotorID = 22;
      //PID VALUES
      public static final double KP_MINI_ARM = 0.035;
      public static final double KI_MINI_ARM = 0.000; //FIXME adjust pid values for mini arm
      public static final double KD_MINI_ARM = 0.0004;
      //POSITION VALUES (in encoder units)
      public static final double miniArmStartingPosition = 0.00;
      public static final double miniArmDropAlgaePosition = 20.00;
      public static final double miniArmDownPosition = 40.00;

    }
}
