// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot16;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc1073.robot16.commands.*;
import org.usfirst.frc1073.robot16.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Vision vision;
    public static Navigation navigation;
    public static Launcher launcher;
    public static LauncherElevation launcherElevation;
    public static Collector collector;
    public static Climber climber;
    public static DefenseElevation defenseElevation;
    public static DefenseArm defenseArm;
    public static DashboardSender dashboardSender;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
 // Preferences 
    private Preferences prefs;
    // DriveTrain Preferences
    public static double cubicScale;
    public static double deadZone;
    public static double robotTopSpeed;
    public static boolean isDriveTrainPID = true; // PID initializes on
    public static boolean isCubic = true; // Cubic initializes on
    public static boolean inverseLeft = true; // Initialize one side inverted
    public static boolean inverseRight = false;
    // PID Preferences
    private static double driveTrainP; //P
    private static double driveTrainI; //I
    private static double driveTrainD; //D
    private static double driveTrainTolerance; // tolerance
    // Collector Preferences
    public static double rollerSpeed;
    public static boolean invertRoller = false;
    // Launcher Preferences
    public static boolean invertLauncher = false;
    public static boolean invertLauncherElevation = true;
    // Defense Manipulator
    public static boolean invertDefenseDir = false;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
    
    prefs = prefs.getInstance();
    
    //PID
    driveTrainP = prefs.getDouble("driveTrainkP", 0.05);
    driveTrainI = prefs.getDouble("driveTrainkI", 0.05);
    driveTrainD = prefs.getDouble("driveTrainkD", 0.05);
    driveTrainTolerance = prefs.getDouble("driveTrainTolerance", 0.1);
    
    // DriveTrain
    cubicScale = prefs.getDouble("cubicScale", 0.07);
    deadZone = prefs.getDouble("deadZone", 0.05);
    inverseLeft = prefs.getBoolean("inverseLeft", false);
    inverseRight = prefs.getBoolean("inverseRight", true);
    robotTopSpeed = prefs.getDouble("robotTopSpeed", 10.5);
    
    // Collector
    rollerSpeed = prefs.getDouble("rollerSpeed", 0.90);
    invertRoller = prefs.getBoolean("invertRoller", false);
    
    // Laucher
    invertLauncher = prefs.getBoolean("invertLaucher", false);
    invertLauncherElevation = prefs.getBoolean("invertLaucherElevation", true);
    
    // Defense
    invertDefenseDir = prefs.getBoolean("invertDefenseDir", false);
    
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        vision = new Vision();
        navigation = new Navigation();
        launcher = new Launcher();
        launcherElevation = new LauncherElevation();
        collector = new Collector();
        climber = new Climber();
        defenseElevation = new DefenseElevation();
        defenseArm = new DefenseArm();
        dashboardSender = new DashboardSender();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
