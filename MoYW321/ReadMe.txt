1.Algorithm to solve this problem : 
I Using Dynamic Programming to find the potential Matching Motif; 
Build the 2D matrix to find the potential candidates, basic DP formula is : 
F[i][j] = { 
			F[i-1][j-1] +0 ; if(x.charAt(i) !=y.charAt(j)) 
	   		F[i-1][j-1] + 1 ; if(x.charAt(i) == y.charAt(j) or x.char(i) or y.char(j) value is 'A' or 'G' at same time )
	  	  }
Since each motif is only allowed to occur within exon(XXX) in the sequences, so we need to modify above formula when filling the matrix,
if 'XXX' occurs in any string x or y ,set current matrix value F[i][j] = -1 . Thus 
the above formula is valid only when x.charAt(i)!='X' and y.charAt(j) !='X' and its last diagonal value F[i-1][j-1] is not -1(no exons);
When x.charAt(i)!='X' and y.charAt(j) !='X' but if it's last F[i-1][j-1] value is -1(exons), we have to reset currentF[i][j] to 0 or 1 (depends on whether current x.charAt(i) == y.charAt(j) or 'A' and 'G' )  ; 
if either of x.charAt(i) or y.charAt(j) is 'X', we have to reset the current value F[i][j] to -1 ;


II Find valid motif 
After we fill up the matrix, we are able to find the motif candidates. The advantage of using DP and matrix is that
based on the requirements (The first 15 and last 15 characters of the motif must match at least
80% of a substring's ends to match for that string.) So first we need to find the matrix value F[i][j] >=12 (15*80%), and 
traverse back all its last diagonal,for all diagonal values between (F[i-14][j-14]~F[i][j] ) don't contain -1,
and F[i][j] - F[i-15][j-15]>=12; 
For the mid 20 elements, its correspondent value in matrix need to verify no -1 exists, and after passing the mid 20 elements
validation, continue to traverse the last 15 elements, no XXX(-1,-1,-1) in the matrix, and F[i+35][j+35] -F[i+20][j+20] >=12
During the traversal, must make sure the prefix(15 char),substring(20 char) and suffix(15 char) can not exceeds the matrix edges(M,N)


III Scan all sequence files and find the valid motif 
Find valid motif among 20 sequence file, use Brute Force to compare one with the remaining 142 files ;
HashMap to store, when two file compares, the file index at p (outer loop;(0,143)), the file index at q( inner loop, 0-p),
compute file p and q motif StartY(from file p) as Key, and  <StartX, fileIndex of the motif StartX occurs( from file q) >-create class Item as value, 
for each file p we can generate a hashmap to  store all it's compared files index and startX; 
In order to keep track all file p compared hashmap, need to use ArrayList store each hashmap according to p's order.  

IV To find the valid motif that must match at least 20 of the sequences
for each file sequence p compared hashmap, need to find it's StartY coordinate value
occurs more than 19 times in the compared unique file index, using hashset to store it's uniquely compared file sequence,
for any file p, hashset size is greater than 19, and get the file p's start Y, find the substring(StartY-1,startY+49) , that
is the one valid motif 

2. Run the project 




 

