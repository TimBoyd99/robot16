package org.usfirst.frc1073.robot16.navClasses.gameObjects;

import org.usfirst.frc1073.robot16.navClasses.Core.RegionManager;

/**
 * The class that represents the robot on the map
 * @author Matt
 */

public class RobotNav {
	private int[][] robotArray; //Holds the space the robot occupies
	private int[][] mapArray; //Holds the imported map array
	
	private double robotXWidth; //ft.decInch
	private double robotYWidth; //ft.decInch
	
	public RobotNav(double xStart, double yStart, double robotXWidth, double robotYWidth, Map gameMap) {
		this.robotXWidth = robotXWidth; //TODO May be unnecessary...
		this.robotYWidth = robotYWidth; //TODO May be unnecessary...
				
		robotArray = RegionManager.rectangleBuilder(xStart, yStart, robotXWidth, robotYWidth, 1); //Return the array for the robot
		mapArray = gameMap.getMapArray(); //Return the array for the map
		
		for(int x = (int) (10 * xStart); x < (10 * (xStart + robotXWidth)); x++){
			for(int y = (int) (10 * yStart); y < (10 * (yStart + robotYWidth)); y++){
				mapArray[x][y] = 1;
			}
		}
	}
	
	public int[][] getMapRobotArray(){
		//return robotArray;
		return mapArray;
	}
	
	
}
