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
import org.usfirst.frc1073.robot16.commands.LaucherMoveElevation;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Laucher extends Subsystem implements PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController pullBackMotor = RobotMap.laucherpullBackMotor;
    private final SpeedController elevationMotor = RobotMap.laucherelevationMotor;
    private final AnalogPotentiometer elevationAngle = RobotMap.laucherelevationAngle;
    private final DigitalInput elevationHighLimit = RobotMap.laucherelevationHighLimit;
    private final DigitalInput elevationLowLimit = RobotMap.laucherelevationLowLimit;
    private final DigitalInput laucherBackLimit = RobotMap.laucherlaucherBackLimit;
    private final DigitalInput laucherFrontLimit = RobotMap.laucherlaucherFrontLimit;
    private final Solenoid laucherRelease = RobotMap.laucherlaucherRelease;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private final Encoder laucherLimitEncoder = RobotMap.laucherlaucherLimitEncoder;
    private boolean open = true;
    private boolean closed = false;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /****************************************
     * 
     * State machine variables
     * 
     *   - emptyForwards: means the motor is
     *   at the front and the grabber is open
     *   - emptyBackwards: means the motor is
     *   at the back and the grabber is open
     *   - emptyMiddle: means the motor is
     *   somewhere in the middle and empty
     *   - primedMiddle: means the motor is
     *   somewhere in the middle and primed
     *   - primed: means the motor is at the
     *   front and the grabber is closed
     *   - loaded: means the motor is at the
     *   back and ready
     *
     ****************************************/
    public enum laucherState{readyToLauch, emptyMiddle, closedForwards, emptyBackwards, closedMiddle};
    
    /*public enum laucherState { emptyForwards, 
    						 	emptyBackwards, 
    						 	emptyMiddle,
    						 	primedMiddle,
    							primed, 
    							loaded };
    							*/
    
    private laucherState current; // Current state of the laucher  
    private laucherState destination; // Where you want to be
    
    private final double SPEED = 1.00;
    private final double ELEVATION_SPEED = 0.38;
    
    public Laucher() {
    	current = laucherState.emptyMiddle; // This makes the default position of the robot emptyMiddle
    	pullBackMotor.setInverted(Robot.invertLaucher);
    	elevationMotor.setInverted(Robot.invertLaucherElevation);
    }
    
    /**********************************
     * 
     * Method to get the current state
     * 
     * @return the current state
     *
     **********************************/
    public laucherState getState() {
    	return current;
    }
    
    /**********************************
     * 
     * Method to get the destination
     * 
     * @return the current destination
     *
     **********************************/
    public laucherState getDestination() {
    	return destination;
    }
    
    private void setCurrentState(laucherState newState) {
    	current = newState;
    }
    
    public void setReady(){
    	current = laucherState.readyToLauch;
    }
    
    public void setEmptyMiddle(){
    	current = laucherState.emptyMiddle;
    }
    
    public void setEmptyBackwards(){
    	current = laucherState.emptyBackwards;
    }
    
    public void setClosedForwards(){
    	current = laucherState.closedForwards;
    }
    
    public void setClosedMiddle(){
    	current = laucherState.closedMiddle;
    }

    /*
    public void prime(){
    	if(isBackLimitHit() && isClamed()){
    		stopLaucherMotor();
    	}
    	else if (isBackLimitHit() && !isClamed()){
    		driveLaucherMotorForwards();
    		toggleRelease();
    		driveLaucherMotorBackwards();
    	}
    	else if (isFrontLimitHit() && isClamed()){
    		driveLaucherMotorBackwards();
    	}
    	else if (isFrontLimitHit() && !isClamed()){
    		toggleRelease();
    		driveLaucherMotorBackwards();
    	}
    	else {
    		System.out.println("Laucher Error in Load Method.");
    	}
    }
    
    public void shoot(){
    	if(isFrontLimitHit() && isClamed()){
    		toggleRelease();
    		driveLaucherMotorForwards();
    	}
    	else {
    		System.out.println("Laucher Error in Shoot Method.");
    	}
    }
    
    
    public void move(laucherState destination) {
    	
    	// This uses the sensors to check the current position and will set the current position to where ever it actually is
    	if(isFrontLimitHit() && isClamed()) setCurrentState(laucherState.primed);                                                // primed
    	else if(isFrontLimitHit() && !isClamed()) setCurrentState(laucherState.emptyForwards);                                   // emptyForwards
    	else if(isBackLimitHit() && isClamed()) setCurrentState(laucherState.loaded);                                            // loaded 
    	else if(isBackLimitHit() && !isClamed()) setCurrentState(laucherState.emptyBackwards);                                   // emptyBackwards
    	else if((!isFrontLimitHit() && !isBackLimitHit()) && isClamed()) setCurrentState(laucherState.primedMiddle);             // primedMiddle
    	else setCurrentState(laucherState.emptyMiddle);                                                                          // emptyMiddle
    	
    	this.destination = destination; // Sets global to current destination
    	
    	switch(this.destination) {
    	case emptyForwards:
    		switch(this.current) {
        	case emptyForwards:
        		// Do nothing
        		stopLaucherMotor();
        	break;
        	case emptyBackwards:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case emptyMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primedMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case primed:
        		toggleRelease();
        		setCurrentState(laucherState.emptyForwards);
            break;
        	case loaded:
        		toggleRelease();
        		setCurrentState(laucherState.emptyBackwards);
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
    	break;
    	case emptyBackwards:
    		switch(this.current) {
        	case emptyForwards:
        		toggleRelease();
        		setCurrentState(laucherState.primed);
        	break;
        	case emptyBackwards:
        		// Do nothing
        		stopLaucherMotor();
            break;
        	case emptyMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primedMiddle:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case primed:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case loaded:
        		toggleRelease();
        		setCurrentState(laucherState.emptyBackwards);
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
        break;
    	case emptyMiddle:
    		switch(this.current) {
        	case emptyForwards:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.emptyMiddle);
        	break;
        	case emptyBackwards:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case emptyMiddle:
        		// Do nothing
        		stopLaucherMotor();
            break;
        	case primedMiddle:
        		toggleRelease();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primed:
        		toggleRelease();
        		setCurrentState(laucherState.emptyForwards);
            break;
        	case loaded:
        		toggleRelease();
        		setCurrentState(laucherState.emptyBackwards);
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
        break;
    	case primedMiddle:
    		switch(this.current) {
        	case emptyForwards:
        		toggleRelease();
        		setCurrentState(laucherState.primed);
        	break;
        	case emptyBackwards:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case emptyMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primedMiddle:
        		// Do nothing
        		stopElevationMotor();
            break;
        	case primed:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case loaded:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
        break;
    	case primed:
    		switch(this.current) {
        	case emptyForwards:
        		toggleRelease();
        		setCurrentState(laucherState.primed);
        	break;
        	case emptyBackwards:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case emptyMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primedMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case primed:
        		// Do nothing
        		stopLaucherMotor();
            break;
        	case loaded:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
        break;
    	case loaded:
    		switch(this.current) {
        	case emptyForwards:
        		toggleRelease();
        		setCurrentState(laucherState.primed);
        	break;
        	case emptyBackwards:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case emptyMiddle:
        		driveLaucherMotorForwards();
        		setCurrentState(laucherState.emptyMiddle);
            break;
        	case primedMiddle:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case primed:
        		driveLaucherMotorBackwards();
        		setCurrentState(laucherState.primedMiddle);
            break;
        	case loaded:
        		// Do nothing
        		stopLaucherMotor();
            break;
            default: 
            	stopLaucherMotor();
            break;
        	}
        break;
        default: 
        	stopLaucherMotor();
        break;
    	}
    }
    */
    
    /********************************
     * 
     * Toggles the release valve
     * 
     ********************************/
    public void toggleRelease() {
    	laucherRelease.set(!laucherRelease.get());
    }
    
    public void openClam(){
    	laucherRelease.set(open);
    }
    
    public void closeClam(){
    	laucherRelease.set(closed);
    }
    
    /*********************************
     * 
     * Drives the pullBack motor 
     * forwards
     * 
     *********************************/
    public void driveLaucherMotorForwards() {
    	if(!isFrontLimitHit()) pullBackMotor.set(SPEED);
    	else stopLaucherMotor();
    }
    
    /*********************************
     * 
     * Drives the pullBack motor 
     * backwards
     * 
     *********************************/
    public void driveLaucherMotorBackwards() {
    	if(!isBackLimitHit()) pullBackMotor.set(-SPEED);
    	else stopElevationMotor();
    }
    
    /*********************************
     * 
     * Stops the pullBack motor
     * 
     *********************************/
    public void stopLaucherMotor() {
    	pullBackMotor.set(0.0);
    }
    
    /*********************************
     * 
     * Elevates the motor up manually
     * 
     *********************************/
    public void elevateLaucherUp(double rate) {
    	if(!isHighElevationHit()) elevationMotor.set(ELEVATION_SPEED * rate);	// rate is fraction of max elevation speed to move
    	else stopElevationMotor();
    }
    
    /*********************************
     * 
     * Elevates the motor down
     * manually
     * 
     *********************************/
    public void elevateLaucherDown(double rate) {
    	if(!isLowElevationHit()) elevationMotor.set(-ELEVATION_SPEED * rate);
    	else stopElevationMotor();
    }
    
    /*********************************
     * 
     * Stops elevation
     * 
     *********************************/
    public void stopElevationMotor() {
    	elevationMotor.set(0.0);
    }
    
    /***************************
     * 
     * returns elevation angle 
     * 
     * @return angle in degrees
     *
     ***************************/
    public double getAngle() {
    	return elevationAngle.get() * 360.0;
    }
    
    public void lockIt() {
    	RobotMap.laucherlockLaucher.set(true);
    }
    
    public boolean isFrontLimitHit() {
    	return !laucherFrontLimit.get();
    }
    
    public boolean isBackLimitHit() {
    	return !laucherBackLimit.get();
    }
    
    public boolean isLowElevationHit() {
    	return !elevationLowLimit.get();
    }
    
    public boolean isHighElevationHit() {
    	return !elevationHighLimit.get();
    }
    
    public boolean isClamed() {
    	return laucherRelease.get();
    }
    
    public void resetDistance(){
    	laucherLimitEncoder.reset();
    }
    
    public int getEncoderValue(){
    	return laucherLimitEncoder.get();
    }
    
	@Override
	public double getPIDSource(int marker) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPIDOutput(double output, int marker) {
		// TODO Auto-generated method stub
		
	}
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	
    	setDefaultCommand(new LaucherMoveElevation());
    	
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

