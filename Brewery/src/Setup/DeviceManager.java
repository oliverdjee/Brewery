package Setup;

import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.ArrayList;

import Communication.SerialSend;
import Hardware.Device;
import Hardware.DeviceAction;
import Hardware.DeviceGroup;

public class DeviceManager{

	private ArrayList<DeviceGroup>	Groups		= new ArrayList<DeviceGroup>();
	private ArrayList<DeviceAction> Actions		= new ArrayList<DeviceAction>();
	private SerialSend Arduino;
	ArrayList<String> Controllers;
	public static final double StartTime = System.currentTimeMillis()/1000.0;
	
	public DeviceManager() throws UnsupportedCommOperationException, InterruptedException, IOException
	{
		System.out.println("Looking for Arduino \"MEGA\"");
		String ArduinoMega = "MEGA";
		Controllers = new ArrayList<String>();
		Controllers.add(ArduinoMega);
		Arduino = new SerialSend(Controllers);
		Arduino.InitSerialComm();
		
		DeviceGroup WaterTank = new DeviceGroup("WaterTank");
		DeviceGroup HERMS = new DeviceGroup("HERMS");
		DeviceGroup MashTun = new DeviceGroup("MashTun");
		DeviceGroup BoilKettle = new DeviceGroup("BoilKettle");
		DeviceGroup Fermentor = new DeviceGroup("Fermentor");
		
		
		//SETTING UP THE WATER TANK
		
		Device WaterTankTempSensorMiddle = new Device("03", Device.TemperatureSensor, ArduinoMega);
		WaterTankTempSensorMiddle.DisplayReading(true);
		WaterTank.AddDevice(WaterTankTempSensorMiddle);
		WaterTank.AddTemperatureDevice(WaterTankTempSensorMiddle);
		
		Device WaterTankFlowSensorIn = new Device("04", Device.FlowSensor, ArduinoMega);
		WaterTankFlowSensorIn.setVisible(false);
		WaterTankFlowSensorIn.setVolumeCorrection(0.00386);
		WaterTank.AddDevice(WaterTankFlowSensorIn);
		WaterTank.AddFlowEntryDevice(WaterTankFlowSensorIn);
		
		Device WaterTankFlowSensorOut = new Device("05", Device.FlowSensor, ArduinoMega);
		WaterTankFlowSensorOut.setVisible(false);
		WaterTankFlowSensorOut.setVolumeCorrection(0.00386);
		WaterTank.AddDevice(WaterTankFlowSensorOut);
		WaterTank.AddFlowExitDevice(WaterTankFlowSensorOut);
		
		Device WaterTankValveIn = new Device("13", Device.Valve, ArduinoMega);
		WaterTank.AddDevice(WaterTankValveIn);
		
		Device WaterTankValveOut = new Device("14", Device.Valve, ArduinoMega);
		WaterTank.AddDevice(WaterTankValveOut);
		
		Device WaterTankHeater = new Device("11", Device.Heater, ArduinoMega);
		WaterTank.AddDevice(WaterTankHeater);
		
		for(Device device:WaterTank.getDevices()){
			device.Init(Arduino);
		}
		
		//SETTING UP THE HERMS
		
		Device HERMSTempSensorExit = new Device("24", Device.TemperatureSensor, ArduinoMega);
		HERMSTempSensorExit.DisplayReading(true);
		HERMS.AddDevice(HERMSTempSensorExit);
		HERMS.AddTemperatureDevice(HERMSTempSensorExit);
		
		Device HERMSValveIn = new Device("15", Device.Valve, ArduinoMega);
		HERMS.AddDevice(HERMSValveIn);
		
		Device HERMSRecircValveOut = new Device("16", Device.Valve, ArduinoMega);
		HERMS.AddDevice(HERMSRecircValveOut);
		
		Device HERMSHeater = new Device("12", Device.Heater, ArduinoMega);
		HERMS.AddDevice(HERMSHeater);
		
		Device HERMSPump = new Device("09", Device.Pump, ArduinoMega);
		HERMS.AddDevice(HERMSPump);
		
		for(Device device:HERMS.getDevices()){
			device.Init(Arduino);
		}
		
		
		//SETTING UP MASHTUN
		
		Device MashTunTempSensorMiddle = new Device("02", Device.TemperatureSensor, ArduinoMega);
		MashTunTempSensorMiddle.DisplayReading(true);
		MashTun.AddDevice(MashTunTempSensorMiddle);
		MashTun.AddTemperatureDevice(MashTunTempSensorMiddle);
		
		MashTun.AddDevice(WaterTankFlowSensorOut);
		MashTun.AddFlowEntryDevice(WaterTankFlowSensorOut);
		
		Device MashTunFlowSensorOut = new Device("06",Device.FlowSensor, ArduinoMega);
		MashTunFlowSensorOut.setVisible(false);
		MashTunFlowSensorOut.setVolumeCorrection(0.00386);
		MashTun.AddDevice(MashTunFlowSensorOut);
		MashTun.AddFlowExitDevice(MashTunFlowSensorOut);
		
		Device MashTunValveIn = new Device("17", Device.Valve, ArduinoMega);
		MashTun.AddDevice(MashTunValveIn);
		
		Device MashTunValveOut = new Device("18", Device.Valve, ArduinoMega);
		MashTun.AddDevice(MashTunValveOut);
		
		Device MashTunStirrer = new Device("07", Device.Stirrer, ArduinoMega);
		MashTun.AddDevice(MashTunStirrer);
		
		Device pHMeter = new Device("22", Device.pHMeter, ArduinoMega);
		pHMeter.DisplayReading(true);
		MashTun.AddDevice(pHMeter);
		
		Device CerealGrinder = new Device("08", Device.Mill, ArduinoMega);
		MashTun.AddDevice(CerealGrinder);
		
		for(Device device:MashTun.getDevices()){
			device.Init(Arduino);
		}
		
		
		//SETTING BOILKETTLE
		
		//Device BoilKettleTempSensorMiddle = new Device("BoilKettleTempSensorMiddle", Device.TemperatureSensor, ArduinoMega);
		//BoilKettleTempSensorMiddle.DisplayReading(true);
		//BoilKettle.AddDevice(BoilKettleTempSensorMiddle);
		//BoilKettle.AddTemperatureDevice(BoilKettleTempSensorMiddle);
		
		BoilKettle.AddDevice(MashTunFlowSensorOut);
		BoilKettle.AddFlowEntryDevice(MashTunFlowSensorOut);
		
		Device BoilKettleFlowSensorOut = new Device("BoilKettleFlowSensorOut", Device.FlowSensor, ArduinoMega);
		BoilKettleFlowSensorOut.setVisible(false);
		BoilKettle.AddDevice(BoilKettleFlowSensorOut);
		BoilKettle.AddFlowExitDevice(BoilKettleFlowSensorOut);
		
		Device BoilKettleValveIn = new Device("19", Device.Valve, ArduinoMega);
		BoilKettle.AddDevice(BoilKettleValveIn);
		
		Device BoilKettleValveOut = new Device("20", Device.Valve, ArduinoMega);
		BoilKettle.AddDevice(BoilKettleValveOut);
		
		Device BoilKettlePump = new Device("10", Device.Pump, ArduinoMega);
		BoilKettle.AddDevice(BoilKettlePump);
		
		for(Device device:BoilKettle.getDevices()){
			device.Init(Arduino);
		}
		
		
		//SETTING UP FERMENTOR
		Device FermentorTempSensorMiddle = new Device("23", Device.TemperatureSensor, ArduinoMega);
		FermentorTempSensorMiddle.DisplayReading(true);
		Fermentor.AddDevice(FermentorTempSensorMiddle);
		Fermentor.AddTemperatureDevice(FermentorTempSensorMiddle);
		
		Fermentor.AddDevice(BoilKettleFlowSensorOut);
		Fermentor.AddFlowEntryDevice(BoilKettleFlowSensorOut);
		
		Device FermentorValveIn = new Device("21", Device.Valve, ArduinoMega);
		Fermentor.AddDevice(FermentorValveIn);
		
		for(Device device:Fermentor.getDevices()){
			device.Init(Arduino);
		}
		
		Groups.add(WaterTank);
		Groups.add(HERMS);
		Groups.add(MashTun);
		Groups.add(BoilKettle);
		Groups.add(Fermentor);
		
		DeviceAction WT_TransferHERMS = new DeviceAction("WaterTankTransferHERMS");
		WT_TransferHERMS.AddDevice(WaterTankValveOut,true,false);
		WT_TransferHERMS.AddDevice(HERMSPump,true,false);
		WT_TransferHERMS.AddDevice(HERMSValveIn,true,false);
		WT_TransferHERMS.AddDevice(MashTunValveIn,false,false);
		WT_TransferHERMS.AddDevice(HERMSRecircValveOut,false,false);
		Actions.add(WT_TransferHERMS);
		
		DeviceAction Sparge = new DeviceAction("Sparge");
		Sparge.AddDevice(WaterTankValveOut,true,false);
		Sparge.AddDevice(HERMSPump,true,false);
		Sparge.AddDevice(HERMSValveIn,true,false);
		Sparge.AddDevice(MashTunValveIn,false,false);
		Sparge.AddDevice(HERMSRecircValveOut,false,false);
		Sparge.AddDevice(BoilKettleValveIn,true,false);
		Sparge.AddDevice(BoilKettleValveOut,false,false);
		Sparge.AddDevice(FermentorValveIn,false,false);
		Sparge.AddDevice(BoilKettlePump,true,false);
		Sparge.AddDevice(MashTunValveOut,true,false);
		Actions.add(Sparge);
		
		DeviceAction WT_TransferMashTun = new DeviceAction("WaterTankTransferMashTun");
		WT_TransferMashTun.AddDevice(WaterTankValveOut,true,false);
		WT_TransferMashTun.AddDevice(HERMSPump,true,false);
		WT_TransferMashTun.AddDevice(HERMSValveIn,false,false);
		WT_TransferMashTun.AddDevice(MashTunValveIn,true,false);
		WT_TransferMashTun.AddDevice(HERMSRecircValveOut,false,false);
		Actions.add(WT_TransferMashTun);
		
		DeviceAction HERMS_Recirc = new DeviceAction("HERMSRecirculation");
		HERMS_Recirc.AddDevice(WaterTankValveOut,false,false);
		HERMS_Recirc.AddDevice(HERMSPump,true,false);
		HERMS_Recirc.AddDevice(HERMSValveIn,true,false);
		HERMS_Recirc.AddDevice(MashTunValveIn,false,false);
		HERMS_Recirc.AddDevice(MashTunValveOut,false,false);
		HERMS_Recirc.AddDevice(HERMSRecircValveOut,true,false);
		Actions.add(HERMS_Recirc);
		
		DeviceAction Cool = new DeviceAction("BoilKettleTransferFermentor");
		Cool.AddDevice(BoilKettleValveIn,false,false);
		Cool.AddDevice(BoilKettleValveOut,true,false);
		Cool.AddDevice(FermentorValveIn,true,false);
		Cool.AddDevice(BoilKettlePump,true,false);
		Cool.AddDevice(MashTunValveOut,false,false);
		Actions.add(Cool);
		
		DeviceAction MashTuntoFermentor = new DeviceAction("MashTunTransferFermentor");
		MashTuntoFermentor.AddDevice(BoilKettleValveIn,false,false);
		MashTuntoFermentor.AddDevice(BoilKettleValveOut,false,false);
		MashTuntoFermentor.AddDevice(FermentorValveIn,true,false);
		MashTuntoFermentor.AddDevice(BoilKettlePump,true,false);
		MashTuntoFermentor.AddDevice(MashTunValveOut,true,false);
		MashTuntoFermentor.AddDevice(HERMSRecircValveOut,false,false);
		Actions.add(MashTuntoFermentor);
		
		DeviceAction MashTuntoBoilKettle = new DeviceAction("MashTunTransferBoilKettle");
		MashTuntoBoilKettle.AddDevice(BoilKettleValveIn,true,false);
		MashTuntoBoilKettle.AddDevice(BoilKettleValveOut,false,false);
		MashTuntoBoilKettle.AddDevice(FermentorValveIn,false,false);
		MashTuntoBoilKettle.AddDevice(BoilKettlePump,true,false);
		MashTuntoBoilKettle.AddDevice(MashTunValveOut,true,false);
		MashTuntoBoilKettle.AddDevice(HERMSRecircValveOut,false,false);
		Actions.add(MashTuntoBoilKettle);
	}
	
	public SerialSend getSerialCommunicator()
	{
		return this.Arduino;
	}
	
	public void setSerialCommunicator(SerialSend arduino)
	{
		this.Arduino = arduino;
	}
	
	public ArrayList<DeviceGroup> getGroups() {
		return Groups;
	}

	public DeviceGroup getGroup(String Name)
	{
		DeviceGroup fRet = null;
		for(DeviceGroup group:Groups)
		{
			if(group.getName().equals(Name))
			{
				fRet = group;
				break;
			}
		}
		return fRet;
	}
	
	public void setGroups(ArrayList<DeviceGroup> groups) {
		Groups = groups;
	}
	
	public ArrayList<DeviceAction> getActions() {
		return Actions;
	}

	public void setActions(ArrayList<DeviceAction> actions) {
		Actions = actions;
	}

	public static void main(String[] args) {

	}

}

