package alimusic.tools.Recording;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import alimusic.tools.Outlog.NSlog;


public class Recording {


	public static void main(String []args) throws IOException, InterruptedException{
		
		DosCommand("C:/Users/yuge.ly/Desktop/1.bat");
	}

	
	public static String  DosCommand(String cmmd) {
		String result=new String ();
		BufferedReader br = null;
	    try {String[] cmd = new String[] { "cmd.exe", "/C", cmmd };
	        Process process = Runtime.getRuntime().exec(cmd); 
	        br = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
	        StringBuffer stringBuffer = new StringBuffer();
	        String line = null;
	        while ((line = br.readLine()) != null) {
	        	stringBuffer.append(line+" ");
	        	System.out.println(line+" ");
	            }result=stringBuffer.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {if (br != null) {
	            try {br.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    } return result;
	}



	public static void Monkey(String cmd )
	{
		Runtime runtime1 = Runtime.getRuntime();     
	    System.out.println(cmd);
		try {
			String[] args = new String[]{"cmd","/c",cmd};
			Process pro1 = runtime1.exec(args);
			//检查命令是否失败
			try {
				if(pro1.waitFor()!=0){
					System.err.println("exit value:" + pro1.exitValue());
					JOptionPane.showMessageDialog(new JFrame(), "哥们抱歉，好像出问题了！关掉重试吧！");
				}
			} catch (InterruptedException e) {
				System.err.println();
				e.printStackTrace();		
			}

		} catch (IOException e) {
			System.out.println("error Message:"+e.getMessage());
			e.printStackTrace();
		} finally{
			
		}
	  }	
}
  

