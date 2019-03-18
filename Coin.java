public class Coin {  // parent class
	
		// constants to be used in program
	protected final int HEADS = 0;
	protected final int TAILS = 1;
	
		// declare instance field
		// to be used in program
		// (i.e. the face of the coin)
	private int face;
	
	public Coin()
	{
		flip();
	}   // end constructor
	
	   // flip the coin by randomly choosing a value
	public void flip()
	{
		face = (int) (Math.random() * 2);
	}   // end method flip
	
	public int getFace()
	{
		return face;  // to be modified
	}   // end method getFace
	
	public String toString()  // parent's toString method
	{
		String faceName = "";
		
		if (getFace() == HEADS)
		{
			faceName = "(heads)";
		}
		else
		{
			faceName = "(tails)";
		}
		
		return faceName;
	}   // end method toString
}   // end class Coin

