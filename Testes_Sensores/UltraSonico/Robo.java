import lejos.nxt.*;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class Robo {
	//Sensores
	private UltrasonicSensor ultrasonicSensor;
	private FeatureDetector featureDetector;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;
	int speed;

	//Vari�veis do Filtro de Kalman
	double distSensorUltrasonico;
	float distance=0;


	public Robo(int MAX_DISTANCE, int PERIOD, int s) {
		ultrasonicSensor = new UltrasonicSensor(SensorPort.S4);
		featureDetector = new RangeFeatureDetector(ultrasonicSensor, MAX_DISTANCE, PERIOD);
		motorA = Motor.A;
		motorB = Motor.B;
		speed = s;
	}

	public void ourCode(float dist_target) {
		float initialDist  = 28;
		this.getMotorA().setSpeed(speed);
		this.getMotorB().setSpeed(speed);
		this.getMotorA().backward();
		this.getMotorB().backward();
		Feature fd;

		while(true) {
			fd = featureDetector.scan();

			//if(fd!=null) {//first sensor read
		  distSensorUltrasonico = fd.getRangeReading().getRange(); //sensor distance from object
			//}

			System.out.println((double)distSensorUltrasonico - (double)initialDist); //printa distancia percorrida na tela
			if(distSensorUltrasonico > dist_target + initialDist) {
				this.getMotorA().stop();
				this.getMotorB().stop();
				break;
			}
		}

	}
	public UltrasonicSensor getUltrasonicSensor() {
		return ultrasonicSensor;
	}


	public FeatureDetector getFeatureDetector() {
		return featureDetector;
	}

	public RegulatedMotor getMotorA() {
		return motorA;
	}


	public RegulatedMotor getMotorB() {
		return motorB;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}




}
