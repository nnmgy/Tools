package alimusic.tools.IosMontian;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alimusic.tools.GetResource.GetTop;
import alimusic.tools.GetResource.ResultDouble;
import alimusic.tools.Outlog.NSlog;
public class IosMontianFm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
		public static String text;
		public static String time=NSlog.time();
		public static ArrayList<Double> cpu =new ArrayList<Double>();
		public static ArrayList<Double> Flow =new ArrayList<Double>();
		public static ArrayList<Double> heap=new ArrayList<Double>();
		public static boolean log;
		public JPanel frame = new JPanel();
		public IosMontianFm() throws IOException{		
			frame.setLayout(null);
			final CpuChart rtcp=new CpuChart("CPU","CPU","%");  
			 rtcp.setBounds(0, 40, 700, 450); 
			frame.add(rtcp);
		    final HeapChart rtcp1=new HeapChart("Memory","Memory","kb");  
		    rtcp1.setBounds(805, 40, 700, 450); 
			frame.add(rtcp1); 
		   
			JLabel lable = new JLabel("进程名:");
		    frame.add(lable);
		    lable.setBounds(280, 580, 45, 25);
		    final JTextField JText = new JTextField("TTEntertai");
		    frame.add(JText);
		    JText.setBounds(388, 580, 220, 30);
			final JButton button1 = new JButton("开始测试");		
			button1.setBounds(780, 580, 200, 35);  
			  frame.add(button1);
			final JButton button9 = new JButton("停止测试");		
			button9.setBounds(1050, 580, 200, 35);  
		    frame.add(button9);
		    final Checkbox r1 = new Checkbox("Log", true);
		    r1.setBounds(700, 588, 105, 15); 
		    frame.add(r1);
 
			button9.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					try {
						System.out.println(cpu);
						System.out.println(heap);
						NSlog.writelogs("D:/log/Monitor_log",time," MaxCpu:"+ResultDouble.ArrayListMax(cpu) +" MinCpu:"+ResultDouble.ArrayListMin(cpu)  +" MpCpu:"+ResultDouble.ArrayListaverage(cpu));
						NSlog.writelogs("D:/log/Monitor_log",time," MaxHeap:"+(ResultDouble.ArrayListMax(heap)/1024) +" m MinHeap:"+(ResultDouble.ArrayListMin(heap)/1024)  +"m MpHeap:"+(ResultDouble.ArrayListaverage(heap)/1024)+" m ");
						System.exit(1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				});
		    
		button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					text= JText.getText();			
			  if (r1.getState()){
				  	log=true;
					try {
						NSlog.writelogs("D:/log/Monitor_log",time,"Time       "+"                          TopCpu"+"                    Cpuinfo"+"               Memory"+"                  Flows");
					} catch (IOException e1) {
						// TODO Auto-generated catch blocks
						e1.printStackTrace();
					}
				}else{
					log=false;
					System.out.print("不要日志");
				}	if(text.isEmpty()){
					JOptionPane.showMessageDialog(new JFrame(), "伙计，包名不要为空好么？");
				}try{
					if(GetTop.cpu(text)==-0.1){      		
					JOptionPane.showMessageDialog(new JFrame(), "请检查设备是否连接,或者你的设备没有连接好,也可能是你的包名不正确！");
				}else{
				  button1.setBackground(Color.LIGHT_GRAY);
				  (new Thread(rtcp)).start(); 
				  (new Thread(rtcp1)).start(); 
				 
				  }}
					catch (HeadlessException | IOException e1){
					e1.printStackTrace();
					}
				}   
				});
		
		setTitle("IosMontian 1.0");
		setBounds(200, 150, 1555, 777);
		add(frame);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e) {
					        exit();
		}});}
		public void actionPerformed(ActionEvent e){
		}
		 public void exit() {	   
		       setVisible(false);
		      //setDefaultCloseOperation(EXIT_ON_CLOSE); 
		  }
		
			
			public static void IosMontian() throws Exception{
			
		/*
				Thread j= new Thread();	
				j.start();*/
				IosMontianFm m=new IosMontianFm();
				//m.setState(JFrame.ICONIFIED); 
				m.setVisible(true);
				
				}
	
}
