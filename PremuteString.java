package LinkedIn;

import java.util.ArrayList;

public class PremuteString {
	 public String s ;
	    public PremuteString(String s)
	    {
	        this.s = s ;
	    }
	    public  void getPremute(String begin , String end)
	    {
	       // ArrayList<String> prem = new ArrayList<String>() ;
	        if(end.length() <= 1)
	        {
	            System.out.println(begin+end) ;
	        	//prem.add(begin+end) ;
	        	// return prem ;
	        }
	        else
	        {
	            for(int i = 0 ; i < end.length() ; i ++)
	            {
	               // String newS = end.substring(0,i)  + end.substring(i+1) ;
	                getPremute(begin + end.charAt(i) , end.substring(0,i)+ end.substring(i+1)) ;
	                
	            }
	        }
	       
	       
	    }
	    public void getPremute()
	    {
	        getPremute(" ",this.s) ;
	    }
	    public static void main(String[] args)
	    {
	        PremuteString ps = new PremuteString("String") ;
	        ps.getPremute("", "String") ;
	       // ArrayList<String> ls =ps.getPremute(" " ,"String") ;
	       // for(String s : ls)
	       // 	System.out.println(s) ;

	    }
	    }



