package SystemDesign;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class HashStatistics{
	public HashMap<String, Integer> statMap ;
	public String s ; // max freq IP
	public Integer max ; // max freq 
	public HashStatistics()
	{
		statMap = new HashMap<String,Integer>() ;
		max = 0 ;
	}
	public void getIPMaxFreq(String filename) throws IOException
	{
		
		File file = new File(filename) ;
		FileInputStream fin = new FileInputStream(file) ;
		DataInputStream din = new DataInputStream(fin) ;
		BufferedReader br = new BufferedReader(new InputStreamReader(din)) ;
		
		//Integer max = 0 ;
		while(true)
		{
			String line = br.readLine() ;
			if(line == null) break ;
			Integer freq = statMap.get(line) ;
			statMap.put(line, freq == null? 1 : freq+1) ; // get eachIP's freqency in each small file
		}
		
	/*	for(Entry<String,Integer> en : statMap.entrySet())
		{
			System.out.println(" " + en.getKey() +" " + en.getValue()) ;
		}
		*/
		for(Integer i : statMap.values())
		{
			//(max == i )? i>max : i<=max ;
			if( i > max)  max = i ;
		}
		for(String ip: statMap.keySet())
		{
			if(statMap.get(ip) == max)
				s = ip ;
		}
		//return null	 ;
		
	}
	
	/*public static void main(String[] args) throws IOException
	{
		
	}*/

}
