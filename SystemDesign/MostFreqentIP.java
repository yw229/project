package SystemDesign;

import java.io.IOException;

public class MostFreqentIP {
	public void MostFrequentIP() throws IOException
	{
		int max = 0 ;
		String IP = new String() ;
		for(int i = 0 ; i < 12 ; i ++)
		{
			HashStatistics hs = new HashStatistics() ; 
			hs.getIPMaxFreq(i+"IndexIP.txt") ;
			int tmp = hs.max ;
			String tempIP = hs.s ;
			if(max < tmp)
			{
				max = tmp ;
				IP = tempIP ;
			}
			
		}
		System.out.print("Most Frequent visited IP  is " + IP + " ; visited time is " + max) ;
	}
	
/*	public static void main(String[] args)
	{
		try {
			MostFrequentIP() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
