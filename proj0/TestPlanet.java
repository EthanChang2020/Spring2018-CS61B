
public class TestPlanet{
	public static void main(String[] args)
	{
		Planet p1 = new Planet(0, 0, 1.0, 2.0, 100, "jupiter.gif");
		Planet p2 = new Planet(2.0, 4.0, 3.0, 5.0, 150, "jupiter.gif");
		System.out.println("the exerted force is : " + p1.calcForceExertedBy(p2));
	}

}