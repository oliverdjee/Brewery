#include <ooPinChangeInt.h>
#include <cbiface.h>
#include <FlowCounters.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include "C:/Users/gagno_000/Documents/Arduino/Projects/libraries/Ds18b20/floatToString.h"
#define SENSOR_RESOLUTION 9
#define SENSOR_INDEX 0

//Recon Name fo RPi
String ArduinoID = "MEGA";
boolean SetConnected = false;
char* DevicesConnected[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};

//PINOUT
int ConnectionLed = 13;

int HERMSRecircPump = 6;
int BoilKettlePump = 5;

int StirMotor = 7;
int MillMotor = 8;

int WaterTankHeater =9;
int HERMSHeater = 10;

int FermentorTempSensorMiddle = 22;
int WaterTankTempSensorMiddle = 23;
int MashTunTempSensorMiddle = 24;
int HERMSTempSensorExit = 25;

int WaterTankValveOut = 32;     // 32 = Valve 5 of the autoBrew
int WaterTankValveIn = 33;    // 33 = Valve 10 of the autoBrew

int HERMSValveIn = 50;         // 50 = Valve 1 of the autoBrew
int HERMSRecircValveOut = 51;  // 51 = Valve 2 of the autoBrew
int MashTunValveIn = 26;       // 26 = Valve 3 of the autoBrew
int MashTunValveOut = 27;      // 27 = Valve 4 of the autoBrew

int BoilKettleValveIn = 38;    // 38 = Valve 9 of the autoBrew
int BoilKettleValveOut = 39;   // 39 = Valve 8 of the autoBrew

int FermentorValveIn = 44;    // 44 = Valve 7 of the autoBrew

int pHMeter = 11;

//TempSensors
DeviceAddress FermentorTempSensorMiddleAddress;
DeviceAddress WaterTankTempSensorMiddleAddress;
DeviceAddress MashTunTempSensorMiddleAddress;
DeviceAddress HERMSTempSensorExitAddress;
OneWire DriverFermentorTempSensorMiddle(FermentorTempSensorMiddle);
DallasTemperature FermentorTempSensorMiddleVar(&DriverFermentorTempSensorMiddle);
OneWire DriverWaterTankTempSensorMiddle(WaterTankTempSensorMiddle);
DallasTemperature WaterTankTempSensorMiddleVar(&DriverWaterTankTempSensorMiddle);
OneWire DriverMashTunTempSensorMiddle(MashTunTempSensorMiddle);
DallasTemperature MashTunTempSensorMiddleVar(&DriverMashTunTempSensorMiddle);
OneWire DriverHERMSTempSensorExit(HERMSTempSensorExit);
DallasTemperature HERMSTempSensorExitVar(&DriverHERMSTempSensorExit);

//Flow Sensors
FlowCounters WaterTankFlowCounterIn(A8,7.5);
FlowCounters WaterTankFlowCounterOut(A9,7.5);
FlowCounters MashTunFlowCounterOut(A10,7.5);

//DATA RECEPTION
byte value;
char EndingChar = '~';
int NEW_LINE_ASCII = 10;
String completeString = "";
char buffer[50];


//Field Variables
float Reading_FermentorTempSensorMiddle;
float Reading_WaterTankTempSensorMiddle;
float Reading_MashTunTempSensorMiddle;
float Reading_HERMSTempSensorExit;

float Reading_WaterTankFlowCounterIn;
float Reading_WaterTankFlowCounterOut;
float Reading_MashTunFlowCounterOut;

float Reading_pHMeter;

boolean State_ConnectionLed;

boolean State_HERMSRecircPump;
boolean State_BoilKettlePump;

boolean State_StirMotor;
boolean State_MillMotor;

boolean State_WaterTankHeater;
boolean State_HERMSHeater;

boolean State_WaterTankValveIn;
boolean State_WaterTankValveOut; 

boolean State_HERMSValveIn;       
boolean State_HERMSRecircValveOut;
boolean State_MashTunValveIn;
boolean State_MashTunValveOut;

boolean State_BoilKettleValveIn;
boolean State_BoilKettleValveOut;

boolean State_FermentorValveIn;

//Reading intervals for flowsensors
long LastRead;
int ReadDelay = 1000;

