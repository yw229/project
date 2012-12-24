package LinkedIn;

public class KnapSack {
/* given n items, and KnapSack with capacity c
 * Recursive function  0/1 knapsack 
 * F(i,w) = Max {vi + F(i-1, w- wi) , F(i-1,wi)}
 * either the value is from the above cell(current value can't be added)
 * or it will be the current value add with the rest weight (w-wi) value
 * 
 * Only value need to be sorted, capacity (wi  from 1 ~ Cap)
 * Complexity O(cN), where c is the max capacity of the pack, and N is the total number of items
 * */
	
	
	public static int KnackSap(Item[] it , int Cap)
    {
        if(Cap < 0 )
            throw new NullPointerException("knsacksap illegal"); 
        int sol[][] = new int[it.length+1][Cap+1] ;
        int max = 0 ;  
        
      /*  for(int i =0; i <= it.length; i ++) // item increment
        	for(int j = 0; j <= Cap  ; j ++) // capacity increment
        	{
        		sol[0][j] = 0 ;
        		sol[i][0] = 0 ;
        	}
       */
        for(int i =1 ; i < it.length; i ++) // item increment
        {
        	
            for(int j = 1; j <= Cap  ; j ++) // capacity increment
            {
            	
                if ( it[i].weight > j)  // current item weigh is greater than the current capacity 
                    sol[i][j] = sol[i-1][j] ;
                else
                    sol[i][j] = Math.max( it[i].value + sol[i-1][j - it[i].weight] , sol[i-1][j] ) ;
            }
        }
       for(int i = 1 ; i < it.length ; i ++)
       {
           if(max < sol[i][Cap])
               max = sol[i][Cap] ;
       }
       return max ;
    }
	public static void main (String[] args)
	{
		int cap = 10 ;
		//Item a = new Item(0, 0);
		
		Item[] items = {new Item(0,0) ,  new Item(3, 5), new Item(4, 7),
                new Item(5, 8) }; 
		System.out.println("s: = " + KnackSap(items, cap) ) ;
	}

}
