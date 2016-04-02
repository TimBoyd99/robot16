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
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class LauncherElevation extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon elevationMotor = RobotMap.launcherElevationelevationMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //private static final double RAMP_RATE = 0;
    
    public LauncherElevation() {
    	elevationMotor.setInverted(Robot.invertLauncherElevation);
    	
    	setupPID();
    }
    
    private void setupPID() {
    	elevationMotor.changeControlMode(TalonControlMode.Position);
    	
    	elevationMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
    	elevationMotor.configPotentiometerTurns(360);
    	
    	elevationMotor.configNominalOutputVoltage(+0f, -0f);
    	elevationMotor.configPeakOutputVoltage(+12f, -12f);
    	
    	elevationMotor.setAllowableClosedLoopErr(0);
    	
    	elevationMotor.setPID(32.0, 0.0, 0.0);
    	
    	ZeroOutPosition();
    }
    
    public void setBasic() {
    	elevationMotor.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void movePID(double angle) {
    	elevationMotor.set(-angle);
    }
    
    public void moveBasic(double speed) {
    	
    	if(elevationMotor.getControlMode() != TalonControlMode.PercentVbus) setBasic();
    	
    	elevationMotor.set(speed);
    }
    
    public double getRawPot() {
    	return elevationMotor.getPosition();
    }
    
    public boolean isPID() {
    	return elevationMotor.getControlMode() == TalonControlMode.Position;
    }
    
    public void ZeroOutPosition() {
    	elevationMotor.setPosition(0);
    }
    
    public double convertToDegrees(double rawPotReading) {
    	return rawPotReading * (360.0 / 1024.0);
    }
    
    public double convertFromDegrees(double degrees) {
    	return degrees * (1024.0 / 360.0);
    }
    
    public double getAngle() {
    	return convertToDegrees(getRawPot());
    }
    
    public double getRawSetpoint() {
    	return elevationMotor.getSetpoint();
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

