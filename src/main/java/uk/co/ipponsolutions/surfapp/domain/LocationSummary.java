package uk.co.ipponsolutions.surfapp.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationSummary {

	private String location;
	private WaveData waveData;
	private ForecastTimestep forecastTimestep;
	private TideTable tideTable;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public WaveData getWaveData() {
		return waveData;
	}
	public void setWaveData(WaveData waveData) {
		this.waveData = waveData;
	}
	public ForecastTimestep getForecastTimestep() {
		return forecastTimestep;
	}
	public void setForecastTimestep(ForecastTimestep forecastTimestep) {
		this.forecastTimestep = forecastTimestep;
	}
	public TideTable getTideTable() {
		return tideTable;
	}
	public void setTideTable(TideTable tideTable) {
		this.tideTable = tideTable;
	}
	
}
