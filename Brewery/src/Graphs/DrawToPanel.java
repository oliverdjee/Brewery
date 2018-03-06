package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


import ca.crc.cwdi.JSavablePanel;

public class DrawToPanel extends JSavablePanel 
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Color CircleColor = Color.white;
			Color AxisColor = Color.black;
			Color TextColor = Color.black;
			
			
			List<DrawLine> lines = new ArrayList<DrawLine>();
			DrawLine XAxis = null;
			DrawLine YAxis = null;
			DrawXCircle XPivotPoint = null;
			DrawYCircle YPivotPoint = null;
			String TitleFontName = "Ubuntu";
			String AxisFontName = "Lucida Console";
			String GraduationFontName = "Lucida Console";
			int TitleFontSize = 15;
			Font TitleFont = new Font(TitleFontName, Font.BOLD, TitleFontSize);
			int AxisFontSize = 12;
			Font AxisFont = new Font(AxisFontName, Font.PLAIN, AxisFontSize);
			int GraduationFontSize = 10;
			Font GraduationFont = new Font(GraduationFontName, Font.PLAIN, GraduationFontSize);
			
			
			class DrawLine {

			    int x1,x2, y1,y2;
			    public DrawLine(int x1, int y1, int x2, int y2) {
			        this.x1 = x1;
			        this.y2 = y2;
			        this.x2 = x2;
			        this.y1 = y1;
			    }

			    public void draw(Graphics g, Color c) {
			    	Graphics2D g2d = (Graphics2D) g.create();

		            g2d.setStroke(new BasicStroke(2));
			    	g2d.setColor(c);
			        g2d.drawLine(x1, y1, x2, y2);
			    }
			}
			
			class DrawXCircle {

			    int x1,y1;
			    public DrawXCircle(int x1, int y1) {
			        this.x1 = x1;
			        this.y1 = y1;
			    }

			    public void draw(Graphics g) {
			    	Graphics2D g2d = (Graphics2D) g.create();

		            g2d.setStroke(new BasicStroke(6));
			    	g2d.setColor(MyTempGraph.myLightBlue);
			    	
			        g2d.drawOval(x1-10, y1-10, 20, 20);
			    }
			}
			
			class DrawYCircle {

			    int x1,y1;
			    public DrawYCircle(int x1, int y1) {
			        this.x1 = x1;
			        this.y1 = y1;
			    }

			    public void draw(Graphics g) {
			    	Graphics2D g2d = (Graphics2D) g.create();

		            g2d.setStroke(new BasicStroke(6));
			    	g2d.setColor(MyTempGraph.myBlue);
			    	
			        g2d.drawOval(x1-7, y1-7, 14, 14);
			    }
			}
			
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            for(DrawLine line:lines)
	            {
	            	line.draw(g,AxisColor);
	            }
	            if(XAxis != null)
	            {
	            	XAxis.draw(g,MyTempGraph.myLightBlue);
	            }
	            if(YAxis != null)
	            {
	            	YAxis.draw(g,MyTempGraph.myBlue);
	            }
	            if(XPivotPoint != null)
	            {
	            	XPivotPoint.draw(g);
	            }
	            if(YPivotPoint != null)
	            {
	            	YPivotPoint.draw(g);
	            }
	           
	        }
	        
	        public void setTitleFont(Font f)
	        {
	        	TitleFont = f;
	        	
	        }
	        public Font getTitleFont()
	        {
	        	return this.TitleFont;
	        }
	        
	        public void setTitleFontSize(int size)
	        {
	        	this.TitleFontSize = size;
	        	TitleFont = new Font(TitleFontName, Font.BOLD, TitleFontSize);
	        }
	        
	        public int getTitleFontSize()
	        {
	        	return this.TitleFontSize;
	        }
	        
	        public void setTitleFontName(String Fontname)
	        {
	        	this.TitleFontName = Fontname;
	        	TitleFont = new Font(TitleFontName, Font.BOLD, TitleFontSize);
	        }
	        
	        public void setAxisFont(Font f)
	        {
	        	AxisFont = f;
	        	
	        }
	        public Font getAxisFont()
	        {
	        	
	        	return this.AxisFont;
	        }
	        
	        public void setAxisFontSize(int size)
	        {
	        	this.AxisFontSize = size;
	        	AxisFont = new Font(AxisFontName, Font.BOLD, AxisFontSize);
	        }
	        
	        public int getAxisFontSize()
	        {
	        	return this.AxisFontSize;
	        	
	        }
	        
	        public void setAxisFontName(String Fontname)
	        {
	        	this.AxisFontName = Fontname;
	        	AxisFont = new Font(AxisFontName, Font.BOLD, AxisFontSize);
	        }
	        
	        public int getGraduationFontSize()
	        {
	        	return this.GraduationFontSize;
	        }
	        
	        public void addLine(int x1, int y1, int x2, int y2) {
	        	DrawLine line = new DrawLine(x1,y1,x2,y2);
	        	lines.add(line);
	        	repaint();
	        }
	        
	        public void setXPivotPoint(int x1, int y1)
	        {
	        	XPivotPoint = new DrawXCircle(x1,y1);
	        	repaint();
	        }
	        
	        public void setYPivotPoint(int x1, int y1)
	        {
	        	YPivotPoint = new DrawYCircle(x1,y1);
	        	repaint();
	        }
	        
	        public void SetXaxis(int x1, int y1, int x2, int y2) {
	        	XAxis = new DrawLine(x1,y1,x2,y2);
	        	repaint();
	        }
	        
	        public void SetYaxis(int x1, int y1, int x2, int y2) {
	        	YAxis = new DrawLine(x1,y1,x2,y2);
	        	repaint();
	        }
	        
	        @Override
	        public Dimension getPreferredSize() {
	            return new Dimension(400, 400);
	        }

			@Override
			public void Destroy() {
				// TODO Auto-generated method stub
				
			}
		}
	
