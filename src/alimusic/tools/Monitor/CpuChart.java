package alimusic.tools.Monitor;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.time.Millisecond;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  

import alimusic.tools.GetResource.GetTop;
import alimusic.tools.Outlog.NSlog;
@SuppressWarnings("serial")
public class CpuChart extends ChartPanel implements Runnable
{
	private static TimeSeries timeSeries;  ;  
	static String time =alimusic.tools.Outlog.NSlog.time();
	static String time1 =alimusic.tools.Outlog.NSlog.timenow();
    public CpuChart(String chartContent,String title,String yaxisName)  {  
        super(createChart(chartContent,title,yaxisName));
}

private static JFreeChart createChart(String chartContent,String title,String yaxisName)
{
        timeSeries = new TimeSeries(chartContent,Millisecond.class);  
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);  
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"时间(秒)",yaxisName,timeseriescollection,true,true,false);   
        XYPlot xyplot = jfreechart.getXYPlot();  
        xyplot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        ValueAxis valueaxis = xyplot.getDomainAxis();  
        valueaxis.setAutoRange(true);  
        valueaxis.setFixedAutoRange(30000D);  
        valueaxis = xyplot.getRangeAxis();  
        DateAxis domainAxis = (DateAxis)xyplot.getDomainAxis();    
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, 1, new SimpleDateFormat("hh:mm:ss"))); 
        return jfreechart;
}
	public void run()
	{
		while(true){ 
			try { 
				if(GetTop.cpu(Menu.text)==-0.1){
	        		break;
	        	}else{
		        	timeSeries.add(new Millisecond(),GetTop.Topcpu(Menu.text) );  
		            Thread.sleep(100);
		            log();
	        	} 
	        }catch (InterruptedException e){
		}catch (IOException e){
		e.printStackTrace();
		}  
        }         
    }

@SuppressWarnings("unused")
	private double randomNum() throws IOException{ 	      
	return GetTop.Topcpu(Menu.text);
}

public static void log() throws IOException, InterruptedException{
	if (Menu.log==true){
			 NSlog.writelogs("D:/log/Monitor_log",Menu.time,NSlog.times()+"                   "+GetTop.Topcpu(Menu.text)+"%"+"                   "+GetTop.cpu(Menu.text)+"%"+"              "+HeapChart.randomNum()/1024+"MB"+"               "+FlowChart.randomNum()+"Kb");
		}
	 } 
}  