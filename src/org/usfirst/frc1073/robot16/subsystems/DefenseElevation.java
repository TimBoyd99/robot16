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
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DefenseElevation extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogPotentiometer defenseAngle = RobotMap.defenseElevationdefenseAngle;
    private final SpeedController elevationMotor = RobotMap.defenseElevationelevationMotor;
    private final DigitalInput elevationHighLimit = RobotMap.defenseElevationelevationHighLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private final double SPEED = 0.65;
    
    public DefenseElevation() {
    	elevationMotor.setInverted(Robot.invertDefenseDir);
    }
    
    public void driveDefenseUp() {
    	elevationMotor.set(SPEED);
    }
    
    public void driveDefenseDown() {
    	elevationMotor.set(-SPEED);
    }
    
    public void stopDefenseMotor() {
    	elevationMotor.set(0.0);
    }
    
    public double getAngleDegrees() {
    	return (defenseAngle.get() * 360.0);
    }
    
    public double getRawAngle() {
    	return defenseAngle.get();
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DefenseMoveElevation());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

