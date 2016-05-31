package alimusic.tools.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	
	    public static String PID = "adb -s 010a1da7d4b8a0a8 shell dumpsys meminfo com.sds.android.ttpod|grep pid|awk '{print $5}'";
	    public static String UID = "adb -s 010a1da7d4b8a0a8 shell dumpsys package com.sds.android.ttpod|grep packageSetting|cut -d \"/\" -f2|cut -d \"}\" -f1";
        /**实时抓取**/
	    // TIMESTAMP(yyyymmddHHMMSS) CPU(%) PID/PACKAGE
	    public static String CPU_USAGE = "adb -s 010a1da7d4b8a0a8 shell dumpsys cpuinfo|grep #filter#|awk '{print '$(date +%Y%m%d%H%M%S)',$1,$2}'|grep #pid#";

	    // TIMESTAMP(yyyymmddHHMMSS) PSS(KB) PACKAGE
	    public static String PSS = "adb -s 010a1da7d4b8a0a8 shell dumpsys meminfo|awk '/process:/,/adjustment:/{if(i>1)print x;x=$0;i++}'|grep #filter#|grep #pid#|awk '{print '$(date +%Y%m%d%H%M%S)',$1,$3}'";

	    // TIMESTAMP(yyyymmddHHMMSS) RX_BYTES(BYTE)
	    public static String RX_BYTES = "adb -s 010a1da7d4b8a0a8 shell cat /proc/net/xt_qtaguid/stats|grep #uid#|awk '{rx_bytes+=$6}END{print '$(date +%Y%m%d%H%M%S)',rx_bytes}'";

	    // TIMESTAMP(yyyymmddHHMMSS) TX_BYTES(BYTE)
	    public static String TX_BYTES = "adb -s 010a1da7d4b8a0a8 shell cat /proc/net/xt_qtaguid/stats|grep #uid#|awk '{tx_bytes+=$8}END{print '$(date +%Y%m%d%H%M%S)',tx_bytes}'";

	        /**异步抓取**/
	    // TIMESTAMP(yyyymmddHHMMSS) PID VSS(KB) RSS(KB) PACKAGE
	    public static String SAVE_TOP_INFO = "adb -s 010a1da7d4b8a0a8 shell top -n 1 -d 0|grep #filter#|awk '{print '$(date +%Y%m%d%H%M%S)',$1,$6,$7,$10}' >> output/010a1da7d4b8a0a8/latest/top_010a1da7d4b8a0a8.txt";

	    // TIMESTAMP(yyyymmddHHMMSS) CPU(%) PID/PACKAGE
	    public static String SAVE_CPU_INFO = "adb -s 010a1da7d4b8a0a8 shell dumpsys cpuinfo|grep #filter#|awk '{print '$(date +%Y%m%d%H%M%S)',$1,$2}' >> output/010a1da7d4b8a0a8/latest/cpu_010a1da7d4b8a0a8.txt";

	    // TIMESTAMP(yyyymmddHHMMSS) PSS(KB) PACKAGE
	    public static String SAVE_PSS_INFO = "adb -s 010a1da7d4b8a0a8 shell dumpsys meminfo|awk '/process:/,/adjustment:/{if(i>1)print x;x=$0;i++}'|grep #filter#|awk '{print '$(date +%Y%m%d%H%M%S)',$1,$3}' >> output/010a1da7d4b8a0a8/latest/pss_010a1da7d4b8a0a8.txt";

	    // TIMESTAMP(yyyymmddHHMMSS) RX_BYTES(BYTE) TX_BYTES(BYTE)
	    public static String SAVE_TRAFFIC_INFO = "adb -s 010a1da7d4b8a0a8 shell cat /proc/net/xt_qtaguid/stats|grep #uid#|awk '{rx_bytes+=$6}{tx_bytes+=$8}END{print '$(date +%Y%m%d%H%M%S)',rx_bytes,tx_bytes}' >> output/010a1da7d4b8a0a8/latest/traffic_010a1da7d4b8a0a8.txt";

	    // Draw(MS) Process(MS) Execute(MS)
	    public static String SAVE_GFX_INFO = "adb -s 010a1da7d4b8a0a8 shell dumpsys gfxinfo com.sds.android.ttpod|awk '/Execute/,/hierarchy/{if(i>1)print x;x=$0;i++}'|sed /^[[:space:]]*$/d|awk '{if(length($0)==16)print $1,$2,$3}' >> output/010a1da7d4b8a0a8/latest/gfxinfo_010a1da7d4b8a0a8.txt";
	    public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    	System.err.println( battery(TX_BYTES));
	    	
	    	
	    	
	}

	    
	    
		public static String  battery(String commd) throws IOException{
			String	batt = null	;
			Runtime runtime = Runtime.getRuntime();
			Process proc	= runtime.exec( "adb  shell dumpsys gfxinfo com.sds.android.ttpod|awk '/Execute/,/hierarchy/{if(i>1)print x;x=$0;i++}'|sed /^[[:space:]]*$/d|awk '{if(length($0)==16)print $1,$2,$3}'" );
			try {
				if ( proc.waitFor() != 0 ){
					System.err.println( "exit value = " + proc.exitValue() );
				}
				BufferedReader in = new BufferedReader( new InputStreamReader(proc.getInputStream() ) );
				StringBuffer	stringBuffer	= new StringBuffer();
				String		line		= null;
				while ( (line = in.readLine() ) != null ){
					stringBuffer.append( line + " " );
				}
				String	str1	= stringBuffer.toString();
				batt = str1;
				} catch ( InterruptedException e ) {
				System.err.println( e );
				} finally {
				try {
					proc.destroy();
				} catch ( Exception e2 ) {
				}
			}
			return batt;
		}
}
