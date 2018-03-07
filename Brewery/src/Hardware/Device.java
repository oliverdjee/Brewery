package Hardware;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.event.EventListenerList;

import org.jfree.data.time.Second;
import org.jfree.ui.RefineryUtilities;
import Communication.SerialSend;
import Graphs.Graph;
import Graphs.MyTempGraph;

public class Device extends JButton
{
	public static final int STATE_EVENT = 1;
	public static final int READING_EVENT = 0;
	
	public static final String pHMeter = "pHMeter";
	public static final String TemperatureSensor = "TemperatureSensor";
	public static final String FlowSensor = "FlowSensor";
	public static final String LightSensor = "LightSensor";
	public static final String Valve = "Valve";
	public static final String Stirrer = "Stirrer";
	public static final String Pump = "Pump";
	public static final String Laser = "Laser";
	public static final String Heater = "Heater";
	public static final String Motor = "Motor";
	public static final String Mill = "Mill";
	public static final long PollingDelay = 3000;
	private static final long serialVersionUID = 1L;
	public static final String[] ANALOGUE = new String[]{"TempSensor",TemperatureSensor,FlowSensor,
														   LightSensor,pHMeter};
	public static final String[] DIGITAL = new String[]{"Valve","Stir","Stirrer","Heater","Motor","Mill",
														  "Pump","Laser"};
	
	public static final Color OPEN_COLOR = MyTempGraph.myGreen;
	public static final Color CLOSED_COLOR = MyTempGraph.myRed;
	public static final Color WAIT_COLOR = Color.YELLOW;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	
	private boolean Display;
	private EventListenerList		StateChangelistenerList;
	private String					ArduinoController;
	private boolean 				Open;
	private String 					Name;
	private String 					Type;
	private double 					Reading;
	private Boolean 				Connected;
	private long 					RequestDelay;
	private PollData				PollThread;
	private boolean					Polling;
	private double					VolumeCorrection;
	private Graph					Graph;
	private JButton 				GraphButton;
	private SerialSend 				Communicator;
	
	public Device(String name, String type, String Controller)
	{
		Polling							= false;
		RequestDelay 					= PollingDelay;
		Connected 						= false;
		Display 						= false;
		ArduinoController 				= Controller;
		Open 							= false;
		Name 							= name;
		Type 							= type;
		StateChangelistenerList 		= new EventListenerList();
		Reading							= (float) -1000;
		VolumeCorrection = 1;
		InitGraph();
		InitDeviceButton();
	}
	
	private void InitDeviceButton() {
		try{
			setIcon(new ImageIcon(this.getClass().getResource("/"+Type+"Icon.png")));
		}catch(Exception Err){setText("N/A");}
		setEnabled(Connected.booleanValue());
		setVisible(true);
		setName(Name);
		setSize(new Dimension(28,28));
		setBorder(new MatteBorder(1, 1, 7, 1, CLOSED_COLOR));
		setBackground(BACKGROUND_COLOR);
		addActionListener(new ButtonPressedListener());
	}

	private void InitGraph() {
		if(Type == null)
		{
			Graph = null;
			return;
		}
		if(Type.equals(Device.TemperatureSensor))
		{
			Graph 						= new Graph(
											Name,
											"Time (hh:mm:ss)",
											"Temperature (Celcius)"
											);
			GraphButton = new JButton();
			GraphButton.setIcon(new ImageIcon(this.getClass().getResource("/Graph_Icon.png")));
			GraphButton.addActionListener(new GraphButtonPressedListener());
			GraphButton.setName(Name+"_Graph");
			GraphButton.setSize(new Dimension(37,37));
			GraphButton.setBackground(BACKGROUND_COLOR);
		}
		if(Type.equals(Device.pHMeter))
		{
			Graph 						= new Graph(
											Name,
											"Time (hh:mm:ss)",
											"pH"
											);
			GraphButton = new JButton();
			GraphButton.setIcon(new ImageIcon(this.getClass().getResource("/Graph_Icon.png")));
			GraphButton.setName(Name+"_Graph");
			GraphButton.setSize(new Dimension(37,37));
			GraphButton.setBackground(BACKGROUND_COLOR);
			GraphButton.addActionListener(new GraphButtonPressedListener());
		}
		if(Type.equals(Device.FlowSensor))
		{
			Graph 						= new Graph(
											Name,
											"Time (hh:mm:ss)",
											"Volume (L)"
											);
			GraphButton = new JButton();
			GraphButton.setIcon(new ImageIcon(this.getClass().getResource("/Graph_Icon.png")));
			GraphButton.setName(Name+"_Graph");
			GraphButton.setSize(new Dimension(37,37));
			GraphButton.setBackground(BACKGROUND_COLOR);
			GraphButton.addActionListener(new GraphButtonPressedListener());
		}
	}

