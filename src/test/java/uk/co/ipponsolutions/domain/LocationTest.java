package uk.co.ipponsolutions.domain;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.ipponsolutions.surfapp.domain.Location;

public class LocationTest {
	
	static Location Location;
	static String LocationName = "Location1";
	static double LocationLat = 123;
	static double LocationLng = 456.2;
	
	@BeforeClass
	public static void setup() {
		Location = new Location();
	}
	
	@Test
	public void testLocationNameIsSet() {
		Location.setLocationName(LocationName);
		assertEquals(LocationName, Location.getLocationName());
	}

	@Test
	public void testLocationLatitudeIsSet() {
		Location.setLatitude(LocationLat);
		assertEquals(LocationLat, Location.getLatitude(), 0.0);
	}

	@Test
	public void testLocationLongitudeIsSet() {
		Location.setLongitude(LocationLng);
		assertEquals(LocationLng, Location.getLongitude(), 0.0);
	}
}
