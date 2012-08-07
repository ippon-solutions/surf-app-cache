package uk.co.ipponsolutions.surfapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationInfo;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;

@Path("Locations")
public class LocationsUrlResolver {

	private LocationsList locationsList;
	
	@GET
	@Path("/All")
	@Produces("application/json")
	public List<LocationInfo> getLocations() {
		List<LocationInfo> locations = new ArrayList<LocationInfo>();
		for (Location location : locationsList.getLocationsList()) {
			locations.add(new LocationInfo(location.getLocationName()));
		}
		return locations;
	}
	
	public LocationsList getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(LocationsList locationsList) {
		this.locationsList = locationsList;
	}
}
