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
	
	public Plane(Geolocation geolocation,float height, float speedX, float speedY,
			float direction, float horodatageSpeed, float horodatageGeolocation, boolean grounded) 
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

	/**
	 * @return the model
	 */
	
	
	

}
