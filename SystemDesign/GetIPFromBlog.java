package SystemDesign;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Random;

public class GetIPFromBlog {
	
		public ArrayList<String> IPList ; 
		public int N ; // given IP address Number 
		private long runTime=0 ;
		public GetIPFromBlog (int N)
		{
			this.N =  N ;
			IPList = new ArrayList<String>() ;  
			//runTime = 0 ;
		}
		public  ArrayList<String> generateIP ( )
		{
			//ArrayList<String> IPList = new ArrayList<String>() ;
			long startTime = System.nanoTime(); 
			for(int i = 0; i < N; i ++)
			{
				Random r = new Random() ;
				StringBuffer sb = new StringBuffer() ;
				int a = r.nextInt(256) ;
				sb.append(a) ;
				sb.append(".") ;
				sb.append(a) ;
				sb.append(".") ;
				sb.append(a) ;
				sb.append(".") ;
				sb.append(a) ;
				//sb.append(".") ;
				String s = sb.toString() ;
				if(isValidIP(s))
					IPList.add(s) ;
			}
			long stopTime = System.nanoTime();
			
			runTime = stopTime - startTime ;
			return IPList;
			
		}
		/*
		 * Check if generated IP adress is valid or not 
		 * */
		
		public  boolean isValidIP(String content)
		{
			try
			{
				String[] ss = content.split("\\.") ; 
				if(ss.length !=4) return false ;
				for(String s : ss)
				{
					int i = Integer.parseInt(s) ;
					if(i>255 || i < 0) return false ;
				}
			}catch (Exception e)
			{
				return false ;
			}
			return true ; 
		}



		
		public void writeToFile()
		{
			try
			{
				FileWriter fr = new FileWriter("IP.txt") ;
				PrintWriter out = new PrintWriter(fr) ;
				for(int i = 0 ; i < N ; i ++)
					{
						out.println( IPList.get(i)  ) ;
					}
				//out.println(" time " + runTime) ;
				out.close() ;
				
			}catch(IOException e)
			{
				e.printStackTrace() ;
			}
			
		}
		/*public static void main (String[] args)
		{
			int N = 36571;
			System.out.println(N) ;
			GetIPFromBlog IP = new GetIPFromBlog(N) ;
			IP.generateIP() ;
			IP.writeToFile() ;
			
			
		}*/

}
