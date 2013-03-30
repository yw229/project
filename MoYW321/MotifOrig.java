package MoYW321 ;

/* I
 * Build the DP matrix, and fill the matrix based on the formula 
 * 
 * F[i][j] = { if(x[i] !='X' && y[j] !='X')
				F[i-1][j-1] +0 ; if(x.charAt(i) !=y.charAt(j)) 
	   			F[i-1][j-1] + 1 ; if(x.charAt(i) == y.charAt(j) or x.char(i) or y.char(j) value is 'A' or 'G' at same time )
	  	 	   else
	  	 	   	 -1  	 	   		
	  	  }
* II 
* Based on the filled matrix, find the F[i][j] >=12 , and its last 15 consecutive diagonal value F[i][j]-F[i-15][j-15] >=12
* and any mid 20 diagonal value from F[i+1][j+1]~F[i+20][j+20] cannot have -1, and the last 15 suffix elements must be the 
* same condition with prefix (F[i+35][j+35] - F[i+20][j+20] >=12)
* During the traversal, must make sure the prefix(15 char),substring(20 char) and suffix(15 char) can not exceeds the matrix edges(M,N)
* 
* III 
* Store motif candidates coordinates in the ArrayList  
**/

import java.io.IOException;
import java.util.ArrayList;

public class MotifOrig {
	public String x ;
	public String y ;
	public ArrayList<Node> result ;

	private int[][] opt ;

	public MotifOrig(String x, String y)
	{
		this.x  = x ;
		this.y  = y ;
		result = new ArrayList<Node>() ;
		
	}

	public void find()
	{
		int M = x.length();
		int N = y.length() ;
		 opt = new int[M+1][N+1] ; // fill matrix 
		
	for(int i = 0 ; i < M ; i++ )
		for(int j = 0 ; j < N ; j++)
			{
			if(x.charAt(i) !='X' && y.charAt(j) !='X' )
			{								
					if((x.charAt(i) =='G' &&y.charAt(j)=='A') || (x.charAt(i) =='A' &&y.charAt(j)=='G') )
					{
						if(opt[i][j] !=-1 ) // not on the exon edge 
							opt[i+1][j+1] = opt[i][j] + 1 ;  // A and G regarded as identical 
						else 
							opt[i+1][j+1] =1 ; // reset current as 1, A and G regarded as identical
					//System.out.println(" A G " + i + j + opt[i+1][j+1]) ;
					}
					else
						if(x.charAt(i) == y.charAt(j) )
						{
							if(opt[i][j] !=-1 ) //not on the exon edge nor Concatenated Strings edge
								opt[i+1][j+1] = opt[i][j] + 1 ; // current char are the same 
							else
								opt[i+1][j+1] = 1 ; // reset current value to 1
						}
						else // x.char(i) != y.charAt(j)  not identical 
						{
							if(opt[i][j] !=-1) // not on the exon edge nor Concatenated Strings edge 
								opt[i+1][j+1] = opt[i][j] + 0 ;// set current value same with it's diagnal  
							else
								opt[i+1][j+1] = 0 ; // reset current value to 0 
						}
				}
			else		
					if(x.charAt(i) == 'X' || y.charAt(j) =='X')
					{
						opt[i+1][j+1] =-1 ; // this is edge mark for the exons 
					}
			
			}
		}
			
	public void print()
	{
		int M = x.length();
		int N = y.length() ;
		System.out.printf("%1$c ", ' ');
		for(int j = 0; j < N+1 ; j++)
		{
			System.out.printf("%1$c ", (j == 0) ? ' ' : y.charAt(j - 1));
		}
		System.out.print("\n") ;
		for(int i = 0 ; i < M+1 ;i++)
		{
			System.out.printf("%1$c ", (i == 0) ? ' ' : x.charAt(i - 1));
			for(int j =0 ; j < N +1 ; j ++)	
			{
				System.out.printf("%1$d " , opt[i][j]) ;
			}
			System.out.printf("\n");
		}
	
	}
	
