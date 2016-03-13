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
public class LauncherMoveElevation extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public LauncherMoveElevation() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.launcherElevation);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
	/***********************************************
	 * 
	 * Method to adjust a dead zone on joystick
	 * 
	 * @param mag
	 *            is the magnitude of the joystick
	 * 
	 * @return an adjusted value based on deadZone set in Robot Main
	 * 
	 ***********************************************/
	public double checkDeadZone(double mag) {
		if (Math.abs(mag) <= Robot.deadZone)
			return 0;
		else
			return mag;
	}
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double mag = Robot.oi.getoperatorStick().getRawAxis(2);
		mag = checkDeadZone(mag);
		
		if(Robot.launcherElevation.isPID()) {
			if(mag > 0) {
				mag *= 40;
				Robot.launcherElevation.movePID(mag);
			}
			else if(mag < 0){
				mag *= 10;
				Robot.launcherElevation.movePID(mag);
			}
		}
		else {
			if (mag > 0 && !Robot.launcherElevation.isHighElevationHit()){
				Robot.launcherElevation.elevateLauncherUp(mag);
				Robot.collector.rollerPurge(0.21);
				
			}
			else if (mag < 0 && !Robot.launcherElevation.isLowElevationHit()) {
				Robot.launcherElevation.elevateLauncherDown(-mag);
				Robot.collector.rollerIn(0.21);
			}
			else {
				Robot.launcherElevation.stopElevationMotor();
				Robot.collector.rollerOff();
			}
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.launcherElevation.stopElevationMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
