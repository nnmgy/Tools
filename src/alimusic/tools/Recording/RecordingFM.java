package alimusic.tools.Recording;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alimusic.tools.Monitor.Menu;



public class RecordingFM extends JFrame
	implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static String text;
		public JPanel frame1 = new JPanel();
       
		public RecordingFM() throws IOException{
			frame1.setLayout(null);
		    JLabel lable0 = new JLabel("视频文件名:");
		    frame1.add(lable0);
		    lable0.setBounds(30, 30, 75, 25);
		    final JTextField JText0 = new JTextField("Demo");
		    frame1.add(JText0);
		    JText0.setBounds(100, 30, 180, 30);
		    
		    final JLabel lable19= new JLabel("");
		    frame1.add(lable19);
		    lable19.setBounds(230, 200, 375, 125);
		    
			JLabel lable13 = new JLabel("比特率:");
			frame1.add(lable13);
		    lable13.setBounds(300, 30, 200, 35);   
	        //初始化下拉列表框  
	        final JComboBox<String> box = new JComboBox<String>();  
	        box.addItem("4Mbps");  
	        box.addItem("6Mbps");   
	        box.setBounds(350, 30, 75, 35);  
	        frame1.add(box); 
	      
	        JLabel lable14 = new JLabel("录制时间:");
			frame1.add(lable14);
		    lable14.setBounds(450, 30, 200, 35);   
	        //初始化下拉列表框  
	        final JComboBox<String> box1 = new JComboBox<String>();
	        box1.addItem("20s"); 
	        box1.addItem("45s");  
	        box1.addItem("60s");  
	        box1.addItem("120s"); 
	        box1.setBounds(520, 30, 75, 35);  
	        frame1.add(box1); 

			 JLabel lable11 = new JLabel("视频存放路径：");
			 frame1.add(lable11);
			 lable11.setBounds(0, 300, 200, 35);   
		     final JTextField JText11 = new JTextField("D:/");
			 frame1.add(JText11);
			 JText11.setBounds(90, 300, 200, 35); 
			 
			final JButton button1 = new JButton("开始录制");		
			button1.setBounds(400, 300, 200, 35);  
		    frame1.add(button1);
		    

	button1.addActionListener(new ActionListener(){//匿名类实现ActionListener接口
		public void actionPerformed(ActionEvent e)
		{	
			  lable19.setText("录制中  请勿断开手机~~~~~~~~~~~");
			new Thread(new Runnable(){
	            public void run() {
	        		try {
					String throttle = null;
					String time = null;
					String name=JText0.getText();
					String path=JText11.getText();
					if(box.getSelectedItem().equals("4Mbps"))
					{
						throttle="";
					}
					if(box.getSelectedItem().equals("6Mbps"))
					{
						throttle="--bit-rate 6000000 " ;
					}
					
					if(box1.getSelectedItem().equals("20s"))
					{
						time="--time-limit 20 " ;
					}
					
					if(box1.getSelectedItem().equals("45s"))
					{
						time="--time-limit 45";
					}
					if(box1.getSelectedItem().equals("60s"))
					{
						time="--time-limit 60 " ;
					}
					if(box1.getSelectedItem().equals("120s"))
					{
						time="--time-limit 120" ;
					}
					Recording.Monkey("adb shell screenrecord  "+throttle+"  "+time+"  /sdcard/"+name+".mp4");
					lable19.setText("录制完成~开始拉取视频文件到本地~~~");	
					Recording.Monkey("adb pull /sdcard/"+name+".mp4 "+path);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						  lable19.setText("录制完成~");
					}
	            }
		        }).start();
				}});
			
					setTitle("录屏逐帧-----testly");
					setBounds(0, 150, 635, 408);
					add(frame1);
					setVisible(true);
					addWindowListener(new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        exit();
			      }});
			
			
			
		}
			
		
		
		public void actionPerformed(ActionEvent e){

		 
		}

		 public void exit() {
			   
			       setVisible(false);
			      //setDefaultCloseOperation(EXIT_ON_CLOSE);
			    
			  }
			

		
		 public static void main(String []args) throws Exception{
			 RecordingFM m=new RecordingFM();
						m.setVisible(true);
				}	
		
	public static void Recording() throws Exception{
	
/*
		Thread j= new Thread();	
		j.start();*/
		RecordingFM m=new RecordingFM();
		//m.setState(JFrame.ICONIFIED); 
		m.setVisible(true);
		
		}
	
	
		

}
