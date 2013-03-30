package MoYW321;

public class Item {
	/*
	 * This Item class is used to store the All the motif candidates coordinates Y's key value and it's correspondent  
	 * coordinates X with it's occurrence file index 
	 * */
	private int x ; 
	private int FileIndex;
	public Item(int x , int FileIndex)
	{
		this.x = x ;
		this.FileIndex = FileIndex;
	}
	public int getX()
	{
		return x ;
	}
	public int getFileIndex ()
	{
		return FileIndex;
	}
	

}
