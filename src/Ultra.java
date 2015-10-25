import java.io.File;
import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class Ultra {
	
	public static void main(String[] args) {
		LCD.drawString("Logic", 6, 3);
		
		Button.waitForAnyPress();
		//Sound.playSample(sample);
		int rotation = -365;
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
		int turn = 0;
		
		while(!Button.ESCAPE.isPressed()){
			if(us.getDistance() < 8){
				//Sound.playTone(900,800);
				Motor.B.rotate(-180,true);
				Motor.C.rotate(-180,true);
				Delay.msDelay(2000);
				if(turn % 2 == 0){
					Motor.C.rotate(rotation);
					Delay.msDelay(1000);
					turn++;
				}
				else{
					Motor.B.rotate(rotation);
					Delay.msDelay(1000);
					turn++;
				}
			}
			else{
				Motor.B.rotate(45,true);
				Motor.C.rotate(45,true);
			}
		}
		
	}

}
