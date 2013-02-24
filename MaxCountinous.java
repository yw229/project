package LinkedIn;

/*
 * Dynamic Programming 
 * Find the max sum of any sub array in the range of array[0,n-1] (zero/negative/positive)
 * subproblem find max sum of any sub array starting at any index in the range [0,i] end at index i 
 * array contains negative,positive, sub[i] = max {sum[i-1] + a[i],0}, if after adding next sum value is negative, which means does not
 * contribute to the sub array; for each intermediate result sub[0]...sub[i] we find the max value 
 * 
 * */

public class MaxCountinous {
	public int[] array ;
	public int max ;
	public int startpoint; 
	public int endpoint;
	
	public  int  max(int[] array)
	{
		 //max = 0;
	/*	this.a = a ;
		int temp = 0;
		
		for ( int i : a)
		{
			
			temp +=i ;
			if (temp > max)
			{
				max = temp ;
				//list.add(i);
				endpoint++;
			}
			
			if (temp < 0)
			{
				temp = 0 ;
				startpoint = endpoint;
			}
			
		}
		return max ; */
		this.array = array ;
		int current_max_so_far = 0 ;
		int current_index = 0 ;
	      //  max = 0 ;
	        for (int i = 0 ; i < array.length; i ++)
	        {
	        	
	           // current_max_so_far = Math.max(0, current_max_so_far + array[i]) ;
	          //  max = Math.max(current_max_so_far, max) ;
	        	current_max_so_far = current_max_so_far+ array[i] ; 
	        	if(current_max_so_far <0)
	        	{
	        		current_max_so_far = 0 ;
	        		current_index= i+1 ; // store the startpointer 
	        	}
	        	else
	        		if(current_max_so_far > max)
	        		{
	        			max = current_max_so_far ;
	        			endpoint = i ;
	        			startpoint = current_index ;
	        		}
	        }
	        return max ; 
	}
	
	public void initialize()
	{
		max = 0 ;
		startpoint = 0 ;
		endpoint = 0;
		
	}
	public void print(int[] array)
	{
		System.out.println("Max Countinous subset is: ");
		
		System.out.println("Total is"+max);
		System.out.println("Start is"+startpoint + "end" +endpoint);
		System.out.print("Total max subset") ;
		
		for (int i = startpoint; i <= endpoint ; i++)
			System.out.print(array[i]+" ");
		
	}
	public void print()
	{
		print(array) ;
	}
	public static void main (String[] args)
	{
		int a[] = {-9,-3,33,-6,27} ;
		MaxCountinous max = new MaxCountinous() ;
		max.initialize(); 
		max.max(a) ;
		max.print();
		
	}

}
