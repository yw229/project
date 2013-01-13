package LinkedIn;

import java.util.ArrayList;
import java.util.Arrays;

public class Sum3 {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>() ;
        Arrays.sort(num) ; // sorted the array, if sum > 0,k decrease, sum<0 k increase
        for(Integer i=0 ;i < num.length;i ++ )
        {
            Integer j = i + 1 ;
            Integer k = num.length-1 ;
            // for particular item i, look up num[i+1] to num[k-1] , sum >0 bigger, so decrease , if sum < 0 smaller, increase i , sum = 0 which means i j k is result , decease j and k the same time 
                        
            while(j< k) 
            {
                Integer sum = num[i] + num[j] + num[k] ;
                if(sum>0)
                    k--;
                if(sum <0)
                    j++ ;
                if(sum == 0)
                {
                     ArrayList<Integer> triple = new  ArrayList<Integer>() ;
                     triple.add(num[i]);
                     triple.add(num[j]);
                     triple.add(num[k]);
                     if(!list.contains(triple))
                         list.add(triple) ;
                     j++ ; // decrease j and k the same time 
                     k-- ;
                } 
            }
            
        }
        return list ;
    }
}
