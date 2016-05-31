package alimusic.tools.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import alimusic.tools.Outlog.NSlog;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class IosMontian
{
    public static void main(String[] args) throws InterruptedException{
    		getIOSRosrcue();
    		
    }
    public static  void getIOSRosrcue(){
    	String hostname = "30.133.8.138";
        String username = "root";
        String password = "alpine";
        String  time =NSlog.time();
        try{
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false)
            throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand("top |  grep xiami");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String  line = null ;
            while ((line = br.readLine() ) != null) {
            	 System.out.println(line);
                NSlog.writelogs("d:/log/", time, line);
              }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
        }
        catch (IOException e){
            e.printStackTrace(System.err);
            System.exit(2);
        }
    } 
    
}