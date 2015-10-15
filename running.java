import lejos.nxt.*;
//import lejos.navigation
import lejos.util.Delay;

public class Running {
	
	public static void main(String[] args) {
		System.out.println("Logical Operator");
		Button.waitForAnyPress();
		Boolean run = true;
		TouchSensor ts = new TouchSensor(SensorPort.S1);
		//Pilot pl = new Pilot
		while(run){
			Motor.B.forward();
			Motor.C.forward();
			if(ts.isPressed()){
				Sound.playTone(600,2000);
				Motor.B.rotate(-1080,true);
				Motor.C.rotate(-1080,true);
				Delay.msDelay(1000);
				Motor.C.rotate(-360);
				Delay.msDelay(1000);
				Motor.B.rotate(360*5,true);
				Motor.C.rotate(360*5,true);
				Delay.msDelay(1000);
				Motor.B.rotate(-360);
				Delay.msDelay(1000);
				Sound.playTone(300,2000);
			}
			if(Button.ENTER.isPressed()){
				run = false;
			}
		}
		Button.waitForAnyPress();
	}
	
}


