import java.lang.*;

public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
		
	}

	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	/*
	public Planet()
	{
		xxPos = 0;
		yyPos = 0;
		xxVel = 0;
		yyVel = 0;
		mass = 0;
		imgFileName = null;
	}
*/
	public double calcDistance(Planet target)
	{
		double distance = (target.xxPos - this.xxPos) * (target.xxPos - this.xxPos) 
		+ (target.yyPos - this.yyPos) * (target.yyPos - this.yyPos);
		return Math.sqrt(distance);
	}

	public double calcForceExertedBy(Planet target)
	{
		double r = this.calcDistance(target);
		double exertedForce = (G * this.mass * target.mass) / (r * r);
		return exertedForce;
	}

	public double calcForceExertedByX(Planet target)
	{
		double exertedForceX = calcForceExertedBy(target) * ((target.xxPos - this.xxPos) / calcDistance(target));
		return exertedForceX;
	}

	public double calcForceExertedByY(Planet target)
	{
		double exertedForceY = calcForceExertedBy(target) * (target.yyPos - this.yyPos) / calcDistance(target);
		return exertedForceY;
	}

	public double calcNetForceExertedByX(Planet[] planets)
	{
		double netForceX = 0;
		for(Planet p : planets)
		{
			if(p.equals(this))
			{
				continue;
			}
			netForceX += this.calcForceExertedByX(p);

		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets)
	{
		double netForceY = 0;
		for(Planet p : planets)
		{
			if(p.equals(this))
			{
				continue;
			}
			netForceY += this.calcForceExertedByY(p);
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY)
	{
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw()
	{
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
	
}