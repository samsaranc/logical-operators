import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UTest {

	public static void main(String[] args) throws Exception {
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
		int line = 0;
		while(!Button.ENTER.isPressed()){
			LCD.drawInt(us.getDistance(), line, 0);
		}

	}

}
