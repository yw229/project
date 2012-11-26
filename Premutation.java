package LinkedIn;

import java.util.ArrayList;

public class Premutation 
{
    public String s ;
    public Premutation(String s)
    {
        this.s = s ;
    }
    public ArrayList<String> getPerms(String s)
    {
        ArrayList<String> list = new ArrayList<String>() ;
        if(s==null)
            return null ;
        
        if(s.length() == 0)
        {
            list.add(" ") ;
          
        }
        char first = s.charAt(0) ;
        System.out.println("first "  + first) ;
        String remain = s.substring(1) ;
        ArrayList<String> words = getPerms(remain) ;
        for(String e : words)
        {
            for(int i = 0 ; i < e.length(); i ++)
            list.add(InsertCharAt(e,first,i)) ;
            
        }
        return list ; 
   
    }
    public String InsertCharAt(String s, char c , int j)
    {
        String st = s.substring(0,j) ;
        String e = s.substring(j) ;
        System.out.println("inser st + c + e : " + st + c + e ) ;
        return st + c + e ;
    }
    public static void main(String[] args)
    {
    	String s = "abc" ;
    	//System.out.println(" sub " + s.substring(1)) ;
    	Premutation ps = new Premutation(s) ;
    	ArrayList<String> words = ps.getPerms(s) ;
    	for(String w: words ) 
    	{
    		System.out.println(w) ;
    	}
    }
    
}