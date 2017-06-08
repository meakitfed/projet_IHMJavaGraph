package classes;

public class Plane 
{

	private float height;
	private float speedX;
	private float speedY;
	private float direction;
	private float horodatageSpeed;
	private float horodatageGeolocalisation;
	private Geolocation geolocation;
	private boolean grounded;
	
	public Plane()
	{
		
	}
	public Plane(Geolocation geolocation,float height, float speedX,float direction, 
		  float horodatageGeolocation,float horodatageSpeed,float speedY, boolean grounded) 
	{
		this.height=height;
		this.speedX=speedX;
		this.speedY=speedY;
		this.direction=direction;
		this.horodatageGeolocalisation=horodatageGeolocation;
		this.horodatageSpeed=horodatageSpeed;
		this.grounded=grounded;
		this.geolocation=geolocation;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Plane [height=" + height + ", speedX=" + speedX + ", speedY=" + speedY + ", direction=" + direction
				+ ", horodatageSpeed=" + horodatageSpeed + ", horodatageGeolocalisation=" + horodatageGeolocalisation
				+ ", geolocation=" + geolocation + ", grounded=" + grounded + "]";
	}

	/**
	 * @return the model
	 */
	
	
	

}
