/*
 * I NEED TO IMPROVE THE SERIALCONNECTION LISTENER CLASS HERE WHERE THE VALVES BUTTONS
 * BECOME ENABLED WHEN CONNECTED. I COULD ADD A LIST OF ALL THE DEVICES BY NAME AND 
 * SETTING THE NAME OF THE CORRESPONDING COMPONENTS APPROPRIATELY
 * 
 */


package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Hardware.Device;
import Hardware.DeviceAction;
import Hardware.DeviceGroup;
import Hops.HopNomogram;
import Schedule.SchedulePanel;
import Schedule.Master;
import Setup.DeviceManager;
import TouchScreenPanel.SetterDialog;
import gnu.io.UnsupportedCommOperationException;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.LineBorder;

import Graphs.MyTempGraph;

import javax.swing.JProgressBar;

public class BreweryUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeviceManager Brewery;
	private JFrame GUI;
	private SetterDialog 	SetterPanel;
	private SchedulePanel 	ScheduleFrame;
	private Timer BlinkTimer = new Timer(true);

	private JPanel 	MainPanel;
	private JPanel InfoPanel;
	private JPanel BoilKettlePanel;
	private JPanel BeerSchedulePanel;
	private JPanel HideBoilKettlePanel;
	private JPanel FermentorPanel;
	private JPanel HERMSPanel;
	private JPanel Hide_HermsPanel;
	private JPanel ValvePanel;
	private JPanel WaterTankPanel;
	private JPanel MashTunPanel;
	private HopNomogram Nomogram;
	
	private JButton 		SetBeerSchedule;
	private JButton 		GrainBill;
	private JButton 		Hops;
	
	private JLabel 			TempSensor_Connection;
	private JLabel 			Valve_Connection;
	private JLabel 			FlowSensor_Connection;
	private JLabel 			Heater_Connection;
	private JLabel 			Mill_Connection;
	
	private TextArea		LOG_Text;
	
	private JTextField 		CurrentStep;
	private JTextField 		NextStep;
	private JTextField 		ActionTimer;
	private JTextField 		BoilKettleActual_V;
	private JTextField 		MashTunActual_V;
	private JTextField 		FermentorActual_V;
	private JTextField 		WaterTankTarget_V;
	
	private JTextField 		WaterTankTarget_T;
	private JTextField 		MashTunTarget_T;
	private JTextField 		WaterTankActual_V;
	private JTextField 		MashTunTarget_V;
	
	private Device 			WaterTankActual_T;
	private Device 			HERMSActual_T;
	private Device 			MashTunActual_T;
	private Device 			FermentorActual_T;
	
	private Device 			pHMeter;
	
	private Master	ScheduleVal;
	//Cooling/Heating Icons
	private	 JButton Cool;
	private	 JLabel KettleHeatIcon;
	
	private	Device WaterTankHeater;
	private	Device HERMSHeater;
	private Device WaterTankValveIn;
	private Device HERMSValveIn;
	private Device HERMSRecircPump;
	private Device WaterTankValveOut;
	private Device HERMSRecircValveOut;
	private Device MashTunValveOut;
	private Device MashTunValveIn;
	private Device BoilKettlePump;
	private Device BoilKettleValveOut;
	private Device BoilKettleValveIn;
	private Device FermentorValveIn;
	private Device Milling;
	private Device MashTun_Stir;
	
	private JProgressBar BoilKettle_Progress;
	private JProgressBar MashTun_Progress;
	private JProgressBar WaterTank_Progress;
	private JProgressBar progressBar;
	private JButton Recirculate;
	private JButton WT_TransferUP;
	private JButton WT_TransferRIGHT;
	private JLabel Cooling_lbl;
	private JButton Fermentor_Transfer;
	private JButton BoilKettle_Transfer;
	private JButton Sparge;
	
	public DeviceManager getDeviceManager() {
		return Brewery;
	}

	public void setBrewery(DeviceManager brewery) {
		Brewery = brewery;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BreweryUI frame = new BreweryUI();
					frame.setVisible(true);
				}
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		});
	}
	
	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 * @throws UnsupportedCommOperationException 
	 * @throws IOException 
	 */
	
	public BreweryUI() throws UnsupportedCommOperationException, InterruptedException, IOException {
		
		System.out.println("Initiating Brewery Operations");
		Brewery = new DeviceManager();
		
		GUI = new JFrame("SCADA SYSTEM FOR BREWING OPERATIONS");
		GUI.setAlwaysOnTop(true);
		GUI.setSize(new Dimension(1024, 60));
		GUI.setResizable(false);
		GUI.setTitle("SCADA SYSTEM FOR BREWING OPERATIONS");
		GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GUI.setBounds(0, -3, 1024, 620);
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5,5,5,5));
		MainPanel.setLayout(null);
		GUI.setContentPane(MainPanel);
		Image background = new ImageIcon(this.getClass().getResource("images/GUIBreweryImage.png")).getImage();
		
		SetBeerSchedule = new JButton("Beer Schedule");
		SetBeerSchedule.setBackground(Color.LIGHT_GRAY);
		SetBeerSchedule.setBounds(350, 0, 200, 70);
		SetBeerSchedule.setBackground(Color.white);
		SetBeerSchedule.setIcon(new ImageIcon(this.getClass().getResource("images/Schedule.png")));
		SetBeerSchedule.setFont(new Font("Tahoma", Font.BOLD, 14));
		SetBeerSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleFrame.setVisible(true);
			}
		});
		MainPanel.add(SetBeerSchedule);
		
		JLabel Recirculate_lbl = new JLabel("Recirculate");
		Recirculate_lbl.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 16));
		Recirculate_lbl.setBounds(400, 325, 93, 20);
		MainPanel.add(Recirculate_lbl);
		
		JLabel WT_Transfer_lbl = new JLabel("Transfer");
		WT_Transfer_lbl.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 16));
		WT_Transfer_lbl.setBounds(280, 222, 69, 20);
		MainPanel.add(WT_Transfer_lbl);
		
		WT_TransferUP = new JButton("");
		WT_TransferUP.setIcon(new ImageIcon(this.getClass().getResource("images/transferUP.png")));
		WT_TransferUP.setFont(new Font("Tahoma", Font.BOLD, 14));
		WT_TransferUP.setBackground(Color.WHITE);
		WT_TransferUP.setBounds(280, 244, 32, 32);
		MainPanel.add(WT_TransferUP);
		WT_TransferUP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("WaterTankTransferMashTun"))
					{
						if(action.isOn())
						{
							action.Stop();
							WT_TransferUP.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(WT_TransferUP,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		WT_TransferRIGHT = new JButton("");
		WT_TransferRIGHT.setIcon(new ImageIcon(this.getClass().getResource("images/transfer.png")));
		WT_TransferRIGHT.setFont(new Font("Tahoma", Font.BOLD, 14));
		WT_TransferRIGHT.setBackground(Color.WHITE);
		WT_TransferRIGHT.setBounds(315, 244, 32, 32);
		MainPanel.add(WT_TransferRIGHT);
		WT_TransferRIGHT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("WaterTankTransferHERMS"))
					{
						if(action.isOn())
						{
							action.Stop();
							WT_TransferRIGHT.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(WT_TransferRIGHT,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		Recirculate = new JButton("");
		Recirculate.setBounds(425, 350, 32, 32);
		MainPanel.add(Recirculate);
		Recirculate.setIcon(new ImageIcon(BreweryUI.class.getResource("/GUI/images/recirculating.png")));
		Recirculate.setFont(new Font("Tahoma", Font.BOLD, 14));
		Recirculate.setBackground(Color.WHITE);
		Recirculate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("HERMSRecirculation"))
					{
						if(action.isOn())
						{
							action.Stop();
							Recirculate.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(Recirculate,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		
		JLabel MT_Transfer_lbl = new JLabel("Transfer");
		MT_Transfer_lbl.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 16));
		MT_Transfer_lbl.setBounds(724, 228, 69, 20);
		MainPanel.add(MT_Transfer_lbl);
		
		Fermentor_Transfer = new JButton("");
		Fermentor_Transfer.setIcon(new ImageIcon(this.getClass().getResource("images/transferUP.png")));
		Fermentor_Transfer.setFont(new Font("Tahoma", Font.BOLD, 14));
		Fermentor_Transfer.setBackground(Color.WHITE);
		Fermentor_Transfer.setBounds(724, 250, 32, 32);
		MainPanel.add(Fermentor_Transfer);
		Fermentor_Transfer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("MashTunTransferFermentor"))
					{
						if(action.isOn())
						{
							action.Stop();
							Fermentor_Transfer.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(Fermentor_Transfer,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		Cool = new JButton("");
		Cool.setBounds(795, 350, 32, 32);
		Cool.setBackground(Color.WHITE);
		MainPanel.add(Cool);
		Cool.setEnabled(true);
		Cool.setIcon(new ImageIcon(this.getClass().getResource("images/CoolingIcon.png")));
		Cool.setHorizontalAlignment(SwingConstants.CENTER);
		Cool.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("BoilKettleTransferFermentor"))
					{
						if(action.isOn())
						{
							action.Stop();
							Cool.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(Cool,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		
		Cooling_lbl = new JLabel("Cool");
		Cooling_lbl.setHorizontalTextPosition(SwingConstants.CENTER);
		Cooling_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Cooling_lbl.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 16));
		Cooling_lbl.setBounds(783, 332, 53, 20);
		MainPanel.add(Cooling_lbl);
		
		
		BoilKettle_Transfer = new JButton("");
		BoilKettle_Transfer.setIcon(new ImageIcon(this.getClass().getResource("images/transfer.png")));
		BoilKettle_Transfer.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoilKettle_Transfer.setBackground(Color.WHITE);
		BoilKettle_Transfer.setBounds(759, 250, 32, 32);
		MainPanel.add(BoilKettle_Transfer);
		
		BoilKettle_Transfer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("MashTunTransferBoilKettle"))
					{
						if(action.isOn())
						{
							action.Stop();
							BoilKettle_Transfer.setEnabled(true);
						}
						else
						{
							action.Start();
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(BoilKettle_Transfer,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		BeerSchedulePanel = new JPanel();
		BeerSchedulePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		BeerSchedulePanel.setBounds(699, 0, 305, 162);
		BeerSchedulePanel.setLayout(null);
		MainPanel.add(BeerSchedulePanel);
		
		//Variable for the Temperature Setter Panel (showConfirmDialog)
		SetterPanel = new SetterDialog();
		ScheduleFrame = new SchedulePanel(Brewery.getMaster());
		ScheduleFrame.setPreferredSize(new Dimension(400,800));
		ScheduleFrame.pack();
		ScheduleFrame.setVisible(false);
		
		JButton LogBtn = new JButton("Log");
		LogBtn.setBounds(550, 0, 75, 70);
		MainPanel.add(LogBtn);
		LogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		LogBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		LogBtn.setBackground(Color.WHITE);
		
		//SETTING UP THE TOP PANEL
		InfoPanel = new JPanel();
		InfoPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		InfoPanel.setBounds(5, 2, 628, 70);
		InfoPanel.setLayout(null);
		
		JLabel label = new JLabel(":");
		label.setIcon(new ImageIcon(this.getClass().getResource("images/TimerIcon.png")));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(200, 4, 33, 30);
		InfoPanel.add(label);
		
		JLabel Label_Status = new JLabel("Status:");
		Label_Status.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label_Status.setForeground(Color.BLACK);
		Label_Status.setBounds(5, 4, 73, 30);
		InfoPanel.add(Label_Status);
		CurrentStep = new JTextField();
		CurrentStep.setText("Powering Up");
		CurrentStep.setFont(new Font("Tahoma", Font.BOLD, 16));
		CurrentStep.setForeground(Color.DARK_GRAY);
		CurrentStep.setEditable(false);
		CurrentStep.setBackground(Color.WHITE);
		CurrentStep.setBounds(70, 4, 127, 30);
		InfoPanel.add(CurrentStep);
		CurrentStep.setColumns(10);
		
		JLabel Label_Next = new JLabel("Next:");
		Label_Next.setForeground(Color.BLACK);
		Label_Next.setFont(new Font("Tahoma", Font.BOLD, 16));
		Label_Next.setBounds(5, 36, 73, 30);
		InfoPanel.add(Label_Next);
		
		NextStep = new JTextField();
		NextStep.setText("Verifying Connections");
		NextStep.setForeground(Color.DARK_GRAY);
		NextStep.setFont(new Font("Tahoma", Font.BOLD, 16));
		NextStep.setEditable(false);
		NextStep.setColumns(10);
		NextStep.setBackground(Color.WHITE);
		NextStep.setBounds(70, 36, 263, 30);
		InfoPanel.add(NextStep);
		
		ActionTimer = new JTextField("Time Left ");
		ActionTimer.setForeground(Color.DARK_GRAY);
		ActionTimer.setFont(new Font("Tahoma", Font.BOLD, 16));
		ActionTimer.setEditable(false);
		ActionTimer.setColumns(10);
		ActionTimer.setBackground(Color.WHITE);
		ActionTimer.setBounds(234, 4, 99, 30);
		InfoPanel.add(ActionTimer);
		
		MainPanel.add(InfoPanel);
		
		ValvePanel = new JPanel();
		ValvePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		ValvePanel.setBounds(5, 304, 77, 105);
		ValvePanel.setLayout(null);
		
		MainPanel.add(ValvePanel);
		
		//SETTING UP THE WATER TANK GROUP
		WaterTankPanel = new JPanel();
		WaterTankPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		WaterTankPanel.setBackground(Color.WHITE);
		WaterTankPanel.setBounds(82, 76, 170, 378);
		MainPanel.add(WaterTankPanel);
		WaterTankPanel.setLayout(null);
		
		WaterTankValveIn = Brewery.getGroup("WaterTank").getDevice("13");
		WaterTankValveIn.setBounds(11, 0, 55, 55);
		ValvePanel.add(WaterTankValveIn);
		
		WaterTankValveOut = Brewery.getGroup("WaterTank").getDevice("14");
		WaterTankValveOut.setBounds(259, 401, 37, 37);
		MainPanel.add(WaterTankValveOut);
		
		JLabel WaterHeater_ActualTemp = new JLabel();
		WaterHeater_ActualTemp.setHorizontalAlignment(SwingConstants.TRAILING);
		WaterHeater_ActualTemp.setBounds(6, 67, 80, 28);
		WaterTankPanel.add(WaterHeater_ActualTemp);
		WaterHeater_ActualTemp.setText("T (C)");
		//WaterHeater_ActualTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		WaterHeater_ActualTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		WaterHeater_ActualTemp.setForeground(Color.BLACK);
		
		WaterTankActual_T = Brewery.getGroup("WaterTank").getDevice("03");
		WaterTankActual_T.setHorizontalAlignment(SwingConstants.CENTER);
		WaterTankActual_T.setBounds(92, 67, 62, 28);
		WaterTankActual_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		WaterTankActual_T.showGraphButton();
		WaterTankPanel.add(WaterTankActual_T.getGraphButton());
		WaterTankPanel.add(WaterTankActual_T);
		
		JLabel WaterHeater_TargetTemp = new JLabel();
		WaterHeater_TargetTemp.setHorizontalAlignment(SwingConstants.TRAILING);
		WaterHeater_TargetTemp.setBounds(6, 99, 80, 28);
		WaterTankPanel.add(WaterHeater_TargetTemp);
		WaterHeater_TargetTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		WaterHeater_TargetTemp.setText("Target:");
		WaterHeater_TargetTemp.setForeground(MyTempGraph.myRed);
		WaterHeater_TargetTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel WaterTank_Level = new JLabel("Vol (L)");
		WaterTank_Level.setBounds(0, 139, 86, 34);
		WaterTankPanel.add(WaterTank_Level);
		WaterTank_Level.setForeground(Color.BLACK);
		WaterTank_Level.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		WaterTank_Level.setHorizontalAlignment(SwingConstants.TRAILING);
		WaterTank_Level.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		WaterTankTarget_T = new JTextField();
		WaterTankTarget_T.setName("WaterTank");
		WaterTankTarget_T.setEditable(false);
		WaterTankTarget_T.setText("-");
		WaterTankTarget_T.setBounds(92, 99, 62, 28);
		WaterTankPanel.add(WaterTankTarget_T);
		WaterTankTarget_T.setHorizontalAlignment(SwingConstants.CENTER);
		WaterTankTarget_T.setForeground(new Color(255, 0, 0));
		WaterTankTarget_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		WaterTankTarget_T.setColumns(10);
		WaterTankTarget_T.addMouseListener(new Target_T());
		
		WaterTankTarget_V = new JTextField("-");
		WaterTankTarget_V.setName("WaterTank");
		WaterTankTarget_V.setEditable(false);
		WaterTankTarget_V.setBounds(92, 176, 62, 28);
		WaterTankPanel.add(WaterTankTarget_V);
		WaterTankTarget_V.setHorizontalAlignment(SwingConstants.CENTER);
		WaterTankTarget_V.setForeground(new Color(0, 0, 0));
		WaterTankTarget_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		WaterTankTarget_V.setColumns(10);
		WaterTankTarget_V.addMouseListener(new Target_V());
		
		WaterTankActual_V = new JTextField("0.0");
		WaterTankActual_V.setEditable(false);
		WaterTankActual_V.setHorizontalAlignment(SwingConstants.CENTER);
		WaterTankActual_V.setForeground(new Color(0, 0, 205));
		WaterTankActual_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		WaterTankActual_V.setColumns(10);
		WaterTankActual_V.setBounds(92, 143, 62, 28);
		WaterTankPanel.add(WaterTankActual_V);
		Brewery.getGroup("WaterTank").addGroupStateChangeEventListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					if(arg0.getID() == DeviceGroup.FLOW_EVENT)
					{
						WaterTankActual_V.setText(arg0.getActionCommand());
					}
				
			}
			
		});
		
		JLabel lblHotWater = new JLabel("Hot Water");
		lblHotWater.setBounds(0, 0, 170, 42);
		WaterTankPanel.add(lblHotWater);
		lblHotWater.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHotWater.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotWater.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 18));
		
		JLabel WaterTank_LevelTarget = new JLabel("Target:");
		WaterTank_LevelTarget.setHorizontalAlignment(SwingConstants.TRAILING);
		WaterTank_LevelTarget.setForeground(MyTempGraph.myBlue);
		WaterTank_LevelTarget.setFont(new Font("Tahoma", Font.BOLD, 12));
		WaterTank_LevelTarget.setBounds(0, 170, 86, 34);
		WaterTank_LevelTarget.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		WaterTankPanel.add(WaterTank_LevelTarget);
		
		WaterTankHeater = Brewery.getGroup("WaterTank").getDevice("11");
		WaterTankHeater.setBounds(68, 32, 44, 34);
		WaterTankPanel.add(WaterTankHeater);
		
		WaterTank_Progress = new JProgressBar();
		WaterTank_Progress.setBackground(Color.WHITE);
		WaterTank_Progress.setOrientation(SwingConstants.VERTICAL);
		WaterTank_Progress.setBounds(3, 10, 164, 366);
		WaterTankPanel.add(WaterTank_Progress);
		
		
		//SETTING UP THE HERMS GROUP
		HERMSPanel = new JPanel();
		HERMSPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		HERMSPanel.setBounds(383, 158, 110, 124);
		HERMSPanel.setLayout(null);
		MainPanel.add(HERMSPanel);
		
		HERMSValveIn = Brewery.getGroup("HERMS").getDevice("15");
		HERMSValveIn.setBounds(367, 283, 37, 37);
		MainPanel.add(HERMSValveIn);
		
		HERMSRecircValveOut = Brewery.getGroup("HERMS").getDevice("16");
		HERMSRecircValveOut.setBounds(420, 400, 37, 37);
		MainPanel.add(HERMSRecircValveOut);
		
		HERMSRecircPump = Brewery.getGroup("HERMS").getDevice("09");
		HERMSRecircPump.setBounds(326, 343, 40, 37);
		MainPanel.add(HERMSRecircPump);
		
		HERMSHeater = Brewery.getGroup("HERMS").getDevice("12");
		HERMSHeater.setBounds(30, 28, 46, 35);
		HERMSHeater.setHorizontalAlignment(SwingConstants.CENTER);
		HERMSPanel.add(HERMSHeater);
		
		JLabel HERMS_ActualTemp = new JLabel();
		HERMS_ActualTemp.setBounds(0, 70, 20, 28);
		HERMSPanel.add(HERMS_ActualTemp);
		HERMS_ActualTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		
		HERMSActual_T = Brewery.getGroup("HERMS").getDevice("24");
		HERMSActual_T.setBounds(20,70,61,28);
		HERMSActual_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		HERMSActual_T.setHorizontalAlignment(SwingConstants.CENTER);
		HERMSPanel.add(HERMSActual_T);
		
		JLabel HERMS_Label = new JLabel("H.E.R.M.S.");
		HERMS_Label.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 18));
		HERMS_Label.setHorizontalAlignment(SwingConstants.CENTER);
		HERMS_Label.setHorizontalTextPosition(SwingConstants.CENTER);
		HERMS_Label.setBounds(0, 0, 110, 40);
		HERMSPanel.add(HERMS_Label);
		
		//SETTING UP THE MASHTUN GROUP
		MashTunPanel = new JPanel();
		MashTunPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		MashTunPanel.setBackground(Color.WHITE);
		MashTunPanel.setBounds(504, 76, 170, 378);
		MainPanel.add(MashTunPanel);
		MashTunPanel.setLayout(null);
		
		MashTunValveOut = Brewery.getGroup("MashTun").getDevice("18");
		MashTunValveOut.setBounds(450, 443, 37, 37);
		MainPanel.add(MashTunValveOut);
		
		MashTunValveIn = Brewery.getGroup("MashTun").getDevice("17");
		MashTunValveIn.setBounds(288, 283, 37, 37);
		MainPanel.add(MashTunValveIn);
		
		Milling = Brewery.getGroup("MashTun").getDevice("08");
		Milling.setBounds(183, 0, 76, 65);
		BeerSchedulePanel.add(Milling);
		
		MashTun_Stir = Brewery.getGroup("MashTun").getDevice("07");
		MashTun_Stir.setBounds(50, 32, 40, 34);
		MashTunPanel.add(MashTun_Stir);
		
		Sparge = new JButton("");
		Sparge.setIcon(new ImageIcon(this.getClass().getResource("images/sparge.png")));
		Sparge.setFont(new Font("Tahoma", Font.BOLD, 14));
		Sparge.setBackground(Color.WHITE);
		Sparge.setBounds(95, 34, 40, 32);
		MashTunPanel.add(Sparge);
		Sparge.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(DeviceAction action:Brewery.getActions())
				{
					if(action.getActionName().equals("Sparge"))
					{
						String TankName = "HERMS";
						String SensorExt = "24";
						String HeaterExt = "12";
						
						if(action.isOn())
						{
							action.Stop();
							Brewery.getGroup(TankName).setTarget_T((float)0.0,
									Brewery.getGroup(TankName).getDevice(SensorExt), 
									Brewery.getGroup(TankName).getDevice(HeaterExt));
							Sparge.setEnabled(true);
						}
						else
						{
							action.Start();
							Brewery.getGroup(TankName).setTarget_T((float)85.0,
									Brewery.getGroup(TankName).getDevice(SensorExt), 
									Brewery.getGroup(TankName).getDevice(HeaterExt));
							BlinkTimer.scheduleAtFixedRate(new BlinkTask(Sparge,action), new Date(), 750);
						}
					}
				}
			}
		});
		
		JLabel MashTun_Level = new JLabel("Vol (L)");
		MashTun_Level.setBounds(4, 139, 86, 34);
		MashTunPanel.add(MashTun_Level);
		MashTun_Level.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		MashTun_Level.setHorizontalAlignment(SwingConstants.TRAILING);
		MashTun_Level.setForeground(Color.BLACK);
		MashTun_Level.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel Mash_ActualTemp = new JLabel();
		Mash_ActualTemp.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_ActualTemp.setBounds(0, 71, 90, 28);
		MashTunPanel.add(Mash_ActualTemp);
		//Mash_ActualTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		Mash_ActualTemp.setText("T (C)");
		Mash_ActualTemp.setForeground(Color.BLACK);
		Mash_ActualTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		MashTunActual_T = Brewery.getGroup("MashTun").getDevice("02");
		MashTunActual_T.setBounds(96, 71, 61, 28);
		MashTunActual_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		MashTunActual_T.setHorizontalAlignment(SwingConstants.CENTER);
		MashTunActual_T.showGraphButton();
		MashTunPanel.add(MashTunActual_T.getGraphButton());
		MashTunActual_T.getGraph().getDataset().addSeries(HERMSActual_T.getGraph().getData());
		MashTunPanel.add(MashTunActual_T);
		
		MashTunActual_V = new JTextField();
		MashTunActual_V.setEditable(false);
		MashTunActual_V.setText("0.0");
		MashTunActual_V.setBounds(96, 143, 61, 28);
		MashTunPanel.add(MashTunActual_V);
		MashTunActual_V.setHorizontalAlignment(SwingConstants.CENTER);
		MashTunActual_V.setForeground(Color.BLACK);
		MashTunActual_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		MashTunActual_V.setColumns(10);
		Brewery.getGroup("MashTun").addGroupStateChangeEventListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					if(arg0.getID() == DeviceGroup.FLOW_EVENT)
					{
						MashTunActual_V.setText(arg0.getActionCommand());
					}
				
			}
			
		});
		
		MashTunTarget_T = new JTextField();
		MashTunTarget_T.setName("HERMS"); 	// THIS IS NECESASARY SINCE THE HERMS CONTAINS THE HEATER 
											//ELEMENT, NOT THEW MASHTUN.
		MashTunTarget_T.setEditable(false);
		MashTunTarget_T.setText("-");
		MashTunTarget_T.setBounds(96, 102, 61, 28);
		MashTunPanel.add(MashTunTarget_T);
		MashTunTarget_T.setHorizontalAlignment(SwingConstants.CENTER);
		MashTunTarget_T.setForeground(MyTempGraph.myRed);
		MashTunTarget_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		MashTunTarget_T.setColumns(10);
		MashTunTarget_T.addMouseListener(new Target_T());
		
		JLabel Mash_TargetTemp = new JLabel();
		Mash_TargetTemp.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_TargetTemp.setBounds(0, 102, 90, 28);
		MashTunPanel.add(Mash_TargetTemp);
		Mash_TargetTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		Mash_TargetTemp.setText("Target:");
		Mash_TargetTemp.setForeground(MyTempGraph.myRed);
		
		Mash_TargetTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel MashTun_TargetLevel = new JLabel("Vol (L)");
		MashTun_TargetLevel.setRequestFocusEnabled(false);
		MashTun_TargetLevel.setHorizontalAlignment(SwingConstants.TRAILING);
		MashTun_TargetLevel.setForeground(MyTempGraph.myBlue);
		MashTun_TargetLevel.setFont(new Font("Tahoma", Font.BOLD, 12));
		MashTun_TargetLevel.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		MashTun_TargetLevel.setBounds(4, 172, 86, 34);
		MashTunPanel.add(MashTun_TargetLevel);
		
		MashTunTarget_V = new JTextField();
		MashTunTarget_V.setName("MashTun");
		MashTunTarget_V.setEditable(false);
		MashTunTarget_V.setText("-");
		MashTunTarget_V.setHorizontalAlignment(SwingConstants.CENTER);
		MashTunTarget_V.setForeground(MyTempGraph.myBlue);
		MashTunTarget_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		MashTunTarget_V.setColumns(10);
		MashTunTarget_V.setBounds(96, 175, 61, 28);
		MashTunTarget_V.addMouseListener(new Target_V());
		MashTunPanel.add(MashTunTarget_V);
		
		JLabel MashTun_Label = new JLabel("Mash Tun");
		MashTun_Label.setHorizontalTextPosition(SwingConstants.CENTER);
		MashTun_Label.setHorizontalAlignment(SwingConstants.CENTER);
		MashTun_Label.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 18));
		MashTun_Label.setBounds(0, 0, 176, 42);
		MashTunPanel.add(MashTun_Label);
		
		JLabel MashTun_pHLabel = new JLabel("     pH:");
		MashTun_pHLabel.setRequestFocusEnabled(false);
		MashTun_pHLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		MashTun_pHLabel.setForeground(new Color(255, 153, 255));
		MashTun_pHLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		//MashTun_pHLabel.setIcon(new ImageIcon(this.getClass().getResource("images/pH.png")));
		MashTun_pHLabel.setBounds(4, 216, 86, 34);
		MashTunPanel.add(MashTun_pHLabel);
		
		pHMeter = Brewery.getGroup("MashTun").getDevice("22");
		pHMeter.setBounds(96,219,61,28);
		pHMeter.setFont(new Font("Tahoma", Font.BOLD, 14));
		pHMeter.setHorizontalAlignment(SwingConstants.CENTER);
		pHMeter.showGraphButton();
		MashTunPanel.add(pHMeter.getGraphButton());
		MashTunPanel.add(pHMeter);
		
		
		MashTun_Progress = new JProgressBar();
		MashTun_Progress.setBackground(Color.WHITE);
		MashTun_Progress.setOrientation(SwingConstants.VERTICAL);
		MashTun_Progress.setBounds(3, 10, 164, 366);
		MashTunPanel.add(MashTun_Progress);
		
		
		//SETTING UP THE BOIL KETTLE
		BoilKettleValveOut = Brewery.getGroup("BoilKettle").getDevice("20");
		BoilKettleValveOut.setBounds(800, 399, 39, 37);
		MainPanel.add(BoilKettleValveOut);
		
		BoilKettlePump = Brewery.getGroup("BoilKettle").getDevice("10");
		BoilKettlePump.setBounds(743, 351, 40, 37);
		MainPanel.add(BoilKettlePump);
		
		BoilKettleValveIn = Brewery.getGroup("BoilKettle").getDevice("19");
		BoilKettleValveIn.setBounds(783, 286, 37, 37);
		MainPanel.add(BoilKettleValveIn);
		
		BoilKettlePanel = new JPanel();
		BoilKettlePanel.setBackground(Color.WHITE);
		BoilKettlePanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		BoilKettlePanel.setBounds(843, 163, 155, 279);
		MainPanel.add(BoilKettlePanel);
		BoilKettlePanel.setLayout(null);
		
		JLabel BoilKettle_Level = new JLabel("Vol (L)");
		BoilKettle_Level.setBounds(0, 96, 83, 34);
		BoilKettlePanel.add(BoilKettle_Level);
		BoilKettle_Level.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		BoilKettle_Level.setHorizontalAlignment(SwingConstants.TRAILING);
		BoilKettle_Level.setForeground(Color.BLACK);
		BoilKettle_Level.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		BoilKettleActual_V = new JTextField();
		BoilKettleActual_V.setEditable(false);
		BoilKettleActual_V.setText("-");
		BoilKettleActual_V.setBounds(88, 100, 62, 28);
		BoilKettlePanel.add(BoilKettleActual_V);
		BoilKettleActual_V.setHorizontalAlignment(SwingConstants.CENTER);
		BoilKettleActual_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoilKettleActual_V.setForeground(Color.BLACK);
		BoilKettleActual_V.setColumns(10);
		Brewery.getGroup("BoilKettle").addGroupStateChangeEventListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					if(arg0.getID() == DeviceGroup.FLOW_EVENT)
					{
						BoilKettleActual_V.setText(arg0.getActionCommand());
					}
				
			}
			
		});
		
		KettleHeatIcon = new JLabel("");
		KettleHeatIcon.setBounds(60, 27, 39, 41);
		KettleHeatIcon.setEnabled(false);
		KettleHeatIcon.setIcon(new ImageIcon(this.getClass().getResource("images/HeatIcon.png")));
		KettleHeatIcon.setHorizontalAlignment(SwingConstants.CENTER);
		KettleHeatIcon.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoilKettlePanel.add(KettleHeatIcon);
		
		JLabel BoilKettleLbl = new JLabel("Boil Kettle");
		BoilKettleLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		BoilKettleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		BoilKettleLbl.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 18));
		BoilKettleLbl.setBounds(0, 0, 155, 42);
		BoilKettlePanel.add(BoilKettleLbl);
		
		BoilKettle_Progress = new JProgressBar();
		BoilKettle_Progress.setOrientation(SwingConstants.VERTICAL);
		BoilKettle_Progress.setBackground(Color.WHITE);
		BoilKettle_Progress.setBounds(3, 10, 149, 267);
		BoilKettlePanel.add(BoilKettle_Progress);
		
		
		//SETTING UP THE FERMENTOR
		FermentorValveIn = Brewery.getGroup("Fermentor").getDevice("21");
		FermentorValveIn.setBounds(707, 286, 37, 37);
		MainPanel.add(FermentorValveIn);
		
		FermentorPanel = new JPanel();
		FermentorPanel.setBackground(Color.WHITE);
		FermentorPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		FermentorPanel.setBounds(139, 457, 227, 103);
		MainPanel.add(FermentorPanel);
		FermentorPanel.setLayout(null);
		
		JLabel Ferm_ActualTemp = new JLabel();
		Ferm_ActualTemp.setHorizontalAlignment(SwingConstants.TRAILING);
		Ferm_ActualTemp.setBounds(30, 40, 80, 28);
		FermentorPanel.add(Ferm_ActualTemp);
		//Ferm_ActualTemp.setIcon(new ImageIcon(this.getClass().getResource("images/TempIcon.png")));
		Ferm_ActualTemp.setText("T (C)");
		Ferm_ActualTemp.setForeground(Color.BLACK);
		Ferm_ActualTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		FermentorActual_T = Brewery.getGroup("Fermentor").getDevice("23");
		FermentorActual_T.setBounds(120, 40, 62, 28);
		FermentorActual_T.setHorizontalAlignment(SwingConstants.CENTER);
		FermentorActual_T.setForeground(Color.BLACK);
		FermentorActual_T.setFont(new Font("Tahoma", Font.BOLD, 14));
		FermentorActual_T.showGraphButton();
		FermentorPanel.add(FermentorActual_T.getGraphButton());
		FermentorPanel.add(FermentorActual_T);
		
		JLabel Fermentor_Level = new JLabel("Vol (L)");
		Fermentor_Level.setBounds(24, 70, 86, 28);
		FermentorPanel.add(Fermentor_Level);
		Fermentor_Level.setIcon(new ImageIcon(this.getClass().getResource("images/WaterLvl.png")));
		Fermentor_Level.setHorizontalAlignment(SwingConstants.TRAILING);
		Fermentor_Level.setForeground(Color.BLACK);
		Fermentor_Level.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		FermentorActual_V = new JTextField();
		FermentorActual_V.setEditable(false);
		FermentorActual_V.setText("-");
		FermentorActual_V.setBounds(120, 70, 62, 28);
		FermentorPanel.add(FermentorActual_V);
		FermentorActual_V.setHorizontalAlignment(SwingConstants.CENTER);
		FermentorActual_V.setForeground(Color.BLACK);
		FermentorActual_V.setFont(new Font("Tahoma", Font.BOLD, 14));
		FermentorActual_V.setColumns(10);
		
		JLabel lblFermentor = new JLabel("Fermentor");
		lblFermentor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFermentor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFermentor.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 18));
		lblFermentor.setBounds(3, 2, 227, 42);
		FermentorPanel.add(lblFermentor);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(3, 10, 221, 91);
		FermentorPanel.add(progressBar);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBackground(Color.WHITE);
		
		//SETTING UP THE AUTOMATION 
		GrainBill = new JButton("Grains");
		GrainBill.setBounds(0, 0, 141, 65);
		GrainBill.setBackground(new Color(255, 255, 255));
		BeerSchedulePanel.add(GrainBill);
		GrainBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		GrainBill.setIcon(new ImageIcon(this.getClass().getResource("images/Grain.png")));
	
		Hops = new JButton("Hops");
		Hops.setBounds(0, 65, 141, 65);
		Hops.setBackground(new Color(255, 255, 255));
		BeerSchedulePanel.add(Hops);
		Hops.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hops.setIcon(new ImageIcon(this.getClass().getResource("images/Hops.png")));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HopNomogram Nomogram = new HopNomogram();
					Nomogram.setVisible(false);
					Nomogram.pack();
				}
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		});
		Hops.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Nomogram.setVisible(true);
				
			}
			
		});
		
		
		//SETTING UP THE HIDDING PANELS
		Hide_HermsPanel = new JPanel();
		Hide_HermsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		Hide_HermsPanel.setBounds(284, 145, 91, 70);
		MainPanel.add(Hide_HermsPanel);
		
		JPanel Hide_ThermP3 = new JPanel();
		Hide_ThermP3.setBounds(383, 72, 80, 34);
		MainPanel.add(Hide_ThermP3);
		Hide_ThermP3.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel Hide_ThemPAnel1 = new JPanel();
		Hide_ThemPAnel1.setBorder(new EmptyBorder(0, 0, 0, 0));
		Hide_ThemPAnel1.setBounds(441, 298, 62, 113);
		MainPanel.add(Hide_ThemPAnel1);
		
		HideBoilKettlePanel = new JPanel();
		HideBoilKettlePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		HideBoilKettlePanel.setBounds(777, 425, 225, 135);
		HideBoilKettlePanel.setLayout(null);
		MainPanel.add(HideBoilKettlePanel);
		
		//Insert LOG text area in the GUI
		LOG_Text = new TextArea();
		LOG_Text.setName("LogText");
		LOG_Text.setBounds(0, 32, 227, 103);
		
		LOG_Text.setEditable(false);
		LOG_Text.setColumns(20);
		LOG_Text.setFont(new Font("Lucida Console", Font.BOLD, 10));
		LOG_Text.setForeground(MyTempGraph.myRed);
		HideBoilKettlePanel.add(LOG_Text);
		
		JLabel LOG = new JLabel("Log:");
		LOG.setBounds(0, 8, 58, 25);
		HideBoilKettlePanel.add(LOG);
		LOG.setFont(new Font("Tahoma", Font.BOLD, 14));
		LOG.setHorizontalAlignment(SwingConstants.CENTER);
		
		//BACKGROUND IMAGE NEEDS TO BE ADDED AT THE END SO IT DOES NOT COVERS ANYTHING
		JLabel Background_Icon = new JLabel("");
		Background_Icon.setHorizontalAlignment(SwingConstants.CENTER);
		Background_Icon.setIcon(new ImageIcon(background));
		Background_Icon.setBounds(0, 0, 1004, 564);
		MainPanel.add(Background_Icon);
		
		GUI.setVisible(true);
		
		
	}
	
	public void SetConnectHeaters(boolean value)
	{
		Heater_Connection.setEnabled(value);
	}
	
	public void SetConnectMill(boolean value)
	{
		Mill_Connection.setEnabled(value);
	}
	public void SetConnectValves(boolean value)
	{
		Valve_Connection.setEnabled(value);
	}
	public void SetConnectTempSensor(boolean value)
	{
		TempSensor_Connection.setEnabled(value);
	}
	public void SetConnectFlowSensor(boolean value)
	{
		FlowSensor_Connection.setEnabled(value);
	}
	
	private class Target_T implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			SetterPanel.getSel_C().setSelected(true);
			SetterPanel.getSel_F().setVisible(false);
			SetterPanel.getSel_L().setSelected(false);
			SetterPanel.getSel_L().setVisible(false);
			if(SetterPanel.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
			{
				String TankName = ((JTextField)arg0.getSource()).getName();
				String SensorExt = "";
				String HeaterExt = "";
				if(TankName.equals("HERMS"))
				{
					SensorExt = "24";
					HeaterExt = "12";
				}
				if(TankName.equals("WaterTank"))
				{
					SensorExt = "03";
					HeaterExt = "11";
				}
				if(TankName.equals("MashTun"))
				{
					SensorExt = "24";
					HeaterExt = "12";
				}
				
				((JTextField)arg0.getSource()).setText(SetterPanel.getUser_Entry());
				try
				{
					Brewery.getGroup(TankName).setTarget_T(Float.parseFloat(SetterPanel.getUser_Entry()),
							Brewery.getGroup(TankName).getDevice(SensorExt), 
							Brewery.getGroup(TankName).getDevice(HeaterExt));
					LOG_Text.setText(LOG_Text.getText()+"\nSet "+TankName+" Temp to: "+Float.toString(Brewery.getGroup(TankName).getTarget_T())+ " C");
				}
				catch(Exception error){}
				
				SetterPanel.setUser_Entry("");
				SetterPanel.setTextArea("");
			}
		}		
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class Target_V implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			SetterPanel.getSel_C().setSelected(false);
			SetterPanel.getSel_C().setVisible(false);
			SetterPanel.getSel_F().setVisible(false);
			SetterPanel.getSel_L().setSelected(true);
			SetterPanel.getSel_L().setVisible(true);
			if(SetterPanel.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
			{
				String TankName = ((JTextField)arg0.getSource()).getName();
				String Vin = "";
				String Vout = "";
				
				if(TankName.equals("WaterTank"))
				{
					Vin = "13";
					Vout = "14";
				}
				if(TankName.equals("MashTun"))
				{
					Vin = "17";
					Vout = "18";
				}
				((JTextField)arg0.getSource()).setText(SetterPanel.getUser_Entry());
				try
				{
					Brewery.getGroup(TankName).setTarget_V(Float.parseFloat(SetterPanel.getUser_Entry()),
							Brewery.getGroup(TankName).getDevice(Vin), 
							Brewery.getGroup(TankName).getDevice(Vout));
					LOG_Text.setText(LOG_Text.getText()+"\nSet "+TankName+" Vol to: "+String.valueOf(Brewery.getGroup(TankName).getTarget_V())+ " L");
				}
				catch(Exception error){}
				SetterPanel.setUser_Entry("");
				SetterPanel.setTextArea("");
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	public class BlinkTask extends TimerTask
	{
		private JButton toBlink;
		private DeviceAction Action;
		
		public BlinkTask(JButton ButtonToBlink, DeviceAction action)
		{
			toBlink = ButtonToBlink;
			Action = action;
		}
		@Override
		public void run() {
			if(Action.isOn())
			{
				toBlink.setEnabled(!toBlink.isEnabled());
			}
			else
			{
				this.cancel();
			}
		}
		
	}
}

