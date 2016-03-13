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
public class LauncherLock extends Command {
	
	private boolean isLocked = false;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public LauncherLock() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.launcher);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* if(!Robot.launcherElevation.isLocked()) {
			Robot.launcherElevation.unlockIt();
			
			isLocked = true;
		}
		else {
			if(Robot.launcherElevation.getRawAngleDegrees() > 172) {
    			Robot.launcherElevation.elevateLauncherUp(0.7);
    		}
    		else if(Robot.launcherElevation.getRawAngleDegrees() < 168) {
    			Robot.launcherElevation.elevateLauncherDown(0.7);
    		}
    		else {
    			Robot.launcherElevation.stopElevationMotor();
    			Robot.launcherElevation.lockIt();
    			
    			isLocked = true;
    		}
		} */
    	if(Robot.launcherElevation.isLocked()) {
    		Robot.launcherElevation.unlockIt();
    	}
    	else {
    			Robot.launcherElevation.lockIt();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isLocked;
    }

    // Called once after isFinished returns true
    protected void end() {
    	isLocked = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
