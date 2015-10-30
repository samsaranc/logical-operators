package robotm;
import java.util.*;
import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class Navigation{
	static boolean hasHit=false;
	static ColorSensor color = new ColorSensor(SensorPort.S1);
	static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	static TouchSensor touch = new TouchSensor(SensorPort.S3);
	static Stack<String> moves;

	//-------------------------------
	
	public static int rotationBack = -365;
	
	public static void turnLeft(){
		Motor.C.rotate(rotationBack);
		Delay.msDelay(1000);
	}
	
	public static void turnRight(){
		Motor.B.rotate(rotationBack);
		Delay.msDelay(1000);
	}
	
	public static void uTurn(){
		Motor.C.rotate(-50,true);
		Motor.B.rotate(-50,true);
		Delay.msDelay(1000);
		Motor.C.rotate(-365,true);
		Motor.B.rotate(365,true);
		Delay.msDelay(1800);
	}
	
	//-------------------------------------
	
	
		double hBlueMin = 175;
		double hBlueMax = 260;
		double sBlueMin = 45;
	    double sBlueMax = 100;
	    double vBlueMin = 55;
	    double vBlueMax = 100;
	    double hBlackMin = 0;
	    double hBlackMax = 10;
	    double sBlackMin = 0;
	    double sBlackMax = 10;
	    double vBlackMin = 0;
	    double vBlackMax = 10;



		public static double[] RGBtoHSV(ColorSensor.Color colors){
			double[] hsv = new double[3];
			// read colors
			int r = colors.getRed();
			int b = colors.getBlue();
			int g = colors.getGreen();

			double min = Math.min(r, Math.min(b,g));
			double max = Math.max(r, Math.max(b, g));
			double delta = max - min;
			hsv[2] = max/255; //set v to max as a percentage
			if (max != 0){
				hsv[1] = delta/max;
			}
			else{ //r = b = g =0
				hsv[1] = 0; //s = 0;		// s = 0, v is undefined
				hsv[0] = -1; //h = -1;
				return hsv;
			}

			if (r == max){
				hsv[0] = (g-b)/delta; //h
			}
			else{
				if (g == max)
					hsv[0] = 2 + (b - r)/delta; //h
				else
					hsv[0] = 4 + (r - g)/delta; //h
			}

			hsv[0] *=60;	//degrees
			if (hsv[0] < 0)
				hsv[0] +=360;

			return hsv;
		}
	
	public boolean isBlue(double[] hsv){
		double h = hsv[0];
		double s = hsv[1];
		double v = hsv[2];

		if (h > hBlueMin && h < hBlueMax && s > sBlueMin && s < sBlueMax && v > vBlueMin && v < vBlueMax){
		return true;
			//stack
		}
		return false;
	}

	public boolean isBlack(double[] hsv) {
		double h = hsv[0];
		double s = hsv[1];
		double v = hsv[2];
		if (h > hBlackMin && h < hBlackMax && s > sBlackMin && s < sBlackMax && v > vBlackMin && v < vBlackMax)
			return true;
		return false;
		//do something
	}
	
	
	
	//-------------------------------------
	
	public static void main(String[] args){
		Converter col = new Converter();
		while(!col.isBlue(Converter.RGBtoHSV(color.getColor()))){
			if(touch.isPressed()){
				hasHit=true;
				uTurn();
			}
			if(col.isBlack(Converter.RGBtoHSV(color.getColor())))
				Motor.C.rotate(15);
			else
				Motor.B.rotate(15);
		}
		if(col.isBlue(Converter.RGBtoHSV(color.getColor())) && hasHit==false){
			if(ultra.getDistance()<8){
				turnLeft();
				moves.push("left");
			}
			else{
				turnRight();
				moves.push("right");
			}
		}

		if(col.isBlue(Converter.RGBtoHSV(color.getColor())) && hasHit==true){
			String top = moves.peek();
			if(top.equals("left")){
				turnLeft();
				moves.push("left");
			}
			else if(top.equals("right")){
				if(!(ultra.getDistance()<8)){
					turnRight();
					moves.push("right");
					
				}
				else{
					Motor.C.rotate(360,true);
					Motor.B.rotate(360,true);
					moves.push("straight");
				}
			}
			else if(ultra.getDistance()<8){
				turnLeft();
				moves.push("left");
			}
			else{
				turnRight();
				moves.push("right");
			}
			hasHit=false;
		}
	}
}