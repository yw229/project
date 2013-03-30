package MoYW321;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * READ String from given file, and parse the read contents,
 * extract the Sequences into ArrayList 
 * 
 * */


public class ParseFile {
	private int len ;
	ArrayList<String> result ; // store all the RNA sequence 
	
	public ParseFile()
	{
		result = new ArrayList<String>() ; 
	}
	
	public ArrayList<String> readOneFile(String name) throws IOException 
	{
		ArrayList<String> chunkList = new ArrayList<String> () ;
		//File file = new File(name) ;
		FileInputStream fin = new FileInputStream(name) ;
		DataInputStream din = new DataInputStream(fin) ;
		BufferedReader br = new BufferedReader(new InputStreamReader(din)) ;
		while(true)
		{
			String line = br.readLine() ;
			if(line == null) break ;
		
			//if(line.length() >=50 && !line.isEmpty())  // to find motif, the chunk string length must at least >=50
				chunkList.add(line) ;	
		}
		System.out.println() ;
		System.out.println("length" +  chunkList.size()) ;
		return chunkList;
	}
	
	
	public ArrayList<String> readFile(String FolderName) throws IOException // read seq files from folder
	{
		//ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>() ;
		File folder = new File(FolderName) ;
		File[] listOfFiles = folder.listFiles() ; // list all files under that folder 
		for(File file : listOfFiles)
		{
			if(file.isFile())
			{
				
				FileInputStream fin = new FileInputStream(file) ;
				DataInputStream din = new DataInputStream(fin) ;
				BufferedReader br = new BufferedReader(new InputStreamReader(din)) ;
				String line ;
				while( (line = br.readLine() )!=null)
				{
					
					line = line.trim() ; // trim to remove any space in the in the end of the string 
					if(line.length() >=50 && !line.isEmpty())  // to find motif, the chunk string length must at least >=50
							//result.add(line) ;	
					{
						String[] chunks = line.split("\\s+");
						for(String s : chunks)
							result.add(s);
						
					}
					
				}
				
			}
		}
		len = result.size() ;
		return result;
		
	}
	public ArrayList<String> getAllSequences() 
	{
		return result ;
	}
	
	/*
	public static void main(String[] args) throws IOException
	{
		ParseFile p = new ParseFile() ;
		//ArrayList<String> one = p.readOneFile("/home/wang/mouse_seq/Rbm8a.txt") ; 
		// p.readFile("/home/wang/mouse_seq") ;
	//	ArrayList<String> l = p.readFile("/home/wang/mouse_seq")  ;
		//System.out.println(l ) ;
		//for(int i = 0 ; i < l.size() ; i ++)
			System.out.println( l.get(115).substring(549, 549+50) ) ;
			System.out.println( l.get(0).substring(250, 250+50) ) ;
		
		try
		{
			FileWriter fr = new FileWriter("D://Mot.txt") ;
			PrintWriter out = new PrintWriter(fr) ;
			for(int i = 0 ; i < l.size() ; i ++)
				{
					out.println(i + " " + l.get(i)  ) ;
				}
			//out.println(" time " + runTime) ;
			out.close() ;
			
		}catch(IOException e)
		{
			e.printStackTrace() ;
		}			
	}
*/
}
