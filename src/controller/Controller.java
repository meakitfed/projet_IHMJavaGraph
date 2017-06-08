package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import classes.Airport;
import classes.Flight;
import classes.Geolocation;
import classes.Plane;

public class Controller 
{	
	private ArrayList<Flight> flights=new ArrayList<Flight>();
	private ArrayList<Airport> airports=new ArrayList<Airport>();
	
	public Controller() 
	{
		getAirportData("src/Data/airports.dat");
		getFlightData("src/Data/flights.dat"); 
		getRealTimeFlightsData("src/Data/realtime_flights.dat");
	}
	
	public void getAirportData(String path) 
	{
		try 
		{
			FileReader file=new FileReader(path);
			BufferedReader bufRead = new BufferedReader(file);
				
			String line= bufRead.readLine();
			
			while(line != null)
			{
				String[] array = line.split("///");
				float lon= Float.parseFloat(array[3]);
				float lat = Float.parseFloat(array[4]);
				airports.add(new Airport(new Geolocation(lon,lat),array[0],array[1],array[2]));				
				line = bufRead.readLine();
			}
			
			bufRead.close();
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param path
	 */
	public void getRealTimeFlightsData(String path) 
	{

		try 
		{
			FileReader file=new FileReader(path);
			BufferedReader bufRead = new BufferedReader(file);
				
			String line= bufRead.readLine();
			
			while(line != null)
			{
				String[] array = line.split("///");
				
				
				try
				{
					BigInteger realTime = BigInteger.parseBigInteger(array[0]);
					float lon= Float.parseFloat(array[2]);
					float lat = Float.parseFloat(array[3]);
					float height = Float.parseFloat(array[4]);
					float speedX = Float.parseFloat(array[5]);
					float direction = Float.parseFloat(array[6]);
					float horodatageLocation = Float.parseFloat(array[7]);
					float horodatageSpeed = Float.parseFloat(array[8]);
					float speedY = Float.parseFloat(array[9]);
					boolean grounded = Boolean.parseBoolean(array[10]);
					Plane tempPlane=new Plane(new Geolocation(lon,lat),height,speedX,
							direction,horodatageLocation,horodatageSpeed,speedY,grounded);
					for(Flight f: flights)
					{
						if(f.getId().equals(array[1].trim()))
						{
							f.setRealTime(array[0]);
							f.setPlane(tempPlane);
						}
					}
				}
				catch(NumberFormatException e)
				{


				}
				
				line = bufRead.readLine();
			}
			
			bufRead.close();
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param path
	 */
	public void getFlightData(String path) 
	{
		try 
		{
			FileReader file=new FileReader(path);
			BufferedReader bufRead = new BufferedReader(file);
				
			String line= bufRead.readLine();
			
			while(line != null)
			{
				String[] array = line.split("///");
				Airport temp1=null;
				Airport temp2=null;
				
				for(Airport a : airports)
				{
					if(a.getShortName().equals(array[1])) temp1 = a;
					if(a.getShortName().equals(array[2]) ) temp2 = a;
				}
				flights.add(new Flight(array[0], temp1, temp2, array[3], array[4], new Plane()));	
				line = bufRead.readLine();
			}
			
			bufRead.close();
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
	{
		Controller c =new Controller();
		for(Airport a : c.airports)
		{
			System.out.println(a.toString());
		}

		for(Flight f : c.flights)
		{
			System.out.println(f.toString());
			System.out.println(f.getPlane().toString());
		}
	}
}
