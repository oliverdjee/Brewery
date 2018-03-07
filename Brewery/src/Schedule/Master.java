/**
 * I have to decide of how this is going to handle all the devices
 * I want to add devices to this class and then listen to any readings
 * of the devices. I add a trigger for a device, and an action to perform
 * when it is triggered by the reading event on the device.
 * 
 * I would have to build a an interface in which it would be possible to 
 * link a device to an action (DeviceAction Class maybe) and to assign a trigger
 * to a device.
 * 
 * These would be designed in the DeviceManager class, to make it exactly
 * fit the brewery needs.
 */


package Schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import Hardware.Device;

public class Master {
	
	private EventListenerList		InitialWaterVolumelistenerList;
	private EventListenerList		MashVolumelistenerList;
	private EventListenerList		WaterStrikeTemplistenerList;
	private EventListenerList		SpargeTemplistenerList;
	private EventListenerList		SpargeVolumelistenerList;
	private EventListenerList		MashSteplistenerList;
	private EventListenerList		HopAdditionSteplistenerList;
	
	private double InitialWaterVolume 	= 19.0;
	private double MashVolume 			= 9.0;
	private double WaterStrikeTemp 		= 73.0;
	private double SpargeTemp 			= 80.0;
	private double SpargeVolume 		= InitialWaterVolume - MashVolume;
	
	private double Mash_Step1_Time 	= 60;
	private double Mash_Step1_Temp 	= 68;
	private double Mash_Step2_Time 	= 0;
	private double Mash_Step2_Temp 	= 68;
	private double Mash_Step3_Time 	= 0;
	private double Mash_Step3_Temp 	= 68;
	
	private double Hop_Step1_Time 	= 0;
	private double Hop_Step1_Percent 	= 0;
	private double Hop_Step2_Time 	= 0;
	private double Hop_Step2_Percent 	= 0;
	private double Hop_Step3_Time 	= 0;
	private double Hop_Step3_Percent 	= 0;
	
	final private  double[] HopTimeOfAddition 	= {180,150,120, 90, 60, 45, 30, 20, 10, 5};
	final private  double[] UtilizationPercent = { 40, 38, 35, 33, 27, 24, 20, 13, 10, 5};
	final private  double[] AlphaAcidPercent	= {2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0,
													   6.5, 7.0, 7.5, 8.0, 9.0, 10.0, 11.0, 12.0,
													   13.0, 14.0, 15.0};
	final private  double[] UtilizationRange	= {0.0, 40};
	final private  double[] AlphaAcidTheta 	= {};
	
	private ArrayList<Device>	Slaves;
	
	public Master()
	{
		InitialWaterVolumelistenerList 	= new EventListenerList();
		MashVolumelistenerList 			= new EventListenerList();
		WaterStrikeTemplistenerList 	= new EventListenerList();
		SpargeTemplistenerList 			= new EventListenerList();
		SpargeVolumelistenerList 		= new EventListenerList();
		MashSteplistenerList 			= new EventListenerList();
		HopAdditionSteplistenerList 	= new EventListenerList();
		Slaves 							= new ArrayList<Device>();
	}

/*********************Private Functions*************************/	
	
	public void addInitialWaterVolumeEventListener(ActionListener listener)
	{
		InitialWaterVolumelistenerList.add(ActionListener.class, listener);
	}
	
