package Communication;

import gnu.io.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TooManyListenersException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.event.EventListenerList;

public class SerialSendWindows implements SerialPortEventListener
{
private ArrayList<String>					ArduinoList								= null;
	
	private EventListenerList				SerialEventlistenerList 				= null;
	
	//To hold the ports that will be found
	@SuppressWarnings("rawtypes")
	private Enumeration 						ports 									= null;
	
	//HashMap for mapping variables to corresponding SerialPort opened
	private HashMap<SerialPort,String> 			ReceivedMap;
	private HashMap<String,CommPortIdentifier>  portMap;
	private HashMap<String,SerialPort> 			SerialPortMap;
	
	//Object referring to the opened Serial Port
	private CommPortIdentifier 					selectedPortIdentifier 					= null;
	
	//Timeout for trying to connect to a port
	final static int 							TIMEOUT 								= 2000;

	//ASCII values of data delimiters
	final static int 							SPACE_ASCII 							= 32; // space " "
	final static int 							DASH_ASCII 								= 45; // dash "-" for new message
	final static int 							NEW_LINE_ASCII 							= 10; // line feed "\n" for end of message
	private Object								sync;
	//Recording any issues with this String variable
	private String 								logText 								= null;
	
	//Constructor
	public SerialSendWindows(ArrayList<String> arduinoList)
	{
		ArduinoList = new ArrayList<String>();
		ArduinoList = arduinoList;
		SerialEventlistenerList = new EventListenerList();					
		SerialPortMap 			= new HashMap<String,SerialPort>();
		portMap 				= new HashMap<String,CommPortIdentifier>();
		ReceivedMap 			= new HashMap<SerialPort,String>();
		logText 				= "";
		sync = new Object();
	}
	
	public boolean InitSerialComm() throws UnsupportedCommOperationException, InterruptedException
	{
		boolean fRet 		= false;
		SearchForPorts();
		ListenAndAssign();
		return fRet;
		
	}
	
	public HashMap<String,CommPortIdentifier> GetPortMap()
	{
		return(this.portMap);
	}
	
	public HashMap<String,SerialPort> GetSerialPortMap()
	{
		return(this.SerialPortMap);
	}

	//Method to search for ports
	public void SearchForPorts()
	{
		ports = CommPortIdentifier.getPortIdentifiers(); //returns an enumeration of ports 
		logText = "Searching for available Ports ...\n";												 //to the ports variable
		while(ports.hasMoreElements())
		{
			CommPortIdentifier currentPort = (CommPortIdentifier) ports.nextElement();
			
			//Only select Serial ports
			if(currentPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
				portMap.put(currentPort.getName(), currentPort);
			}
		}
		logText = portMap.size()+ " available ports : " + portMap.keySet().toString()+"\n";
		logText = portMap.keySet().toString()+" is/are connected Serial Port(s)\n";
	}
	
	public boolean ListenAndAssign() throws UnsupportedCommOperationException, InterruptedException
	{
		boolean fRet = false;
		for(String port:portMap.keySet())
		{
			int maxAttempt = 3;
			int Attempt = 1;
			
			while(Attempt <= maxAttempt)
			{
				SerialPort serialPort = Connect(port);
				Thread.sleep(2000);
				ReceivedMap.put(serialPort,new String(""));
	
				InitListener(serialPort);
				serialPort.setOutputBufferSize(256);
				logText = "Looking for port Info ...\n";
				logText = "Attempt " + String.valueOf(Attempt)+ 
						" of " +  String.valueOf(maxAttempt)+ "...\n";
				QueueCommand("Recon", serialPort);
				long time = System.currentTimeMillis();
				boolean out = false;
				while(ReceivedMap.get(serialPort).isEmpty() == true && out == false)
				{
					Thread.sleep(1000);
					if(System.currentTimeMillis() > time + 3000)
					{
						out = true;
					}
				}
				
				if(ArduinoList.contains(ReceivedMap.get(serialPort)) == true)
				{

					logText = "Attempt Successfull\n";
					String PortId = ReceivedMap.get(serialPort);
					SerialPortMap.put(PortId,serialPort);
					Thread.sleep(500);
					QueueCommand("connect", serialPort);
					logText = "Ready for communications with "+ PortId + "\n";
					System.out.println(logText);
					fRet = true;
					break;
				}
				else
				{
					ReceivedMap.remove(serialPort);
					Disconnect(serialPort);
					logText = "Attempt Failed"+ "\n";
					Attempt++;
					fRet = false;
				}
				
			}
			
		}
		return fRet;
	}
	
