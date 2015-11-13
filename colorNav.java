package robotm;
import java.util.Stack;

import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class Color {


	public static double rBlueMin = 70.0;
	public static double rBlueMax = 135.0;
	public static double gBlueMin = 80.0;
	public static double gBlueMax = 155.0;
	public static double bBlueMin = 155.0;
	public static double bBlueMax = 195.0;

	public static double rBlackMin = 40;
	public static double rBlackMax = 150.0;
	public static double gBlackMin = 30.0;
	public static double gBlackMax = 115.0;
	public static double bBlackMin = 15.0;
	public static double bBlackMax = 90.0;

	public static double rSilverMin = 220;
	public static double rSilverMax = 350;
	public static double gSilverMin = 200;
	public static double gSilverMax = 350;
	public static double bSilverMin = 210;
	public static double bSilverMax = 350;


  public static boolean isBlack(ColorSensor color){
    int r = color.getColor().getRed();
  	int g = color.getColor().getGreen();
  	int b = color.getColor().getBlue();
		if(r > rBlackMin && r < rBlackMax && g > gBlackMin && g < gBlackMax && b > bBlackMin && b < bBlackMax)
    	return true;
  	else
  		return false;
  }

	public static boolean isBlue(ColorSensor color){
		int r = color.getColor().getRed();
		int g = color.getColor().getGreen();
		int b = color.getColor().getBlue();
		if(r > rBlueMin && r < rBlueMax && g > gBlueMin && g < gBlueMax && b > bBlueMin && b < bBlueMax)
			return true;
		else
			return false;
	}

	public static boolean isSilver(ColorSensor color){
		int r = color.getColor().getRed();
		int g = color.getColor().getGreen();
		int b = color.getColor().getBlue();
		if(r > rSilverMin && r < rSilverMax && g > gSilverMin && g < gSilverMax && b > bSilverMin && b < bSilverMax)
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

	public static void turnLeft(){
		Motor.B.rotate(360);
	}

	public static void turnRight(){
		Motor.C.rotate(300);
	}

	public static void main(String[] args){
		LCD.drawString("Logic", 6, 3);
		Button.waitForAnyPress();

		ColorSensor colorSen = new ColorSensor(SensorPort.S1);
		TouchSensor touchSen = new TouchSensor(SensorPort.S3);
		UltrasonicSensor ultraSen = new UltrasonicSensor(SensorPort.S2);
		boolean hasHit = false;
		boolean wayBack = false;
		Stack<String> moves = new Stack<String>();
		int dis = 30;

		while(!Button.ESCAPE.isPressed()){
			if(isSilver(colorSen)){
				Delay.msDelay(1000);
				wayBack=true;
				uTurn();
				Motor.B.rotate(360,true);
				Motor.C.rotate(360,true);

			}

			if(isBlack(colorSen)){
				Motor.B.rotate(20,true);
				Motor.C.rotate(-3,true);
			}

			if(!isBlack(colorSen)&&!isBlue(colorSen)&&!isSilver(colorSen)){
				Motor.C.rotate(20,true);
				Motor.B.rotate(-3,true);
			}

			if(touchSen.isPressed()){
				uTurn();
				hasHit = true;
			}

			if(isBlue(colorSen)){
				Delay.msDelay(500);
				if(hasHit==false && wayBack==false){
					if(ultraSen.getDistance() < dis){
						turnLeft();
						moves.push("left");
					}
					else{
						turnRight();
						moves.push("right");
					}

				}
				if(hasHit==true && wayBack==false){
					String top=moves.pop();
					if(top.equals("left")){
						turnLeft();
						moves.push("straight");
					}
					else if(top.equals("right")){
						if(!(ultraSen.getDistance() < dis)){
							turnRight();
							moves.push("straight");
						}
						else{
							Motor.C.rotate(360,true);
							Motor.B.rotate(360,true);
							moves.push("left");
						}
					}
					else if(ultraSen.getDistance() < dis){
						turnLeft();
						moves.push("straight");
					}
					else{
						turnRight();
						moves.push("straight");
					}
					hasHit = false;
				}

				if(wayBack==true){
					String direction =moves.pop();
					if(direction.equals("right"))
						turnLeft();
					if(direction.equals("left"))
						turnRight();
					else{
						Motor.C.rotate(360,true);
						Motor.B.rotate(360,true);
					}
				}

			}

		}

	}

}
