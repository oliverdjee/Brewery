package TouchScreenPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import Schedule.SchedulePanel;


public class SetterDialog extends JOptionPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 			IconPath = "/home/pi/EclipseBrewery/HomeBrewery/img/";
	Icon icon = new ImageIcon(IconPath+"TempIcon.png");
	private String				Title;
	private JPanel				SetterPanel;
	private String 				User_Entry = "";
	private JTextField 			TextField_UserEntry;
	private double 				value;
	private boolean 			Addition = false;
	private boolean 			Substraction = false;
	private boolean 			Multiplication = false;
	private boolean 			Division = false;
	private	 boolean[] 			CalcMove = {Addition,Substraction,Division,Multiplication};
	
	private JRadioButton 		Sel_Celsius;
	private JRadioButton 		Sel_Farenheit;
	private JRadioButton 		Sel_Litre;
	
	public SetterDialog()
	{
		Title = "Set Desired Value";
		SetterPanel = new JPanel();
		SetterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLACK, null, null));
		SetterPanel.setBounds(384, 157, 258, 259);
		SetterPanel.setLayout(null);
		SetterPanel.setVisible(true);
		

		TextField_UserEntry = new JTextField();
		TextField_UserEntry.setHorizontalAlignment(SwingConstants.CENTER);
		TextField_UserEntry.setText("");
		TextField_UserEntry.setFont(new Font("Tahoma", Font.BOLD, 16));
		TextField_UserEntry.setForeground(Color.RED);
		TextField_UserEntry.setEditable(false);
		TextField_UserEntry.setBounds(10, 30, 244, 29);
		SetterPanel.add(TextField_UserEntry);
		TextField_UserEntry.setColumns(10);
		
		JButton Btn_7 = new JButton("7");
		Btn_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_7.setForeground(Color.BLACK);
		Btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"7");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_7.setBounds(10, 59, 67, 44);
		SetterPanel.add(Btn_7);
		
		JButton Btn_8 = new JButton("8");
		Btn_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_8.setForeground(Color.BLACK);
		Btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"8");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_8.setBounds(76, 59, 67, 44);
		SetterPanel.add(Btn_8);
		
		JButton Btn_9 = new JButton("9");
		Btn_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_9.setForeground(Color.BLACK);
		Btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"9");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_9.setBounds(142, 59, 67, 44);
		SetterPanel.add(Btn_9);
		
		JButton Btn_4 = new JButton("4");
		Btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"4");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_4.setForeground(Color.BLACK);
		Btn_4.setBounds(10, 107, 67, 44);
		SetterPanel.add(Btn_4);
		
		JButton Btn_5 = new JButton("5");
		Btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"5");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_5.setForeground(Color.BLACK);
		Btn_5.setBounds(76, 107, 67, 44);
		SetterPanel.add(Btn_5);
		
		JButton Btn_6 = new JButton("6");
		Btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"6");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_6.setForeground(Color.BLACK);
		Btn_6.setBounds(142, 107, 67, 44);
		SetterPanel.add(Btn_6);
		
		JButton Btn_1 = new JButton("1");
		Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"1");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_1.setForeground(Color.BLACK);
		Btn_1.setBounds(10, 156, 67, 44);
		SetterPanel.add(Btn_1);
		
		JButton Btn_2 = new JButton("2");
		Btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"2");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_2.setForeground(Color.BLACK);
		Btn_2.setBounds(76, 156, 67, 44);
		SetterPanel.add(Btn_2);
		
		JButton Btn_3 = new JButton("3");
		Btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"3");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_3.setForeground(Color.BLACK);
		Btn_3.setBounds(142, 156, 67, 44);
		SetterPanel.add(Btn_3);
		
		JButton Btn_0 = new JButton("0");
		Btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry(getUser_Entry()+"0");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_0.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_0.setForeground(Color.BLACK);
		Btn_0.setBounds(10, 208, 143, 44);
		SetterPanel.add(Btn_0);
		
		JButton Btn_plus = new JButton("+");
		Btn_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setValue(Double.parseDouble(getUser_Entry()));
					setUser_Entry("");
					TextField_UserEntry.setText("");
					setCalcMove(true,false,false,false);
				}
				catch(Exception exception)
				{
					TextField_UserEntry.setText("ERROR WHEN ADDING TERMS");
					setUser_Entry("");
				}
			}
		});
		Btn_plus.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_plus.setForeground(Color.BLACK);
		Btn_plus.setBounds(209, 208, 45, 45);
		SetterPanel.add(Btn_plus);
		
		JButton Btn_minus = new JButton("-");
		Btn_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setValue(Double.parseDouble(getUser_Entry()));
					setUser_Entry("");
					TextField_UserEntry.setText("");
					setCalcMove(false,true,false,false);
				}
				catch(Exception exception)
				{
					TextField_UserEntry.setText("ERROR WHEN SUBSTRACTING TERMS");
					setUser_Entry("");
				}
			}
		});
		Btn_minus.setFont(new Font("Tahoma", Font.BOLD, 16));
		Btn_minus.setForeground(Color.BLACK);
		Btn_minus.setBounds(209, 156, 45, 45);
		SetterPanel.add(Btn_minus);
		
		JButton Btn_divide = new JButton("/");
		Btn_divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setValue(Double.parseDouble(getUser_Entry()));
					setUser_Entry("");
					TextField_UserEntry.setText("");
					setCalcMove(false,false,true,false);
				}
				catch(Exception exception)
				{
					TextField_UserEntry.setText("ERROR WHEN DIVIDING TERMS");
					setUser_Entry("");
				}
			}
		});
		Btn_divide.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_divide.setForeground(Color.BLACK);
		Btn_divide.setBounds(209, 107, 45, 45);
		SetterPanel.add(Btn_divide);
		
		JButton Btn_multiply = new JButton("x");
		Btn_multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setValue(Double.parseDouble(getUser_Entry()));
					setUser_Entry("");
					TextField_UserEntry.setText("");
					setCalcMove(false,false,false,true);
				}
				catch(Exception exception)
				{
					TextField_UserEntry.setText("ERROR WHEN MULTIPLYING TERMS");
					setUser_Entry("");
				}
			}
		});
		Btn_multiply.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_multiply.setForeground(Color.BLACK);
		Btn_multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Btn_multiply.setBounds(209, 59, 45, 45);
		SetterPanel.add(Btn_multiply);
		
		JButton Btn_Erase = new JButton("Erase");
		Btn_Erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUser_Entry("");
				TextField_UserEntry.setText(getUser_Entry());
			}
		});
		Btn_Erase.setBounds(166, 4, 89, 25);
		SetterPanel.add(Btn_Erase);
		
		JButton Btn_equal = new JButton("=");
		Btn_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value =  getValues();
				boolean[] action = getCalcMove();
				
				for(int x = 0; x < action.length; x++)
				{
					System.out.println(action[x]);
					if(action[x] == true)
					{
						if(x == 0)
						{
							double fValue = value + Double.parseDouble(TextField_UserEntry.getText());
							TextField_UserEntry.setText(Double.toString(fValue));
							setUser_Entry(Double.toString(fValue));
						}
						if(x == 1)
						{
							double fValue = value - Double.parseDouble(TextField_UserEntry.getText());
							TextField_UserEntry.setText(Double.toString(fValue));
							setUser_Entry(Double.toString(fValue));
						}
						if(x == 2)
						{
							double fValue = value / Double.parseDouble(TextField_UserEntry.getText());
							TextField_UserEntry.setText(Double.toString(fValue));
							setUser_Entry(Double.toString(fValue));
						}
						if(x == 3)
						{
							double fValue = value * Double.parseDouble(TextField_UserEntry.getText());
							TextField_UserEntry.setText(Double.toString(fValue));
							setUser_Entry(Double.toString(fValue));
						}
					}
				}
				setCalcMove(false,false,false,false);
			}
		});
		Btn_equal.setForeground(Color.BLACK);
		Btn_equal.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_equal.setBounds(152, 208, 57, 45);
		SetterPanel.add(Btn_equal);
		
		//Radio Button to decide what value is to be Set by the user (Temperature or Volume);
		Sel_Celsius = new JRadioButton("(C)");
		Sel_Celsius.setSelected(true);
		Sel_Celsius.setBounds(6, 5, 45, 23);
		SetterPanel.add(Sel_Celsius);
		Sel_Farenheit = new JRadioButton("(F)");
		Sel_Farenheit.setBounds(49, 5, 45, 23);
		Sel_Farenheit.setEnabled(false);
		SetterPanel.add(Sel_Farenheit);
		Sel_Litre = new JRadioButton("(L)");
		Sel_Litre.setBounds(96, 5, 45, 23);
		SetterPanel.add(Sel_Litre);
		
		Sel_Celsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sel_Farenheit.setSelected(false);
				Sel_Litre.setSelected(false);
				icon = new ImageIcon(IconPath+"TempIcon.png");
			}
		});
		Sel_Farenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sel_Celsius.setSelected(false);
				Sel_Litre.setSelected(false);
				icon = new ImageIcon(IconPath+"TempIcon.png");
			}
		});
		Sel_Litre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sel_Celsius.setSelected(false);
				Sel_Farenheit.setSelected(false);
				icon = new ImageIcon(IconPath+"WaterLvl.png");
			}
		});
	}
	
	public Icon getIcon()
	{
		return icon;
	}
	//A function to access what is selected between each RadioButton (Celsius, Farenheit or Litre)
	public String getSelection()
	{
		String selection = null;
		if(this.Sel_Celsius.isSelected() == true)
		{
			selection = "C";
			return selection;
		}
		else if(this.Sel_Farenheit.isSelected() == true)
		{
			selection = "F";
			return selection;
		}
		else if(this.Sel_Litre.isSelected() == true)
		{
			selection = "L";
		}
		return selection;
	}
	
	public String getUser_Entry() 
	{
		return User_Entry;
	}
	
	public boolean getUserInput() 
	{
		boolean fRet = false;
		Dimension dim = new Dimension(270,320);
		SetterPanel.setPreferredSize(dim);
		//Triggers the DialogBox (parentFrame, Panel to be displayed in the DialogFrame, TiTle,
		//						  dialog button options,
		//						  DialogFrame Type, Icon
		//						  Word to be written on the buttons as String[]
		//						  default initial values);
		
		if(showOptionDialog(null, SetterPanel, Title, 
		                    JOptionPane.OK_CANCEL_OPTION, 
		                    JOptionPane.QUESTION_MESSAGE, getIcon(), 
		                    new String[]{"SET","CANCEL"},
		                    "default") 
		                    == 0 ) // if SET button is pressed, returns true
		{
			fRet = true;
		}
		
		return fRet;
	}
	
	public void setTitle(String title){
		Title = title;
	}
	
	public String getTitle(){
		return Title;
	}

	public void setTextArea(String text)
	{
		this.TextField_UserEntry.setText(text);
	}
	
	public void setUser_Entry(String user_Entry) {
		User_Entry = user_Entry;
	}
	
	public double getValues() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public boolean isAddition() {
		return Addition;
	}
	
	public void setAddition(boolean addition) {
		Addition = addition;
	}
	
	public boolean isSubstraction() {
		return Substraction;
	}
	
	public void setSubstraction(boolean substraction) {
		Substraction = substraction;
	}
	
	public boolean isMultiplication() {
		return Multiplication;
	}
	
	public void setMultiplication(boolean multiplication) {
		Multiplication = multiplication;
	}
	
	public boolean isDivision() {
		return Division;
	}
	
	public void setDivision(boolean division) {
		Division = division;
	}
	
	public boolean[] getCalcMove()
	{
		return CalcMove;
	}
	
	public JPanel getSetterPanel()
	{
		return this.SetterPanel;
	}
	
	public void setCalcMove(boolean add, boolean sub, boolean div, boolean mul)
	{
		CalcMove[0] = add;
		CalcMove[1] = sub;
		CalcMove[2] = div;
		CalcMove[3] = mul;
	}
	
	public JRadioButton getSel_C()
	{
		return Sel_Celsius;
	}
	
	public JRadioButton getSel_F()
	{
		return Sel_Farenheit;
	}
	
	public JRadioButton getSel_L()
	{
		return Sel_Litre;
	}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				SetterDialog message = new SetterDialog();
				JFrame frame = new JFrame();
				frame.add(message.getSetterPanel());
				frame.setPreferredSize(new Dimension(285,320));
				frame.pack();
				frame.setVisible(true);
				} catch (Exception e) {
				e.printStackTrace();
				}
			}
		});
	}
}