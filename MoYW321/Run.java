package MoYW321;

import java.io.IOException;
import java.util.ArrayList;

public class Run {
	public static void main(String[] args) throws IOException
	{
		ParseFile p = new ParseFile() ;
		ArrayList<String> list =  p.readFile("D://mouse_seq/") ;
	//	ArrayList<String> list =  p.readFile("/home/wang/mouse_seq") ;

		Matching mat = new Matching() ;
		ArrayList<String> r =mat.validMotif(list) ;
		System.out.println("The valid motif are : ") ;
		for(String s : r)
			System.out.println(s) ;
					
	}
}