	public boolean QueueCommand(String commandToSend, SerialPort serialPort)
	{
		synchronized(sync)
		{
			boolean fRet = false;
			byte[] byteS2 = commandToSend.getBytes();
			for(int x = 0; x<byteS2.length;x++)
			{
				WriteData((char)byteS2[x], serialPort);
				if(x==byteS2.length-1)
				{
					EndOfData(serialPort);
				}
			}
			fRet = true;
			return fRet;
		}
		
	}
	
	
	//Next is a method to connect to the Serial Ports
	@SuppressWarnings("null")
	public SerialPort Connect(String SelectedPort) throws UnsupportedCommOperationException
	{
		selectedPortIdentifier = (CommPortIdentifier)portMap.get(SelectedPort);
		//now opening the connection with the arduino
		CommPort commPort = null;
		SerialPort serialPort = null;
		try
		{
			//this step is opening the connection for data send/receive
			commPort = selectedPortIdentifier.open(SelectedPort, TIMEOUT);
			serialPort = (SerialPort)commPort;
			logText = "Connecting to: "+serialPort.getName()+"\n";
			//First set the parameters of the serial port
			//The arduino listen to Baud 9600, 8N1 start and stop bits
			//Therefore, I will set those params to be the same on the selected port
			serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			logText = serialPort.getName() + " opened successfully!\n";
		}
		catch(PortInUseException e)
		{
			logText = serialPort.getName() + " is in use. ("+e.toString()+")\n";
		}
		catch(Exception e)
		{
			logText = serialPort.getName() + " failed to open. ("+ e.toString()+ ")\n";
		}
		return serialPort;
		
	}
	
	public void InitListener(SerialPort serialPort)
	{
		try
		{
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			logText = "Initializing Listener on port "+serialPort.getName() +"\n";
		}
		catch(TooManyListenersException e)
		{
			logText = "Too many Listeners ("+e.toString()+")\n";
		}
	}
	
	
	//Method for disconnecting of the Port
	public void Disconnect(SerialPort serialPort)
	{
		try
		{
			QueueCommand("disconnect", serialPort);
			Thread.sleep(500);
			String name = serialPort.getName();
			serialPort.removeEventListener();
			serialPort.getInputStream().close();
			serialPort.getOutputStream().close();
			serialPort.close();
			logText = name + " disconnected Successfully\n";
		}
		catch(Exception e)
		{
			logText = "Failed to disconnect from the port ("+e.toString()+")";
		}
	}
	
	//Method for writing data to the Arduino!
		public void WriteData(char message, SerialPort serialPort)
		{
			try
			{
				serialPort.getOutputStream().write((int)message);
			}
			catch(Exception e)
			{
				
			}
		}
		
		public void EndOfData(SerialPort serialPort)
		{
			try
			{
				serialPort.getOutputStream().write('~');
				serialPort.getOutputStream().flush();
			}
			catch(Exception e)
			{
				logText = "Failed to write data("+e.toString()+")\n";
			}
		}
	
	public void addSerialEventListener(ActionListener listener)
	{
		SerialEventlistenerList.add(ActionListener.class, listener);
	}
	
	public void removeSerialEventListener(ActionListener listener)
	{
		SerialEventlistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireSerialEvent(ActionEvent evt)
	{
		Object[] listeners = SerialEventlistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent evt) 
	{
		if(evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
		{
			SerialPort serialPort = (SerialPort) evt.getSource();
			try
			{
				byte singleData = (byte)(serialPort.getInputStream().read());
				if(singleData == '~')
				{
					ReceivedMap.put(serialPort, "");
				}
				String OldData  = ReceivedMap.get(serialPort);
				if(singleData != NEW_LINE_ASCII && singleData != '~')
				{
					OldData += (char)singleData;
					ReceivedMap.put(serialPort,OldData); // Update the old value by overwriting what is at serialPort
					
				}
				else if(singleData == NEW_LINE_ASCII)
				{
					ReceivedMap.put(serialPort,OldData.replace("\n", ""));
					//boolean assignSuccessful = AssignDataToVariable(serialPort);
					ActionEvent Data = new ActionEvent(evt, 1, OldData.replace("\n",""));
					fireSerialEvent(Data);
					
					logText = "Received Data: " + OldData;
					System.out.println(logText);
					//if(assignSuccessful == false)
					//{
					//	logText = "Received Unkown Data: " + OldData;
					//}
				}
			}
			catch(Exception e)
			{
				logText = "Failed to read data from ("+e.toString()+")\n";
			}
		}
		
	}
	
	public String GetLogText()
	{
		return logText;
	}
	
	public static void main(String[] args) throws UnsupportedCommOperationException, InterruptedException, IOException
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("Uno");
		SerialSend test = new SerialSend(list);
		test.InitSerialComm();
		test.QueueCommand("WaterTankFlowSensorMiddletrue",test.GetSerialPortMap().get("Uno"));
		String s = "";
		while(s.equals("disconnect") == false)
		{
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("Data to send >>>   ");
			s = scan.next();
			byte[] byteS2 = s.getBytes();
			for(int x = 0; x<byteS2.length;x++)
			{
				test.WriteData((char)byteS2[x], test.GetSerialPortMap().get("Uno"));
				if(x==byteS2.length-1)
				{
					test.EndOfData(test.GetSerialPortMap().get("Uno"));
				}
			}
			System.out.println(s);
			if(s.equalsIgnoreCase("disconnect") == true)
			{
				Set<String> ketSet = test.GetSerialPortMap().keySet();
				for(String item:ketSet)
				{
					test.Disconnect( ((SerialPort) test.GetSerialPortMap().get(item)));
				}
			}
		}
	}
}
