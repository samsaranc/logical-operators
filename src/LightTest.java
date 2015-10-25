import java.io.File;
import lejos.nxt.*;
import lejos.util.*;
import lejos.robotics.*;

public class LightTest {

	public static void main(String[] args) {
		LCD.drawString("Logic", 6, 3);
		Button.waitForAnyPress();
		LightSensor ls = new LightSensor(SensorPort.S3);
		while(!Button.ESCAPE.isPressed())
			LCD.drawInt(ls.readValue(), 0, 0);
	}

}
//Result: <40 black, >40 white