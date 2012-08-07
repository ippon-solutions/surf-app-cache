package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location {
	
	
	private String locationId;
	private String locationName;
	private double latitude;
	private double longitude;
	private ForecastTimestep[] forecastTimesteps;
	private TideTable[] tideTables;
	private WaveData[] waveData;
	private Map<String, String> eTags;
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public ForecastTimestep[] getForecastTimesteps() {
		return forecastTimesteps;
	}
	public void setForecastTimesteps(ForecastTimestep[] forecastTimesteps) {
		this.forecastTimesteps = forecastTimesteps;
	}
	
	public TideTable[] getTideTables() {
		return tideTables;
	}
	public void setTideTables(TideTable[] tideTables) {
		this.tideTables = tideTables;
	}
	
	public WaveData[] getWaveData() {
		return waveData;
	}
	public void setWaveData(WaveData[] waveData) {
		this.waveData = waveData;
	}
	
	public void setETagMapItem(String id, String value) {
		if (eTags == null) {
			eTags = new HashMap<String, String>(2);
		}
		eTags.put(id, value);
	}
	
	public String geteTagMapItem(String id) {
		String value = null;
		if (eTags != null) {
			value = eTags.get(id);
		}
		return value;
	}
	
	public ForecastTimestep getWeatherDataForTimeStep(Date date) {
		ForecastTimestep forecastTimestep = null;
		for (ForecastTimestep timestep : getForecastTimesteps()) {
			if (date.compareTo(timestep.getTimePeriod()) == 0) {
				forecastTimestep = timestep;
				break;
			}
		}
		return forecastTimestep;
	}

	public TideTable getTidalDataForTimeStep(Date date) {
		TideTable tidetable = null;
		for (TideTable tidetableLoop : getTideTables()) {
			if (date.compareTo(tidetableLoop.getDate()) == 0) {
				tidetable = tidetableLoop;
				break;
			}
		}
		return tidetable;
	}

	public WaveData getWaveDataForTimeStep(Date date) {
		WaveData waveData = null;
		for (WaveData waveDataLoop : getWaveData()) {
			if (date.compareTo(waveDataLoop.getDate()) == 0) {
				waveData = waveDataLoop;
				break;
			}
		}
		return waveData;
	}
}
