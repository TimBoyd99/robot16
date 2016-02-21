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
public class LaucherMoveElevation extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public LaucherMoveElevation() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.laucher);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
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
    public double checkDeadZone(double mag) {
    	if(Math.abs(mag) <= Robot.deadZone) return 0;
    	else return mag;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double mag = Robot.oi.getoperatorStick().getThrottle();
    	mag = checkDeadZone(mag);
    	
    	if(mag >= 0.3) Robot.laucher.elevateLaucherUp();
    	else if(mag <= -0.3) Robot.laucher.elevateLaucherDown();
    	else Robot.laucher.stopElevationMotor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.laucher.stopElevationMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
