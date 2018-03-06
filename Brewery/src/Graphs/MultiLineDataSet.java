package Graphs;

import java.util.ArrayList;
import java.util.Vector;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
public class MultiLineDataSet extends AbstractXYDataset implements XYDataset
{
	private static final long serialVersionUID = 1491956790065695423L;
	private Vector<String>		keys = new Vector<String>();
	private Vector<double[]>	list = new Vector<double[]>();
	private double				xMin = 0.0;
	private double				xMax = 0.0;
	private double				yMin = 0.0;
	private double				yMax = 0.0;
	private double  			cutOff = 0.0;
	private ArrayList<Double[]> markerArray = new ArrayList<Double[]>();
	
	/**
	 * @brief FLag indicating if the data sets contain XY data of just Y with x ad index of the array.
	 */
	private boolean				fIs_XY;

    /**
     * Default constructor.
     */
    public MultiLineDataSet(boolean fIs_XY_Flag)
    {
    	fIs_XY = fIs_XY_Flag;
    }

    /////////////////////////////////////////////////
    //	Private function implementations
    /////////////////////////////////////////////////
    public void ResetMarkers()
    {
    	markerArray = new ArrayList<Double[]>();
    }
    
    public void AddMarker(Double[] markerInfo)
    {
    	markerArray.add(markerInfo);
    }
    
    public ArrayList<Double[]> GetMarkers()
    {
    	return(this.markerArray);
    }
    
    public void SetCutOff(Double value)
    {
    	cutOff = value;
    }
    
    public Double GetCutOff()
    {
    	return(cutOff);
    }
    
    public Vector<double[]> GetDataSets()
    {
    	return(this.list);
    }
    
    private void IncludeMinMax(double[] thisSet)
    {
    	int inc = 1;
    	
    	if(fIs_XY == true)
    	{
    		inc++;
    	}
    	for(int i = 0; i < thisSet.length; i += inc)
    	{
    		if((i == 0) && (list.isEmpty()== true) && thisSet[i] < cutOff)
    		{
    			if(fIs_XY == false)
    			{
    				xMin = i;
    				xMax = i;
    				yMin = thisSet[i];
    				yMax = thisSet[i];
    			}
    			else
    			{
    				xMin = thisSet[i];
    				xMax = thisSet[i];
    				yMin = thisSet[i+1];
    				yMax = thisSet[i+1];
    			}
    		}
    		else if(fIs_XY == false && thisSet[i] < cutOff)
    		{
    			if(i > xMax)
    			{
    				xMax = i;
    			}
    			if(thisSet[i] < yMin)
    			{
    				yMin = thisSet[i];
    			}
    			if(thisSet[i] > yMax)
    			{
    				yMax = thisSet[i];
    			}
    		}
    		else if (thisSet[i] < cutOff)
    		{
    			if(thisSet[i] < xMin)
    			{
    				xMin = thisSet[i];
    			}
    			if(thisSet[i] > xMax)
    			{
    				xMax = thisSet[i];
    			}
    			if(thisSet[i+1] < yMin)
    			{
    				yMin = thisSet[i+1];
    			}
    			if(thisSet[i+1] > yMax)
    			{
    				yMax = thisSet[i+1];
    			}
    		}
   		}
    }
    
    /////////////////////////////////////////////////
    //	Public function implementations
    /////////////////////////////////////////////////
    
    public void AddNewSet(String key, double[] newSet)
    {
    	IncludeMinMax(newSet);
    	keys.add(key);
    	list.add(newSet);
    }
    
    public void AddNewSet(String key, ArrayList<Double> newSet)
    {
    	keys.add(key);
    	double[] localArray = new double[newSet.size()];
    	for(int i = 0; i < localArray.length; i++)
    	{
    		localArray[i] = newSet.get(i);
    	}
    	IncludeMinMax(localArray);
    	list.add(localArray);
    }
    
    public double[] GetArray(int series)
    {
    	if(series >= keys.size())
    	{
    		series = keys.size() - 1;
    	}
    	return(list.elementAt(series));
    }
       
    /**
     * @brief Get X value of a given series.
     * @return Returns value as originally provided to dataset.
     */
    public double GetX_Value(int series, int item)
    {
		double	value;
		
		if(fIs_XY == false)
		{
			value = (double)item;
		}
		else
		{
			value = list.get(series)[item * 2];
		}
		return(value);
	}

    /**
     * @brief Get Y value of a given series
     * @return Returns value as originally provided to dataset.
     */
    public double GetY_Value(int series, int item)
    {
    	double	value;
    	
    	if(fIs_XY == false)
		{
			value = list.get(series)[item];
		}
		else
		{
			value = list.get(series)[(item * 2) + 1];
		}
    	return(value);
	}

    public void UpdateSeries(int series, double[] newValues)
    {
    	IncludeMinMax(newValues);
    	list.setElementAt(newValues, series);
    }
    
    public void UpdateSeries(int series, ArrayList<Double> newValues)
    {
    	double[] localArray = new double[newValues.size()];
    	for(int i = 0; i < localArray.length; i++)
    	{
    		localArray[i] = newValues.get(i);
    	}
    	IncludeMinMax(localArray);
    	list.setElementAt(localArray, series);
    }
    
    public double GetXmin()
    {
    	return(xMin);
    }
    
    public double GetXmax()
    {
    	return(xMax);
    }
    
    public double GetYmin()
    {
    	return(yMin);
    }
    
    public double GetYmax()
    {
    	return(yMax);
    }
    
    public boolean IsXY()
    {
    	return(fIs_XY);
    }
    
    /////////////////////////////////////////////////
    //	AbstractXYDataset implementations
    /////////////////////////////////////////////////
    
	@Override
	public int getItemCount(int series)
	{
		int		retSize;
		
		if(fIs_XY == false)
		{
			retSize = list.get(series).length;
		}
		else
		{
			retSize = list.get(series).length / 2;
		}
		return(retSize);
	}

	@Override
	public Number getX(int series, int item)
	{
		double	value;
		
		if(fIs_XY == false)
		{
			value = (double)item;
		}
		else
		{
			value = list.get(series)[item * 2];
		}
		return(value);
	}

	@Override
	public Number getY(int series, int item)
	{
		double	value;
		
		if(fIs_XY == false)
		{
			value = list.get(series)[item];
		}
		else
		{
			value = list.get(series)[(item * 2) + 1];
		}
		return(value);
	}

	@Override
	public int getSeriesCount()
	{
		int	retSize = keys.size();
		
		return(retSize);
	}

	@Override
	public Comparable<String> getSeriesKey(int series)
	{
        if (series < keys.size())
        {
            return(keys.get(series));
        }
        else
        {
            return "Error";
        }
	}
}
