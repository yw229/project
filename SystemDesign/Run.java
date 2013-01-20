package SystemDesign;

import java.io.IOException;

public class Run {
	public static void main(String[] args)
	{
		int N = 36571;
		//System.out.println(N) ;
		GetIPFromBlog IP = new GetIPFromBlog(N) ;
		IP.generateIP() ;
		IP.writeToFile() ; 
		
		SplitFiles sp = new SplitFiles();
		try {
			sp.readFileIntoMap("IP.txt") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sp.DivideIntoSmallFile() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MostFreqentIP mostIP = new MostFreqentIP() ;
		try {
			mostIP.MostFrequentIP() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
