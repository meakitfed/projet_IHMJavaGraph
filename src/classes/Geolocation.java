/**
 * 
 */
package classes;

/**
 * @author nathan.bonnard
 *
 */
public class Geolocation 
{
	private float latitude;
	private float longitude;
	
	public Geolocation(float longitude,float latitude) 
	{
		this.latitude=latitude;
		this.longitude=longitude;
		// TODO Auto-generated constructor stub
	}

	public float getLatitude() 
	{
		return latitude;
	}

	public void setLatitude(float latitude) 
	{
		this.latitude = latitude;
	}

	public float getLongitude()
	{
		return longitude;
	}

	public void setLongitude(float longitude) 
	{
		this.longitude = longitude;
	}
	

}
