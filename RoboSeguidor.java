/* UNIP - ALPHAVILLE
APS - 2º Semestre Turma: CC2S06 
James Marques N9508A0
*/

//declaração das variaveis

//Sensores
#define SE A1 //ESQUERDO
#define SD A2 //DIREITO

//Motores
#define motor_esquerdo_re 2
#define mEsquerdo 3
#define motor_direito_re 4
#define mDireto 5

#define potencia_dir 9
#define potencia_esq 10



//Incluindo biblioteca Ultrasonic.h
#include "Ultrasonic.h"

//Criando objeto ultrasonic e definindo as portas digitais
//do Trigger - 0 - e Echo - 1
Ultrasonic SensorUltrassonico1(0, 1);



//CRIA O OBJETO ULTRASONIC E DEFINE OS PINOS PARA O TRIGGER(0) E ECHO(1)(SENSOR DE DISTÂNCIA1152Ultrasonic SensorUltrassonico1(0, 1);

long Microsegundos = 0;// Variável para armazenar o valor do tempo da reflexão do som refletido pelo objeto fornecido pela biblioteca do sensor
float DistanciaemCM = 0;// Variável para armazenar o valor da distância a ser convertido por uma função da própria bilbioteca do sensor

int ValorCorte = 300; //Valor de Corta 700: Linha Preta 300: Linha Branca
const int velocidade = 150; //Velocidade do carrinho




void setup (){

  Serial.begin(115200); 
  pinMode(motor_esquerdo_re, OUTPUT);
  pinMode(mEsquerdo, OUTPUT);
  pinMode(motor_direito_re, OUTPUT);
  pinMode(mDireto, OUTPUT);

  pinMode(potencia_esq, OUTPUT);
  pinMode(potencia_dir, OUTPUT);

  pinMode(SE, INPUT);
  pinMode(SD, INPUT);

  delay(5000);// Tempo de espera para inicialização   
}

void loop(){
  
  //Le os valores analogicos dos sensores Direita e Esquerda
  int L_Sensor = analogRead(SE);
  int R_Sensor = analogRead(SD);

  //Print de valores dos sensores para teste
      Serial.print(L_Sensor);
      Serial.print(R_Sensor);
     

  bool teste = false;

  // bool VLD = !(R_Sensor > ValorCorte);
  // bool VLE = !(L_Sensor > ValorCorte);

  //Define um valor booleano para os sensores 
  bool VLD = R_Sensor < ValorCorte;
  bool VLE = L_Sensor < ValorCorte;

  //Convertendo a distância em CM e lendo o sensor
  DistanciaemCM = SensorUltrassonico1.convert(SensorUltrassonico1.timing(), Ultrasonic::CM);

  Serial.print(DistanciaemCM);
  Serial.println(" cm");

  if (DistanciaemCM <= 5) {// Se a distância lida pelo sensor for ou igual a 5 centimetros
    Serial.print("obstaculo")
  }
  }else{ //FRENTE
    if(!(VLE)&&!(VLD)){

      analogWrite (potencia_dir,0); //PARA     
      analogWrite (potencia_esq,0);
      delay(500);

    }
    if((VLE)&&(VLD)){
      analogWrite (potencia_dir,velocidade);    
      analogWrite (potencia_esq,velocidade); 
      digitalWrite(mDireto,HIGH);  
      digitalWrite(mEsquerdo,HIGH);  
      Serial.print("FRENTE \n"); //TESTE CONSOLE
      // delay(500); 
    }
    //DIREITA
    if((!VLE)&&(VLD)){
      analogWrite (potencia_dir,velocidade);   
      analogWrite (potencia_esq,velocidade);
      digitalWrite(mDireto,LOW);
      digitalWrite(mEsquerdo,HIGH);
      Serial.print("DIREITA \n"); //TESTE CONSOLE
      // delay(500); 
    }
    //ESQUERDA
    if((VLE)&&(!VLD)){
      analogWrite (potencia_dir,velocidade);     
      analogWrite (potencia_esq,velocidade); 
      digitalWrite(mDireto,HIGH);
      digitalWrite(mEsquerdo,LOW);
      Serial.print("ESQUERDA \n"); //TESTE CONSOLE
      // delay(500); 
    }
}

  

  
