package uk.co.ipponsolutions.surfapp.domain;

import uk.co.ipponsolutions.surfapp.units.MetresPerSecond;

public class WeatherParameters {
	
	private MetresPerSecond windGust;
	private String windDirection;
	private MetresPerSecond windSpeed;
	
	public MetresPerSecond getWindGust() {
		return windGust;
	}
	public void setWindGust(MetresPerSecond windGust) {
		this.windGust = windGust;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public MetresPerSecond getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(MetresPerSecond windSpeed) {
		this.windSpeed = windSpeed;
	}
	
}