// Initialize Pins and Stuff...
void setup()
{ 
    LastRead = millis();
  
    //PINOUT
    pinMode(ConnectionLed,OUTPUT);
    
    pinMode(HERMSRecircPump,OUTPUT);
    pinMode(BoilKettlePump,OUTPUT);
    pinMode(StirMotor,OUTPUT);
    pinMode(MillMotor,OUTPUT);
    
    pinMode(WaterTankHeater,OUTPUT);
    pinMode(HERMSHeater,OUTPUT);
    
    pinMode(WaterTankValveIn,OUTPUT);     // Valve 5 of the autoBrew
    pinMode(WaterTankValveOut,OUTPUT);    // Valve 10 of the autoBrew
    
    pinMode(HERMSValveIn,OUTPUT);     // Valve 3 of the autoBrew
    pinMode(HERMSRecircValveOut,OUTPUT);    // Valve 4 of the autoBrew
    pinMode(MashTunValveIn,OUTPUT);     // Valve 1 of the autoBrew
    pinMode(MashTunValveOut,OUTPUT);    // Valve 2 of the autoBrew
    
    pinMode(BoilKettleValveIn,OUTPUT);
    pinMode(BoilKettleValveOut,OUTPUT);
 
    pinMode(FermentorValveIn,OUTPUT);
    
    //Pin Starting Voltage
    digitalWrite(ConnectionLed,LOW);
    
    digitalWrite(HERMSRecircPump,LOW);
    digitalWrite(BoilKettlePump,LOW);
    digitalWrite(StirMotor,LOW);
    digitalWrite(MillMotor,LOW);
    
    digitalWrite(WaterTankHeater,LOW);
    digitalWrite(HERMSHeater,LOW);
    
    digitalWrite(WaterTankValveIn,LOW);     // Valve 5 of the autoBrew
    digitalWrite(WaterTankValveOut,LOW);    // Valve 10 of the autoBrew
    
    digitalWrite(HERMSValveIn,LOW);     // Valve 3 of the autoBrew
    digitalWrite(HERMSRecircValveOut,LOW);    // Valve 4 of the autoBrew
    digitalWrite(MashTunValveIn,LOW);     // Valve 1 of the autoBrew
    digitalWrite(MashTunValveOut,LOW);    // Valve 2 of the autoBrew
    
    digitalWrite(BoilKettleValveIn,LOW);
    digitalWrite(BoilKettleValveOut,LOW);
    
    digitalWrite(FermentorValveIn,LOW);
    
    //Initializing Field Variables

    Reading_FermentorTempSensorMiddle = 0;    
    Reading_WaterTankTempSensorMiddle = 0;
    Reading_MashTunTempSensorMiddle = 0;
    Reading_HERMSTempSensorExit = 0;
    
    Reading_WaterTankFlowCounterIn = 0;
    Reading_WaterTankFlowCounterOut = 0;
    Reading_MashTunFlowCounterOut = 0;

    Reading_pHMeter = 0;
    
    State_ConnectionLed = false;
    
    State_HERMSRecircPump = false;
    State_BoilKettlePump = false;
    
    State_StirMotor = false;
    State_MillMotor = false;
    
    State_WaterTankHeater = false;
    State_HERMSHeater = false;
    
    State_WaterTankValveIn = false;
    State_WaterTankValveOut = false; 
    
    State_HERMSValveIn = false;       
    State_HERMSRecircValveOut = false;
    State_MashTunValveIn = false;
    State_MashTunValveOut = false;
    
    State_BoilKettleValveIn=false;
    State_BoilKettleValveOut=false;
    
    State_FermentorValveIn=false;
    
    //Memory for DATA reception
    completeString.reserve(100);  //Allocate 100 bytes for the incoming Command
    
    //Serial Communication Connection Step
    Serial.begin(115200, SERIAL_8N1);
    FermentorTempSensorMiddleVar.begin();
    FermentorTempSensorMiddleVar.getAddress(FermentorTempSensorMiddleAddress, 0);
    FermentorTempSensorMiddleVar.setResolution(FermentorTempSensorMiddleAddress, SENSOR_RESOLUTION);
    WaterTankTempSensorMiddleVar.begin();
    WaterTankTempSensorMiddleVar.getAddress(WaterTankTempSensorMiddleAddress, 0);
    WaterTankTempSensorMiddleVar.setResolution(WaterTankTempSensorMiddleAddress, SENSOR_RESOLUTION);
    MashTunTempSensorMiddleVar.begin();
    MashTunTempSensorMiddleVar.getAddress(MashTunTempSensorMiddleAddress, 0);
    MashTunTempSensorMiddleVar.setResolution(MashTunTempSensorMiddleAddress, SENSOR_RESOLUTION);
    HERMSTempSensorExitVar.begin();
    HERMSTempSensorExitVar.getAddress(HERMSTempSensorExitAddress, 0);
    HERMSTempSensorExitVar.setResolution(HERMSTempSensorExitAddress, SENSOR_RESOLUTION);
} 

