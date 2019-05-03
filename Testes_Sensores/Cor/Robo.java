import lejos.nxt.*;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class Robo {
	//Sensores
	public ColorSensor colorSensor;
	private RegulatedMotor motorA;
	private RegulatedMotor motorB;
	int speed;
	double distEsteira;
	float andou = 0;
	boolean lastCor = false;


	public Robo(int s){
		colorSensor = new ColorSensor(SensorPort.S1);
		motorA = Motor.A;
		motorB = Motor.B;
		speed = s;
		colorSensor.calibrateLow();
	}


	public boolean pegaCor() {
		colorSensor.calibrateLow();
		int cor = colorSensor.getColorID();
		//System.out.println("ID da cor: " + cor);
		return cor==7;
	}

	public void ourCode(float dist_target) {

		lastCor = pegaCor();
		this.getMotorA().setSpeed(speed);
		this.getMotorB().setSpeed(speed);
		this.getMotorA().backward();
		this.getMotorB().backward();

		while(true) {
			passo();
			distEsteira = getLightDistance();
		  System.out.println((double)distEsteira); //printa distancia percorrida na tela

			if(distEsteira > dist_target) {
				this.getMotorA().stop();
				this.getMotorB().stop();
				break;
			}
		}

	}

	public void passo() {

		boolean cor = pegaCor();
		if(cor!=lastCor) {
			if(lastCor) {
				andou+=1.17f; //1.17 para 250 de speed
			}else{
				andou+=0.54; //0.54 para 250 de speed
			}
		}
		lastCor = cor;
	}
	public float getLightDistance() {
		return this.andou;
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
