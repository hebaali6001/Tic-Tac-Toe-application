
int const UP_BTN = 2;
int const DOWN_BTN = 4;
int const LEFT_BTN = 5;
int const RIGHT_BTN = 3;
int const E_BTN = 6;
int const F_BTN = 7;
int const JOYSTICK_BTN = 8;
int const JOYSTICK_AXIS_X = A0;
int const JOYSTICK_AXIS_Y = A1;
int buttons[] = {UP_BTN, DOWN_BTN, LEFT_BTN, RIGHT_BTN, E_BTN, F_BTN, JOYSTICK_BTN};
//===============================================================================
//  Initialization
//===============================================================================
void setup() {
  //Set all button pins as inputs with internal pullup resistors enabled.
  for (int i; i < 7; i++)  pinMode(buttons[i], INPUT_PULLUP);
  Serial.begin(9600);
}
//===============================================================================
//  Main
//===============================================================================
void loop() {
 int X = analogRead(JOYSTICK_AXIS_X);
  int Y = analogRead(JOYSTICK_AXIS_Y);
  //int x = map(X, 1, 1023, 1917, 2);
  //int y = map(Y, 1, 1023, 2, 1077);
  Serial.print("\nY=");
  Serial.print(Y);
      delay(100);

  Serial.print("\nX=");
  Serial.print(X);
      delay(100);
  
  if(digitalRead(UP_BTN)==0)
  {
    delay(100);
    if(digitalRead(UP_BTN)==0)
  {
      Serial.print("U");    
  }
  }
  
   if(digitalRead(DOWN_BTN)==0)
  {
    delay(100);
    if(digitalRead(DOWN_BTN)==0)
  {
      Serial.print("D"); 
  }   
  }

   if(digitalRead(LEFT_BTN)==0)
  {
    delay(100);
    if(digitalRead(LEFT_BTN)==0)
  {
      Serial.print("L");    
  }
  }
  
   if(digitalRead(RIGHT_BTN)==0)
  {
    delay(100);
     if(digitalRead(RIGHT_BTN)==0)
  {
      Serial.print("R");    
  }
  }
  
  if (digitalRead(E_BTN) == 0 || digitalRead(F_BTN) == 0 || digitalRead(JOYSTICK_BTN) == 0)
  {
        delay(100);
        if (digitalRead(E_BTN) == 0 || digitalRead(F_BTN) == 0 || digitalRead(JOYSTICK_BTN) == 0)
  {
    Serial.print("F");  
  }
  }
 // Serial.print("\tJOYSTICK BTN="),Serial.print(digitalRead(JOYSTICK_BTN));
      
  // Print the full X/Y joystick values (0-1023)
  //Serial.print("\tX="),Serial.print(analogRead(JOYSTICK_AXIS_X));
  //Serial.print("\tY="),Serial.println(analogRead(JOYSTICK_AXIS_Y)); 

 }