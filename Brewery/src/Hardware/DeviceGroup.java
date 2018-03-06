package Hardware;

import gnu.io.UnsupportedCommOperationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.EventListenerList;

public class DeviceGroup {
	private String 					Name;
	
	private HashMap<String, Double>	Actual_T_Map;
	private float 					Actual_V;
	
	private Object[] 					Target_T;
	private Object[] 					Target_V;
	
	private ArrayList<Device>		Devices;
	
	private EventListenerList		DeviceStateChangelistenerList = null;
	
	private ArrayList<Device> FlowEntryDevices;
	private ArrayList<Device> FlowExitDevices;
	private ArrayList<Device> TemperatureDevices;

	private boolean Recirculating;
	
	
	
/**********************CONSTRUCTOR******************************/
	
	public DeviceGroup(String name)
	{
		Name 				= name;
		Target_V = new Object[3];
		Target_T = new Object[3];
		Actual_V 			= (float) 0;
		Actual_T_Map 			= new HashMap<String, Double>();
		Recirculating = false;
		DeviceStateChangelistenerList    = new EventListenerList();
		Devices	= new ArrayList<Device>();
		FlowEntryDevices = new ArrayList<Device>();
		FlowExitDevices = new ArrayList<Device>();
		TemperatureDevices = new ArrayList<Device>();
		
	}

	
	
/**********************Public Functions
 * @return 
 * @throws InterruptedException 
 * @throws UnsupportedCommOperationException *************************/
	
	public String getName()
	{
		return Name;
	}
	
	public boolean isRecirculating() {
		return Recirculating;
	}

	public void setRecirculating(boolean recirculating) {
		Recirculating = recirculating;
	}
	
	public void AddDevice(Device device)
	{
		device.addStateChangeEventListener(new DeviceListener());
		Devices.add(device);
	}
	
	public void RemoveDevice(Device device)
	{
		Devices.remove(device);
	}
	
	public void AddTemperatureDevice(Device device)
	{
		TemperatureDevices.add(device);
	}
	
	public void RemoveTemperatureDevice(Device device)
	{
		TemperatureDevices.remove(device);
	}
	
	public void AddFlowEntryDevice(Device device)
	{
		FlowEntryDevices.add(device);
	}
	
	public void RemoveFlowEntryDevice(Device device)
	{
		FlowEntryDevices.remove(device);
	}
	
	public void AddFlowExitDevice(Device device)
	{
		FlowExitDevices.add(device);
	}
	
	public void RemoveFlowExitDevice(Device device)
	{
		FlowExitDevices.remove(device);
	}
	
	
	public ArrayList<Device> getDevices()
	{
		return Devices;
	}
	
	public Device getDevice(String name)
	{
		Device _dev = null;
		for(Device device:Devices)
		{
			if (device.getName().equals(name) == true)
			{
				_dev = device;
			}
		}
		return _dev;
	}
	
	public void setDeviceState(String name, boolean state)
	{
		getDevice(name).setOpen(state);
	}
	
	public void RequestDeviceReading(String name)
	{
		getDevice(name).RequestReading();
	}
	
	public void setTarget_T(float target,Device TempSensor, Device Responder)
	{
		Target_T[0] = target;
		Target_T[1] = TempSensor;
		Target_T[2] = Responder;
		if(TempSensor.getReading() < target)
		{
			Responder.setOpen(true);
		}
		else
		{
			Responder.setOpen(false);
		}
	}
	
	public float getTarget_T()
	{
		return (float)Target_T[0];
	}
	public void setTarget_V(float target, Device ValveIn, Device ValveOut)
	{
		Target_V[0] = target;
		Target_V[1] = ValveIn;
		Target_V[2] = ValveOut;
		if(Actual_V < target)
		{
			for(Device in:FlowExitDevices)
			{
				in.StopPolling();
			}
			for(Device in:FlowEntryDevices)
			{
				in.StartPolling(2000);
			}
			
			ValveIn.setOpen(true);
			ValveOut.setOpen(false);
		}
		else
		{
			for(Device in:FlowEntryDevices)
			{
				in.StopPolling();
			}
			for(Device in:FlowExitDevices)
			{
				in.StartPolling(2000);
			}
			ValveOut.setOpen(true);
			ValveIn.setOpen(false);
		}
	}
	
	public HashMap<String, Double> getActual_T_Map() 
	{
		return Actual_T_Map;
	}
	
	public float getTarget_V() 
	{
		return (float)Target_V[0];
	}

	public Float getActual_V()
	{
		return Actual_V;
	}
	
	public void setActual_V(Float volume)
	{
		Actual_V = volume;
	}
	
/**********************Private Functions*************************/
	
	private class DeviceListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent evt) 
		{
			Device device = (Device)evt.getSource();
			int EventType = evt.getID();
		
			if(EventType == 1)
			{
				evt = new ActionEvent(device, 1, Name);
			}
			else if(EventType == 0 && FlowEntryDevices.contains(device))
			{
				
				double reading = device.getReading();
				Actual_V +=  reading;
				if(Target_V[0] != null && Target_V[1] != null && Target_V[2] != null)
				{
					if(Actual_V >= ((float)Target_V[0]-(float)0.370))
					{
						Device InValve = (Device)Target_V[1];
						if(InValve.isOpen() == true)
						{
							InValve.setOpen(false);
						}
					}
				}
				evt = new ActionEvent(this, 2, String.valueOf(Actual_V));
			}
			else if(EventType == 0 && FlowExitDevices.contains(device))
			{
				double reading = device.getReading();
				Actual_V -=  reading;
				
				if(Target_V[0] != null && Target_V[1] != null && Target_V[2] != null)
				{
					if(Actual_V <= ((float)Target_V[0]+(float)0.370))
					{
						Device OutValve = (Device)Target_V[2];
						if(OutValve.isOpen() == true)
						{
							OutValve.setOpen(false);
						}
					}
				}
				evt = new ActionEvent(this, 2, String.valueOf(Actual_V));
			}
			else if(EventType == 0 && TemperatureDevices.contains(device))
			{
				double reading = device.getReading();
				Actual_T_Map.put(device.getName(), reading);
				if(Target_T[0] != null && Target_T[1] != null && Target_T[2] != null)
					{
					if(((Device)Target_T[1]).getName().equals(device.getName()))
					{
						
						if((reading > (float)Target_T[0]) && ((Device)Target_T[2]).isOpen() == true)
						{
							((Device)Target_T[2]).setOpen(false);
						}
						if(reading < (float)Target_T[0] && ((Device)Target_T[2]).isOpen() == false)
						{
							((Device)Target_T[2]).setOpen(true);
						}
						
					}
				}
				evt = new ActionEvent(this, 0, String.valueOf(reading));
				
			}
			fireGroupStateChangeEvent(evt);
		}
	}
	
	public void addGroupStateChangeEventListener(ActionListener listener)
	{
		DeviceStateChangelistenerList.add(ActionListener.class, listener);
	}
	
	public void removeGroupStateChangeEventListener(ActionListener listener)
	{
		DeviceStateChangelistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireGroupStateChangeEvent(ActionEvent evt)
	{
		Object[] listeners = DeviceStateChangelistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
}
