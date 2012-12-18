package Questions4;

import java.util.Random;

public class SecondLargest
{
    public int[] array ;
    public SecondLargest(int[] array)
    {
        this.array = array; 
    }
    public int SecondL()
    {
        int max = 0 ; 
        int second = 0 ; 
        for(int i = 0 ; i < array.length ; i ++)
        {
            if(max < array[i])
            {
            	second = max ;
            	max = array[i] ;
                
            }
            else 
            if( second < array[i] && max != array[i])
            {
                second = array[i] ;
            }
        }
        return second ;
    }
    public static void main(String[] args)
    {
        int size = 10 ; 
        int[] array = new int[10] ;
        Random rand = new Random() ;
        for(int i= 0 ; i < size ; i++)
        {
            array[i] = rand.nextInt(10) ;
            System.out.print(array[i]) ;
        }
        SecondLargest sl = new SecondLargest(array) ;
        System.out.print("second largest" ) ;
        System.out.println( sl.SecondL()) ;
    }
  }