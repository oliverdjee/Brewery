package Hardware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceAction
{
	private ArrayList<Device> Devices;
	private String ActionName;
	private Map<Device,boolean[]> ActionMap;
	private Timer timer;
	private ArrayList<Object[]> Tasks;
	private boolean Running;
	
	public DeviceAction(String LinkName)
	{
		setActionName(LinkName);
		Devices = new ArrayList<Device>();
		ActionMap = new HashMap<Device,boolean[]>();
		timer = null;
		Tasks = new ArrayList<Object[]>();
		Running = false;
	}


	public String getActionName() {
		return ActionName;
	}

	public void setActionName(String actionName) {
		ActionName = actionName;
	}

	public void AddDevice(Device device, boolean OnActionState, boolean OffActionState)
	{
		Devices.add(device);
		ActionMap.put(device,new boolean[]{OnActionState,OffActionState});
	}
	
	public void AddOnOffActionState(Device device, boolean OnActionState,  boolean OffActionState)
	{
		ActionMap.put(device,new boolean[]{OnActionState,OffActionState});
	}
	
	public void AddDevice(Device device)
	{
		Devices.add(device);
		ActionMap.put(device, null);
	}
	
	public void RemoveDevice(Device device)
	{
		Devices.remove(device);
	}
	
	public Device[] getDevices() {
		return Devices.toArray(new Device[]{});
	}
	public void setDevices(ArrayList<Device> devices) {
		Devices = devices;
	}
	
	public void Start()
	{
		Running = true;
		if(timer != null)
		{
			for(Object[] action:Tasks)
			{
				TimerTask task = (TimerTask) action[0];
				long time = (long) action[1];
				timer.schedule(task, time);
			}
		}
		else
		{
			for(Device device:Devices)
			{
				boolean[] action = ActionMap.get(device);
				if(action != null)
				{
					device.setOpen(action[0]);
				}
			}
		}
		
	}
	public void Stop()
	{
		Running = false;
		if(timer != null)
		{
			timer.cancel();
		}
		for(Device device:Devices)
		{
			boolean[] action = ActionMap.get(device);
			if(action != null)
			{
				device.setOpen(action[1]);
			}
		}
	}

	public Timer getTimer() {
		return timer;
	}
	
	public void AddTimerTask(Device device, boolean OnActionState, long delay) {
		if(timer == null){
			timer = new Timer(true);
		}
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				device.setOpen(OnActionState);
			}	
		};
		Tasks.add(new Object[]{task,delay});
	}


	public boolean isOn() {
		return Running;
	}
}
