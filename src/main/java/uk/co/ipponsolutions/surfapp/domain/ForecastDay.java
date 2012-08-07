package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;

public class ForecastDay {
	
	private ForecastTimestep[] forecastTimesteps;
	private Date forecastDate;

	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	public ForecastTimestep[] getForecastTimesteps() {
		return forecastTimesteps;
	}

	public void setForecastTimesteps(ForecastTimestep[] forecastTimesteps) {
		this.forecastTimesteps = forecastTimesteps;
	}
	
}
