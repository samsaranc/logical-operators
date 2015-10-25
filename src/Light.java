import java.io.File;
import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class Light {

	public static void main(String[] args) {
		LCD.drawString("Logic", 6, 3);
		Button.waitForAnyPress();
		LightSensor ls = new LightSensor(SensorPort.S3);
		int turn = 0;
		
		while(!Button.ESCAPE.isPressed()){
			if(ls.readValue()>41){
				Motor.C.rotate(15);
			}
			else{
				Motor.B.rotate(15);
			}
		}
		
	}

}
