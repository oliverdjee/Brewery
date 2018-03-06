package Grain;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Util.Utility;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class GrainBill extends JFrame{
	private GrainAddition GrainAddition;
	
	public GrainBill() {
		setGrainAddition(new GrainAddition(10));
		setTitle("Grain Bill");
		getContentPane().setLayout(null);
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(Color.WHITE);
		TopPanel.setBounds(0, 0, 640, 39);
		getContentPane().add(TopPanel);
		TopPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Hide");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setForeground(new Color(0, 139, 139));
		btnNewButton.setBackground(new Color(255, 222, 173));
		btnNewButton.setBounds(5, 5, 115, 29);
		TopPanel.add(btnNewButton);
		
		JPanel GrainBillPanel = new JPanel();
		GrainBillPanel.setBackground(new Color(255, 222, 173));
		GrainBillPanel.setBounds(0, 40, 640, 350);
		getContentPane().add(GrainBillPanel);
		GrainBillPanel.setLayout(null);
		
		Malt WheatMalt = new Malt(Cereal.WheatMalt);
		MaltedWheat_Toggle = new JToggleButton("");
		MaltedWheat_Toggle.setSelectedIcon(new ImageIcon(GrainBill.class.getResource("/GUI/images/transfer.png")));
		MaltedWheat_Toggle.setBorder(new LineBorder(new Color(0, 128, 128), 4, true));
		MaltedWheat_Toggle.setIcon(new ImageIcon(GrainBill.class.getResource("/GUI/images/Grain.png")));
		MaltedWheat_Toggle.setOpaque(false);
		MaltedWheat_Toggle.setMargin(new Insets(0, 0, 0, 0));
		MaltedWheat_Toggle.setFont(new Font("BankGothic Md BT", Font.BOLD, 18));
		MaltedWheat_Toggle.setBounds(15, 40, 60, 60);
		GrainBillPanel.add(MaltedWheat_Toggle);
		MaltedWheat_Toggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(((JToggleButton)arg0.getSource()).isSelected())
				{
					GrainAddition.AddMalt(WheatMalt);
					UpdateMaltSpinners(WheatMalt,MaltedWheat_Spinner);
				}
				else
				{
					GrainAddition.RemoveMalt(WheatMalt);
					WheatMalt.setQuantity(0.0);
					UpdateMaltSpinners(WheatMalt,MaltedWheat_Spinner);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Available Grain:");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		lblNewLabel.setBounds(15, 0, 263, 47);
		GrainBillPanel.add(lblNewLabel);
		
		JLabel lblMaltedWheat = new JLabel("Malted Wheat");
		lblMaltedWheat.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblMaltedWheat.setBounds(90, 69, 117, 31);
		GrainBillPanel.add(lblMaltedWheat);
		
		JLabel lblMarrisOtter = new JLabel("Marris Otter");
		lblMarrisOtter.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblMarrisOtter.setBounds(90, 134, 117, 31);
		GrainBillPanel.add(lblMarrisOtter);
		
		JLabel lblPaleAleMalt = new JLabel("Pale Ale Malt");
		lblPaleAleMalt.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblPaleAleMalt.setBounds(90, 199, 117, 31);
		GrainBillPanel.add(lblPaleAleMalt);
		
		Malt MarrisOtter = new Malt(Cereal.MarisOtter);
		MarrisOtter_Toggle = new JToggleButton("");
		MarrisOtter_Toggle.setIcon(new ImageIcon(GrainBill.class.getResource("/GUI/images/Grain.png")));
		MarrisOtter_Toggle.setOpaque(false);
		MarrisOtter_Toggle.setMargin(new Insets(0, 0, 0, 0));
		MarrisOtter_Toggle.setFont(new Font("BankGothic Md BT", Font.BOLD, 18));
		MarrisOtter_Toggle.setBorder(new LineBorder(new Color(0, 128, 128), 4, true));
		MarrisOtter_Toggle.setBounds(15, 105, 60, 60);
		GrainBillPanel.add(MarrisOtter_Toggle);
		MarrisOtter_Toggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(((JToggleButton)arg0.getSource()).isSelected())
				{
					GrainAddition.AddMalt(MarrisOtter);
					UpdateMaltSpinners(MarrisOtter,MarrisOtter_Spinner);
				}
				else
				{
					GrainAddition.RemoveMalt(MarrisOtter);
					MarrisOtter.setQuantity(0.0);
					UpdateMaltSpinners(MarrisOtter,MarrisOtter_Spinner);
				}
			}
		});
		
		Malt PaleMalt = new Malt(Cereal.PaleAleMalt);
		PaleAleMalt_Toggle = new JToggleButton("");
		PaleAleMalt_Toggle.setIcon(new ImageIcon(GrainBill.class.getResource("/GUI/images/Grain.png")));
		PaleAleMalt_Toggle.setOpaque(false);
		PaleAleMalt_Toggle.setMargin(new Insets(0, 0, 0, 0));
		PaleAleMalt_Toggle.setFont(new Font("BankGothic Md BT", Font.BOLD, 18));
		PaleAleMalt_Toggle.setBorder(new LineBorder(new Color(0, 128, 128), 4, true));
		PaleAleMalt_Toggle.setBounds(15, 170, 60, 60);
		GrainBillPanel.add(PaleAleMalt_Toggle);
		PaleAleMalt_Toggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(PaleAleMalt_Toggle.isSelected())
				{
					GrainAddition.AddMalt(PaleMalt);
					UpdateMaltSpinners(PaleMalt,PaleAleMalt_Spinner);
				}
				else
				{
					GrainAddition.RemoveMalt(PaleMalt);
					PaleMalt.setQuantity(0.0);
					UpdateMaltSpinners(PaleMalt,PaleAleMalt_Spinner);
				}
			}
		});
		
		MaltedWheat_Spinner = new JSpinner();
		MaltedWheat_Spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.01)));
		MaltedWheat_Spinner.setBackground(new Color(0, 0, 0));
		MaltedWheat_Spinner.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		MaltedWheat_Spinner.setForeground(new Color(255, 255, 255));
		MaltedWheat_Spinner.setBounds(90, 45, 106, 26);
		GrainBillPanel.add(MaltedWheat_Spinner);
		MaltedWheat_Spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				GrainAddition.setQty(WheatMalt,(double)((JSpinner)arg0.getSource()).getValue());
				UpdateOGSpinners();
			}
		});
		
		MarrisOtter_Spinner = new JSpinner();
		MarrisOtter_Spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.01)));
		MarrisOtter_Spinner.setBackground(new Color(0, 0, 0));
		MarrisOtter_Spinner.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		MarrisOtter_Spinner.setForeground(new Color(255, 255, 255));
		MarrisOtter_Spinner.setBounds(90, 112, 106, 26);
		GrainBillPanel.add(MarrisOtter_Spinner);
		MarrisOtter_Spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				GrainAddition.setQty(MarrisOtter,(double)((JSpinner)arg0.getSource()).getValue());
				UpdateOGSpinners();
			}
		});
		
		PaleAleMalt_Spinner = new JSpinner();
		PaleAleMalt_Spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.01)));
		PaleAleMalt_Spinner.setBackground(new Color(0, 0, 0));
		PaleAleMalt_Spinner.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		PaleAleMalt_Spinner.setForeground(new Color(255, 255, 255));
		PaleAleMalt_Spinner.setBounds(90, 175, 106, 26);
		GrainBillPanel.add(PaleAleMalt_Spinner);
		PaleAleMalt_Spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				GrainAddition.setQty(PaleMalt,(double)((JSpinner)arg0.getSource()).getValue());
				UpdateOGSpinners();
			}
		});
		
		TargetOG_Spinner = new JSpinner();
		TargetOG_Spinner.setModel(new SpinnerNumberModel(1.056, 1.000, 1.200, 0.001));
		TargetOG_Spinner.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		TargetOG_Spinner.setBounds(463, 45, 146, 26);
		GrainBillPanel.add(TargetOG_Spinner);
		
		CurrentOG = new JTextField();
		CurrentOG.setHorizontalAlignment(SwingConstants.TRAILING);
		CurrentOG.setText("1.000");
		CurrentOG.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		CurrentOG.setBounds(463, 97, 146, 26);
		GrainBillPanel.add(CurrentOG);
		CurrentOG.setColumns(10);
		
		JLabel lblTargetOg = new JLabel("Target OG:");
		lblTargetOg.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblTargetOg.setBounds(463, 27, 146, 20);
		GrainBillPanel.add(lblTargetOg);
		
		JLabel lblCurrentOg = new JLabel("Current OG:");
		lblCurrentOg.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblCurrentOg.setBounds(463, 80, 146, 20);
		GrainBillPanel.add(lblCurrentOg);
		
		BatchVolume_Spinner = new JSpinner();
		BatchVolume_Spinner.setModel(new SpinnerNumberModel(new Double(10), new Double(5), null, new Double(0.5)));
		BatchVolume_Spinner.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		BatchVolume_Spinner.setBounds(463, 152, 146, 26);
		GrainBillPanel.add(BatchVolume_Spinner);
		BatchVolume_Spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				GrainAddition.setBatchVolume((double)((JSpinner)arg0.getSource()).getValue());
				UpdateOGSpinners();
			}
		});
		
		JLabel lblExpectedBatchOg = new JLabel("Batch Volume:");
		lblExpectedBatchOg.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblExpectedBatchOg.setBounds(463, 134, 146, 20);
		GrainBillPanel.add(lblExpectedBatchOg);
		
		JLabel lblKg = new JLabel("Kg");
		lblKg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblKg.setBounds(201, 170, 38, 34);
		GrainBillPanel.add(lblKg);
		
		JLabel label = new JLabel("Kg");
		label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		label.setBounds(201, 105, 38, 34);
		GrainBillPanel.add(label);
		
		JLabel label_1 = new JLabel("Kg");
		label_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		label_1.setBounds(201, 40, 38, 34);
		GrainBillPanel.add(label_1);
		
		JLabel lblL = new JLabel("L");
		lblL.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblL.setBounds(613, 152, 27, 29);
		GrainBillPanel.add(lblL);
	}

	public GrainAddition getGrainAddition() {
		return GrainAddition;
	}

	public void setGrainAddition(GrainAddition grainAddition) {
		GrainAddition = grainAddition;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner MaltedWheat_Spinner;
	private JSpinner MarrisOtter_Spinner;
	private JSpinner PaleAleMalt_Spinner;
	private JToggleButton MaltedWheat_Toggle;
	private JToggleButton MarrisOtter_Toggle;
	private JToggleButton PaleAleMalt_Toggle;
	private JTextField CurrentOG;
	private JSpinner TargetOG_Spinner;
	private JSpinner BatchVolume_Spinner;

	private void UpdateOGSpinners() {
		CurrentOG.setText(Utility.FormatDecimals(String.valueOf(GrainAddition.getCurrentOG()),3,"0"));
		if(GrainAddition.isOverTarget())
		{
			TargetOG_Spinner.setForeground(new Color(225,225,225));
			TargetOG_Spinner.setBackground(new Color(200,40,40));
		}
		else
		{
			TargetOG_Spinner.setForeground(new Color(0,0,0));
		}
	}
	
	private void UpdateMaltSpinners(Malt Malt, JSpinner Spinner) {
		
		Spinner.setValue(Malt.getQuantity());
		UpdateOGSpinners();
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrainBill frame = new GrainBill();
					frame.setVisible(true);
					frame.setPreferredSize(new Dimension(750,600));
					frame.pack();
				}
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		});
	}
}