	public JButton getGraphButton()
	{
		return this.GraphButton;
	}
	
	public void DisplayReading(boolean display)
	{
		Display = display;
	}
	
	public void setArduinoController(String name)
	{
		ArduinoController = name;
	}
	
	public String getArduinoController()
	{
		return ArduinoController;
	}
	
	public SerialSend getCommunicator() 
	{
		return Communicator ;
	}
	
	public boolean Init(SerialSend Comm)
	{
		if(Comm != null)
		{
			setCommunicator(Comm);
			Communicator.addSerialEventListener(new SerialEventListener());
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isOpen()
	{
		return Open;
	}
	
	public void setOpen(Boolean state)
	{
		if(Open == state)
		{
			return;
		}
		else
		{
			Open = state;
			setBorder(new MatteBorder(1, 1, 7, 1, WAIT_COLOR));
			Communicator.QueueCommand(Name+String.valueOf(state), Communicator.GetSerialPortMap().get(ArduinoController));
		}
	}


	public void RequestReading()
	{
		setBorder(new MatteBorder(1, 1, 7, 1, WAIT_COLOR));
		Communicator.QueueCommand(Name+"R", Communicator.GetSerialPortMap().get(ArduinoController));
	}

	
	public String getName()
	{
		return Name;
	}
	
	public void setName(String name)
	{
		Name = name;
	}
	
	
	public boolean isConnected() {
		return Connected;
	}

	public void setConnected(boolean connected) {
		Connected = connected;
		setEnabled(connected);
	}
		
	public String getType() 
	{
		return Type;
	}

	public void setType(String type) 
	{
		Type = type;
	}
	
	public long getRequestDelay() {
		return RequestDelay;
	}

	public void setRequestDelay(long requestDelay) {
		RequestDelay = requestDelay;
	}

	public double getReading()
	{
		return this.Reading;
	}
	
	public Device getDevice()
	{
		return this;
	}

	public Graph getGraph() 
	{
		return Graph;
	}

	public void setGraph(Graph graph) {
		Graph = graph;
	}

/*********************Private Functions / Classes*************************/	
	
	private void setCommunicator(SerialSend communicator) 
	{
		Communicator = communicator;
	}
	
	public double getVolumeCorrection() {
	return VolumeCorrection;
}

public void setVolumeCorrection(Float volumeCorrection) {
	VolumeCorrection = volumeCorrection;
}

	private class SerialEventListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				String[] Command= e.getActionCommand().trim().split(" ");
			
				if(Command[0].equals(Name) == true)
				{
					
					for(String item:DIGITAL)
					{
						if(Type.equalsIgnoreCase(item))
						{
							Open = Boolean.parseBoolean(Command[1]);
							if(!Open){
								setBorder(new MatteBorder(1, 1, 7, 1, CLOSED_COLOR));
							}
							else{
								setBorder(new MatteBorder(1, 1, 7, 1, OPEN_COLOR));
							}
							
							ActionEvent Evt = new ActionEvent(getDevice(), Device.STATE_EVENT, Command[1]);
							fireStateChangeEvent(Evt);
							return;
						}
					}
					for(String item:ANALOGUE)
					{
						
						if(Type.equalsIgnoreCase(item))
						{
							if(Double.parseDouble(Command[1]) > 0)
							{
								Reading = Double.parseDouble(Command[1]);
								
								if(Type.equalsIgnoreCase("FlowSensor"))
								{
									Reading = Reading*VolumeCorrection;
								}
								String read = String.valueOf(Reading);
								if(Display){
									setText(String.format("%.1f",read));
									setBorder(new MatteBorder(1, 1, 7, 1, OPEN_COLOR));
								}
								//Update Graph dataSeries
								try
								{
									Graph.getData().add(new Second(new Date()), Reading );
								}catch(Exception e1){
									System.out.println("Cannot Update Graph: "+ Reading);
								}
								
								ActionEvent Evt = new ActionEvent(getDevice(), Device.READING_EVENT, read);
								fireStateChangeEvent(Evt);
								return;
							}
							
						}
					}
				}
				else if(Command[0].equals("c") == true)
				{
					if(Command[1].equals(Name)){
						Connected = true;
						setEnabled(true);
					}
					return;
				}
			}
			catch(Exception Err){
				System.out.println("Non-Standard SerialEvent: "+ e.getActionCommand());
			}
			
		}
		
	}
	
	public void addStateChangeEventListener(ActionListener listener)
	{
		StateChangelistenerList.add(ActionListener.class, listener);
	}
	
	public void removeStateChangeEventListener(ActionListener listener)
	{
		StateChangelistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireStateChangeEvent(ActionEvent evt)
	{
		Object[] listeners = StateChangelistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	
	private class GraphButtonPressedListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ShowGraph();
		}
	}
	
	private class ButtonPressedListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(Type.equalsIgnoreCase(TemperatureSensor)) 
			{
				if(Polling)
				{
					StopPolling();
				}
				else
				{
					StartPolling(RequestDelay);
				}
			}
			
			else if(Type.equalsIgnoreCase(pHMeter)) 
			{
				if(Polling)
				{
					StopPolling();
				}
				else
				{
					StartPolling(RequestDelay);
				}
			}
			
			else if(Type.equalsIgnoreCase(FlowSensor))
			{
				if(Polling)
				{
					StopPolling();
				}
				else
				{
					//ADD WHAT TO DO HERE
				}
				
			}
			else
			{
				setOpen(!Open);
			}
		}
		
	}
	
	public void StopPolling()
	{
		PollThread.Stop();
	}
	
	public void StartPolling(long Delay)
	{
		if(!Polling)
		{
			Polling = true;
			PollThread = new PollData(Delay);
			PollThread.start();
		}
		else{
			System.out.println("Device Already Polling");
		}
	}
	
	private class PollData extends Thread
	{
		boolean Stop;
		long PollDelay;
		
		public void Stop()
		{
			Stop = true;
		}
		
		public PollData(long Delay)
		{
			PollDelay = Delay;
			Stop = false;
		}
		@Override
		public void run() {
			Stop = false;
			System.out.println("Polling Started: "+Name);
			while(!Stop)
			{
				setBorder(new MatteBorder(1, 1, 7, 1, OPEN_COLOR));
				RequestReading();
				try {
					Thread.sleep(PollDelay);
				} 
				catch (InterruptedException e) {
					setBorder(new MatteBorder(1, 1, 7, 1, CLOSED_COLOR));
					System.out.println("Interrupted Exception Detected");
					e.printStackTrace();
				}
				
			}
			Polling = false;
			System.out.println("Polling Stopped: "+Name);
			setBorder(new MatteBorder(1, 1, 7, 1, CLOSED_COLOR));
		}
	}

	public void setVolumeCorrection(double d) {
		VolumeCorrection = d;
		
	}
	
	public void ShowGraph() {
		if(Graph == null)
		{
			return;
		}
		Device that = this;
		EventQueue.invokeLater(new Runnable() {
		   @Override
            public void run() {
			    {
			    	Graph.LinkDevice(that);
			    }
			    Graph.pack();
                RefineryUtilities.centerFrameOnScreen(Graph);
                Graph.setVisible(true);
            }
        });
    }

	public void showGraphButton() {
		if (GraphButton != null)
		{
			GraphButton.setBounds(16, getY(), 28, 28);
			GraphButton.setVisible(true);
			GraphButton.setEnabled(true);
		}
		
	}
}
