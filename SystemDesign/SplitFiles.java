package SystemDesign;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class SplitFiles {
	
	 HashMap< Integer,ArrayList<String> > map ;
	 public SplitFiles()
	 {
		 map = new HashMap< Integer, ArrayList<String> >() ;
	 }
	/*
	 * split the generated IP address LargeFile 
	 * 
	 * Assume Memory = 128 MB , to store each IP address need 16byte (4 sets of 3 digits + 3 period)
	 * So for each file, total number of IP each could processed = 128 MB/16B = 128*2^20 B/16 B = 8,388,608 IPs 
	 * Total number of files(Hash Mod number) = 100,000,000 / 8,388,608 = 12 files 
	 * */
	public HashMap< Integer,ArrayList<String> > readFileIntoMap(String filename) throws IOException 
	{
		File file = new File(filename) ;
		FileInputStream fin = new FileInputStream(file) ;
		DataInputStream din = new DataInputStream(fin) ;
		BufferedReader br = new BufferedReader(new InputStreamReader(din)) ;
		
		
		while(true)
		{
			String line = br.readLine() ;  // read each line in the large IP file 
			if(line == null) break ;
			Integer fileIndex = Math.abs( line.hashCode() % 12) ; // 12 files, mod%12 is each IP's stored file index 
			if(!map.containsKey(fileIndex)) // hashmap does not contain file index, put index and string in
			{
				ArrayList<String> list = new ArrayList<String>() ;
				list.add(line) ;
				map.put(fileIndex, list) ;
			}
			else // if hashmap contains file index, get the index list and add the current string
			{
				ArrayList<String> list = map.get(fileIndex) ;
				list.add(line) ;
			}
		}
		return map ;
	}
	
	public void DivideIntoSmallFile() throws IOException
	{
		//HashMap< Integer,ArrayList<String> > map = readFileIntoMap("IP.txt") ;
		
		for( Entry< Integer,ArrayList<String> > en : map.entrySet())
		{
			FileWriter fr = new FileWriter(en.getKey()+"IndexIP.txt" ) ; // name each file based on the index
			PrintWriter out = new PrintWriter(fr) ; 
			ArrayList<String> ls = en.getValue() ; //get each string list value in the map
			for(String s : ls) 
				out.println( s ) ;
			out.close() ;
		}
	}
	
/*	public static void main (String[] args) throws IOException
	{
		
		SplitFiles sp = new SplitFiles();
		sp.readFileIntoMap("IP.txt") ;
		sp.DivideIntoSmallFile() ;
				
	}
*/
}
