// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot16.subsystems;

import org.usfirst.frc1073.robot16.subsystems.PIDSubsystem;
import org.usfirst.frc1073.robot16.Robot;
import org.usfirst.frc1073.robot16.RobotMap;
import org.usfirst.frc1073.robot16.commands.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem implements PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftMotor1 = RobotMap.driveTrainleftMotor1;
    private final SpeedController leftMotor2 = RobotMap.driveTrainleftMotor2;
    private final SpeedController rightMotor1 = RobotMap.driveTrainrightMotor1;
    private final SpeedController rightMotor2 = RobotMap.driveTrainrightMotor2;
    private final Encoder leftSideEncoder = RobotMap.driveTrainleftSideEncoder;
    private final Encoder rightSideEncoder = RobotMap.driveTrainrightSideEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    
    private Drive drive = new Drive(); //drive command
    
    // fix spelling of this, consider changing name to inchesPerPulse if it generates inches. 
    private static final double distancePurPulse = 0.017453; // Constant for the distance traveled per pulse
    
    private double robotTopSpeed = Robot.robotTopSpeed; // Constant for the top speed of the robot in FPS
    double cubicConstant = Robot.cubicScale;
    
    // Sync groups information
    private byte leftSyncGroup = 0;
    private byte rightSyncGroup = 1;
    
    /****************************
     * 
     * Constructs DriveTrain
     * sets the distancePurPulse
     * 
     ****************************/
    public DriveTrain() {
    	leftSideEncoder.setDistancePerPulse(distancePurPulse);
    	rightSideEncoder.setDistancePerPulse(distancePurPulse);
    }
    
    /***************************
     * 
     * Returns the rate of the
     * left side in FPS
     * 
     ***************************/
    public double getLeftRateFps() {
    	return leftSideEncoder.getRate() / 12;		// SC: 12 because getRate returns inches/sec because of distancePurPulse?
    }
    
    /***************************
     * 
     * Returns the rate of the
     * right side in FPS
     * 
     ***************************/
    public double getRightRateFps() {
    	return rightSideEncoder.getRate() / 12;
    }
    
    /***************************************
     * 
     * Method to scale a joystick value.
     *    @param arg is the joystick value
     *
     ***************************************/
    private double cubicScale(double arg) {
    	return (cubicConstant*arg + (1 - cubicConstant) * Math.pow((double)arg, 3));
    }
    
    /*********************************************
     * 
     * Method to drive the robot.
     *	  @param left is the speed of left side
     * 	  @param right is the speed of right side
     * Values are between -1 and 1
     * 
     *********************************************/
    public void move(double left, double right){
    	
    	// Checks for cubic scaling
    	if(Robot.isCubic) {
    		left = cubicScale(left);
        	right = cubicScale(right);
    	}
    	
    	leftMotor1.set(left, leftSyncGroup);
    	leftMotor2.set(left, leftSyncGroup);
    	rightMotor1.set(right, rightSyncGroup);
    	rightMotor2.set(right, rightSyncGroup);
    }
    
    // SC: maybe rename these xyzEncoderDistanceInches to make it clear
    public double leftEncoderDistance(){
    	return leftSideEncoder.getDistance();
    }
    
    public double rightEncoderDistance(){
    	return rightSideEncoder.getDistance();  			
    }
    
    // SC: maybe rename these xyzEncoderInchesPerSec if that's correct, or put a comment here saying it returns inches/sec
    public double leftEncoderRate(){
    	return leftSideEncoder.getRate();
    }
    
    public double rightEncoderRate(){
    	return rightSideEncoder.getRate();
    }
    
	public Drive getDriveCommand() {
		return drive;
	}
    
	/****************************************
	 * 
	 * A PID accessory method to get the
	 * encoder information
	 * 
	 * @param marker is the corresponding 
	 * side you want
	 * 
	 ****************************************/
	@Override
	public double getPIDSource(int marker) {
		if(Robot.isPID) {
			switch(marker) {
			case 0:
				return getLeftRateFps() / robotTopSpeed;
			case 1:
				return getRightRateFps() / robotTopSpeed;
			default:
				return 0;
			}
		}
		else return 0;
	}
	
	/*******************************************
	 * 
	 * A PID method that sets the motor values
	 * 
	 * @param marker is the corresponding 
	 * side you want
	 * 
	 *******************************************/
	@Override
	public void setPIDOutput(double output, int marker) {
		if(Robot.isPID) {
			switch(marker) {
			case 0:
				leftMotor1.set(output, leftSyncGroup);
				leftMotor2.set(output, leftSyncGroup);
			break;
			case 1:
				rightMotor1.set(output, rightSyncGroup);
				rightMotor2.set(output, rightSyncGroup);
			break;
			}
		}
	}
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(drive);
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
	
}

