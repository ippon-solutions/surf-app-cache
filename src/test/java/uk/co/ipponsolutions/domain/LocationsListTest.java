package uk.co.ipponsolutions.domain;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.UriDetails;
import uk.co.ipponsolutions.util.test.TestUtility;


public class LocationsListTest {

	static UriDetails uriDetails;
	static LocationsList locationsList = null;
	static Location location = null;
	
	@BeforeClass
	public static void setup() {
		locationsList = (LocationsList)TestUtility.loadSpringManagedObject("locationsList", "appTestContext-locationDetails.xml");
		uriDetails = (UriDetails)TestUtility.loadSpringManagedObject("uriDetails", "appTestContext-locationDetails.xml");
		location = locationsList.getLocationDetails("Bude");
	}

	@Test
	public void testGetLocation() {
		Location location1 = locationsList.getLocationDetails("Bude");
		assertEquals(location1, location);
	}
	
	@Test
	public void testGetLocationName() {
		assertEquals("Bude", location.getLocationName());
	}

	@Test
	public void testGetLocationUrl() {
		assertEquals("/Forecasts/bude-BestForecast.xml", uriDetails.getWeatherUri());
	}

	@Test
	public void testLocationExists() {
		assertTrue("Bude exists", locationsList.isLocationExists("Bude"));
	}

	@Test
	public void testLocationNotExists() {
		assertFalse("Holsworthy doens't exist", locationsList.isLocationExists("Holsworthy"));
	}

	@Test
	public void testLocationListLength() {
		assertEquals(1, locationsList.getLocationsList().size());
	}
}
