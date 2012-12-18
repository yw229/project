//package Quetion1_1;

//package Quetions;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class AnagramSort {


	/*
	 *build an Hashmap where key is sort(w) and the value is list of all the words that sorted,give 
	 *corresponding key;
	 * */
	public static Map< String, List<String> > anagramGroup(String[] words)
	{
		List<String> anagramList = null;
		Map< String, List<String> > map=new HashMap< String, List<String> >();
		//Set< String> hasAnagram= new HashSet<String>();
		
		for(String w: words)
		{
			String key=sort(w).toLowerCase();
			
			if(  map.containsKey(key) )
			{	
				anagramList = map.get(key);
				anagramList.add(w);
				//hasAnagram.add(key);
			}
			if(!map.containsKey(key))
			{
				anagramList=new ArrayList<String>();
				//map.put(key,new LinkedList<String>());
                                   anagramList.add(w) ;
				map.put(key, anagramList);
			}
			if(! anagramList.contains(w) )
			{
				anagramList.add(w);
			}
		}
		//get rid of those words without anagrams and single letter string and length <2
		String[] keys=new String[map.size()];
		for(String k: map.keySet().toArray(keys))
		{
		     if(map.get(k).size()<2)
                         map.remove(k);
		}
		
		return map;
	}
	

	/*
	 * Sort the anagram groups by the List<String> size 
	 * */
	@SuppressWarnings("unchecked")
	public static Map< List<String>, Integer>  sortAnagramGroup ( Map<String, List<String>> groups )
	{
		Map< List<String>, Integer > tempMap= new HashMap< List<String>, Integer >(); 
		for( Entry<String, List<String> > e: groups.entrySet())
		{
			//create tempMap to store by stringlist and the size
			tempMap.put(e.getValue(),e.getValue().size());
		}	
			
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> list = new LinkedList(tempMap.entrySet());  // store tempMap  to a new list  
		
		/*
		 * @override 
		 * descending sort
		 * */				
		Collections.sort( list, new Comparator<Object>() 
		{
			public int compare(Object o1, Object o2) {          

             return extracted(o1, o2); 

             }

			private int extracted(Object o1, Object o2) 
			{
				@SuppressWarnings({ "rawtypes", "unchecked" })
				int compareTo2 = ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
				int compareTo = compareTo2;
				return compareTo;
			}
				} );   // 
		
		@SuppressWarnings("rawtypes")
		HashMap result = new LinkedHashMap< List<String>, Integer >();  
		  for (@SuppressWarnings("rawtypes")
		Iterator it = list.iterator(); it.hasNext();) {    

              @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry)it.next();    

               result.put(entry.getKey(), entry.getValue());     

          }      

         return result; 
		
	}
		
	/* 
	 * sort the string  alphabetically and return the anagramKey for the hashmap
	 * */
	private static String sort(String str) {
		
		char[] arrstr=str.toCharArray();
		Arrays.sort(arrstr);
		String anagrmKey=String.valueOf(arrstr);
		
		return anagrmKey;
		}
	
	public static String[] ReadFile(String filename) throws IOException
	{
		//File file= new File(AnagramSort.class.getResource(filename).getPath());
		File file= new File("/usr/share/dict/"+filename);
		//FileReader fr= new FileReader(file);
		
		FileInputStream fin = new FileInputStream(file);
		DataInputStream din = new DataInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader( din ));
		List<String> words = new ArrayList<String>();
		
		
		while(true)
		{
			String line=br.readLine();
			if(line==null)
				break;
			String[] chunks=line.split(" ");
			for(int i=0;i<chunks.length;i++)
			{
				words.add(chunks[i]);
			}
		}
		String[] results=words.toArray(new String[words.size()]); // convert Stringlist to string array
		return results;
	}

	public static void main(String[] args)
	{
		//List<String>
		String[] words=null;
		try{
			  words=ReadFile("words");

			
		}catch ( IOException e){
			e.printStackTrace();
		}
		
		Map<String, List<String>> group= anagramGroup(words);
		Map<List<String>, Integer> sorted = sortAnagramGroup(group);
		//System.out.println(sorted); //print out the sorted hash map 
		try{
			FileWriter outfile=new FileWriter("out.txt");
			PrintWriter out=new PrintWriter(outfile);
			for (Entry< List<String> , Integer> Name : sorted.entrySet())
			    out.println(Name.getKey() + "  (set that contains " + Name.getValue()+" words)");
			
			out.close();
			
		}
			 catch( IOException e)
			{
			e.printStackTrace();
		}
	
			/*for (Entry< List<String> , Integer> Name : sorted.entrySet()){
		    System.out.println(Name.getKey() + " sets that contains " + Name.getValue()+" words");*/
		}
}
