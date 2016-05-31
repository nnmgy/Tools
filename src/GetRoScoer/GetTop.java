package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
;


public class GetTop {
	private static  double Cpu = 0;


	public static void main( String[] args ) throws IOException{
		for ( int i = 0; i < 10; i++ ){
			System.out.println( " Cpu：" + cpu( "com.sds.android.ttpod" ) );

		}
	}


	  public static double cpu(String packageName) throws IOException {
		  try{ 
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys cpuinfo| grep "+packageName);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader( proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		        	if(line.contains("%")){
		             stringBuffer.append(line+" "); 
		        	}
		        }
		    String str1=stringBuffer.toString(); 
		    if(str1.contains(packageName))  {	
		    System.out.println(str1);
		    String str2=str1.substring(str1.indexOf("%")-4,str1.indexOf("%"));
		    System.out.println(str2);
		    if(str2.contains("%")){
		    	str2=str2.replaceAll("%", "");
		    	Cpu=Double.parseDouble( str2 );
		    }else{
		    	Cpu=Double.parseDouble( str2 );
		    }
		    }} catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }}
			catch ( Exception StringIndexOutOfBoundsException ){
				System.out.print( "请检查设备是否连接" );
				return -0.1;
			}
			return Cpu;
	  }
	  public static double Topcpu(String packageName) throws IOException {
		  try{ 
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell top -n 1| grep "+packageName);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader( proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		        	if(line.contains("%")){
		             stringBuffer.append(line+" "); 
		        	}
		        }
		    String str1=stringBuffer.toString(); 
		    if(str1.contains(packageName))  {		    	
		    String str2=str1.substring(str1.indexOf("%")-4,str1.indexOf("%"));
		    System.out.println(str2);
		    if(str2.contains("%")){
		    	str2=str2.replaceAll("%", "");
		    	Cpu=Double.parseDouble( str2 );
		    }else{
		    	Cpu=Double.parseDouble( str2 );
		    }
		    }} catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }}
			catch ( Exception StringIndexOutOfBoundsException ){
				System.out.print( "请检查设备是否连接" );
				return -0.1;
			}
			return Cpu;
	  }

public static double heap( String PackageName ) throws IOException
	{
		double Heap = 0;
		try{
			Runtime runtime = Runtime.getRuntime();
			Process proc	= runtime.exec( "adb shell dumpsys meminfo " + PackageName );
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
				String str1 = stringBuffer.toString();
				if ( str1.contains( "Objects" ) ){
					String	str2	= str1.substring( str1.indexOf( "TOTAL" )+6, str1.indexOf( "Objects" ) );
					String	str3	= str2.substring( 0, 10 );
					str3	= str3.trim();
					Heap	= Double.parseDouble( str3 );
				}  else{
					Heap = 0;
				}
			} catch ( InterruptedException e ) {
				System.err.println( e );
			} finally {
				try {
					proc.destroy();
				} catch ( Exception e2 ) {
				}
			}
		}
		catch ( Exception StringIndexOutOfBoundsException ){
			System.out.print( "请检查设备是否连接" );
			return(-0.1);
		}
		return(Heap);
	}


	public static void heapTal() throws IOException
	{

	}
}


