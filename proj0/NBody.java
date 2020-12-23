
public class NBody{
	public static double readRadius(String filePath)
	{
		In in = new In(filePath);	
		in.readDouble();	
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filePath)
	{
		
		In in = new In(filePath);
		int numberOfPlanets = in.readInt();
		Planet[] planets = new Planet[numberOfPlanets];
		double universeRadius = in.readDouble();

		for(int row = 0; row < numberOfPlanets; row++)
		{
			planets[row] =  new Planet(0, 0, 0, 0, 0, null);
			
			planets[row].xxPos = in.readDouble();
			planets[row].yyPos = in.readDouble();
			planets[row].xxVel = in.readDouble();
			planets[row].yyVel = in.readDouble();
			planets[row].mass = in.readDouble();
			planets[row].imgFileName = in.readString();
		}

		return planets;
	}

	public static void main(String[] args)
	{

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] planets = readPlanets(filename);

		double radiusOfUniverse = readRadius(filename);
		String imageFilePath = "images/starfield.jpg";
		StdDraw.setScale(-radiusOfUniverse, radiusOfUniverse);

		StdDraw.enableDoubleBuffering();
		double time = 0.0;

		while(time < T)
		{
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i++)
			{
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int k = 0; k < planets.length; k++)
			{
				planets[k].update(dt, xForces[k], yForces[k]);
			}

			StdDraw.picture(0, 0, imageFilePath);

			for(Planet p : planets)
			{
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radiusOfUniverse);

		for (int i = 0; i < planets.length; i++) 
		{			
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
    		 planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, 
    		 planets[i].mass, planets[i].imgFileName);    						        					      					
		}	

	}


}