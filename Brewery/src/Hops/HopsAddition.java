package Hops;

import Util.Utility;

public class HopsAddition {
	private Double AdditionTime;
	private Double WortGravity;
	private Double Utilization;
	public static final Double fiveminutes = 0.085;
	public static final Double tenminutes = 0.17;
	public static final Double twentyminutes = 0.33;
	public static final Double thirtyminutes = 0.5;
	public static final Double fortyfiveminutes = 0.75;
	public static final Double sixtyminutes = 1.0;

	public HopsAddition(double addtime, double OG)
	{
		AdditionTime = addtime;
		WortGravity = OG;
		if(AdditionTime == 3)
		{
			Utilization = ThreeHours();
		}
		if(AdditionTime == 2.5)
		{
			Utilization = TwoAndHalfHours();
		}
		if(AdditionTime == 2)
		{
			Utilization = TwoHours();
		}
		if(AdditionTime == 1.5)
		{
			Utilization = OneAndHalfHours();
		}
		if(AdditionTime == sixtyminutes.doubleValue())
		{
			Utilization = OneHour();
		}
		if(AdditionTime == fortyfiveminutes.doubleValue())
		{
			Utilization = ThreeQuarterHour();
		}
		if(AdditionTime == thirtyminutes.doubleValue())
		{
			Utilization = HalfHour();
		}
		
		if(AdditionTime == twentyminutes.doubleValue())
		{
			Utilization = TwoThirdHour();
		}
		
		if(AdditionTime == tenminutes.doubleValue())
		{
			Utilization = OneSixthHour();
		}
		
		if(AdditionTime == fiveminutes.doubleValue())
		{
			Utilization = OneTwelvethHour();
		}
		
	}
	
	private double OneTwelvethHour()
	{
		double v1 = 0.052;
		double v2 = 0.02;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.035 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double OneSixthHour()
	{
		double v1 = 0.10;
		double v2 = 0.05;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.075 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double TwoThirdHour()
	{
		double v1 = 0.130;
		double v2 = 0.08;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.110 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double HalfHour()
	{
		double v1 = 0.195;
		double v2 = 0.12;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.165 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double ThreeQuarterHour()
	{
		double v1 = 0.24;
		double v2 = 0.15;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.20 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double OneHour()
	{
		double v1 = 0.27;
		double v2 = 0.17;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.225 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double OneAndHalfHours()
	{
		double v1 = 0.32;
		double v2 = 0.20;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.27 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double TwoHours()
	{
		double v1 = 0.355;
		double v2 = 0.22;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.30 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private double TwoAndHalfHours()
	{
		double v1 = 0.38;
		double v2 = 0.235;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.318 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private Double ThreeHours()
	{
		double v1 = 0.4;
		double v2 = 0.245;
		double g1 = 1.060;
		double g2 = 1.120;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0.335 - (slope*1.090);
		System.out.print("3 hours boil time function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	private Double IBUvsUtilization()
	{
		double v1 = 0;
		double v2 = 40;
		double g1 = 0;
		double g2 = 48;
		// f(x) = mx + b;
		double slope = (v2-v1)/(g2-g1);
		double b = 0;
		System.out.print("IBU vs Utilization % function:\n");
		System.out.print("\tf(x) = "+
				Utility.FormatDecimals(String.valueOf(slope), 2, "0")+
				"*x + "+
				Utility.FormatDecimals(String.valueOf(b), 2, "0")+
				"\n");
		System.out.print("\tFor a Three Hour Boil: "+
				Utility.FormatDecimals(String.valueOf((slope*WortGravity+b)*100.0), 1, "0")+
				"% Utilization Rate"+
				"\n");
		return ((slope*WortGravity+b)*100.0);
	}
	
	
	
	public Double getUtilization() {
		return Utilization;
	}

	public void setUtilization(Double utilization) {
		Utilization = utilization;
	}

	public static void main(String[] args) throws Exception 
	{
		HopsAddition hop = new HopsAddition(HopsAddition.fiveminutes,1.056);
	}
}
