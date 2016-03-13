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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DashboardSender extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final PowerDistributionPanel pDP = RobotMap.dashboardSenderPDP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double[] robotArray = new double[3];
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void sendBaseData() {
        SmartDashboard.putBoolean("Locking piston", Robot.launcherElevation.isLocked());
        SmartDashboard.putNumber("matchTime", Timer.getMatchTime());
        //SmartDashboard.putNumber("batteryPercent", pDP.getVoltage() / 12);
        
        robotArray[0] = Robot.launcherElevation.getAngleDegrees();
        robotArray[1] = Robot.defenseElevation.getAngleDegrees();
        if(Robot.defenseArm.isExtended()) robotArray[2] = 1;
        else robotArray[2] = 0;
        SmartDashboard.putString("robotWidget", "" + robotArray[0] + "," + robotArray[1] + "," + robotArray[2]);
        
        // key to success : "the cheat sheet"
        //SmartDashboard.putNumber(key, value);
        

    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DashboardPeriodic());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

