import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class UltraSonico{

  static int MAX_DISTANCE = 300; // In centimeters (DISTANCIA PARA ABORTAR O PROGRAMA)
  static int PERIOD = 500; // In milliseconds
  static int speed = 250; //

	public static void main(String[] args){
    Robo nossoRobo = new Robo(MAX_DISTANCE, PERIOD, speed);
		nossoRobo.ourCode(100.0f); //param %f cm distancia em centimetros para percorrer
  }

}
