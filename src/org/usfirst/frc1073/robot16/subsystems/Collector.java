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

import org.usfirst.frc1073.robot16.Robot;
import org.usfirst.frc1073.robot16.RobotMap;
import org.usfirst.frc1073.robot16.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Collector extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController rollerMotor = RobotMap.collectorrollerMotor;
    private final DigitalInput ballSensor = RobotMap.collectorballSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
	/**************************
	 * 
	 * Method to role into the
	 * robot
	 * 
	 **************************/
	public void rollerIn(double speed) {
		if(Robot.invertRoller) rollerMotor.set(-speed);
		else rollerMotor.set(speed);
	}
	
	/**************************
	 * 
	 * Method to purge the ball
	 * out of the robot
	 * 
	 **************************/
	public void rollerPurge(double speed) {
		if(Robot.invertRoller) rollerMotor.set(speed);
		else rollerMotor.set(-speed);
	}
	
	/*****************************
	 * 
	 * Method to turn rollers off
	 * 
	 *****************************/
	public void rollerOff() {
		rollerMotor.set(0);
	}
	
	/****************************************
	 * 
	 * Returns when a ball is in the robot.
	 * 
	 * @return true if ball is in the robot
	 *	else false
	 *
	 ****************************************/
	public boolean isLoaded() {
		return ballSensor.get();
	}
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new CollectorMoveRoller());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

