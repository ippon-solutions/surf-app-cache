package uk.co.ipponsolutions.forecastTask;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.ipponsolutions.surfapp.domain.ForecastTimestep;
import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.UriDetails;
import uk.co.ipponsolutions.surfapp.domain.WeatherParameters;
import uk.co.ipponsolutions.surfapp.forecastTask.ForecastWeatherDataParser;
import uk.co.ipponsolutions.surfapp.units.MetresPerSecond;
import uk.co.ipponsolutions.util.test.TestUtility;

public class ForecastWeatherDataParserTest {

	static UriDetails uriDetails;
	static LocationsList locationsList = null;
	static Location location = null;
	static ForecastWeatherDataParser forecastDataParser;
	
	static ForecastTimestep firstForecastTimestep;
	static Calendar firstDayCalendar;
	static WeatherParameters firstDayWeatherParameters;

	static ForecastTimestep fifthForecastTimestep;
	static Calendar fifthDayCalendar;
	static WeatherParameters fifthDayWeatherParameters;
	
	static ForecastTimestep lastForecastTimestep;
	static Calendar lastDayCalendar;
	static WeatherParameters lastDayWeatherParameters;

	
	@BeforeClass
	public static void setup() throws IOException {
		locationsList = (LocationsList)TestUtility.loadSpringManagedObject("locationsList", "appTestContext-locationDetails.xml");
		uriDetails = (UriDetails)TestUtility.loadSpringManagedObject("uriDetails", "appTestContext-locationDetails.xml");
		
		location = locationsList.getLocationDetails("Bude");
		forecastDataParser = new ForecastWeatherDataParser();
		forecastDataParser.setLocationsList(locationsList);

		InputStream forecastXml = TestUtility.loadResourceAsStream(uriDetails.getWeatherUri());
		
		forecastDataParser.initializeDocumentAndXpath(forecastXml);
		forecastDataParser.getForecastsForLocation(location);

		ForecastTimestep[] forecastTimesteps = location.getForecastTimesteps();
		firstForecastTimestep = forecastTimesteps[0];
		firstDayWeatherParameters = firstForecastTimestep.getWeatherParameters();
		Date firstTimePeriod = firstForecastTimestep.getTimePeriod();
		
		firstDayCalendar = Calendar.getInstance();
		firstDayCalendar.setTime(firstTimePeriod);


		fifthForecastTimestep = forecastTimesteps[4];
		fifthDayWeatherParameters = fifthForecastTimestep.getWeatherParameters();
		Date fifthTimePeriod = fifthForecastTimestep.getTimePeriod();
		
		fifthDayCalendar = Calendar.getInstance();
		fifthDayCalendar.setTime(fifthTimePeriod);

		
		
		lastForecastTimestep = forecastTimesteps[forecastTimesteps.length-1];
		lastDayWeatherParameters = lastForecastTimestep.getWeatherParameters();
		Date lastForecastTimePeriod = lastForecastTimestep.getTimePeriod();
		
		lastDayCalendar = Calendar.getInstance();
		lastDayCalendar.setTime(lastForecastTimePeriod);
	}

	@Test
	public void testGetFirstTimeStepDate() {
		Calendar calendarFirstDay = Calendar.getInstance();
		calendarFirstDay.setTime(firstForecastTimestep.getTimePeriod());
		assertEquals(26, calendarFirstDay.get(Calendar.DATE));
		assertEquals(3, calendarFirstDay.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarFirstDay.get(Calendar.YEAR));
	}

	@Test
	public void testGetTimeOfFirstTimeStep() {
		assertEquals(18, firstDayCalendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, firstDayCalendar.get(Calendar.MINUTE));
		assertEquals(0, firstDayCalendar.get(Calendar.SECOND));
	}
	
	@Test
	public void testGetWindDirectionOfFirstTimeStep() {
		assertEquals("ESE", firstDayWeatherParameters.getWindDirection());
	}

	@Test
	public void testGetWindSpeedOfFirstTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(4.53);
		assertEquals(metresPerSecond.getValue(), firstDayWeatherParameters.getWindSpeed().getValue(), 0.0);
	}

	@Test
	public void testGetWindGustOfFirstTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(8.23);
		assertEquals(metresPerSecond.getValue(), firstDayWeatherParameters.getWindGust().getValue(), 0.0);
	}
	

	@Test
	public void testGetFifthTimeStepDate() {
		Calendar calendarFifthDay = Calendar.getInstance();
		calendarFifthDay.setTime(fifthForecastTimestep.getTimePeriod());
		assertEquals(27, calendarFifthDay.get(Calendar.DATE));
		assertEquals(3, calendarFifthDay.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarFifthDay.get(Calendar.YEAR));
	}

	@Test
	public void testGetFifthTimeStepTime() {
		assertEquals(6, fifthDayCalendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, fifthDayCalendar.get(Calendar.MINUTE));
		assertEquals(0, fifthDayCalendar.get(Calendar.SECOND));
	}
	
	@Test
	public void testGetWindDirectionOfFifthTimeStep() {
		assertEquals("E", fifthDayWeatherParameters.getWindDirection());
	}

	@Test
	public void testGetWindSpeedOfFifthTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(3.8);
		assertEquals(metresPerSecond.getValue(), fifthDayWeatherParameters.getWindSpeed().getValue(), 0.0);
	}

	@Test
	public void testGetWindGustOfFifthTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(6.52);
		assertEquals(metresPerSecond.getValue(), fifthDayWeatherParameters.getWindGust().getValue(), 0.0);
	}	

	@Test
	public void testGetLastTimeStepDate() {
		Calendar calendarLastDay = Calendar.getInstance();
		calendarLastDay.setTime(lastForecastTimestep.getTimePeriod());
		assertEquals(30, calendarLastDay.get(Calendar.DATE));
		assertEquals(3, calendarLastDay.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarLastDay.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetLastTimeStepTime() {
		assertEquals(21, lastDayCalendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, lastDayCalendar.get(Calendar.MINUTE));
		assertEquals(0, lastDayCalendar.get(Calendar.SECOND));
	}

	@Test
	public void testGetWindDirectionOfLastTimeStep() {
		assertEquals("N", lastDayWeatherParameters.getWindDirection());
	}

	@Test
	public void testGetWindSpeedOfLastTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(4.81);
		assertEquals(metresPerSecond.getValue(), lastDayWeatherParameters.getWindSpeed().getValue(), 0.0);
	}

	@Test
	public void testGetWindGustOfLastTimeStep() {
		MetresPerSecond metresPerSecond = new MetresPerSecond(7.7);
		assertEquals(metresPerSecond.getValue(), lastDayWeatherParameters.getWindGust().getValue(), 0.0);
	}
}
