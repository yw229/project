package LinkedIn;

public class bitCompare
{
    public int[] array ;
    public bitCompare(int[] array)
    {
        this.array = array ;
    }
    public int findOdd()
    {
        int compare = 0 ;
        for(int a : array)
        {
           compare = compare^a ;  // ^ if even, the value would be 0, else the value would be the odd number
        }
        return compare ;
    }
    public static void main(String[] args)
    {
        int[] a = {2,2,2,2,3,3,4,5,5,6,6};
        bitCompare bt = new bitCompare(a);
        System.out.print( bt.findOdd() );
    }
}