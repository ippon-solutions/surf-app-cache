package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ForecastTimestep {
	
	private Date timePeriod;
	private WeatherParameters weatherParameters;
	
	public Date getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(Date timePeriod) {
		this.timePeriod = timePeriod;
	}
	public WeatherParameters getWeatherParameters() {
		return weatherParameters;
	}
	public void setWeatherParameters(WeatherParameters weatherParameters) {
		this.weatherParameters = weatherParameters;
	}
	
}
