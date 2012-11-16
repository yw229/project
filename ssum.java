package LinkedIn;

import java.util.HashMap;
import java.util.Map;

public class ssum {
    public int k ; 
    public int ssum ;
    public int[] array ;
    public int start ;
    public int end ;
    public HashMap<Integer,Integer> indexMap ;
    public ssum(int[] array , int k , int ssm)
    {
        this.array = array ;
        this.k = k ; 
        this.ssum = ssm ;
        indexMap = new HashMap<Integer,Integer>() ;
    }
    public void getKSsum()
    {
        start = 0 ; end = 0 ;
        int temp = 0 ;
        for (int i=0 ; i < k ; i++)
        {
            temp += array[i] ;
            end =i;
           if (temp == ssum && i == k-1)   
            	indexMap.put(start, end) ;
        }
        for(int i = k; i < array.length ; i++)
        {
            temp = temp + array[i] - array[i-k] ;
           
            if(temp == ssum)
            {
            	start = i-k+1 ;
            	end = i ;
            	indexMap.put(start, end) ;
            }
                
        }
     
    }
    public void print()
    {
    	for( Map.Entry <Integer,Integer> e : indexMap.entrySet())
        System.out.println("index start at" +e.getKey()+ "end" + e.getValue()) ;
    }
    public static void main (String[] args)
    {
        int array[]={4,3,-1,3,4,5,8,9} ;
        ssum s = new ssum(array,4,26) ;
        s.getKSsum() ;
        s.print() ;
    }
	

}
