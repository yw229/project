package LinkedIn;

public class parenth
{
    public int count ;
    public char str[] ;
    public int l ; // l total number 
    public int r ; // right number 
    public parenth (int l, int r, char[] str , int count) 
    {
        this.l = l ;
        this.r = r ;
        this.count = count ;
        this.str = str ;
    }
    public void printP(int l, int r , char[] str, int count)
    {
        if(l<0 || r < 0 ) return ; 
        if(l== 0 && r == 0)
        {
            System.out.println(str) ;
        }
        if(l>0) // left parent ,as long as we haven't used up all the left parentheses ,we can also insert left paren 
        {
            str[count] = '(' ;
            printP(l-1,r,str,count + 1);
        }
        if(r > l) // right parentheses locations must larger than left one 
        {
            str[count] = ')' ;
            printP(l,r-1,str, count+1) ;
        }
    }
    public void printP()
    {
        
        printP(this.l,this.r,this.str,this.count) ;
    }
    public static void main(String[] args)
    {    
        int count = 3 ;
        int l = count ;
        int r = count ;
        char[] str = new char[count*2] ;
        parenth p = new parenth(l,r,str,0) ;
        p.printP() ;
    }
}
