    /**
15      Trabalho 1 de Robótica - Robô andar 1 metro - 30 execuções de cada tipo
		1 = 30 testes com o sensor ultrasônico(que identifica a parede)
		2 = 30 testes com o sensor de cores(Posicionado para as rodas com o intuito de contar quanto ele andou)
		3 = 30 testes aplicando o filtro de kalman utilizando os dois sensores como entrada
		
		O que é filtro de kalman? É um algoritmo de estimativa ótima (busca um melhor valor com mais de uma entrada)
		
		Fórmula:
		
		Xk = F(k)*X(k-1) + Bk*Uk + Wk
		
		
		Configuração do nosso Robo:
		
		esteira tem 31 centimetros de perímetro
		branco = 60mm
		// preto = 1 cm
		// 18 pretos e 18 brancos
		// esteira tem 36 estados
		 * 
		Aparentemente o sensor ultrasom não pode ficar muito proximo do chao,
		na configuracao atual ele esta a 4 cm do chão e esta detectando, no máximo,
		95 cm (+-). Mas, se levantarmos ele em 3 cm (sensor a 7cm do chao) ele ganha + 60 cm,
		ficando com algo em torno de 150 cm
		
		
		//esteira B
		// 18 estados
		// 1,72 cm / estado
		16       
		
		**/

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.util.Delay;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;
import lejos.robotics.*;

public class Robotica_Trab1_1
 {

	//Variáveis
	static int MAX_DISTANCE = 50; // In centimeters
	static int PERIOD = 500; // In milliseconds
	static int speed = 20;
	int corAtual;
		
	
	
	public static void main(String[] args)
     {
		Robo nossoRobo = new Robo(MAX_DISTANCE, PERIOD, speed);			
		
		nossoRobo.ourCode();
	
		//System.out.println("ID da cor: " + nossoRobo.pegaCor());
		
      }
 }

/**
for (int i= 0;i<30;i++) {
result = this.f
if( result != null && result.getRangeReading().getRange() < 10000) {
	count++;
	initialDist +=  result.getRangeReading().getRange();
}
}
initialDist = initialDist/count;
int speed = 30;

//Motor.A.setSpeed(720);// 2 RPM
//Motor.B.setSpeed(720);
System.out.println(initialDist);
while(true) {
Motor.A.rotate(speed, true);
Motor.B.rotate(speed, true);
result = fd.scan();
if(result != null) {
	System.out.println("Range: " + result.getRangeReading().getRange());
	if(result.getRangeReading().getRange()<=10.0) {
		//Motor.A.backward();
		//Motor.B.backward();
		speed = -30;
		flag = true;
	}
	if(flag == true && result.getRangeReading().getRange() >= initialDist) {		
			speed = 0;
			break;
		
	}

}
}
**/