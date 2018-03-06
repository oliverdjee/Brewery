package Schedule;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import TouchScreenPanel.SetterDialog;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SchedulePanel extends JFrame
{
	private JPanel contentPane;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SetterDialog InputDialog;
	private JTextField InitialWaterVolume;
	private JTextField WaterStrikeTemp;
	private JTextField MashVolume;
	private JTextField Mash_Step1_time;
	private JTextField Mash_Step1_Temp;
	private JTextField Mash_Step2_Time;
	private JTextField Mash_Step2_Temp;
	private JTextField Mash_Step3_Time;
	private JTextField Mash_Step3_Temp;
	private JTextField SpargeTemp;
	private JTextField Hop_Step1_Temp;
	private JTextField Hop_Step1_Time;
	private JTextField Hop_Step2_Time;
	private JTextField Hop_Step2_Temp;
	private JTextField Hop_Step3_Temp;
	private JTextField Hop_Step3_Time;
	private JButton SetInitialVolume_btn;
	private JButton SetStrikeWaterTemp_btn;
	private JButton Set_MashSchedule;
	private JButton button;
	private JButton button_1;
	private JButton Set_HopSchedule;
	private ScheduleValues vals;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleValues vals = new ScheduleValues();
					SchedulePanel frame = new SchedulePanel(vals);
					frame.setPreferredSize(new Dimension(400,800));
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SchedulePanel(ScheduleValues _vals) {
		vals = _vals;
		setAlwaysOnTop(true);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\EclipseBrewery\\HomeBrewery\\img\\TimerIcon.png"));
		setResizable(false);
		setTitle("Beer Mash Schedule");
		setType(Type.NORMAL);
		setForeground(Color.WHITE);
		setBounds(100, 0, 450, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Step1Panel = new JPanel();
		Step1Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 1", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		Step1Panel.setBounds(10, 0, 385, 85);
		contentPane.add(Step1Panel);
		Step1Panel.setLayout(null);
		
		SetInitialVolume_btn = new JButton("SET");
		SetInitialVolume_btn.setBounds(268, 17, 107, 60);
		Step1Panel.add(SetInitialVolume_btn);
		SetInitialVolume_btn.setForeground(Color.RED);
		SetInitialVolume_btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SetInitialVolume_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InputDialog = new SetterDialog();
				InputDialog.getSel_C().setSelected(false);
				InputDialog.getSel_L().setSelected(false);
				if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
				{
					InitialWaterVolume.setText(InputDialog.getUser_Entry());
					vals.setInitialWaterVolume(Double.parseDouble(InputDialog.getUser_Entry()));
				}
			}
		});		
		
		JLabel lblWatertankInitialVolume = new JLabel("WaterTank Init. Volume");
		lblWatertankInitialVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblWatertankInitialVolume.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWatertankInitialVolume.setBounds(49, 11, 244, 30);
		Step1Panel.add(lblWatertankInitialVolume);
		lblWatertankInitialVolume.setFont(new Font("Vani", Font.BOLD, 14));
		
		JLabel lblWaterLevelIconStep1 = new JLabel("");
		lblWaterLevelIconStep1.setBounds(5, 16, 58, 56);
		Step1Panel.add(lblWaterLevelIconStep1);
		lblWaterLevelIconStep1.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\WaterLvl2.png"));
		
		InitialWaterVolume = new JTextField();
		InitialWaterVolume.setFont(new Font("Tahoma", Font.BOLD, 16));
		InitialWaterVolume.setForeground(Color.BLUE);
		InitialWaterVolume.setEditable(false);
		InitialWaterVolume.setBounds(130, 45, 58, 25);
		Step1Panel.add(InitialWaterVolume);
		InitialWaterVolume.setColumns(10);
		
		JLabel lblL = new JLabel("L");
		lblL.setEnabled(false);
		lblL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblL.setBounds(190, 45, 25, 25);
		Step1Panel.add(lblL);
		
		JPanel Step2Panel = new JPanel();
		Step2Panel.setLayout(null);
		Step2Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		Step2Panel.setBounds(10, 85, 385, 85);
		contentPane.add(Step2Panel);
		
		SetStrikeWaterTemp_btn = new JButton("SET");
		SetStrikeWaterTemp_btn.setForeground(Color.RED);
		SetStrikeWaterTemp_btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SetStrikeWaterTemp_btn.setBounds(268, 17, 107, 57);
		SetStrikeWaterTemp_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				InputDialog = new SetterDialog();
				InputDialog.getSel_C().setSelected(false);
				InputDialog.getSel_L().setSelected(false);
				if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
				{
					WaterStrikeTemp.setText(InputDialog.getUser_Entry());
					vals.setWaterStrikeTemp(Double.parseDouble(InputDialog.getUser_Entry()));
				}
			}
		});	
		Step2Panel.add(SetStrikeWaterTemp_btn);
		
		JLabel lblSetStrikeWater = new JLabel("Set Strike Water Temp");
		lblSetStrikeWater.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetStrikeWater.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetStrikeWater.setFont(new Font("Vani", Font.BOLD, 14));
		lblSetStrikeWater.setBounds(49, 11, 244, 30);
		Step2Panel.add(lblSetStrikeWater);
		
		JLabel lblWaterHeatingIconStep2 = new JLabel("");
		lblWaterHeatingIconStep2.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\HeatIcon3.png"));
		lblWaterHeatingIconStep2.setBounds(7, 16, 58, 56);
		Step2Panel.add(lblWaterHeatingIconStep2);
		
		WaterStrikeTemp = new JTextField();
		WaterStrikeTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		WaterStrikeTemp.setForeground(Color.BLUE);
		WaterStrikeTemp.setEditable(false);
		WaterStrikeTemp.setColumns(10);
		WaterStrikeTemp.setBounds(130, 45, 58, 25);
		Step2Panel.add(WaterStrikeTemp);
		
		JLabel lblC_1 = new JLabel("C");
		lblC_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblC_1.setEnabled(false);
		lblC_1.setBounds(190, 45, 25, 25);
		Step2Panel.add(lblC_1);
		
		JPanel Step3Panel = new JPanel();
		Step3Panel.setLayout(null);
		Step3Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		Step3Panel.setBounds(10, 170, 385, 85);
		contentPane.add(Step3Panel);
		
		JLabel lblSetMashingVolume = new JLabel("Set Mashing Volume");
		lblSetMashingVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetMashingVolume.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetMashingVolume.setFont(new Font("Vani", Font.BOLD, 14));
		lblSetMashingVolume.setBounds(49, 11, 244, 30);
		Step3Panel.add(lblSetMashingVolume);
		
		JLabel lblWaterLevelIconStep3 = new JLabel("");
		lblWaterLevelIconStep3.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\WaterLvl2.png"));
		lblWaterLevelIconStep3.setBounds(5, 16, 58, 56);
		Step3Panel.add(lblWaterLevelIconStep3);
		
		button = new JButton("SET");
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(268, 17, 107, 55);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				InputDialog = new SetterDialog();
				InputDialog.getSel_C().setSelected(false);
				InputDialog.getSel_L().setSelected(false);
				if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
				{
					MashVolume.setText(InputDialog.getUser_Entry());
					vals.setMashVolume(Double.parseDouble(InputDialog.getUser_Entry()));
				}
			}
		});
		Step3Panel.add(button);
		
		MashVolume = new JTextField();
		MashVolume.setFont(new Font("Tahoma", Font.BOLD, 16));
		MashVolume.setForeground(Color.BLUE);
		MashVolume.setEditable(false);
		MashVolume.setColumns(10);
		MashVolume.setBounds(130, 45, 58, 25);
		Step3Panel.add(MashVolume);
		
		JLabel label_7 = new JLabel("L");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setEnabled(false);
		label_7.setBounds(190, 45, 25, 25);
		Step3Panel.add(label_7);
		
		JPanel Step4Panel = new JPanel();
		Step4Panel.setBounds(10, 255, 385, 120);
		contentPane.add(Step4Panel);
		Step4Panel.setLayout(null);
		Step4Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		
		JLabel lblSetMashingTemperature = new JLabel("Set Mashing Schedule");
		lblSetMashingTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetMashingTemperature.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetMashingTemperature.setFont(new Font("Vani", Font.BOLD, 14));
		lblSetMashingTemperature.setBounds(47, 3, 244, 30);
		Step4Panel.add(lblSetMashingTemperature);
		
		JLabel lblTimerIconStep4 = new JLabel("");
		lblTimerIconStep4.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\TimerIcon2.png"));
		lblTimerIconStep4.setBounds(5, 33, 58, 56);
		Step4Panel.add(lblTimerIconStep4);
		
		Set_MashSchedule = new JButton("SET");
		Set_MashSchedule.addActionListener(new ActionListener() {
			
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				int step = showMessage("Mashing Schedule Step to Set Up: ");
				if(step == 0){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step1_time.setText(InputDialog.getUser_Entry());
						vals.setMash_Step1_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					
					InputDialog.setTitle("Set Mash Temp (C)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step1_Temp.setText(InputDialog.getUser_Entry());
						vals.setMash_Step1_Temp(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					
				}
				else if(step == 1){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step2_Time.setText(InputDialog.getUser_Entry());
						vals.setMash_Step2_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.setTitle("Set Mash Temp (C)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step2_Temp.setText(InputDialog.getUser_Entry());
						vals.setMash_Step2_Temp(Double.parseDouble(InputDialog.getUser_Entry()));
					}
				}
				else if(step == 3){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step3_Time.setText(InputDialog.getUser_Entry());
						vals.setMash_Step3_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.setTitle("Set Mash Temp (C)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Mash_Step3_Temp.setText(InputDialog.getUser_Entry());
						vals.setMash_Step3_Temp(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					
				}
			}
		});
		Set_MashSchedule.setForeground(Color.RED);
		Set_MashSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Set_MashSchedule.setBounds(268, 17, 107, 93);
		Step4Panel.add(Set_MashSchedule);
		
		Mash_Step1_time = new JTextField();
		Mash_Step1_time.setText("-");
		Mash_Step1_time.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step1_time.setForeground(Color.BLUE);
		Mash_Step1_time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step1_time.setEditable(false);
		Mash_Step1_time.setColumns(10);
		Mash_Step1_time.setBounds(90, 35, 40, 25);
		Step4Panel.add(Mash_Step1_time);
		
		Mash_Step1_Temp = new JTextField();
		Mash_Step1_Temp.setText("-");
		Mash_Step1_Temp.setForeground(Color.BLUE);
		Mash_Step1_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step1_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step1_Temp.setEditable(false);
		Mash_Step1_Temp.setColumns(10);
		Mash_Step1_Temp.setBounds(190, 35, 40, 25);
		Step4Panel.add(Mash_Step1_Temp);
		
		JLabel lblNewLabel = new JLabel("min   @");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(133, 35, 55, 25);
		Step4Panel.add(lblNewLabel);
		
		JLabel lblC = new JLabel("C");
		lblC.setEnabled(false);
		lblC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblC.setBounds(233, 35, 25, 25);
		Step4Panel.add(lblC);
		
		Mash_Step2_Time = new JTextField();
		Mash_Step2_Time.setText("-");
		Mash_Step2_Time.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step2_Time.setForeground(Color.BLUE);
		Mash_Step2_Time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step2_Time.setEditable(false);
		Mash_Step2_Time.setColumns(10);
		Mash_Step2_Time.setBounds(90, 61, 40, 25);
		Step4Panel.add(Mash_Step2_Time);
		
		JLabel label = new JLabel("min   @");
		label.setEnabled(false);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(133, 61, 55, 25);
		Step4Panel.add(label);
		
		Mash_Step2_Temp = new JTextField();
		Mash_Step2_Temp.setText("-");
		Mash_Step2_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step2_Temp.setForeground(Color.BLUE);
		Mash_Step2_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step2_Temp.setEditable(false);
		Mash_Step2_Temp.setColumns(10);
		Mash_Step2_Temp.setBounds(190, 61, 40, 25);
		Step4Panel.add(Mash_Step2_Temp);
		
		JLabel label_1 = new JLabel("C");
		label_1.setEnabled(false);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(233, 61, 25, 25);
		Step4Panel.add(label_1);
		
		Mash_Step3_Time = new JTextField();
		Mash_Step3_Time.setText("-");
		Mash_Step3_Time.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step3_Time.setForeground(Color.BLUE);
		Mash_Step3_Time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step3_Time.setEditable(false);
		Mash_Step3_Time.setColumns(10);
		Mash_Step3_Time.setBounds(90, 87, 40, 27);
		Step4Panel.add(Mash_Step3_Time);
		
		JLabel label_2 = new JLabel("min   @");
		label_2.setEnabled(false);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(133, 87, 55, 27);
		Step4Panel.add(label_2);
		
		Mash_Step3_Temp = new JTextField();
		Mash_Step3_Temp.setText("-");
		Mash_Step3_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Mash_Step3_Temp.setForeground(Color.BLUE);
		Mash_Step3_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mash_Step3_Temp.setEditable(false);
		Mash_Step3_Temp.setColumns(10);
		Mash_Step3_Temp.setBounds(190, 87, 40, 27);
		Step4Panel.add(Mash_Step3_Temp);
		
		JLabel label_3 = new JLabel("C");
		label_3.setEnabled(false);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(233, 87, 25, 27);
		Step4Panel.add(label_3);
		
		JLabel label_4 = new JLabel("1.");
		label_4.setEnabled(false);
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(63, 35, 23, 25);
		Step4Panel.add(label_4);
		
		JLabel label_5 = new JLabel("2.");
		label_5.setEnabled(false);
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(63, 61, 23, 25);
		Step4Panel.add(label_5);
		
		JLabel label_6 = new JLabel("3.");
		label_6.setEnabled(false);
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(63, 87, 23, 25);
		Step4Panel.add(label_6);
		
		JPanel Step5Panel = new JPanel();
		Step5Panel.setLayout(null);
		Step5Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		Step5Panel.setBounds(10, 375, 385, 85);
		contentPane.add(Step5Panel);
		
		JLabel lblSetSpargeTemperature = new JLabel("Set Sparge Temp");
		lblSetSpargeTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetSpargeTemperature.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetSpargeTemperature.setFont(new Font("Vani", Font.BOLD, 14));
		lblSetSpargeTemperature.setBounds(49, 11, 244, 30);
		Step5Panel.add(lblSetSpargeTemperature);
		
		JLabel lblWaterHeatingIconStep5 = new JLabel("");
		lblWaterHeatingIconStep5.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\HeatIcon3.png"));
		lblWaterHeatingIconStep5.setBounds(7, 16, 58, 56);
		Step5Panel.add(lblWaterHeatingIconStep5);
		
		button_1 = new JButton("SET");
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBounds(268, 17, 107, 58);
		Step5Panel.add(button_1);
		
		SpargeTemp = new JTextField();
		SpargeTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		SpargeTemp.setForeground(Color.BLUE);
		SpargeTemp.setEditable(false);
		SpargeTemp.setColumns(10);
		SpargeTemp.setBounds(130, 45, 58, 25);
		Step5Panel.add(SpargeTemp);
		
		JLabel label_8 = new JLabel("C");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setEnabled(false);
		label_8.setBounds(190, 45, 25, 25);
		Step5Panel.add(label_8);
		
		JPanel Step6Panel = new JPanel();
		Step6Panel.setLayout(null);
		Step6Panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Step 6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		Step6Panel.setBounds(10, 460, 385, 113);
		contentPane.add(Step6Panel);
		
		JLabel lblSetHopAddition = new JLabel("Set Hop Schedule");
		lblSetHopAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetHopAddition.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetHopAddition.setFont(new Font("Vani", Font.BOLD, 14));
		lblSetHopAddition.setBounds(40, 0, 244, 30);
		Step6Panel.add(lblSetHopAddition);
		
		JLabel lblTimerIconStep6 = new JLabel("");
		lblTimerIconStep6.setIcon(new ImageIcon("E:\\EclipseBrewery\\HomeBrewery\\img\\TimerIcon2.png"));
		lblTimerIconStep6.setBounds(5, 16, 58, 56);
		Step6Panel.add(lblTimerIconStep6);
		
		Set_HopSchedule = new JButton("SET");
		Set_HopSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int step = showMessage("Hopping Schedule Step to Set Up: ");
				if(step == 0){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Alpha-Acid %");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step1_Time.setText(InputDialog.getUser_Entry());
						vals.setHop_Step1_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step1_Temp.setText(InputDialog.getUser_Entry());
						vals.setHop_Step1_Percent(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					
				}
				else if(step == 1){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Alpha-Acid %");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step2_Time.setText(InputDialog.getUser_Entry());
						vals.setHop_Step2_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step2_Temp.setText(InputDialog.getUser_Entry());
						vals.setHop_Step2_Percent(Double.parseDouble(InputDialog.getUser_Entry()));
					}
				}
				else if(step == 3){
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_C().setLabel("Minutes");
					InputDialog.getSel_C().setSize(InputDialog.getSel_C().getSize().width *2, InputDialog.getSel_C().getSize().height);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.setTitle("Set Alpha-Acid %");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step3_Time.setText(InputDialog.getUser_Entry());
						vals.setHop_Step3_Time(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					InputDialog = new SetterDialog();
					InputDialog.getSel_C().setSelected(true);
					InputDialog.getSel_L().setVisible(false);
					InputDialog.getSel_F().setVisible(false);
					InputDialog.setTitle("Set Time (Min)");
					if(InputDialog.getUserInput() == true) // triggers the Dialog Box and if SET button is pressed
					{
						Hop_Step3_Temp.setText(InputDialog.getUser_Entry());
						vals.setHop_Step3_Percent(Double.parseDouble(InputDialog.getUser_Entry()));
					}
					
				}
			}
		});
		Set_HopSchedule.setForeground(Color.RED);
		Set_HopSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Set_HopSchedule.setBounds(268, 17, 107, 86);
		Step6Panel.add(Set_HopSchedule);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMin.setEnabled(false);
		lblMin.setBounds(233, 28, 35, 25);
		Step6Panel.add(lblMin);
		
		Hop_Step1_Temp = new JTextField();
		Hop_Step1_Temp.setText("-");
		Hop_Step1_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step1_Temp.setForeground(Color.BLUE);
		Hop_Step1_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step1_Temp.setEditable(false);
		Hop_Step1_Temp.setColumns(10);
		Hop_Step1_Temp.setBounds(190, 28, 40, 25);
		Step6Panel.add(Hop_Step1_Temp);
		
		JLabel label_10 = new JLabel("%    @");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_10.setEnabled(false);
		label_10.setBounds(133, 28, 55, 25);
		Step6Panel.add(label_10);
		
		Hop_Step1_Time = new JTextField();
		Hop_Step1_Time.setText("-");
		Hop_Step1_Time.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step1_Time.setForeground(Color.BLUE);
		Hop_Step1_Time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step1_Time.setEditable(false);
		Hop_Step1_Time.setColumns(10);
		Hop_Step1_Time.setBounds(90, 28, 40, 25);
		Step6Panel.add(Hop_Step1_Time);
		
		JLabel label_11 = new JLabel("1.");
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_11.setEnabled(false);
		label_11.setBounds(63, 28, 23, 25);
		Step6Panel.add(label_11);
		
		JLabel label_12 = new JLabel("2.");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_12.setEnabled(false);
		label_12.setBounds(63, 54, 23, 25);
		Step6Panel.add(label_12);
		
		Hop_Step2_Time = new JTextField();
		Hop_Step2_Time.setText("-");
		Hop_Step2_Time.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step2_Time.setForeground(Color.BLUE);
		Hop_Step2_Time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step2_Time.setEditable(false);
		Hop_Step2_Time.setColumns(10);
		Hop_Step2_Time.setBounds(90, 54, 40, 25);
		Step6Panel.add(Hop_Step2_Time);
		
		JLabel label_13 = new JLabel("%    @");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_13.setEnabled(false);
		label_13.setBounds(133, 54, 55, 25);
		Step6Panel.add(label_13);
		
		Hop_Step2_Temp = new JTextField();
		Hop_Step2_Temp.setText("-");
		Hop_Step2_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step2_Temp.setForeground(Color.BLUE);
		Hop_Step2_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step2_Temp.setEditable(false);
		Hop_Step2_Temp.setColumns(10);
		Hop_Step2_Temp.setBounds(190, 54, 40, 25);
		Step6Panel.add(Hop_Step2_Temp);
		
		JLabel lblMin_1 = new JLabel("min");
		lblMin_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMin_1.setEnabled(false);
		lblMin_1.setBounds(233, 54, 25, 25);
		Step6Panel.add(lblMin_1);
		
		JLabel lblMin_2 = new JLabel("min");
		lblMin_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMin_2.setEnabled(false);
		lblMin_2.setBounds(233, 80, 25, 27);
		Step6Panel.add(lblMin_2);
		
		Hop_Step3_Temp = new JTextField();
		Hop_Step3_Temp.setText("-");
		Hop_Step3_Temp.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step3_Temp.setForeground(Color.BLUE);
		Hop_Step3_Temp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step3_Temp.setEditable(false);
		Hop_Step3_Temp.setColumns(10);
		Hop_Step3_Temp.setBounds(190, 80, 40, 27);
		Step6Panel.add(Hop_Step3_Temp);
		
		JLabel label_16 = new JLabel("%    @");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_16.setEnabled(false);
		label_16.setBounds(133, 80, 55, 27);
		Step6Panel.add(label_16);
		
		Hop_Step3_Time = new JTextField();
		Hop_Step3_Time.setText("-");
		Hop_Step3_Time.setHorizontalAlignment(SwingConstants.TRAILING);
		Hop_Step3_Time.setForeground(Color.BLUE);
		Hop_Step3_Time.setFont(new Font("Tahoma", Font.BOLD, 14));
		Hop_Step3_Time.setEditable(false);
		Hop_Step3_Time.setColumns(10);
		Hop_Step3_Time.setBounds(90, 80, 40, 27);
		Step6Panel.add(Hop_Step3_Time);
		
		JLabel label_17 = new JLabel("3.");
		label_17.setHorizontalAlignment(SwingConstants.TRAILING);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_17.setEnabled(false);
		label_17.setBounds(63, 80, 23, 25);
		Step6Panel.add(label_17);
		
		
	}

	protected int showMessage(String Message){
		String 			IconPath = "/home/pi/EclipseBrewery/HomeBrewery/img/";
		Icon icon = new ImageIcon(IconPath+"TempIcon.png");
				
		return JOptionPane.showOptionDialog(new JFrame(), new JPanel().add(new JLabel(Message)), "Step Chooser", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE, icon,
                new String[]{"Step 1","Step 2", "Step 3"},
                "default");
	}
}
