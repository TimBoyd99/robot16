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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public static boolean isCubic = true; // Cubic initializes on
    public static boolean inverseLeft = true; // Initialize one side inverted
    public static boolean inverseRight = false;
    // PID Preferences
    public static double driveTrainP; //P
    public static double driveTrainI; //I
    public static double driveTrainD; //D
    public static double driveTrainTolerance; // tolerance
    public static double launcherP;
    public static double launcherI;
    public static double launcherD;
    public static double launcherTolerance;
    public static double defenseP;
    public static double defenseI;
    public static double defenseD;
    public static double defenseTolerance;
    public static boolean turboOn = false;
    // Collector Preferences
    public static double rollerSpeed;
    public static boolean invertRoller = false;
    // Launcher Preferences
    public static boolean invertLauncher = false;
    public static boolean invertLauncherElevation = true;
    // Defense Manipulator
    public static boolean invertDefenseDir = false;
    
    private static CameraServer cam;
    public static SendableChooser autonomousChooser;
    
    
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
    driveTrainTolerance = prefs.getDouble("driveTrainTolerance", 10);
    launcherP = prefs.getDouble("LauncherP", 0.05);
    launcherI = prefs.getDouble("LauncherI", 0.05);
    launcherD = prefs.getDouble("LauncherD", 0.05);
    launcherTolerance = prefs.getDouble("LauncherTolerance", 10);
    defenseP = prefs.getDouble("defenseP", 0.05);
    defenseI = prefs.getDouble("defenseI", 0.05);
    defenseD = prefs.getDouble("defenseD", 0.05);
    launcherTolerance = prefs.getDouble("defenseTolerance", 10);
    
    // DriveTrain
    cubicScale = prefs.getDouble("cubicScale", 0.07);
    deadZone = prefs.getDouble("deadZone", 0.05);
    inverseLeft = prefs.getBoolean("inverseLeft", false);
    inverseRight = prefs.getBoolean("inverseRight", true);
    
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

        autonomousCommand = new AutonomousLowBar();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        cam = CameraServer.getInstance();
        cam.startAutomaticCapture("cam0");
        cam.setSize(0);
        cam.setQuality(35);
        
        autonomousChooser = new SendableChooser();
        autonomousChooser.addObject("Do Nothing Auto", null);
        autonomousChooser.addDefault("Low Bar Auto", new AutonomousLowBar());
        autonomousChooser.addObject("Basic Drive Auto", new AutonomousBasic());
        SmartDashboard.putData("Autonomous Chooser", autonomousChooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	driveTrain.disablePID();
    	launcherElevation.disablePID();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	
    	autonomousCommand = (Command) autonomousChooser.getSelected();
    	
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        SmartDashboard.putNumber("Left Side Encoder", driveTrain.getLeftDistanceFeet());
        SmartDashboard.putNumber("Right Side Encoder", driveTrain.getRightDistanceFeet());
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        driveTrain.disablePID();
        launcherElevation.disablePID();
        
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        SmartDashboard.putBoolean("isLauncherPID", launcherElevation.isPID());
        SmartDashboard.putNumber("left", driveTrain.getLeftRateFps());
        SmartDashboard.putNumber("right", driveTrain.getRightRateFps());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
