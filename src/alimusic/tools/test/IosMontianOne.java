package alimusic.tools.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class IosMontianOne
{
    public static void main(String[] args) throws InterruptedException
    {
    	for(int i=0;i<1;i++){
    		Thread.sleep(3000);
    		getIOSRosrcue();
    	}
    		
    }
    
    public static  void getIOSRosrcue(){
    	String hostname = "30.133.8.18";
        String username = "root";
        String password = "alpine";
        try
        {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false)
            throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand("top  | grep xiami");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = null ; line=br.readLine();
             if (line.contains("%")) {
                System.out.println(line);
              break;
              }}
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