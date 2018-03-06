package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.ui.RectangleInsets;

public class MyTempGraph {

	public static final Font font = new Font("Arial Rounded MT Bold", Font.BOLD, 14);
	public static final Font Labelfont = new Font("Arial Rounded MT Bold", Font.BOLD, 12);
	public static final Font fontTitle = new Font("Arial Rounded MT Bold", Font.BOLD, 18);
	public static final Color myBlack = new Color(40, 40, 40);
	public static final Color myLightBlue = new Color(2, 215, 244);
	public static final Color myBlue = new Color(55, 70, 239);
	public static final Color myPink = new Color(215, 39, 177);
	public static final Color myRed = new Color(239, 55, 70);
	public static final Color myGreen = new Color(70, 239, 55);
	
	public static void LookAndFeel(JFreeChart XYChart)
	{
		XYPlot plot = (XYPlot) XYChart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(myBlack);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setOutlineVisible(true);
		
		plot.setAxisOffset(new RectangleInsets(0,0,0,0));
		
		XYChart.getTitle().setFont(fontTitle);
		
        XYLineAndShapeRenderer renderer
                = new myRenderer();
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = new myNumberAxis("Temperature (Celcius)", font);
        plot.setRangeAxis(rangeAxis);
        
	}
	
	public static class myNumberAxis extends NumberAxis {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public myNumberAxis(String label, Font font){
			   super(label);
			   setLabelFont(font);
			   setTickLabelFont(Labelfont);
		       setLabelFont(Labelfont);
			   setLowerMargin(.015);
			   setUpperMargin(.015);
			   setStandardTickUnits(NumberAxis.createStandardTickUnits());
			   setAutoRange(false);
			   setRange(0,100);
		}
	}
	
	public static class myRenderer extends DefaultXYItemRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		  // paints selection for range
		  // the length of m_paints should be one more than for m_thresholds where the extra paint color is for the assumed upper range (i.e [18.0, Double.POSITIVE_INFINITY] from above)
		  // ranges defined by m_thresholds elements are painted with the corresponding m_paints element (including the assumed range)
		  // a null element in m_paints means there is no special paint for the region, so return the series paint
		public myRenderer()
		{
			setBaseShapesVisible(true);
			setSeriesShapesVisible(0, true);
	        setSeriesShapesVisible(1, true);
	        setSeriesShapesVisible(2, true);
	        setSeriesShapesVisible(3, true);
	        setSeriesShapesVisible(4, true);
	        setSeriesPaint(0, myRed);
	        setSeriesPaint(1, myLightBlue);
	        setSeriesPaint(2, myGreen);
	        setSeriesPaint(3, myBlue);
	        setSeriesPaint(4, myPink);
	        setSeriesStroke(0, new BasicStroke(0));
	        setSeriesStroke(1, new BasicStroke(0));
	        setSeriesStroke(2, new BasicStroke(0));
	        setSeriesStroke(3, new BasicStroke(0));
	        setSeriesStroke(4, new BasicStroke(0));
		}
	}
}
