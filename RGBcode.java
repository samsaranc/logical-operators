import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;

/*
 * this program get input from an RGB sensor and converts it to HSV
 * test your answers on this: http://www.rapidtables.com/convert/color/rgb-to-hsv.htm
 */

public class RGBtoHSVClass {

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
	public static void main(String[] args) {
		ColorSensor rgb_cs = new ColorSensor(SensorPort.S3,SensorConstants.TYPE_COLORFULL);
		 for(int i = 0; i < 3; i++) {
            Color color = rgb_cs.getColor();
            double[] hsv = RGBtoHSV(color);
            System.out.println("RGB = "+
                " [" + color.getRed() + "," + color.getGreen() + "," + color.getBlue()
                +"] "  + "HSV = " + "[ " + hsv[0] + "," + hsv[1] + "," + hsv[2] + "," +" ]");
            Button.waitForAnyPress();
	      }
    }
}
