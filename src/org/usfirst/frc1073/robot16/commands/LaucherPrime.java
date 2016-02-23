// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


//NOTE: THIS COMMAND NEEDS BUTTONMAPPING.

package org.usfirst.frc1073.robot16.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot16.Robot;
import org.usfirst.frc1073.robot16.subsystems.Laucher.laucherState;

/**
 *
 */
public class LaucherPrime extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private final double CIMRevPerInch = 137.5;
    private final double TravelLength = 4.0;
    private final int AtBack = (int)(TravelLength * CIMRevPerInch);
    private final int AtFront = 0;
    private final double TravelTimeShort = 6.8;
    private final double TravelTimeLong = TravelTimeShort * 2;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public LaucherPrime() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.laucher);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!Robot.laucher.getState().equals(laucherState.readyToLauch)){
    		//if (Robot.laucher.isFrontLimitHit() || Robot.laucher.getEncoderValue() <= AtFront){
    		if (Robot.laucher.isFrontLimitHit()){
    			Robot.laucher.closeClam();
    			Robot.laucher.setClosedForwards();
    			setTimeout(TravelTimeShort);
    		}
    		//else if (Robot.laucher.isBackLimitHit() || Robot.laucher.getEncoderValue() >= AtBack){
    		else if (Robot.laucher.isBackLimitHit()){
    			setTimeout(TravelTimeLong);
    			Robot.laucher.setEmptyBackwards();
    		}
    		else {
    			if (Robot.laucher.isClamed()){
    				setTimeout(TravelTimeShort);
    				Robot.laucher.setClosedMiddle();
    			}
    			else {
    				setTimeout(TravelTimeLong);
    				Robot.laucher.setEmptyMiddle();
    			}
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	SmartDashboard.putString("Launcher state", Robot.laucher.enumReturn(Robot.laucher.getState()));
    	
    	switch(Robot.laucher.getState()){
    	case closedForwards: 
    		Robot.laucher.driveLaucherMotorBackwards();
    		Robot.laucher.setClosedMiddle();
    		break;
    	case closedMiddle:
    		Robot.laucher.driveLaucherMotorBackwards();
    		//if (Robot.laucher.getEncoderValue() >= AtBack || Robot.laucher.isBackLimitHit()){
    		if (Robot.laucher.isBackLimitHit()){
        		Robot.laucher.setReady();
    		}
    		break;
    	case readyToLauch:
    		Robot.laucher.stopLaucherMotor();
    		break;
    	case emptyBackwards:
    		Robot.laucher.driveLaucherMotorForwards();
    		Robot.laucher.setEmptyMiddle();
    		break;
    	case emptyMiddle:
    		Robot.laucher.driveLaucherMotorForwards();
    		//if (Robot.laucher.getEncoderValue() <= AtFront || Robot.laucher.isFrontLimitHit()){
    		if (Robot.laucher.isFrontLimitHit()){
    			Robot.laucher.closeClam();
        		Robot.laucher.setClosedForwards();
    		}
    		break;
    	default:
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if((Robot.laucher.isBackLimitHit() && Robot.laucher.isClamed())
    	if ((Robot.laucher.isBackLimitHit() || isTimedOut() || Robot.laucher.getState().equals(laucherState.readyToLauch))){
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.laucher.stopLaucherMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
