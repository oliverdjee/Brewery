package Hops;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Graphs.DrawToPanel;
import Util.Utility;

import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HopNomogram extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawToPanel GraphArea;
	private JSlider AlphaAcidSlider;
	private JSlider BeerBuSlider;
	private JSlider HopQtySlider;
	private JSlider HopUsageSlider;
	private double Yslope;
	private double Yb;
	private double Xslope;
	private double Xb;
	private double pivotX;
	private double pivotY;
	private JLabel HopIcon;
	private JLabel lblHopQtyg;
	private JLabel lblIbu;
	private JLabel lblAlphaAcid;
	private JLabel lblHopUsage;
	private JLabel lblWortOg;
	private JLabel lblTimeOfAddition;
	private JComboBox<String> comboBox;
	private JLabel lblHopUsage_1;
	private JTextField HopUsageCalc;
	private JSpinner spinner;
	
	public HopNomogram() {
		setResizable(false);
		setMinimumSize(new Dimension(750, 620));
		setType(Type.POPUP);
		setName("HopBitternessFrame");
		setPreferredSize(new Dimension(750, 620));
		setTitle("Hop-Bitterness Nomogram (20L Batch)");
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		
		JPanel GraphPanel = new JPanel();
		GraphPanel.setMinimumSize(new Dimension(620, 475));
		GraphPanel.setBackground(new Color(0, 0, 0));
		GraphPanel.setBounds(40, 35, 657, 520);
		getContentPane().add(GraphPanel);
		GraphPanel.setLayout(null);
		
		lblHopUsage = new JLabel("Hop Usage (%)");
		lblHopUsage.setForeground(Color.WHITE);
		lblHopUsage.setBounds(427, 455, 130, 20);
		GraphPanel.add(lblHopUsage);
		
		HopUsageSlider = new JSlider();
		HopUsageSlider.setPaintTrack(false);
		HopUsageSlider.setPaintTicks(true);
		HopUsageSlider.setPaintLabels(true);
		HopUsageSlider.setEnabled(false);
		HopUsageSlider.setBackground(new Color(0, 0, 0));
		HopUsageSlider.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		HopUsageSlider.setFont(new Font("Tahoma", Font.BOLD, 12));
		HopUsageSlider.setBounds(60, 455, 500, 50);
		GraphPanel.add(HopUsageSlider);
		HopUsageSlider.setMinorTickSpacing(1);
		HopUsageSlider.setValue(20);
		HopUsageSlider.setMajorTickSpacing(5);
		HopUsageSlider.setMaximum(80);
		
		HopQtySlider = new JSlider();
		HopQtySlider.setPaintTrack(false);
		HopQtySlider.setPaintTicks(true);
		HopQtySlider.setPaintLabels(true);
		HopQtySlider.setEnabled(false);
		HopQtySlider.setBackground(new Color(0, 0, 0));
		HopQtySlider.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		HopQtySlider.setInverted(true);
		HopQtySlider.setFont(new Font("Tahoma", Font.BOLD, 12));
		HopQtySlider.setBounds(575, 70, 60, 375);
		GraphPanel.add(HopQtySlider);
		HopQtySlider.setValue(20);
		HopQtySlider.setOrientation(SwingConstants.VERTICAL);
		HopQtySlider.setMinorTickSpacing(1);
		HopQtySlider.setMajorTickSpacing(10);
		
		
		
		AlphaAcidSlider = new JSlider();
		AlphaAcidSlider.setForeground(new Color(0, 191, 255));
		AlphaAcidSlider.setBackground(new Color(0, 0, 0));
		AlphaAcidSlider.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlphaAcidSlider.setMinorTickSpacing(1);
		AlphaAcidSlider.setMajorTickSpacing(2);
		AlphaAcidSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AlphaAcidSlider.setMinimum(3);
		AlphaAcidSlider.setBounds(112, 20, 350, 50);
		GraphPanel.add(AlphaAcidSlider);
		AlphaAcidSlider.setValue(9);
		AlphaAcidSlider.setMaximum(20);
		AlphaAcidSlider.setPaintTicks(true);
		AlphaAcidSlider.setPaintLabels(true);
		
		BeerBuSlider = new JSlider();
		BeerBuSlider.setForeground(new Color(0, 191, 255));
		BeerBuSlider.setBackground(new Color(0, 0, 0));
		BeerBuSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BeerBuSlider.setFont(new Font("Tahoma", Font.BOLD, 12));
		BeerBuSlider.setBounds(0, 70, 60, 375);
		GraphPanel.add(BeerBuSlider);
		BeerBuSlider.setMajorTickSpacing(10);
		BeerBuSlider.setMinorTickSpacing(1);
		BeerBuSlider.setValue(20);
		BeerBuSlider.setPaintLabels(true);
		BeerBuSlider.setPaintTicks(true);
		BeerBuSlider.setOrientation(SwingConstants.VERTICAL);
		
		GraphArea = new DrawToPanel();
		GraphArea.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		GraphArea.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		GraphArea.setBackground(new Color(245, 222, 179));
		GraphArea.setBounds(70, 75, 500, 375);
		GraphPanel.add(GraphArea);
		GraphArea.setLayout(null);
		
		HopIcon = new JLabel("");
		HopIcon.setIcon(new ImageIcon(HopNomogram.class.getResource("/hops.png")));
		HopIcon.setBounds(new Rectangle(15, 16, 49, 50));
		GraphArea.add(HopIcon);
		
		lblWortOg = new JLabel("Wort O.G. :");
		lblWortOg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWortOg.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWortOg.setBounds(279, 275, 128, 20);
		GraphArea.add(lblWortOg);
		
		lblTimeOfAddition = new JLabel("Time of Addition :");
		lblTimeOfAddition.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTimeOfAddition.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimeOfAddition.setBounds(235, 305, 172, 20);
		GraphArea.add(lblTimeOfAddition);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("2h00");
		comboBox.addItem("1h30");
		comboBox.addItem("1h00");
		comboBox.addItem("0h45");
		comboBox.addItem("0h30");
		comboBox.addItem("0h20");
		comboBox.addItem("0h10");
		comboBox.addItem("0h05");
		comboBox.setSelectedIndex(2);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(408, 302, 77, 26);
		GraphArea.add(comboBox);
		
		spinner = new JSpinner();
		spinner.setForeground(new Color(0, 0, 0));
		spinner.setBackground(new Color(0, 191, 255));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 18));
		spinner.setModel(new SpinnerNumberModel(1.056, 1.0, 1.150, 0.001));
		spinner.setBounds(408, 272, 77, 26);
		GraphArea.add(spinner);
		
		lblHopUsage_1 = new JLabel("Hop Usage (%)");
		lblHopUsage_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHopUsage_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHopUsage_1.setBounds(235, 339, 172, 20);
		GraphArea.add(lblHopUsage_1);
		
		HopUsageCalc = new JTextField();
		HopUsageCalc.setText("27");
		HopUsageCalc.setBackground(new Color(0, 0, 0));
		HopUsageCalc.setForeground(new Color(0, 191, 255));
		HopUsageCalc.setFont(new Font("Tahoma", Font.BOLD, 18));
		HopUsageCalc.setEditable(false);
		HopUsageCalc.setBounds(408, 337, 77, 26);
		GraphArea.add(HopUsageCalc);
		HopUsageCalc.setColumns(10);
		
		
		lblHopQtyg = new JLabel("Hop Qty (g)");
		lblHopQtyg.setForeground(new Color(255, 255, 255));
		lblHopQtyg.setBounds(562, 50, 95, 20);
		GraphPanel.add(lblHopQtyg);
		
		lblIbu = new JLabel("IBU");
		lblIbu.setForeground(Color.WHITE);
		lblIbu.setBounds(15, 50, 45, 20);
		GraphPanel.add(lblIbu);
		
		lblAlphaAcid = new JLabel("Alpha Acid (%)");
		lblAlphaAcid.setForeground(Color.WHITE);
		lblAlphaAcid.setBounds(118, 0, 130, 20);
		GraphPanel.add(lblAlphaAcid);
		
		JButton btnHide = new JButton("Hide");
		btnHide.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHide.setForeground(new Color(0, 191, 255));
		btnHide.setBackground(new Color(245, 222, 179));
		btnHide.setBounds(40, 3, 115, 29);
		getContentPane().add(btnHide);
		btnHide.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
			
		});
		
		
		InitAxis();
		GraphArea.addMouseListener(new AxisMouseListener());
		BeerBuSlider.addChangeListener(new YAxisChangeListener());
		AlphaAcidSlider.addChangeListener(new XAxisChangeListener());
		comboBox.addActionListener(new UsageActionListener());
	}
	
	private void InitAxis()
	{
		GraphArea.addLine(0,375,500,0);
		GraphArea.setXPivotPoint(500, 0);
		GraphArea.setYPivotPoint(500, 0);
		
		double x1 = (double)HopUsageSlider.getValue()/HopUsageSlider.getMaximum()*500;
		double y1 = 0;
		double x2 = (double)AlphaAcidSlider.getValue();
		x2 = -0.0309*Math.pow(x2,4) + 1.5896*Math.pow(x2,3) - 29.65*Math.pow(x2,2)
				+ 247.31*x2 - 403.63;
		double y2 = 375;
		double[] axis = getXAxis(x1,y1,x2,y2);
		GraphArea.SetXaxis((int)axis[0],(int)axis[1],(int)axis[2],(int)axis[3]);
		double[] pivot = getPivotPoint(Xslope,Xb);
		GraphArea.setXPivotPoint((int)pivot[0], (int)pivot[1]);
		
		x1 = 0;
		y1 = (double)BeerBuSlider.getValue()*375/100;
		x2 = 500;
		y2 = (HopQtySlider.getMaximum()-(double)HopQtySlider.getValue())*375/100;
		axis = getYAxis(x1,y1,x2,y2);
		GraphArea.SetYaxis((int)axis[0],(int)axis[1],(int)axis[2],(int)axis[3]);
		pivot = getPivotPoint(Yslope,Yb);
		GraphArea.setYPivotPoint((int)pivot[0], (int)pivot[1]);
	}
	
	private class UsageActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(comboBox))
			{
				double value = (double)spinner.getValue();
				String time = (String) comboBox.getSelectedItem();
				if(time.equals("0h05"))
				{
					HopsAddition hop = new HopsAddition(HopsAddition.fiveminutes,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("0h10"))
				{
					HopsAddition hop = new HopsAddition(HopsAddition.tenminutes,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("0h20"))
				{
					HopsAddition hop = new HopsAddition(HopsAddition.twentyminutes,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("0h30"))
				{
					HopsAddition hop = new HopsAddition(HopsAddition.thirtyminutes,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("0h45"))
				{
					HopsAddition hop = new HopsAddition(HopsAddition.fortyfiveminutes,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("1h00"))
				{
					HopsAddition hop = new HopsAddition(1,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("2h00"))
				{
					HopsAddition hop = new HopsAddition(2,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("2h30"))
				{
					HopsAddition hop = new HopsAddition(2.5,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				else if(time.equals("1h30"))
				{
					HopsAddition hop = new HopsAddition(1.5,value);
					HopUsageCalc.setText(Utility.FormatDecimals(String.valueOf(hop.getUtilization()),
							1,
							"0")+" %");
				}
				
			}
		}
		
	}
	
	private class XAxisChangeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			double x2 = (double)AlphaAcidSlider.getValue();
			x2 = -0.0309*Math.pow(x2,4) + 1.5896*Math.pow(x2,3) - 29.65*Math.pow(x2,2)
					+ 247.31*x2 - 403.63;
			double[] axis = getXAxis(pivotX,pivotY,(int)x2, 375);
			GraphArea.SetXaxis((int)axis[0], (int)axis[1], (int)axis[2], (int)axis[3]);
			
			HopUsageSlider.setValue((int)((double)axis[0]/500.0*HopUsageSlider.getMaximum()));
		}
	}
	
	private class YAxisChangeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			double[] axis = getYAxis(0.0,(double)BeerBuSlider.getValue()/(double)BeerBuSlider.getMaximum()*375.0,pivotX,pivotY);
			HopQtySlider.setValue((int)((double)axis[3]/375.0*(double)HopQtySlider.getMaximum()));
			GraphArea.SetYaxis((int)axis[0], (int)axis[1], (int)axis[2], (int)axis[3]);
		}
	}
	
	private class AxisMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			double x = e.getPoint().getX();
			double rx1,ry1,rx2,ry2;
			rx1 = 0;ry1 = 0; rx2 = 500; ry2 = 375;
			
			double rslope = (ry2-ry1)/(rx2-rx1);
			double rb = ry1 - (rslope*rx1);
			double y = rslope*x+rb;
			GraphArea.setXPivotPoint((int)x, (int)(375-y));
			GraphArea.setYPivotPoint((int)x, (int)(375-y));
			pivotX = x;
			pivotY = y;
			double[] axis = getYAxis(0,((double)BeerBuSlider.getValue()/(double)BeerBuSlider.getMaximum())*375.0, x, y);
			GraphArea.SetYaxis((int)axis[0], (int)axis[1], (int)axis[2], (int)axis[3]);
			HopQtySlider.setValue((int)(((double)(axis[3]))/375.0*(double)HopQtySlider.getMaximum()));
			
			
			double x2 = (double)AlphaAcidSlider.getValue();
			x2 = -0.0309*Math.pow(x2,4) + 1.5896*Math.pow(x2,3) - 29.65*Math.pow(x2,2)
					+ 247.31*x2 - 403.63;
			axis = getXAxis(x,y,(int)x2, 375);
			GraphArea.SetXaxis((int)axis[0], (int)axis[1], (int)axis[2], (int)axis[3]);
			
			HopUsageSlider.setValue((int)((double)axis[0]/500.0*HopUsageSlider.getMaximum()));
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	
	
	protected double[] getPivotPoint(double slope, double b) {
		double rx1,ry1,rx2,ry2;
		rx1 = 0;ry1 = 0; rx2 = 500; ry2 = 375;
		
		double rslope = (ry2-ry1)/(rx2-rx1);
		double rb = ry1 - (rslope*rx1);
		
		//rslope*x+rb = slope*x+b
		//rb-b = slope*x - rslope*x
		//rb - b = x(slope-rslope)
		double x = (rb - b) / (slope-rslope);
		double y = (rslope*x) + rb;
		
		pivotX = x;
		pivotY = y;
		
		return new double[]{x,375-y};
	}

	public double[] getYAxis(double x1, double y1, double x2, double y2)
	{
		
		double slope = (y2-y1)/(x2-x1);
		double b = y1 - (slope*x1);
		
		double _x2 = 500;
		double _y2 = (slope*_x2) + b;
		double _x1 = 0;
		double _y1 = (slope*_x1) + b;
		Yslope = slope;
		Yb = b;
		return new double[]{_x1,375-_y1,_x2,375-_y2};
	}
	
	public double[] getXAxis(double x1, double y1, double x2, double y2)
	{
		double slope = (y2-y1)/(x2-x1);
		double b = y1 - (slope*x1);
		
		double _y2 = 375;
		double _x2 = (_y2 - b)/slope;
		double _y1 = 0;
		double _x1 = (_y1 - b)/slope;
		Xslope = slope;
		Xb = b;
		return new double[]{_x1,375-_y1,_x2,375-_y2};
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HopNomogram frame = new HopNomogram();
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