	//get All Motif Candidates coordinates in matrix 
	public ArrayList<Node> getCandi()
	{
		int M = x.length();
		int N = y.length() ;
	//	String subx = new String() ;
	//	String suby = new String() ;
		
		for(int i = 0 ; i < M+1 ;i++)
			for(int j = 0 ; j < N+1; j ++)
			{
				if( opt[i][j] >= 12) // only when the matrix value larger than 12(80% match), there is possible prefix match
				{    /*
				 		* current index must greater or equal to 15
				 		* validate prefix : difference of current matrix value between value at one before potential prefix start must greater or equal to 12 
				 		* middle substring(20 elements can be ignored only when none of those value in[0,-1]) 
				 		* validate suffix : difference of the potential last one between value at one before potential suffix start must greater or equal to 12
					 */
					if( i-15>=0&&j-15>=0  ) //check prefix 
					{ 
						int prei = i , prej = j  ;
						
						while( prei >= i-14  && prej >= j-14)
						{
							if( opt[ prei][prej] !=-1  ) // back track 15 elements check no XXX in the prefix 
							{
								prei -- ;
								prej -- ;
							}
							else break ;
						}
					//	System.out.println("prej" + prej + " " + (j-15)) ;
						if( prei == i-15 && prej == j-15 )
						{ 
							if (opt[i][j] - opt[i-15][j-15] >=12)
							{
								if(i+20<=M && j+ 20 <=N) // mid substring end doesn't exceed the matrix edge 
								{
									int midi = i+1 , midj=j+1 ;
									while(midi <= i + 20 && midj <=j+20)
									{
										if(opt[midi][midj] !=-1 && opt[midi][midj] !=0) // mid 20 char don't contains XXX 
										{
											midi++ ; 
											midj++ ; 
										}
										else
											break ;
									}
									if(midi == i+21 && midj ==j+21) // pass the validation 
									{
										if( (i+ (50-15))<=M && (  j + (50-15) )<=N ) // suffix edge check,don't exceed the matrix edge
										{ 
											int suffixi = i+21 , suffixj = j +21 ;
											while(suffixi <= i+34 && suffixj <= j+34)
											{
												if(opt[suffixi][suffixj] !=-1 )
												{
													suffixi ++ ;
													suffixj ++ ;
												}
												else break ;												
											}
										//	System.out.println("sj" + suffixi + " " + (i+35)) ;
										if(suffixi  == i+35 && suffixj  ==j+35)
										{
											if( opt[i+ (50-15)][j+(50-15) ]-opt[i+20][j+20] >=12  ) // check suffix 15 element difference 
											{
												int prefixStartX = i-14 ;
												//	suffixEndX = prefixStartX-1 + 50  ;
										
												int prefixStartY = j-14 ;
												//suffixEndY = prefixStartY-1 + 50  ;
												//	subx = x.substring(prefixStartX -1 , suffixEndX) ;
												//	suby = y.substring(prefixStartY -1,suffixEndY) ;
												result.add(new Node(prefixStartX ,prefixStartY ) );											
											}
										}
										}
									}
								}
							}
						}
					}
				}
			}
		
		return result ;
	}
	
