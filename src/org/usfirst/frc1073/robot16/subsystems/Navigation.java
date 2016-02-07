
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
import org.usfirst.frc1073.robot16.commands.NavManager;
import org.usfirst.frc1073.robot16.navClasses.Map;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Navigation extends Subsystem {

	private final double mapLengthX = 0.0; // TODO Should be constant
	private final double mapLengthY = 0.0; // TODO Should be constant
	private double robotStartX = 0.0;
	private double robotStartY = 0.0;
	Map gameMap;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * 
	 * Makes the map object that holds game regions, the first time
	 * 
	 * @param robotStartX - 1/10 foot
	 * @param robotStartY - 1/10 foot
	 */
	public void initializeMap(double robotStartX, double robotStartY) {
		//Calls the map constructor, generates robot and defenses
		gameMap = new Map(mapLengthX, mapLengthY, robotStartX, robotStartY);

		//Saves the starting value of the robot
		robotStartX = 0.0;
		robotStartY = 0.0;
		
		gameMap.initializeMap(robotStartX, robotStartY);
	}

	/**
	 * Changes the map to reflect the robot's change in position
	 * 
	 * @param xDistance
	 * @param yDistance
	 */
	public void updateMap(double xDistance, double yDistance) {
		gameMap.updateRobotPosition(xDistance, yDistance);

	}
	/**
	 * Updates the map with the distance traveled
	 * NEEDS: local variables to keep track of last actual double
	 * position and the rounded inch position sent to map
	 */
	public void updateWithDistanceTraveled(){
		//TODO DriveTrain Encoder calls
		Robot.driveTrain.leftEncoderDistance();
		Robot.driveTrain.rightEncoderDistance();
		//updateMap();
		
	}
	
	

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new NavManager());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
