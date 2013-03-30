package MoYW321;
/*
 * Node class is to store each candidate Motif start point's coordinates(x,y) value int the matrix 
 * */
public class Node {
	private int StartX ;
	private int StartY ;
	public Node(int x, int y)
	{
		this.StartX  = x ;
		this.StartY = y ;
	}
	public int getStartX()
	{
		return StartX ;
	}
	public int getStartY()
	{
		return StartY ;
	}
}
