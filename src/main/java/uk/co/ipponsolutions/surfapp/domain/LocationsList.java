package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class facilitates easy fetching from locationsList configuration object.
 * 
 */
@XmlRootElement
public class LocationsList implements Serializable {

	private static final long serialVersionUID = 1939279781365842374L;
	private List<Location> locationsList;
	
	/**
	 * Check whether the location exits in configured locationsList.
	 * @param locationName
	 * @return true / false
	 */
	public boolean isLocationExists(String locationName) {
		Location location = getLocationDetails(locationName);
		if(location!=null){
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Gets Location object on the basis of location name.
	 * @param locationName
	 * @return Location object
	 */
	public Location getLocationDetails(String locationName){
		Location location = null;
		for(Location sLocation : locationsList){
			if(locationName.equals(sLocation.getLocationName())){
				location = sLocation;
				break;
			}
		}
		return location;
	}

	public void getLocationDataForTimePeriod(String locationName, Date date) {
		Location location = getLocationDetails(locationName);
		ForecastTimestep forecastTimestep = location.getWeatherDataForTimeStep(date);
		WaveData waveData = location.getWaveDataForTimeStep(date);
	}
	
	public List<Location> getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(List<Location> locationsList) {
		this.locationsList = locationsList;
	}
}
