package LinkedIn;



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
		int temp = 0 ;
	      //  max = 0 ;
	        for (int i = 0 ; i < array.length; i ++)
	        {
	            temp += array[i] ;
	            if (max < temp)
	              {
	                max = temp ; 
	                endpoint = i ;
	               }
	            if(temp<0)
	            {
	               temp = 0 ;
	               startpoint= endpoint ; 
	               System.out.println("startPoint" + startpoint) ;
	               
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
		int a[] = {3,-1,2,-1,-3} ;
		MaxCountinous max = new MaxCountinous() ;
		max.initialize(); 
		max.max(a) ;
		max.print();
		
	}

}
