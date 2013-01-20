//package Blmbg;

import java.util.HashSet;

public class LongestSubstringWithoutRepeating {
	    public int lengthOfLongestSubstring(String s) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	    	  int max = 0;
	          HashSet<Character> set = new HashSet<Character>();
	          int candidateStartIndex = 0;
	          for(int i = 0; i < s.length(); ++iter) {
	              char c = s.charAt(i);
	              if(set.contains(c)) {
	                  max = Math.max(max, i - candidateStartIndex);
	                  while(s.charAt(candidateStartIndex) 
	                          != s.charAt(i)) {
	                      set.remove(s.charAt(candidateStartIndex));
	                      candidateStartIndex++;
	                  }
	                  candidateStartIndex++;
	              } else 
	                  set.add(c);
	              
	          }
	          max = Math.max(max, s.length() - candidateStartIndex);
	          return max;
	    }

}

