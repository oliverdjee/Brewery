package Graphs;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import ca.crc.cwdi.JSavablePanel;

public class JMultiLineChartPanel extends JSavablePanel implements ChartMouseListener 
{
	private static final long 	serialVersionUID = 5219409541925148292L;
	private static String 		plotTitle;
	private String				xAxisLabel;
	private String				yAxisLabel;
    private Crosshair 			xCrosshair;
    private Crosshair 			yCrosshair;
	private JFreeChart			chart = null;
	private ChartPanel			panel = null;
	private MultiLineDataSet	dataset = null;
	private XYItemEntity 		chartEntity= null ;
	private XYDataset 			dataSet= null;
	private int 				serieIndex = 0;	
	private int					ItemInSerieIndex = 0;
	
	public static final Font font = new Font("Arial Rounded MT Bold", Font.BOLD, 14);
	public static final Font Labelfont = new Font("Arial Rounded MT Bold", Font.BOLD, 12);
	public static final Font fontTitle = new Font("Arial Rounded MT Bold", Font.BOLD, 18);
	public static final Color myBlack = new Color(40, 40, 40);
	public static final Color myBlue = new Color(55, 70, 239);
	public static final Color myRed = new Color(239, 55, 70);
	public static final Color myGreen = new Color(70, 239, 55);

	/*******************************************************************
     * @brief Class constructor(s) section
     * @author Benoit Gagnon @Communications Research Centre
     *
     *******************************************************************/
	
