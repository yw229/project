package LinkedIn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Transform {
	 public String a ;
	    public String b ;
	    public String[] words ;
	    HashSet<String> hash = new HashSet<String>();
	    public Transform(String a, String b , String[] words  )
	    {
	        this.a = a  ;
	        this.b = b ; 
	        this.words = words ;
	       //this.dict = dict ;
	        
	    }
		public  HashSet<String> setupDictionary(String[] words) {
			//HashSet<String> hash = new HashSet<String>();
			//this.words = words ;
			for (String word : words) {
				hash.add(word.toUpperCase());
			}
			return hash;
		}
	    public Set<String> OneLetterChangeWord(String word)
	    {
	    	Set<String> words = new TreeSet<String>();
			// for every letter
			for (int i = 0; i < word.length(); i++) {
				char[] wordArray = word.toCharArray();
				// change that letter to something else
				for (char c = 'A'; c <= 'Z'; c++) {
					if (c != word.charAt(i)) {
						wordArray[i] = c;
						words.add(new String(wordArray));
					}
				}
			}
			return words;
	    } 
	    public  LinkedList<String> getWordsList(String s,String e , HashSet<String> dict ) // get the list 
	    { 
	    	
	    	s = s.toUpperCase();
			e = e.toUpperCase();
	        Queue<String> wordQ = new LinkedList<String>() ;
	        Set<String> visitedSet = new HashSet<String>() ;
	        Map<String, String> backTrack = new TreeMap<String, String>() ; // find the word and back track 
	        wordQ.add(s) ;
	        visitedSet.add(s) ;
	        while(!wordQ.isEmpty())
	        {
	            String w = wordQ.poll() ;  // pop up 
	            for(String v : OneLetterChangeWord(w)) // traverse the possible word 
	            {
	                if(v.equals(e))
	                {
	                    LinkedList<String> result = new LinkedList<String>();
	                    result.add(v) ;
	                    while(w!=null)
	                    {
	                        result.add(0,w) ;
	                        w = backTrack.get(w) ;
	                    }
	                    return result ;
	                } // if v is dictionary word 
	                if(dict.contains(v))
	                {
	                    if(!visitedSet.contains(v))
	                        {
	                            wordQ.add(v);
	                            visitedSet.add(v) ;
	                            backTrack.put(v,w) ;
	                            
	                        }
	                }
	               
	            }
	          }
	            return null;
	            
	      
	    }
	    public  LinkedList<String> getWordsList( )
	    {
	    	return getWordsList(this.a ,this.b,setupDictionary(this.words)) ;
	    }
	 /*   public LinkedList<String> getWordsList()
	    {
	    	return  getWordsList(this.a,this.b , setupDictionary(this.words)  ) ;
	    }
 */
			 public static void main(String[] args) {
					String[] words = {"maps", "tan", "tree", "apple", "cans", "help", "aped", "free", "apes", "flat", "trap", "fret", "trip", "trie", "frat", "fril"};
				//	HashSet<String> dict = setupDictionary(words);
					//HashSet<String> set = setupDictionary(words) ;
					
					Transform ts = new Transform("tree", "flat", words) ;
					
					LinkedList<String> list = ts.getWordsList() ;
					for (String word : list) {
						System.out.println(word);
					}
				} 

}




