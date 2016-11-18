package org.usfirst.frc.team3242.robot;




import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    Joystick controller;
    CANTalon motorOne;
    CANTalon motorTwo;
    CANTalon motorThree;
    CANTalon motorFour;
    CANTalon motorFive;
    CANTalon motorSix;
    RobotDrive driver; 
    Relay actuator;
    
    double rTrigger;
    double lTrigger;
    double xAxisLeft;
    double yAxisLeft;
    double xAxisRight;
    
    boolean rBumper;
    boolean lBumper;
    
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        controller  = new Joystick(0);
        motorOne = new CANTalon(1);
        motorTwo = new CANTalon(2);
        motorThree = new CANTalon(3);
        motorFour = new CANTalon(4);
        motorFive = new CANTalon(5);
        motorSix = new CANTalon(6);
        actuator = new Relay(2);
       
        driver = new RobotDrive(motorFour, motorThree, motorOne, motorTwo);
        
        driver.setInvertedMotor(MotorType.kRearLeft, true);
        driver.setInvertedMotor(MotorType.kFrontLeft, true);
        
        }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
   
    public void teleopPeriodic() {
    	xAxisLeft = controller.getRawAxis(0);
    	yAxisLeft = controller.getRawAxis(1);
    	xAxisRight = controller.getRawAxis(4);
    	rTrigger = controller.getRawAxis(3);
    	lTrigger = controller.getRawAxis(2);
    	rBumper = controller.getRawButton(6);
    	lBumper = controller.getRawButton(5);
    	
    	driver.mecanumDrive_Cartesian(xAxisRight * -1, yAxisLeft * -1, xAxisLeft * -1 , 0);
    	
    	if (rTrigger > 0) {
    		motorFive.set(rTrigger);
    		motorSix.set(rTrigger);
    	}
    	else if (lTrigger > 0) {
    		motorFive.set(lTrigger * -1);
    		motorSix.set(lTrigger * -1);
    	}
    	else {
    		motorFive.set(0);
    		motorSix.set(0);
    	}
    	if (rBumper){
    		actuator.set(Relay.Value.kForward);
    	}
    	else if (lBumper){
    		actuator.set(Relay.Value.kReverse);
    	}
    	else{
    		actuator.set(Relay.Value.kOff);
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
