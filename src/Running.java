import lejos.nxt.*;
//import lejos.navigation
import lejos.util.Delay;

public class Running {
	
	public static void ring(){
		Sound.playTone(300,800);
		Sound.playTone(300,800);
		Sound.playTone(300,800);
		Sound.playTone(300,800);
		Sound.playTone(300,800);
	}
	
	public static void main(String[] args) {
		System.out.println("Logical Operator");
		Button.waitForAnyPress();
		Boolean run = true;
		TouchSensor ts = new TouchSensor(SensorPort.S1);
		int rotation = -385;
		
		while(run){
			Motor.B.forward();
			Motor.C.forward();
			if(ts.isPressed()){
				Sound.playTone(600,2000);
				Motor.B.rotate(-500,true);
				Motor.C.rotate(-500,true);
				Delay.msDelay(4000);
				Motor.C.rotate(rotation);
				Delay.msDelay(3000);
				Motor.B.rotate(1300,true);
				Motor.C.rotate(1300,true);
				Delay.msDelay(5000);
				Motor.B.rotate(rotation);
				Delay.msDelay(3000);
				Running.ring();
			}
			if(Button.ENTER.isPressed()){
				run = false;
			}
		}
		Button.waitForAnyPress();
	}
	
}