void loop ()    
{
     
    if(SetConnected == true)
    {
        long time = millis();
        long interval = time-LastRead;
        if(interval >= ReadDelay)
        {
          
            //Reads FlowSensors every ReadDelay and adds the reading to previous volumes until it is reset by the Rpi Controller.
            Reading_WaterTankFlowCounterIn += (WaterTankFlowCounterIn.readLitersPerMin())/60.0 * interval/1000;
            Reading_WaterTankFlowCounterOut += (WaterTankFlowCounterOut.readLitersPerMin())/60.0 * interval/1000;;
            Reading_MashTunFlowCounterOut += (MashTunFlowCounterOut.readLitersPerMin())/60.0 * interval/1000;;
            LastRead = millis();
        }
        
    }      
    else
    {
        digitalWrite(ConnectionLed,LOW);
    }
}   

//Functions
void serialEvent()
{
    if(Serial.available() > 0)
    {
        value = Serial.read();
        if(value!=(byte)EndingChar)
        {
            completeString+=(char)value;
        }
        else
        {
            //TEMPERATURE SENSORS
            if(completeString.equals("03R"))
            {
              String SensorName = "03";
              String Spacer = " ";  
              WaterTankTempSensorMiddleVar.requestTemperatures();
              String Temperature = (floatToString(buffer, WaterTankTempSensorMiddleVar.getTempCByIndex(0) , 1));
              String data = SensorName+Spacer+Temperature;
              printString(data);
            }

            else if(completeString.equals("23R"))
            {
              String SensorName = "23";
              String Spacer = " ";  
              FermentorTempSensorMiddleVar.requestTemperatures();
              String Temperature = (floatToString(buffer, FermentorTempSensorMiddleVar.getTempCByIndex(0) , 1));
              String data = SensorName+Spacer+Temperature;
              printString(data);
            }
            
            else if(completeString.equals("02R"))
            {
              String SensorName = "02";
              String Spacer = " ";  
              MashTunTempSensorMiddleVar.requestTemperatures();
              String Temperature = (floatToString(buffer, MashTunTempSensorMiddleVar.getTempCByIndex(0) , 1));
              String data = SensorName+Spacer+Temperature;
              printString(data);
            }
            
            else if(completeString.equals("24R"))
            {
              String SensorName = "24";
              String Spacer = " ";  
              HERMSTempSensorExitVar.requestTemperatures();
              String Temperature = (floatToString(buffer, HERMSTempSensorExitVar.getTempCByIndex(0) , 1));
              String data = SensorName+Spacer+Temperature;
              printString(data);
            }
            
            else if(completeString.equals("04R"))
            {
              String SensorName = "04";
              String Spacer = " ";  
              String Volume = (floatToString(buffer, Reading_WaterTankFlowCounterIn, 1));
              String data = SensorName+Spacer+Volume;
              printString(data);
              Reading_WaterTankFlowCounterIn = 0.0; // Reinitialize the volume to 0
            }
            
            else if(completeString.equals("05R"))
            { 
              String SensorName = "05";
              String Spacer = " ";  
              String Volume = (floatToString(buffer, Reading_WaterTankFlowCounterOut, 1));
              String data = SensorName+Spacer+Volume;
              printString(data);
              Reading_WaterTankFlowCounterOut = 0.0; // Reinitialize the volume to 0
            }
            
             
            else if(completeString.equals("06R"))
            {
              String SensorName = "06";
              String Spacer = " ";
              String Volume = (floatToString(buffer, Reading_MashTunFlowCounterOut, 1));
              String data = SensorName+Spacer+Volume;
              printString(data);
              Reading_MashTunFlowCounterOut = 0.0; // Reinitialize the volume to 0
            }

             else if(completeString.equals("22R"))
            {
              String SensorName = "22";
              String Spacer = " ";  
              float val = (float)analogRead(pHMeter);    // read the analog input pin
              float pH = (val-(float)10.995)/(float)54.456;
              String pHString = (floatToString(buffer,pH,2));
              String data = SensorName+Spacer+pHString;
              printString(data);
            }
          
            //MOTORS
            else if(completeString.equals("07true"))
            {
                digitalWrite(StirMotor, HIGH);
                SendState("07", 1);
            }
            else if(completeString.equals("07false"))
            {
                digitalWrite(StirMotor, LOW);
                SendState("07", 0);
            }
            
            else if(completeString.equals("08true"))
            {
                digitalWrite(MillMotor, HIGH);
                SendState("08", 1);
            }
            else if(completeString.equals("08false"))
            {
                digitalWrite(MillMotor, LOW);
                SendState("08", 0);
            }
            
            
            //HEATERS
            else if(completeString.equals("11false"))
            { 
                digitalWrite(WaterTankHeater,LOW);
                SendState("11", 0);
            }
            else if(completeString.equals("11true"))
            {  
                digitalWrite(WaterTankHeater,HIGH);
                SendState("11", 1);
            }
            
            else if(completeString.equals("12false"))
            { 
                digitalWrite(HERMSHeater,LOW);
                SendState("12", 0);
            }
            else if(completeString.equals("12true"))
            {
                digitalWrite(HERMSHeater,HIGH);
                SendState("12", 1);
            }
            
            
            //PUMPS
            else if(completeString.equals("10false"))
            {   
                digitalWrite(BoilKettlePump,LOW);
                SendState("10", 0);
            }
            else if(completeString.equals("10true"))
            { 
                digitalWrite(BoilKettlePump,HIGH);
                SendState("10", 1);
            }
            
            else if(completeString.equals("09false"))
            {   
                digitalWrite(HERMSRecircPump,LOW);
                SendState("09", 0);
            }
            else if(completeString.equals("09true"))
            { 
                digitalWrite(HERMSRecircPump,HIGH);
                SendState("09", 1);
            }
            
            
            //VALVES
            else if(completeString.equals("13true"))
            {
                digitalWrite(WaterTankValveIn,HIGH);
                SendState("13", 1);
            }
            else if(completeString.equals("14true"))
            {
                digitalWrite(WaterTankValveOut,HIGH);
                SendState("14", 1);
            }
            else if(completeString.equals("13false"))
            {  
                digitalWrite(WaterTankValveIn,LOW);
                SendState("13", 0);
            }
            else if(completeString.equals("14false"))
            {   
                digitalWrite(WaterTankValveOut,LOW);
                SendState("14", 0);
            }
            else if(completeString.equals("17true"))
            {
                digitalWrite(MashTunValveIn,HIGH);
                SendState("17", 1);
            }
            else if(completeString.equals("17false"))
            {
                digitalWrite(MashTunValveIn,LOW);
                SendState("17", 0);
            }
            else if(completeString.equals("18true"))
            { 
                digitalWrite(MashTunValveOut,HIGH);
                SendState("18", 1);
            }
            else if(completeString.equals("18false"))
            {
                digitalWrite(MashTunValveOut,LOW);
                SendState("18", 0);
            }
           
            else if(completeString.equals("15true"))
            { 
                digitalWrite(HERMSValveIn,HIGH);
                SendState("15", 1);
            }
            else if(completeString.equals("15false"))
            {
                digitalWrite(HERMSValveIn,LOW);
                SendState("15", 0);
            }
            else if(completeString.equals("16true"))
            { 
                digitalWrite(HERMSRecircValveOut,HIGH);
                SendState("16", 1);
            }
            else if(completeString.equals("16false"))
            {
                digitalWrite(HERMSRecircValveOut,LOW);
                SendState("16", 0);
            }
            
            else if(completeString.equals("19true"))
            { 
                digitalWrite(BoilKettleValveIn,HIGH);
                SendState("19", 1);
            }
            else if(completeString.equals("19false"))
            {
                digitalWrite(BoilKettleValveIn,LOW);
                SendState("19", 0);
            }
            else if(completeString.equals("20true"))
            { 
                digitalWrite(BoilKettleValveOut,HIGH);
                SendState("20", 1);
            }
            else if(completeString.equals("20false"))
            {
                digitalWrite(BoilKettleValveOut,LOW);
                SendState("20", 0);
            }
            
            else if(completeString.equals("21true"))
            { 
                digitalWrite(FermentorValveIn,HIGH);
                SendState("21", 1);
            }
            else if(completeString.equals("21false"))
            {
                digitalWrite(FermentorValveIn,LOW);
                SendState("21", 0);
            }
            
            //CONNECTION
            else if(completeString.equals("connect"))
            {
                printString("Connecting Devices");
                digitalWrite(ConnectionLed,LOW);
                digitalWrite(HERMSRecircPump,LOW);
                digitalWrite(BoilKettlePump,LOW);
                digitalWrite(StirMotor,LOW);
                digitalWrite(MillMotor,LOW);
                
                digitalWrite(WaterTankHeater,LOW);
                digitalWrite(HERMSHeater,LOW);
                
                digitalWrite(WaterTankValveIn,LOW);     // Valve 5 of the autoBrew
                digitalWrite(WaterTankValveOut,LOW);    // Valve 10 of the autoBrew
                
                digitalWrite(HERMSValveIn,LOW);     // Valve 3 of the autoBrew
                digitalWrite(HERMSRecircValveOut,LOW);    // Valve 4 of the autoBrew
                digitalWrite(MashTunValveIn,LOW);     // Valve 1 of the autoBrew
                digitalWrite(MashTunValveOut,LOW);    // Valve 2 of the autoBrew
    
                digitalWrite(BoilKettleValveIn,LOW);
                digitalWrite(BoilKettleValveOut,LOW);
                
                digitalWrite(FermentorValveIn,LOW);
                
                for(int i = 0; i< sizeof(DevicesConnected);i++)
                {  
                    String Device = DevicesConnected[i];
                    if(Device == ""){break;}
                    printString("c "+Device);
                    delay(500);
                }
                LastRead = millis();
                SetConnected = true;
                
            }
            else if(completeString.equals("disconnect"))
            {
                SetConnected = false;
                digitalWrite(ConnectionLed,LOW);
    
                digitalWrite(HERMSRecircPump,LOW);
                digitalWrite(BoilKettlePump,LOW);
                digitalWrite(StirMotor,LOW);
                digitalWrite(MillMotor,LOW);
                
                digitalWrite(WaterTankHeater,LOW);
                digitalWrite(HERMSHeater,LOW);
                
                digitalWrite(WaterTankValveIn,LOW);     // Valve 5 of the autoBrew
                digitalWrite(WaterTankValveOut,LOW);    // Valve 10 of the autoBrew
                digitalWrite(HERMSValveIn,LOW);     // Valve 3 of the autoBrew
                digitalWrite(HERMSRecircValveOut,LOW);    // Valve 4 of the autoBrew
                digitalWrite(MashTunValveIn,LOW);     // Valve 1 of the autoBrew
                digitalWrite(MashTunValveOut,LOW);    // Valve 2 of the autoBrew
                
                digitalWrite(BoilKettleValveIn,LOW);
                digitalWrite(BoilKettleValveOut,LOW);
                
                digitalWrite(FermentorValveIn,LOW);
                Reading_MashTunFlowCounterOut = 0;
                Reading_WaterTankFlowCounterOut = 0;
                Reading_WaterTankFlowCounterIn = 0;
                printString("disconnected "+ArduinoID);
            }
            else if(completeString.equals("Recon"))
            {  
                //Arduino send its info to the RPI
                printString(ArduinoID);
            }
            
            else
            {
                printString("Cannot Process Command: "+completeString); 
            }
            completeString = "";
           
        }
    }   
}

void printString(String string)
{
    Serial.write("~");
    for(int x = 0; x < string.length(); x++)
    {
         Serial.write(string.charAt(x));
    }  
    Serial.write((byte)NEW_LINE_ASCII);
}
void SendState(String name, boolean state){
    String _state = "";  
    if(state)
    {
        _state = "true";
    }
    else
    {
        _state = "false";
    }
    String s = name+" "+_state;
    printString(s);
}

void SendReading(String name, float Read, int decimals)
{
    char buffer[25];  
    String SensorName = name; 
    String Temperature = (floatToString(buffer, Read , decimals));
    String data = SensorName+" "+Temperature;
    printString(data);
}
      
