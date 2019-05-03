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
	public ColorSensor colorSensor;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;
	private int MAX_Dist;
	int speed;
	
	//Variáveis do Filtro de Kalman
	double gKalman = 0.6162647384;
	//double gKalman = 0.042585153428079565;
	double distEsteira;
	double distSensorUltrasonico;
	float andou = 0;
	boolean lastCor = false;
	float distance=0;
	
	
	public Robo(int MAX_DISTANCE, int PERIOD, int s) {
		ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		featureDetector = new RangeFeatureDetector(ultrasonicSensor, MAX_DISTANCE, PERIOD);
		colorSensor = new ColorSensor(SensorPort.S3, SensorConstants.TYPE_LIGHT_ACTIVE);
		motorA = Motor.A;
		motorB = Motor.B;
		MAX_Dist = MAX_DISTANCE;
		speed = s;
		colorSensor.calibrateLow();
	}

	
	public boolean pegaCor() {
		
		int cor = colorSensor.getColorID();
		//System.out.println("ID da cor: " + cor);
		return cor==7;
	}
	
	public void ourCode() {
		boolean  flag = false;
		Feature result;
		float initialDist  = 0;
		float count = 0;
		lastCor = pegaCor();
		this.getMotorA().setSpeed(speed);
		this.getMotorB().setSpeed(speed);
		this.getMotorA().backward();
		this.getMotorB().backward();
		Feature fd;
		while(true) {
			fd = featureDetector.scan();
			
				
			if(fd!=null) {
				distSensorUltrasonico = fd.getRangeReading().getRange() -28.0;
				
				passo();
				distEsteira = getLightDistance();
				this.distance = (float) ((gKalman* distSensorUltrasonico ) + ((1-gKalman)*distEsteira));
				//this.getMotorA().rotate(-45,true);
				//this.getMotorB().rotate(-45,true);
			}
			if(this.distance >100) {
				this.getMotorA().stop();
				this.getMotorB().stop();
				break;
			}
		}
		
	}

	public void passo() {
		 
		boolean cor = pegaCor();
		if(cor!=lastCor) {
			//System.out.println("Troquei de COR");
			if(lastCor) {
				andou+=1.1;
				//andou+=3;
			}else{
				andou+=0.5;
			}
		}
		lastCor = cor;
	}
	public float getLightDistance() {
		return this.andou;
	}
	public UltrasonicSensor getUltrasonicSensor() {
		return ultrasonicSensor;
	}


	public FeatureDetector getFeatureDetector() {
		return featureDetector;
	}

	public ColorSensor getColorSensor() {
		return colorSensor;
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
