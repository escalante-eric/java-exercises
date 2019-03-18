
public class Dog implements Speaker {

		// declare instance field
	private String name;
	
	public Dog(String inName)
	{
		name = inName;
	}
	
	public void speak()
	{
		System.out.println(name + " says: Woof");
	}
	
	public void announce(String str)
	{
		System.out.println(name + " playfully announces: " + str);
	}
	
}	// end of class Dog
