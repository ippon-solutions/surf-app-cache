package uk.co.ipponsolutions.forecastTask;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.UriDetails;
import uk.co.ipponsolutions.surfapp.domain.WaveData;
import uk.co.ipponsolutions.surfapp.forecastTask.ForecastWaveDataParser;
import uk.co.ipponsolutions.surfapp.units.Degrees;
import uk.co.ipponsolutions.surfapp.units.Metres;
import uk.co.ipponsolutions.surfapp.units.Seconds;
import uk.co.ipponsolutions.util.test.TestUtility;

public class ForecastWaveDataParserTest {

	static UriDetails uriDetails;
	static LocationsList locationsList = null;
	static Location location = null;
	static ForecastWaveDataParser forecastWaveDataParser;
	
	static WaveData firstWaveData;
	static Calendar firstWaveDataCalendar;

	static WaveData thirdWaveData;
	static Calendar thirdWaveDataCalendar;

	
	@BeforeClass
	public static void setup() throws IOException {
		locationsList = (LocationsList)TestUtility.loadSpringManagedObject("locationsList", "appTestContext-locationDetails.xml");
		uriDetails = (UriDetails)TestUtility.loadSpringManagedObject("uriDetails", "appTestContext-locationDetails.xml");
		
		location = locationsList.getLocationDetails("Bude");
		forecastWaveDataParser = new ForecastWaveDataParser();
		forecastWaveDataParser.setLocationsList(locationsList);

		InputStream forecastXml = TestUtility.loadResourceAsStream(uriDetails.getWaveUri());
		
		forecastWaveDataParser.initializeDocumentAndXpath(forecastXml);
		forecastWaveDataParser.getForecastsForLocation(location);

		WaveData[] waveData = location.getWaveData();
		firstWaveData = waveData[0];
		firstWaveDataCalendar = Calendar.getInstance();
		firstWaveDataCalendar.setTime(firstWaveData.getDate());

		
		thirdWaveData = waveData[2];
		thirdWaveDataCalendar = Calendar.getInstance();
		thirdWaveDataCalendar.setTime(thirdWaveData.getDate());
	}

	@Test
	public void testGetFirstWaveDataDate() {
		Calendar calendarFirstData = Calendar.getInstance();
		calendarFirstData.setTime(firstWaveData.getDate());
		assertEquals(28, calendarFirstData.get(Calendar.DATE));
		assertEquals(3, calendarFirstData.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarFirstData.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetFirstWaveDataHour() {
		assertEquals(15, firstWaveDataCalendar.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testGetWaveHeightOfFirstWaveData() {
		Metres metres = new Metres(0.65625);
		assertEquals(metres.getValue(), firstWaveData.getWaveHeight().getValue(), 0.0);
	}

	@Test
	public void testGetWavePeriodOfFirstWaveData() {
		Seconds seconds = new Seconds(10.953);
		assertEquals(seconds.getValue(), firstWaveData.getWavePeriod().getValue(), 0.0);
	}

	@Test
	public void testGetWaveDirectionOfFirstWaveData() {
		Degrees degrees = new Degrees(280.0);
		assertEquals(degrees.getValue(), firstWaveData.getWaveDirection().getValue(), 0.0);
	}
	
	@Test
	public void testGetThirdWaveDataDate() {
		Calendar calendarThirdData = Calendar.getInstance();
		calendarThirdData.setTime(thirdWaveData.getDate());
		assertEquals(28, calendarThirdData.get(Calendar.DATE));
		assertEquals(3, calendarThirdData.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarThirdData.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetThirdWaveDataHour() {
		assertEquals(21, thirdWaveDataCalendar.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testGetWaveHeightOfThirdWaveData() {
		Metres metres = new Metres(0.54688);
		assertEquals(metres.getValue(), thirdWaveData.getWaveHeight().getValue(), 0.0);
	}

	@Test
	public void testGetWavePeriodOfThirdWaveData() {
		Seconds seconds = new Seconds(10.328);
		assertEquals(seconds.getValue(), thirdWaveData.getWavePeriod().getValue(), 0.0);
	}

	@Test
	public void testGetWaveDirectionOfThirdWaveData() {
		Degrees degrees = new Degrees(280.0);
		assertEquals(degrees.getValue(), thirdWaveData.getWaveDirection().getValue(), 0.0);
	}
	
}