	public void removeInitialWaterVolumeEventListener(ActionListener listener)
	{
		InitialWaterVolumelistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireInitialWaterVolumeChangeEvent(ActionEvent evt)
	{
		Object[] listeners = InitialWaterVolumelistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addMashVolumeEventListener(ActionListener listener)
	{
		MashVolumelistenerList.add(ActionListener.class, listener);
	}
	
	public void removeMashVolumeEventListener(ActionListener listener)
	{
		MashVolumelistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireMashVolumeChangeEvent(ActionEvent evt)
	{
		Object[] listeners = MashVolumelistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addWaterStrikeTempEventListener(ActionListener listener)
	{
		WaterStrikeTemplistenerList.add(ActionListener.class, listener);
	}
	
	public void removeWaterStrikeTempEventListener(ActionListener listener)
	{
		WaterStrikeTemplistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireWaterStrikeTempChangeEvent(ActionEvent evt)
	{
		Object[] listeners =WaterStrikeTemplistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addSpargeTempEventListener(ActionListener listener)
	{
		SpargeTemplistenerList.add(ActionListener.class, listener);
	}
	
	public void removeSpargeTempEventListener(ActionListener listener)
	{
		SpargeTemplistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireSpargeTempChangeEvent(ActionEvent evt)
	{
		Object[] listeners = SpargeTemplistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addSpargeVolumeEventListener(ActionListener listener)
	{
		SpargeVolumelistenerList.add(ActionListener.class, listener);
	}
	
	public void removeSpargeVolumeEventListener(ActionListener listener)
	{
		SpargeVolumelistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireSpargeVolumeChangeEvent(ActionEvent evt)
	{
		Object[] listeners = SpargeVolumelistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addMashStepEventListener(ActionListener listener)
	{
		MashSteplistenerList.add(ActionListener.class, listener);
	}
	
	public void removeMashStepEventListener(ActionListener listener)
	{
		MashSteplistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireMashStepChangeEvent(ActionEvent evt)
	{
		Object[] listeners = MashSteplistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	public void addHopAdditionStepEventListener(ActionListener listener)
	{
		HopAdditionSteplistenerList.add(ActionListener.class, listener);
	}
	
	public void removeHopAdditionStepEventListener(ActionListener listener)
	{
		HopAdditionSteplistenerList.remove(ActionListener.class,  listener);
	}
	
	private void fireHopAdditionStepChangeEvent(ActionEvent evt)
	{
		Object[] listeners = HopAdditionSteplistenerList.getListenerList();
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(evt);
			}
		}
	}
	
	public void AddDevice(Device device)
	{
		device.addStateChangeEventListener(new SlaveListener());
		Slaves.add(device);
	}
	
	public void RemoveDevice(Device device)
	{
		Slaves.remove(device);
	}

	public double[] getAlphaAcidTheta() {
		return AlphaAcidTheta;
	}

	public double[] getUtilizationRange() {
		return UtilizationRange;
	}

	public double[] getAlphaAcidPercent() {
		return AlphaAcidPercent;
	}

	public double[] getUtilizationPercent() {
		return UtilizationPercent;
	}

	public double[] getHopTimeOfAddition() {
		return HopTimeOfAddition;
	}

	public double getHop_Step3_Percent() {
		return Hop_Step3_Percent;
	}

	public void setHop_Step3_Percent(double hop_Step3_Percent) {
		Hop_Step3_Percent = hop_Step3_Percent;
	}

	public double getHop_Step3_Time() {
		return Hop_Step3_Time;
	}

	public void setHop_Step3_Time(double hop_Step3_Time) {
		Hop_Step3_Time = hop_Step3_Time;
	}

	public double getHop_Step2_Percent() {
		return Hop_Step2_Percent;
	}

	public void setHop_Step2_Percent(double hop_Step2_Percent) {
		Hop_Step2_Percent = hop_Step2_Percent;
	}

	public double getHop_Step2_Time() {
		return Hop_Step2_Time;
	}

	public void setHop_Step2_Time(double hop_Step2_Time) {
		Hop_Step2_Time = hop_Step2_Time;
	}

	public double getHop_Step1_Percent() {
		return Hop_Step1_Percent;
	}

	public void setHop_Step1_Percent(double hop_Step1_Percent) {
		Hop_Step1_Percent = hop_Step1_Percent;
	}

	public double getHop_Step1_Time() {
		return Hop_Step1_Time;
	}

	public void setHop_Step1_Time(double hop_Step1_Time) {
		Hop_Step1_Time = hop_Step1_Time;
	}

	public double getMash_Step3_Temp() {
		return Mash_Step3_Temp;
	}

	public void setMash_Step3_Temp(double mash_Step3_Temp) {
		Mash_Step3_Temp = mash_Step3_Temp;
	}

	public double getMash_Step3_Time() {
		return Mash_Step3_Time;
	}

	public void setMash_Step3_Time(double mash_Step3_Time) {
		Mash_Step3_Time = mash_Step3_Time;
	}

	public double getMash_Step2_Temp() {
		return Mash_Step2_Temp;
	}

	public void setMash_Step2_Temp(double mash_Step2_Temp) {
		Mash_Step2_Temp = mash_Step2_Temp;
	}

	public double getMash_Step2_Time() {
		return Mash_Step2_Time;
	}

	public void setMash_Step2_Time(double mash_Step2_Time) {
		Mash_Step2_Time = mash_Step2_Time;
	}

	public double getMash_Step1_Temp() {
		return Mash_Step1_Temp;
	}

	public void setMash_Step1_Temp(double mash_Step1_Temp) {
		Mash_Step1_Temp = mash_Step1_Temp;
	}

	public double getMash_Step1_Time() {
		return Mash_Step1_Time;
	}

	public void setMash_Step1_Time(double mash_Step1_Time) {
		Mash_Step1_Time = mash_Step1_Time;
	}

	public double getSpargeVolume() {
		return SpargeVolume;
	}

	public void setSpargeVolume(double spargeVolume) {
		SpargeVolume = spargeVolume;
	}

	public double getSpargeTemp() {
		return SpargeTemp;
	}

	public void setSpargeTemp(double spargeTemp) {
		SpargeTemp = spargeTemp;
	}

	public double getWaterStrikeTemp() {
		return WaterStrikeTemp;
	}

	public void setWaterStrikeTemp(double waterStrikeTemp) {
		WaterStrikeTemp = waterStrikeTemp;
	}

	public double getInitialWaterVolume() {
		return InitialWaterVolume;
	}
	
	public void setInitialWaterVolume(double initialWaterVolume) {
		InitialWaterVolume = initialWaterVolume;
	}

	public double getMashVolume() {
		return MashVolume;
	}
	
	public void setMashVolume(double mashVolume) {
		MashVolume = mashVolume;
	}
	
	/**
	 * PRIVATE CLASSES
	 */
	
	private class SlaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent evt) 
		{
			Device device = (Device)evt.getSource();
			int EventType = evt.getID();
		
			if(EventType == Device.READING_EVENT)
			{
				
				double reading = device.getReading();
			}
		}
	}
}