	@SuppressWarnings("deprecation")
	public JMultiLineChartPanel(String title, String xLabel, String yLabel, MultiLineDataSet newDataSet,String markerTitle, ArrayList<Double[]> MarkerInfo)
	{	
		plotTitle = title;
        dataset = newDataSet;
        xAxisLabel = xLabel;
        yAxisLabel = yLabel;
        xCrosshair = new Crosshair();
        yCrosshair = new Crosshair();
        this.xCrosshair.setValue(0.0);
        this.yCrosshair.setValue(0.0);
		// create the chart...
        chart = ChartFactory.createXYLineChart
        (
            plotTitle,  				// chart title
            xAxisLabel,            		// x axis label
            yAxisLabel	,        	    // y axis label
            dataset,                  	// data
            PlotOrientation.VERTICAL,
            true,                     	// include legend
            true,                     	// tooltips
            false                     	// urls
        );
        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setOutlineVisible(true);
		
		plot.setAxisOffset(new RectangleInsets(0,0,0,0));
		
		chart.getLegend().setVisible(false);
		chart.getTitle().setFont(fontTitle);
		plot.getDomainAxis().setLabelFont(Labelfont);
		plot.getDomainAxis().setTickLabelFont(Labelfont);
		plot.getDomainAxis().setLowerMargin(.015);
	    plot.getDomainAxis().setUpperMargin(.015);
	    plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setLegendLine(new Rectangle2D.Double(-4.0, -3.0, 8.0, 6.0));
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setStroke(new BasicStroke(2));

        // change the auto tick unit selection to integer units only...
        plot.getRangeAxis(0).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getRangeAxis(0).setTickLabelFont(Labelfont);
        plot.getRangeAxis(0).setLabelFont(Labelfont);
		plot.getRangeAxis(0).setLowerMargin(.015);
	    plot.getRangeAxis(0).setUpperMargin(.015);

        SetXAxisRange(dataset.GetXmin(), dataset.GetXmax());
        SetYAxisRange(dataset.GetYmin(), dataset.GetYmax());
        
        panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        this.panel.addChartMouseListener(this);
        
    
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        this.xCrosshair = new Crosshair(Double.NaN, Color.black, new BasicStroke(0f));
        this.xCrosshair.setLabelVisible(true);
        this.yCrosshair = new Crosshair(Double.NaN, Color.black, new BasicStroke(0f));
        this.yCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);
        crosshairOverlay.addRangeCrosshair(yCrosshair);
        panel.addOverlay(crosshairOverlay);
 
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        SetChart(chart, panel);
	}
	
	public JFreeChart getChart()
	{
		return this.chart;
	}
	
	public static class myNumberAxis extends NumberAxis {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public myNumberAxis(String label, Font font){
			   super(label);
			   setLabelFont(font);
		   }
	}
	
	public JMultiLineChartPanel(String title, String xLabel, String yLabel, MultiLineDataSet newDataSet)
	{	
		plotTitle = title;
        dataset = newDataSet;
        xAxisLabel = xLabel;
        yAxisLabel = yLabel;
        xCrosshair = new Crosshair();
        yCrosshair = new Crosshair();
        this.xCrosshair.setValue(0.0);
        this.yCrosshair.setValue(0.0);
		// create the chart...
        chart = ChartFactory.createXYLineChart
        (
            plotTitle,  				// chart title
            xAxisLabel,            		// x axis label
            yAxisLabel	,        	    // y axis label
            dataset,                  	// data
            PlotOrientation.VERTICAL,
            true,                     	// include legend
            true,                     	// tooltips
            false                     	// urls
        );
        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        SetXAxisRange(dataset.GetXmin(), dataset.GetXmax());
        SetYAxisRange(dataset.GetYmin(), dataset.GetYmax());
        
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setLegendLine(new Rectangle2D.Double(-4.0, -3.0, 8.0, 6.0));
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        this.panel.addChartMouseListener(this);
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.xCrosshair.setLabelVisible(true);
        this.yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.yCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);
        crosshairOverlay.addRangeCrosshair(yCrosshair);
        panel.addOverlay(crosshairOverlay);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        SetChart(chart, panel);
	}
	
	public void setNewSize(Dimension newSize)
	{
		panel.setPreferredSize(newSize);
	}
	
    /*******************************************************************
     * @brief Class JCwdiControlPanel abstract methods section
     * @author Benoit Gagnon @Communications Research Centre
     *
     *******************************************************************/
 		  
    public void Destroy()
    {
    }
    
	/*******************************************************************
     * @brief Native Class public methods section
     * @author Benoit Gagnon @Communications Research Centre
     *
     *******************************************************************/

    public void UpdateDataSet(double[] newDataSet)
    {
    	dataset.UpdateSeries(0, newDataSet);
    	chart.fireChartChanged();
    }
    
    public void SetXAxisRange(double minValue, double maxValue)
    {
    	XYPlot plot = (XYPlot) chart.getPlot();
        
    	NumberAxis domainAxis = (NumberAxis)plot.getDomainAxis();
        domainAxis.setRange(minValue, maxValue);
    }

    public void SetYAxisRange(double minValue, double maxValue)
    {
    	XYPlot plot = (XYPlot) chart.getPlot();
        
    	NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setRange(minValue, maxValue);
    }
    
    @Override
   	public Dimension getPreferredSize()
    {
       	Dimension dim = new Dimension();
   		dim.width = 400;
   		dim.height = 400;

   		return dim;
    }

  
    @Override
    public void chartMouseMoved(ChartMouseEvent cmevent)
    { 
    	ChartEntity chartentity = cmevent.getEntity();
    	if (chartentity instanceof XYItemEntity)
    	{ 
    		chartEntity= (XYItemEntity) chartentity; 
    		dataSet= chartEntity.getDataset();
    		serieIndex= chartEntity.getSeriesIndex();
    		ItemInSerieIndex= chartEntity.getItem();
    		double x= dataSet.getXValue(serieIndex,ItemInSerieIndex);
    		double y= dataSet.getYValue(serieIndex,ItemInSerieIndex);
    		this.xCrosshair.setValue(x);
            this.yCrosshair.setValue(y);
    	} 
    }
    
    
    /**
  	* @brief Tester for The JSetupScriptFrame and Panel
  	* @param args
  	*/
    public static void main(String[] args)
    {
    	final JFrame	frame = new JFrame();
     	
    	try
    	{
    		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	} 
    	catch (Exception e)
    	{
    		System.exit(ABORT);
    	}
    	MultiLineDataSet dataSet = new MultiLineDataSet(false);
    	double[] a = new double[10];
    	a[0] = -90.0;
    	a[1] = -89.0;
    	a[2] = -87.0;
    	a[3] = -80.0;
    	a[4] = -75.0;
    	a[5] = -73.0;
    	a[6] = -72.9;
    	a[7] = -71.0;
    	a[8] = -40.0;
    	a[9] = -9.0;
    	
    	double[] b = new double[10];
    	b[0] = -60.0;
    	b[1] = -79.0;
    	b[2] = -97.0;
    	b[3] = -94.0;
    	b[4] = -70.0;
    	b[5] = -67.0;
    	b[6] = -55.9;
    	b[7] = -53.0;
    	b[8] = -51.0;
    	b[9] = -21.0;
    	
    	dataSet.AddNewSet("TestA", a);
    	dataSet.AddNewSet("TestB", b);
    	
    	ArrayList<Double[]> markertest= new ArrayList<Double[]>();
    	Double[] XYmarkerinfo = {3.0, -80.0};
    	Double[] XYmarkerinfo2 = {2.0, -87.0};
    	Double[] XYmarkerinfo3 = {9.0, -9.0};
    	markertest.add(XYmarkerinfo);
    	markertest.add(XYmarkerinfo2);
    	markertest.add(XYmarkerinfo3);
    	JMultiLineChartPanel myPanel = new JMultiLineChartPanel("Test", "Rnking","Energy",dataSet, "WT",markertest);
    	
    	
    	frame.setContentPane(myPanel);
  
    	frame.setSize(200, 200);
     	frame.setLocationRelativeTo(null);
     	frame.pack();
     	frame.setVisible(true);
    }

	@Override
	public void chartMouseClicked(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

 }
