package uk.co.ipponsolutions.surfapp.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import uk.co.ipponsolutions.common.HelperMethods;
import uk.co.ipponsolutions.surfapp.domain.ForecastTimestep;
import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationSummary;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.TideTable;
import uk.co.ipponsolutions.surfapp.domain.WaveData;

@Path("Forecasts")
public class ForecastLocationsUrlResolver {

	private LocationsList locationsList;
	
	@GET  
    @Path("{locationName}/Summary")  
    @Produces("application/json")  
    public Response getLocationDetails(@PathParam("locationName") String locationName) {  
		Location location = locationsList.getLocationDetails(locationName);
		Date date = HelperMethods.getNextDatePeriodAfterNow();
		WaveData waveData = location.getWaveDataForTimeStep(date);
		ForecastTimestep forecastTimestep = location.getWeatherDataForTimeStep(date);
		TideTable tideTable = location.getTidalDataForTimeStep(date);

		LocationSummary locationSummary = new LocationSummary();
		locationSummary.setLocation(location.getLocationName());
		locationSummary.setForecastTimestep(forecastTimestep);
		locationSummary.setTideTable(tideTable);
		locationSummary.setWaveData(waveData);
		
		return Response.status(200).entity(locationSummary).build();
    }
		
	@GET
	@Path("{locationName}")
	@Produces("application/json")
	public Location getLocation(@PathParam("locationName") String locationName) {
		Location location = locationsList.getLocationDetails(locationName);
		return location;
	}
	
	public LocationsList getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(LocationsList locationsList) {
		this.locationsList = locationsList;
	}
}
