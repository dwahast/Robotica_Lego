import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class HelloWorld
 {
    /**
15       * @param args
16       */
	public static void main(String[] args)
     {
		int MAX_DISTANCE = 50; // In centimeters
		int PERIOD = 500; // In milliseconds
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		FeatureDetector fd = new RangeFeatureDetector(us, MAX_DISTANCE, PERIOD);
		boolean  flag = false;
		Feature result;
		float initialDist  = 0;
		float count =0;
		for (int i= 0;i<30;i++) {
			result = fd.scan();
			if( result != null && result.getRangeReading().getRange() < 10000) {
				count++;
				initialDist +=  result.getRangeReading().getRange();
			}
		}
		initialDist = initialDist/count;
		
		Motor.A.setSpeed(720);// 2 RPM
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.forward();
		while(true) {
			result = fd.scan();
			if(result != null) {
				System.out.println("Range: " + result.getRangeReading().getRange());
				if(result.getRangeReading().getRange()<=10.0) {
					Motor.A.backward();
					Motor.B.backward();
					flag = true;
				}
				if(flag == true && result.getRangeReading().getRange() >= initialDist) {
					
					Motor.A.stop();
					Motor.B.stop();
					break;
				}
		
			}
		}
      }
 }
