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
	private long t0;
	private long currentTime = 0;
	
	
	/**
	 * 
	 * constructeur du du controller
	 * 
	 * initialise les arraylist d'aeroports et vols
	 */
	public Controller() 
	{
		getAirportData("src/Data/airports.dat");
		getFlightData("src/Data/flights.dat"); 
		getT0("src/Data/realtime_flights.dat");
		updateRealTimeFlightsData("src/Data/realtime_flights.dat");
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
				airports.add(new Airport(new Geolocation(lon,lat,0),array[0],array[1],array[2]));				
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
	
	
	public void getT0(String path)
	{
		try 
		{
			FileReader file=new FileReader(path);
			BufferedReader bufRead = new BufferedReader(file);
				
			String line= bufRead.readLine();
			String[] firstLine = line.split("///");
			
			this.t0 = Long.parseLong(firstLine[0]);
			
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
	public void updateRealTimeFlightsData(String path) 
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
						if(currentTime >= (Long.parseLong(array[0]) - t0) )
						{
							float lon= Float.parseFloat(array[2]);
							float lat = Float.parseFloat(array[3]);
							float height = Float.parseFloat(array[4]);
							float speedX = Float.parseFloat(array[5]);
							float direction = Float.parseFloat(array[6]);
							float horodatageLocation = Float.parseFloat(array[7]);
							float horodatageSpeed = Float.parseFloat(array[8]);
							float speedY = Float.parseFloat(array[9]);
							boolean grounded = Boolean.parseBoolean(array[10]);
							for(Flight f: flights)
							{
								if(f.getId().equals(array[1].trim()))
								{
									f.setRealTime(currentTime - t0);
									f.getPlane().getGeolocation().setHeight(height);
									f.getPlane().getGeolocation().setLatitude(lat);
									f.getPlane().getGeolocation().setLongitude(lon);
									f.getPlane().setSpeedX(speedX);
									f.getPlane().setSpeedY(speedY);
									f.getPlane().setHorodatageGeolocalisation(horodatageLocation);
									f.getPlane().setHorodatageSpeed(horodatageSpeed);
									f.getPlane().setDirection(direction);
									f.getPlane().setGrounded(grounded);
								}
							}
						}
					}
					catch(NumberFormatException e)
					{
						System.err.println("Données erronées");
						
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
	 * Cherche le vol d'id entré en parametre dans l'arrayist flights
	 * 
	 * @param id id du vol recherché
	 * @return le vol recherche ou null si ce vol n'est pas dans la liste
	 */
	public Flight getFlightByID(String id)
	{
		Flight toReturn = null;
		
		for (Flight f : flights)
		{
			if (f.getId().equals(id))
			{
				toReturn = f;
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * returne un tableau de taille 2 contenant le nom du pays de depart et arrivee du vol
	 * @param f le vol
	 * @return tableau de string
	 */
	public String[] printCountriesOfFlight(Flight f)
	{
		String[] toReturn = new String[2];
		
		toReturn[0] = f.getDeparture().getCountry();
		toReturn[1] = f.getArrival().getCountry();
		
		return toReturn;
	}
	
	/**
	 * returne un tableau de taille 2 contenant les noms de la ville de depart et arrivee du vol
	 * @param f le vol
	 * @return tableau de string
	 */
	public String[] printCitiesOfFlight(Flight f)
	{
		String[] toReturn = new String[2];
		
		toReturn[0] = f.getDeparture().getCityName();
		toReturn[1] = f.getArrival().getCityName();
		
		return toReturn;
	}
	
	public void incrementCurrentTime(long nb)
	{
		this.currentTime += nb;
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
		
		System.out.println(c.t0);
		c.incrementCurrentTime(60000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.updateRealTimeFlightsData("src/Data/realtime_flights.dat");
			
		for(Flight f : c.flights)
		{
			System.out.println(f.getPlane().toString());
			
		}
		c.updateRealTimeFlightsData("src/Data/realtime_flights.dat");
		System.out.println("hey");
		for(Flight f : c.flights)
		{
			System.out.println(f.getPlane().toString());
			
		}
	}
}
