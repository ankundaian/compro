package lab3.prob4;

public class Condo extends Property{
	//fields
	private int numFloors;
	
	//constructor
	Condo(String street, String city, String state, String zip, int numFloors){
		super(street,city,state,zip);
		this.numFloors = numFloors;
	}
	/*Condo(int numFloors){
		super();
		this.numFloors = numFloors;
	}*/

	@Override
	public double computeRent() {
		
		return 400 * numFloors;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+": "+this.address;
	}
	
}