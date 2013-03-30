package MoYW321;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap ;
import java.util.HashSet;

public class Matching {
	/*
	 * HashMap to store, when two file compares, the file index at p (outer loop;(0,143)), the file index at q( inner loop, 0-p),
	 * compute file p and q potential motif StartY(from file p) as Key, and  <StartX, fileIndex of the motif occurs( from file q) >-create class Item as value, 
	 * for each file p we can generate a hashmap to  store all it's compared files index and startX; 
	 * In order to keep track all file p compared hashmap, need to use ArrayList store each hashmap according to p's order.  
	 */
		
	public ArrayList<String> ValidMotif ; 
	public Matching()
	{			
		ValidMotif = new ArrayList<String>() ;
	}
	/*
	 * Calculate the  motif of sequences read from File ;
	 * Using the Motif algorithms to find each comparison list 
	 * */
	public ArrayList< HashMap< Integer, ArrayList<Item>> >  MotifCandidateMap(ArrayList<String> list)
	{
		ArrayList< HashMap< Integer, ArrayList<Item>> > maps = new ArrayList<HashMap< Integer, ArrayList<Item>> > () ;
		for(int p = 0 ; p < list.size() ; p ++)	 // outer loop
		{
			HashMap< Integer , ArrayList<Item>> FileIndexMap = new  HashMap< Integer, ArrayList<Item>>() ; 	
			for( int q = 0 ; q < p  ; q ++)
			{	
				
				if( list.get(p) != list.get(q) && p!=q)
				{
					MotifOrig mo = new MotifOrig(list.get(q) ,list.get(p)) ;
					mo.find() ; // fill matrix 
					ArrayList<Node> candi = mo.getCandi()  ;
					//System.out.println(candi.size()) ;
					if(candi.size() >=1)
					{
						for(Node n : candi)
						{					
							if( !FileIndexMap.containsKey(n.getStartY()))
								{
									ArrayList<Item> FileIndexList = new ArrayList<Item>() ;
									FileIndexList.add( new Item(n.getStartX(),q)) ; // candidate y->(x, fileIndexq) 
									FileIndexMap.put( n.getStartY(), FileIndexList) ;
								} 
							else
								{
									ArrayList<Item> FileIndexList = FileIndexMap.get(n.getStartY()) ;
									FileIndexList.add( new Item(n.getStartX() , q)) ; // put the node 
								}
						}
					}	
				}
		
			}
			maps.add( p , FileIndexMap) ; // store the two compared files
		}
		return maps ;	
	}
	/*
	 *  for each file sequence compared hashmap, need to find it's StartY coordinate value occurs more than 19 times 
	 *  in the compared unique file index
	 */
	public ArrayList<String> validMotif(ArrayList<String> list) 
	{
		ArrayList< HashMap< Integer, ArrayList<Item>> > maps = MotifCandidateMap(list) ;
		for(int p = 0 ; p < maps.size() ; p ++)
		{
			HashMap<Integer, ArrayList<Item>> fmap = maps.get(p) ;
			for(Integer StartY : fmap.keySet())
			{
				HashSet<Integer> FileIndexSet = new HashSet<Integer>() ;
				ArrayList<Item> FileIndexList = fmap.get(StartY) ;
				for(Item it : FileIndexList)
				{
					if(!FileIndexSet.contains(it.getFileIndex())) 
						FileIndexSet.add( it.getFileIndex() ) ;	
				}
					
				if(FileIndexSet.size() >=19)
				{
					String seq = list.get(p) ;
					ValidMotif.add( seq.substring(StartY-1 , StartY -1 +50)) ;
				}
			
			}
		}
		return ValidMotif ;
	}	
}
