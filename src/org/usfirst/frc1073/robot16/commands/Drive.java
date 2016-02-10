// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot16.commands;

import edu.wpi.first.wpilibj.command.Command; 
import org.usfirst.frc1073.robot16.Robot;

/**
 *
 */
public class Drive extends Command implements PIDCommand {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	
	private double deadZone = Robot.deadZone;
	private double left;
	private double right;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Drive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	left = 0;
    	right = 0;
    }
    
    /************************************************
     * 
     * Method to move the robot using PID
     * 
     * @param left is the left side drive value
     * @param right is the right side drive value
     * 
     ************************************************/
    public void movePID(double left, double right) {
    	this.left = left;
    	this.right = right;
    }
    
    /*************************************************
     * 
     * Method to change direction of the joystick
     * based on angle
     * 
     * @param angle is the angle to the corresponding
     * joystick magnitude
     * 
     * @return a 1 or -1 to change direction of
     * magnitude of the joystick
     * 
     *************************************************/
    private double checkDirection(double angle){
    	if((angle <= -90 && angle >= -180) || (angle <= 180 && angle >= 90)) return -1;
    	else return 1;
    }
    
    /***********************************************
     * 
     * Method to adjust a dead zone on joystick
     * 
     * @param mag is the magnitude of the joystick
     * 
     * @return an adjusted value based on deadZone
     * set in Robot Main
     * 
     ***********************************************/
    public double checkDeadZone(double mag){
    	if(Math.abs(mag) <= deadZone) mag = 0;
    	return mag;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Sets left and right
    	left = Robot.oi.getdriverLeftStick().getMagnitude();
    	right = Robot.oi.getdriverRightStick().getMagnitude();
    	
    	// checks direction of magnitude
    	left *= checkDirection(Robot.oi.getdriverLeftStick().getDirectionDegrees());
    	right *= checkDirection(Robot.oi.getdriverRightStick().getDirectionDegrees());
    	
    	// checks dead zone
    	left = checkDeadZone(left);
    	right = checkDeadZone(right);
    	
    	// Inverse Check
    	if(Robot.inverseLeft) left *= -1;
    	if(Robot.inverseRight) right *= -1;
    	
    	// Uses regular tank drive if PID is disabled
    	if(!Robot.isPID) {
    		Robot.driveTrain.move(left, right);
    		left = 0;
    		right = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	public double getPIDSetpoint(int marker) {
		switch(marker) {
		case 0:
			return left;
		case 1:
			return right;
		default:
			return 0;
		}
	}

	@Override
	public boolean isPIDEnabled() {
		return Robot.isPID;
	}
}
