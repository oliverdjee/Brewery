package Util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class Utility {

	private static NormalDistribution d;
	
	public static double getMean(double[] data)
    {
    	Mean m = new Mean();
    	return m.evaluate(data);
    }
    
	public static byte[] ListToByteArray(List<String> list) throws IOException
	{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();

		 for (String line : list) {
		   baos.write(line.getBytes());
		 }

		 byte[] bytes = baos.toByteArray();

		 return bytes;
	}
	
	public static int GenerateJobID(int size)
    {
    	if(size>15)
    	{
    		size = 15;
    	}
    	double exp = Math.pow(10, size);
    	int JobID = (int) (Math.random()*exp);
    	return JobID;
    }
	
	public static double CalcAngleRad(double[] point1, double[] point2, double[] point3)
	{
		double[] vec12 = new double[] { point2[0] - point1[0],
									    point2[1] - point1[1],
									    point2[2] - point1[2]};
		
		double[] vec23 = new double[] { point3[0] - point2[0],
			    						point3[1] - point2[1],
			    						point3[2] - point2[2]};
		
		double length12 = 	Math.sqrt(Math.pow(vec12[0],2) + 
							Math.pow(vec12[1],2) + 
							Math.pow(vec12[2],2));
		
		double length23 = 	Math.sqrt(Math.pow(vec23[0],2) + 
							Math.pow(vec23[1],2) + 
							Math.pow(vec23[2],2));
		
		double dot1223 = vec12[0]*vec23[0] + vec12[1]*vec23[1] + vec12[2]*vec23[2];
		
		double operand = dot1223 / (length12 * length23);
		
		double angle = Math.acos(Math.cos(operand));
		
		return angle;
	}
	
	public static double CalcAngleDeg(double[] point1, double[] point2, double[] point3)
	{
		double[] vec21 = new double[] { point1[0] - point2[0],
									    point1[1] - point2[1],
									    point1[2] - point2[2]};
		
		double[] vec23 = new double[] { point3[0] - point2[0],
			    						point3[1] - point2[1],
			    						point3[2] - point2[2]};
		
		double length21 = 	Math.sqrt(Math.pow(vec21[0],2) + 
							Math.pow(vec21[1],2) + 
							Math.pow(vec21[2],2));
		
		double length23 = 	Math.sqrt(Math.pow(vec23[0],2) + 
							Math.pow(vec23[1],2) + 
							Math.pow(vec23[2],2));
		
		double dot1223 = vec21[0]*vec23[0] + vec21[1]*vec23[1] + vec21[2]*vec23[2];
		
		double operand = dot1223 / (length21 * length23);
		
		double angle = Math.acos(operand)/Math.PI*180;
		
		return angle;
	}
	
    public static double getSTD(double[] data)
    {
    	StandardDeviation std = new StandardDeviation();
    	return std.evaluate(data);
    }
    
    public static String FormatString(String s, int length, String charToAdd, boolean before, boolean after)
    {
    	if(s.length() < length && before)
    	{
    		int rest = length-s.length();
        	String ret = "";
        	for(int i = 0; i<rest; i++)
            {
            	ret+= charToAdd;
            }
        	s = ret+s;
    	}
    	else if(s.length() < length && after)
    	{
    		int rest = length-s.length();
        	String ret = "";
        	for(int i = 0; i<rest; i++)
            {
            	ret+= charToAdd;
            }
        	s = s+ret;
    	}
    	else
    	{
    		s = CutString(s,length);
    	}
    	
    	return s;
    }
    
    public static String FormatString(String s, int length, String charToAdd)
    {
    	if(s.length() < length)
    	{
    		s=CompleteString(s,length,charToAdd);
    	}
    	else
    	{
    		s = CutString(s,length);
    	}
    	
    	return s;
    }
    
    public static String FormatPositiveNumberAsString(String s, int length, String charToAdd) 
    {
    	if(!s.contains("."))
    	{
    		s = s+".0";
    	}
    	if(s.length() < length)
    	{
    		s=CompleteString(s,length,charToAdd);
    	}
    	else
    	{
    		s = CutString(s,length);
    	}
    	
    	return s;
	}
    
    public static String FormatDecimals(String s, int length, String charToAdd) 
    {
    	if(length == 0)
    	{
    		if(s.contains("."))
    		{
    			s = s.substring(0,s.indexOf("."));
    		}
    	}
    	else
    	{
    		if(!s.contains("."))
        	{
        		s = s+".0";
        	}
    		if(s.substring(s.indexOf(".")+1).length() < length)
        	{
        		s=CompleteString(s,length+s.substring(0,s.indexOf(".")+1).length(),charToAdd);
        	}
        	else
        	{
        		s = CutString(s,length+s.substring(0,s.indexOf(".")+1).length());
        	}
    	}
    	
    	
    	
    	return s;
	}
    
    public static String FormatNumberAsString(String s, int length, String charToAdd)
    {
    	if(!s.trim().startsWith("-"))
    	{
    		s = " "+s;
    	}
    	if(!s.contains("."))
    	{
    		s = s+".0";
    	}
    	if(s.length() < length)
    	{
    		s=CompleteString(s,length,charToAdd);
    	}
    	else
    	{
    		s = CutString(s,length);
    	}
    	
    	return s;
    }
    
    public static String CompleteString(String s, int max, String charToAdd)
    {
    	int rest = max-s.length();
    	String ret = new String(s);
    	if(s.length() < max)
    	{
    		for(int i = 0; i<rest; i++)
        	{
        		ret+= charToAdd;
        	}
    	}
    	return ret;
    }
    
    public static String CutString(String s, int max)
    {
    	String ret = new String(s);
    	if(s.length() > max)
    	{
    		ret = s.substring(0,max);
    	}
    	return ret;
    }
    
    public static void sortList(List<String> aItems){
        Collections.sort(aItems, String.CASE_INSENSITIVE_ORDER);
    }

    public static void log(Object aObject){
        System.out.println(String.valueOf(aObject)); 
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> aItems){
        TreeMap<String, Object> result = 
          new TreeMap<>(String.CASE_INSENSITIVE_ORDER)
        ;
        result.putAll(aItems);
        return result;
    }
    
    public static String[] CleanArray(String[] params)
	{
		List<String> temp = new ArrayList<String>();
		for(int i = 0; i < params.length; i++)
		{
			String info = params[i];
			info  = info.trim();
			if(!(info.isEmpty()))
			{
				temp.add(info);
			}
		}
		params = temp.toArray(new String[]{});
		return params;
	}
    
    public static ArrayList<Object> noDuplicates(List<Object> l)
    {
    	Set<Object> noDups = new HashSet<Object>();
    	noDups.addAll(l);
    	return new ArrayList<Object>(noDups);
    }
    
    public static double[] RemoveOutliers(double[] data, double ConfidenceInterval,boolean recursive)
    {
    	
    	boolean fRet = false;
    	List<Double> _data = new ArrayList<Double>();
    	List<Integer> indexes = new ArrayList<Integer>();
    
    	d = new NormalDistribution(getMean(data),getSTD(data));
    	for(int i = 0; i < data.length ; i++)
    	{
    		double p = d.cumulativeProbability(data[i]);
    		double area = (1-p)*100;
    		
    		//CALL THE FUNCTION RECURSIVELY
    		if(area<=((100-ConfidenceInterval)/2) || area >=(ConfidenceInterval+((100-ConfidenceInterval)/2)))
    		{
    			System.out.println("REMOVING... " + String.valueOf(data[i]));
    			indexes.add(i);
    			fRet = true;
    		}
    		_data.add(data[i]);
    	}
    	for(int i = indexes.size()-1; i >=0 ; i--)
    	{
    		_data.remove((int)indexes.get(i));
    	}
    
    	data = new double[_data.size()];
    	for(int x = 0; x < _data.size(); x ++)
		{
			data[x] = (double)_data.get(x);
		}
		if(fRet && recursive)
		{
			data = RemoveOutliers(data, ConfidenceInterval, true);
		}
		return data;
    }
    
    public static double[] RemoveOutliers(double[] data,double minAreaExclusive, double maxAreaInclusive,boolean recursive)
    {
    	
    	boolean fRet = false;
    	List<Double> _data = new ArrayList<Double>();
    	List<Integer> indexes = new ArrayList<Integer>();
    
    	d = new NormalDistribution(getMean(data),getSTD(data));
    	for(int i = 0; i < data.length ; i++)
    	{
    		double p = d.cumulativeProbability(data[i]);
    		double area = (1-p)*100;
    		
    		//CALL THE FUNCTION RECURSIVELY
    		if(area>(minAreaExclusive) && area <=(maxAreaInclusive))
    		{
    			System.out.println("REMOVING... " + String.valueOf(data[i]));
    			indexes.add(i);
    			fRet = true;
    		}
    		_data.add(data[i]);
    	}
    	for(int i = indexes.size()-1; i >=0 ; i--)
    	{
    		_data.remove((int)indexes.get(i));
    	}
    
    	data = new double[_data.size()];
    	for(int x = 0; x < _data.size(); x ++)
		{
			data[x] = (double)_data.get(x);
		}
		if(fRet && recursive)
		{
			data = RemoveOutliers(data, minAreaExclusive, maxAreaInclusive, true);
		}
		return data;
    }
    
    public static double[] RemoveOutliers(double[] data, double ConfidenceInterval,boolean recursive, double MinimalRange)
    {
    	double range = Math.abs(Range(data));
    	if(range < MinimalRange)
    	{
    		return data;
    	}
    	else
    		{
    		boolean fRet = false;
    	
	    	List<Double> _data = new ArrayList<Double>();
	    	List<Integer> indexes = new ArrayList<Integer>();
	    
	    	d = new NormalDistribution(getMean(data),getSTD(data));
	    	for(int i = 0; i < data.length ; i++)
	    	{
	    		double p = d.cumulativeProbability(data[i]);
	    		double area = (1-p)*100;
	    		
	    		//CALL THE FUNCTION RECURSIVELY
	    		if(area<=((100-ConfidenceInterval)/2) || area >=(ConfidenceInterval+((100-ConfidenceInterval)/2)))
	    		{
	    			System.out.println("REMOVING... " + String.valueOf(data[i]));
	    			indexes.add(i);
	    			fRet = true;
	    		}
	    		_data.add(data[i]);
	    	}
	    	for(int i = indexes.size()-1; i >=0 ; i--)
	    	{
	    		_data.remove((int)indexes.get(i));
	    	}
	    
	    	data = new double[_data.size()];
	    	for(int x = 0; x < _data.size(); x ++)
			{
				data[x] = (double)_data.get(x);
			}
			if(fRet && recursive)
			{
				data = RemoveOutliers(data, ConfidenceInterval, true, MinimalRange);
			}
			return data;
    	}
    }
    
	public static double Min(double[] data)
	{
		double min = data[0];
		for(int y = 0 ; y < data.length; y++)
		{
			
			min = Math.min(min, data[y]);
		}
		return min;
	}
	
	public static double Max(double[] data)
	{
		double max = data[0];
		for(int y = 0 ; y < data.length; y++)
		{
			
			max = Math.max(max, data[y]);
		}
		return max;
	}
	
	public static double Range(double[] data)
	{
		double min = data[0];
		double max = data[0];
		for(int y = 0 ; y < data.length; y++)
		{
			
			min = Math.min(min, data[y]);
			max = Math.max(max, data[y]);	
			
			
		}
		
		return max-min;
	}
	
	public static double Increments(double[] data, int steps)
	{
		double range  = Range(data);
		
		return range/steps;
	}
	
	public static double getDistance(double[] d1, double[] d2)
	{
		double d =0;
		for(int i = 0; i< d1.length; i++)
		{
			d += Math.pow(d1[i] - d2[i],2);
		}
		d = Math.sqrt(d);
		return d;
	}
	
	public static Color[] ColorGradient(double [] data, Color[] LowestToHighest)
	{
		Color[] fRet = new Color[data.length];
		
		int[] LowtoHigh_RED = new int[LowestToHighest.length];
		int[] LowtoHigh_GREEN = new int[LowestToHighest.length];
		int[] LowtoHigh_BLUE = new int[LowestToHighest.length];
		
		for(int i = 0; i < LowestToHighest.length; i++)
		{
			LowtoHigh_RED[i] = LowestToHighest[i].getRed();
			LowtoHigh_GREEN[i] = LowestToHighest[i].getGreen(); 
			LowtoHigh_BLUE[i] = LowestToHighest[i].getBlue(); 
		}
	    
		double min = Min(data);
		double max = Max(data);
		double range = max-min;
		double increment = range/(LowestToHighest.length-1);
		
		for(int y = 0; y < data.length; y ++)
		{
			int index = -1;
		    int z = 1; 
		    
		    while(index == -1 && z<(LowestToHighest.length))
		    {
		    	if(Math.round(data[y]) <= Math.round(min+z*increment))
		    	{
		    		index = z;
		    	}
		    	z ++;
		    }
			
			
			
			int Variation_RED   = LowtoHigh_RED[index] - LowtoHigh_RED[index-1];
		    int Variation_GREEN = LowtoHigh_GREEN[index] - LowtoHigh_GREEN[index-1];
		    int Variation_BLUE  = LowtoHigh_BLUE[index] - LowtoHigh_BLUE[index-1];
		    
		    
		    double ratio = (data[y]-min) / (index*range);
		    
		    int Color_RED   = (int)(ratio * Variation_RED);
		    int Color_GREEN = (int)(ratio * Variation_GREEN);
		    int Color_BLUE  = (int)(ratio * Variation_BLUE);
		    
		    if(Variation_RED < 0)
		    {
		    	Color_RED   = (int)((1-ratio)*-1 * Variation_RED);
		    }
		    if(Variation_GREEN < 0)
		    {
		    	Color_GREEN = (int)((1-ratio)*-1 * Variation_GREEN);
		    }
		    if(Variation_BLUE < 0)
		    {
		    	Color_BLUE  = (int)((1-ratio)*-1 * Variation_BLUE);
		    }
		    
		    Color gradient = new Color(Color_RED,Color_GREEN,Color_BLUE);
		    fRet[y] = gradient;
		}
		return fRet;
	}
	
	public static Color[] Color(double [] data)
	{
		Color[] fRet = new Color[data.length];
		
		for(int y = 0; y < data.length; y ++)
		{
		    int Color_RED   = (int)(data[y]);
		    int Color_GREEN = (int)(data[y]);
		    int Color_BLUE  = (int)(data[y]);
		    
		    if(Color_RED < 0)
		    {
		    	Color_RED   = 0;
		    }
		    if(Color_GREEN < 0)
		    {
		    	Color_GREEN = 0;
		    }
		    if(Color_BLUE < 0)
		    {
		    	Color_BLUE  = 0;
		    }
		    
		    if(Color_RED > 255)
		    {
		    	Color_RED   = 255;
		    }
		    if(Color_GREEN > 255)
		    {
		    	Color_GREEN = 255;
		    }
		    if(Color_BLUE > 255)
		    {
		    	Color_BLUE  = 255;
		    }
		    
		    Color gradient = new Color(Color_RED,Color_GREEN,Color_BLUE);
		    fRet[y] = gradient;
		}
		return fRet;
	}

	
	public static Color[] ColorGradient(double [] data, Color[] LowestToHighest, double offset)
	{
		Color[] fRet = new Color[data.length];
		
		int[] LowtoHigh_RED = new int[LowestToHighest.length];
		int[] LowtoHigh_GREEN = new int[LowestToHighest.length];
		int[] LowtoHigh_BLUE = new int[LowestToHighest.length];
		
		for(int i = 0; i < LowestToHighest.length; i++)
		{
			LowtoHigh_RED[i] = LowestToHighest[i].getRed();
			LowtoHigh_GREEN[i] = LowestToHighest[i].getGreen(); 
			LowtoHigh_BLUE[i] = LowestToHighest[i].getBlue(); 
		}
	    
		double min = Min(data)-offset;
		double max = Max(data)+offset;
		double range = max-min;
		double increment = range/(LowestToHighest.length-1);
		
		for(int y = 0; y < data.length; y ++)
		{
			int index = -1;
		    int z = 1; 
		    
		    while(index == -1 && z<(LowestToHighest.length))
		    {
		    	if(Math.round(data[y]) <= Math.round(min+z*increment))
		    	{
		    		index = z;
		    	}
		    	z ++;
		    }
			
			
			
			int Variation_RED   = LowtoHigh_RED[index] - LowtoHigh_RED[index-1];
		    int Variation_GREEN = LowtoHigh_GREEN[index] - LowtoHigh_GREEN[index-1];
		    int Variation_BLUE  = LowtoHigh_BLUE[index] - LowtoHigh_BLUE[index-1];
		    
		    
		    double ratio = (data[y]-min) / (index*range);
		    
		    int Color_RED   = (int)(ratio * Variation_RED);
		    int Color_GREEN = (int)(ratio * Variation_GREEN);
		    int Color_BLUE  = (int)(ratio * Variation_BLUE);
		    
		    if(Variation_RED < 0)
		    {
		    	Color_RED   = (int)((1-ratio)*-1 * Variation_RED);
		    }
		    if(Variation_GREEN < 0)
		    {
		    	Color_GREEN = (int)((1-ratio)*-1 * Variation_GREEN);
		    }
		    if(Variation_BLUE < 0)
		    {
		    	Color_BLUE  = (int)((1-ratio)*-1 * Variation_BLUE);
		    }
		    
		    Color gradient = new Color(Color_RED,Color_GREEN,Color_BLUE);
		    fRet[y] = gradient;
		}
		return fRet;
	}
}