	/*
	public static void main(String[] args) throws IOException
	{ /*
		String s2 = "ATGGCTGGGTTAGCAGGCCCCGCGAAGCCGAGTCTGGCATTGAACCCGCA" +
		"GGAAGACTCGCAGTTTGAGAAGGCTCTGACGCAGATCCAAGGGCGCACGAAGAAGXXXCCTCAGCAAAAGAAG" +
		"AAAGAAAAACTTAACCGTGGAGTAGTTTATCTGGGCCACCTACCTTCAACACTTTCTGAAAGCCACATCTATAACTACTGTGCCCAGTTTGGCGACATTAGCAGATTCAGATTGTCTAGAAGTAAGCGGXXXACTGGAAACAGCAAAGGCTATGCCTTTGTGGAGTTTGAGTCTGAGGATGTTGCCAAGATAGTTGCAGAAACAATGGATAACTACCTTTTTGGTGAAAGACTTCTATCA" +
		"XXXAAATTTATGCCACGAAAAAAAGTCCATAAAGACCTTTTCAGCCAGAGGAATGCTCTGTTTCACCGGCCATCATTCCCAGCAGTGAAGCGCTATAATCGGAAACGAGGACATTTGCAGATGTTGAAGATGGAGTATAGGTTTAAGAAGAAGGAAAAATTACTCCGGAAGAAACTAGCTGCAAAGGGGATTGATTATAGTTTTCCCTCACTGXXX" +
		"GTCCTACCTAAGCCAAAGAACATTGCTGTCGCTCATAGAGATTCTGAGGGTAACCAGXXXGTTTTACCTGACCAAAAAGAAGGGCTTTCGGGTGAACCTAGGAGAAAGGAGAAGATGATGAAAGAGGACATTTCCAATAACATTCCTAAGAAGCGTAAGAGGTCTAGGAGGAAGAAGTCGTCTGTGGACAGCCAGXXX" +
		"GGTCCCACACCAGTTTGTACACCGACGTTTTTGGAGAGGCGAAAATCGCAGGTGATGGAAGTCGGTGGCGATAAAGATGATGAAATCATTCTCAAGCTGCCTGTGCC" +
		"CCCAGTGAAAGAAGACACACAAAAGACCCCAACATCTGCAAGCCCTGGGGGAAAAAGACCGAGAAAAAGGAAGAGCAAGCAGTGA" ;


 	String s3 = "TCAGTACGGGCGATTGGCGTCTTTGGGCCGCT" +
				"TCAGCTGCACCTTGAGCCTCTTCATGCCTATCTGGAAGCCG" +
				"TTCATGGCCTGGATGGCGGTCTGTGCGCTGGCCGGGTTGTCGAAGC" +
				"TCACGAAXXXGAAAGGGAGGAACATCTGCATCAGCTCAGCGTCCCCAAACTC" +
				"CTGGGGCAGATGGTAGATGAGCAGGTTACAGCCCTCGGGXXXTTCTCTCTGTTGCTGGGGAATCATT" +
				"GGCGGTGGCTGAGGAAAGGCCTGGCTAATCTGACCATAGGCAGCAGGGTAGGCXXXTCCTGCATACTGCTGCACTC" +
				"CAGCGTAGGCCTGCTGCAGGGGGTCGGCTGCGGTGGGGCTCTGXXXTGGGTAGGGGTGAATGCCATTGGCAAACACAGCTTCCG" +
				"CAGCAGGCTGCCCATTGGCCTGAGGGGGGAGGCCCGTGAAGCCGTTCACCCCAATGG" +
				"GGGATGGGATGCTAGGCACAGCTGGTGCAGTGATGCCTGGAGGGGTGCTGCCXXXTGAGGTTGG" +
				"GGTCATAGGTGCGGCTGCCAGGCCATTCATGTTGAGGGCCGCCATCTGCTGCATTTGGGCGGCAGCGA" +
				"AGGCAGCCATGGGATTCAGGTAGCCTCCTTGCGCGACCGATGCCATGAGGGCTGCTTGCTGCTGCATCA" +
				"GXXXTGCCTGAGCATAGGCGCCATAGGCTCCGAAGGGGATGGCCATGGGGTTGAACATGCCCATCTGGCCAGCCATCT" +
				"GCTGCATCCGTCGCATTGTGCGCTCCTTGTCAGTGTCTGCAAACTTGACCACCAGGCTGGAGGAGGCTCCXXXAGGCATGGTCTG" +
				"GCTGCCATGTAGAGCGTTGATGGCGGCTTGTGCCTCGGCATGGGAGGAGTACTTCACAAAGGCGCAXXXCTTGCTGTTGCCGTCCGGCCCGC" +
				"GCAGGATAGTGCACTCCTCGATGTTCCCGAAGGCCTCGAAGAGGCGGCGCACGTCGTCCTCAGATTGTTGCTTGTTGAGCATACCCACGAAGAGTTT" +
				"TCTXXXTGAAGGGGGCTGGCGCAGGCAGCTACTACCTCCTCGGCTCTCGCTGTCCGCAGGCTTCACCTGGATCGGCCGGTTCATXXXCCCGGGCAGGG" +
				"TCTTCTGCTCGTGCAGCGCGCTCTGGGCCTTCAGCGCTGACTCACGCTCGCAGTAGGTGAGGAAAGCGCAXXXTTTGTGCATGCCTGTGAACCTG" +
				"TCCTTCAGAACCGTAAGCTCGTAGATCTTGCCGAACTCCTCGAAGAGGGGCTTGAGGTCCTTCTCATCCAGGTTTCGGGGGATCTGCCCAATGAACAGCTTGATGGCATCGTGGTCCTTCATGGGAATGGTCGACGGGTTCCCCGGGCTGTGGCTTAATCCGTTCATATGCCCGGCGCTGCCCGGGCTGCTGCCTAGCCCGTTGGTACTGAGGCTCGCGTGTCAGCCTGTCCGTTTGCTAACGTGGCCATCTTTATATACAT" ;
 	String s1 = "TTACCTGCGCAAGCTTCTCTGAGCTTCCTTCAGTAAACTATCAAAATCCAGAGAGTCATACTTGCTCTTGGTGGAAGCAGGGTCAAAATCGTCTGAGTTGGTXXXTAGGTCTGCATAGTTAGACTTGAAAAATTGCTTCCGTCCACAAAAGTACAGCTCGAAGTCAGTTTCGTTCGACCTGCGTAAAGTATATCCATTCTCAAGAGCAGCGAAAGCGTCACAGGTGTAACGGTAGGTGATACCGCTGTCXXXATCATCCCGCAGATTTACGGTGCATTCCTCAATTTCACCAAAAACTTCAAAGCGGTCTCTCAATTCTGTCCGCGTTGTGTCAGGTCTGATTTTACCAACAATCACACGGCGCTCTTCXXXAATTGCTTTCTGCTTCTGCCTCTCTCTCTGTTTGGCCCTTTCAGACTCCCGCTTCTCGTGCTCTTTGCGGTATTCATCCCTCTTGAGCCTTTCGTGCTCATAGGCTTCATAGCTGTCGTAXXXGGGCCTACGGCTGTAGGGTGACCTTGAACGTGATCTCACATACAAGGGAGAATTGCGGTGTGTGCGGTGTCTGTAGTGGCTTGATTCATAGTAGTAACAGGAXXXTGAAGAGGATCTACTGCCTGGGGACCTTGATCTTGACCTGGAATATGGTGATCGGGAACACGACCTGTGTCGAGAAAAGGATCTTGAACGAGAGCGCATCCTTTGGGGTCTTTGAGAAAATAAGGATTTCGGTGGTGACACTGAGTCTCGACACGGAGAGTTAAAGGAAGAGCAAGAAGGCGACACATCGAACAATGAATAGGGCTGCGTGCCATCCCAAGGGTAGCTCAGTTTATCACTTTCATCTTCACTGTCATCAAATAGGCCATCCATGGCTAGTCCTGAATTTATAAACACAGGTAGTTTGGAGAATTGTTCATTACTGAAGTCGCCATCCCTTAGTTCACTGGTCTTGTCTGATTTGTCGTCAAACACAGCTTGACAGGGATGACCGAAGTGCTTGTTCAGCTCCGCTCGGATTTCCTGGTCTTGGAGCTGTTTTCTGGTGCTGCAAGGAGAGACCTGCTTGCTGGCCTCCAAAGTCTCTCTCAGGTAGCACTGGCCTGAATCTGTGGAAGAACAGATGTGCCCCTGCCAGTCACAGGAGGCATCTTTGAAGTCTAGTTGTCTAGAGTCTTGGAGCTCCTGTGATATGTTAATGAGTATATCCGTTTTGGAATTGAGTGACTGACAGTAGTCATGGTCACCAAACAGCCGGAGACTGGGCCGTTTAGTCTTCCTTTCCTCGTGTCCTCGGCTGAGCCCTGAGGACTTGCTGAGTTGTGCGTACAACTCAGATTGCTCGGGCCCTTTCTTGGTGGAGTGGCTGCCTTGGGTACCAGAACACTCACTGTACCGGGCCCTCTTGGTTGGCGGTGGCACCACGGTCTTGCAAGAGGGCTTCAGCTTTGGCGAAGCCTTGAAAGGGTTATCTTGGTTGGCTTTATGAGGAGGAGTTGTGGGAGGAGTTAGXXXTGCAGTTCCAGAGAGTTCCACACTTAAGGTTCGCTCAATAGTCTTGTTCTCAAATGGGGAACCCTTGGGGTCXXXTGGTGACTCTGGGGTCAGAGGAAGAGATAAAGTTGTTGGTTTXXXTTGAGCATGTTGCGACTGCGGTTGTGTATGGGACTTCTTTTTCGAAGCACATTTGTCTCTGCTGCTGTTCCTGTTTTCTGTGGGTTTGGTGTGAGGAGGGTCATCGTTTGTGGTCAGATACTTGAGAAGCTCTGAGCAGGGACGTCTTTGTGGCTTTTGCTGTTGACAAATGCTCTTCGCTTTATTGCTCCATGAATTCTCGGTXXXCTTAACAATGGCAGGGTTTGTTCTGATCCTGTGGGTGTGGTTTGCTGCATGGTTCTGAGTGCTAAGACCGCTGCATTCATTGTAGCTGAGCTGAGTGTTGGCTGGTGCCAGTAAGAGCTTCTTAAGXXXTAGAGACGGCTCTTCTGCCTCCTGAGGGGGAGGGGTGCCGTCAGGCATGGAGGAAGGACTGGCCTCGTTGTCAGTGGTCACGGCTCCATCTGTCAGTGCATCAAATGAGGGCAATCCGTCTTCATCCACGGGGAGACTGTCCAGTGTCTCTGTGAGAACCGCTAGCAAGTTTGCCTCATTCTCTTCATCTATCTTXXXCTCAAATATGTTCGCAGGCTCATTGTTGTACTGGTTGGATATGATTTCCGATTGGTCGCTACACCACTTCAATCCACCCAGAAAGCTGTCTGTATCCAAGTCATTCACATCAAGTTCAGAAAGGTCAAGTTCAGGAAGATCTGGGCAAAGAGGCTGGTCCTCACCAACCAGAGCAGCACAXXXCTCTATGTCACTCCATACAGAGTCTTGGCTGCACATGTCCCAAGCCAT" ;
*/
	/*
		String s1 = "GCTCCAAGCCCTTGAATTGCAATAATTATAAAAGATAATATCCCGTATTTGGTTTGCCA" ;
		String s2 = "ATGCCCTTGAATTGCMMMMMMMMMMMMMMMMMMMMTTTCGTATTTGGTTT" ;

		//p.readFile("/home/wang/mouse_seq") ;  // read folder 
		MotifOrig m = new MotifOrig(s1,s2) ;
		m.find() ;
		
		m.print() ;
		m.find() ;
		
		for(Node n : m.getCandi() ) 
		{
			System.out.print( n.getStartY() + " " + s2.substring(n.getStartY()-1, n.getStartY()-1+50) );
			System.out.println( n.getStartX() +" "+ s1.substring(n.getStartX()-1, n.getStartX()-1+50) );
		}
		System.out.println("\n next is ") ;
		}
	*/
}
		
	
