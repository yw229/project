package LinkedIn;

import java.util.HashMap;

public class Ransom
{
    public String[] magzine ;
    public String ransom ;
    private HashMap<Character , Integer> map ;
    public Ransom (String[] magzine , String ransom)
    {
        this.magzine = magzine ;
        this.ransom = ransom ;
        map = new HashMap<Character,Integer>() ;
    }
    public boolean isRansom()
    {
       // for(String s : magzine)
       // String mag  = magzine.toString();
        StringBuffer sb = new StringBuffer() ;
        for(String s : magzine)
        	sb.append(s) ;
        String mag = sb.toString() ;
        System.out.println(mag) ;
        for(char c : mag.toCharArray())
        {
            Integer freq = map.get(c) ; // find the counter
            map.put(c , freq ==null ? 1 :freq +1) ;
        }
        for(char r : ransom.toCharArray()) // iterate each char in ransom 
        {
            if( !map.containsKey(r)) // current letter not in the magzine map return false
                return false;
            else
            {
                map.put(r,map.get(r)-1);//  get current letter counter in the hashmap, decrement the counter
                if(map.get(r)  <0) // if counter less than 0 ,which means the letter time apears more than the word in the dictroy 
                    return false; 
            }
        }
        return true ; 
    }
    public static void main (String[] args)
    {
        String[] magzine = {"hello", "java","world"} ;
       	
        
        String s = "jallll" ;
        Ransom rs = new Ransom(magzine, s) ;
        System.out.println( "rasom word" + s + " is " +rs.isRansom()) ;


    }
    
}