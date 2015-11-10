package robotm;
import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class Color {
	

	public static double rBlueMin = 70.0;
	public static double rBlueMax = 120.0;
	
	public static double gBlueMin = 80.0;
	public static double gBlueMax = 150.0;
	
	public static double bBlueMin = 155.0;
	public static double bBlueMax = 180.0;
	
	
	public static double rBlackMin = 40;
	public static double rBlackMax = 150.0;
	
	public static double gBlackMin = 30.0;
	public static double gBlackMax = 115.0;
	
	public static double bBlackMin = 15.0;
	public static double bBlackMax = 90.0;
	
    public static boolean isBlack(ColorSensor color){
    	int r = color.getColor().getRed();
    	int g = color.getColor().getGreen();
    	int b = color.getColor().getBlue();
    	if(r > rBlackMin && r < rBlackMax && g > gBlackMin && g < gBlackMax && b > bBlackMin && b < bBlackMax)
    		return true;
    	else
    		return false;
    }
    
    public static void uTurn(){
    	Motor.C.rotate(-100,true);
    	Motor.B.rotate(-100,true);
    	Delay.msDelay(1000);
    	Motor.C.rotate(365,true);
    	Motor.B.rotate(-375,true);
    	Delay.msDelay(1800);
    }
    
    
	public static void main(String[] args){
		LCD.drawString("Logic", 6, 3);
		Button.waitForAnyPress();
		
		ColorSensor colorSen = new ColorSensor(SensorPort.S1);
		TouchSensor touchSen = new TouchSensor(SensorPort.S3);
		
		while(!Button.ESCAPE.isPressed()){
			if(isBlack(colorSen)){
				Motor.B.rotate(20,true);
				Motor.C.rotate(-3,true);
			}
			//Need change!
			if(!isBlack(colorSen)){
				Motor.C.rotate(20,true);
				Motor.B.rotate(-3,true);
			}
			if(touchSen.isPressed()){
				uTurn();
			}
			
		}
		
	}
	
}
