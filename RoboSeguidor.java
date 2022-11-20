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

int ValorCorte = 700; //Valor de Corta 700: Linha Preta 300: Linha Branca
const int velocidade = 150; //Velocidade do carrinho

void setup (){

  Serial.begin(5000); //INICIAR APÓS 5 SEGUNDOS
  pinMode(motor_esquerdo_re, OUTPUT);
  pinMode(mEsquerdo, OUTPUT);
  pinMode(motor_direito_re, OUTPUT);
  pinMode(mDireto, OUTPUT);

  pinMode(potencia_esq, OUTPUT);
  pinMode(potencia_dir, OUTPUT);

  pinMode(SE, INPUT);
  pinMode(SD, INPUT);
  delay(5000)
  
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
  bool VLD = R_Sensor > ValorCorte;
  bool VLE = L_Sensor > ValorCorte;


  if(!(VLE)&&!(VLD)){

    analogWrite (potencia_dir,0); //PARA     
    analogWrite (potencia_esq,0);
    delay(500);
    // if(!(VLE)&&!(VLD)){
    //   analogWrite (potencia_dir,velocidade);  //VELOCIDADE    
    //   analogWrite (potencia_esq,velocidade);  //VELOCIDADE
    //   digitalWrite(motor_esquerdo_re,HIGH); //SENTIDO DE ROTACAO
    //   digitalWrite(motor_direito_re,HIGH); //SENTIDO DE ROTACAO
    // } 
  }else{

    //FRENTE
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
}