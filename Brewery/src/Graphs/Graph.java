package Graphs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import Hardware.Device;

/** @see http://stackoverflow.com/questions/5048852 */
public class Graph extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String START = "Start Polling";
    private static final String STOP = "Stop Polling";
    private static final TimePeriod StartTime = new Second(new Date());
    private Timer timer;
    private Device device;
    private TimeSeries data;
    private TimeSeriesCollection dataset;
    private JFreeChart chart;       

    public Graph(String Title, String Xaxis, String Yaxis) {
        super(Title);
       device = null;
       data = new TimeSeries(Title);
      
        data.setMaximumItemAge(StartTime.getStart().getTime()+84000000);
        dataset = new TimeSeriesCollection();
        chart = createChart(createData(), Title, Xaxis,Yaxis);

        final JButton run = new JButton(STOP);
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    if(device!= null)
                    {
                    	device.StopPolling();
                    }
                    run.setText(START);
                } 
                else 
                {
                    if(device!= null)
                    {
                    	device.StartPolling(device.getRequestDelay());
                   	}
                    run.setText(STOP);
                }
            }
        });

        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);
        this.add(btnPanel, BorderLayout.SOUTH);
    }
    
    public void LinkDevice(Device device)
    {
    	this.device = device;
    }
    
    public Device getLinkedDevice()
    {
    	return this.device;
    }
    
    private XYDataset createData() {
        dataset.addSeries(data);
        return dataset;
    }
    
    public TimeSeries getData() {
        return data;
    }
    
    public TimeSeriesCollection getDataset()
    {
    	return this.dataset;
    }
    
    private JFreeChart createChart(XYDataset dataset, String Title,String Xaxis, String Yaxis) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            Title, Xaxis, Yaxis, dataset, true, true, false);
        MyTempGraph.LookAndFeel(result);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }

    public void start() {
        timer.start();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

            	Graph demo = new Graph("Temperature Over Time",
                		"Time (hh:mm:ss)","Temp (Celcius)");
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                demo.start();
            }
        });
    }
}