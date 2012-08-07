package uk.co.ipponsolutions.forecastTask;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.TideTable;
import uk.co.ipponsolutions.surfapp.domain.TideTime;
import uk.co.ipponsolutions.surfapp.domain.TideType;
import uk.co.ipponsolutions.surfapp.domain.UriDetails;
import uk.co.ipponsolutions.surfapp.forecastTask.ForecastTideDataParser;
import uk.co.ipponsolutions.surfapp.units.Metres;
import uk.co.ipponsolutions.util.test.TestUtility;

public class ForecastTideDataParserTest {

	static UriDetails uriDetails;
	static LocationsList locationsList = null;
	static Location location = null;
	static ForecastTideDataParser forecastTideDataParser;
	
	static TideTable firstTideTable;
	static TideTime firstDayFirstForecastTideTime;
	static Calendar firstDayCalendar;

	static TideTable secondTideTable;
	static TideTime secondDaySecondForecastTideTime;
	static Calendar secondDayCalendar;

	
	@BeforeClass
	public static void setup() throws IOException {
		locationsList = (LocationsList)TestUtility.loadSpringManagedObject("locationsList", "appTestContext-locationDetails.xml");
		uriDetails = (UriDetails)TestUtility.loadSpringManagedObject("uriDetails", "appTestContext-locationDetails.xml");
		location = locationsList.getLocationDetails("Bude");
		forecastTideDataParser = new ForecastTideDataParser();
		forecastTideDataParser.setLocationsList(locationsList);
		
		InputStream forecastXml = TestUtility.loadResourceAsStream(uriDetails.getTidalUri());
		
		forecastTideDataParser.initializeDocumentAndXpath(forecastXml);
		forecastTideDataParser.getForecastsForLocation(location);

		TideTable[] tideTables = location.getTideTables();
		firstTideTable = tideTables[0];
		TideTime[] firstForecastTideTimes = firstTideTable.getTideTime();
		firstDayFirstForecastTideTime = firstForecastTideTimes[0];
		
		Date firsDayFirstForecastTideTimePeriod = firstDayFirstForecastTideTime.getTime();
		
		firstDayCalendar = Calendar.getInstance();
		firstDayCalendar.setTime(firsDayFirstForecastTideTimePeriod);

		
		secondTideTable = tideTables[1];
		TideTime[] secondForecastTideTimes = secondTideTable.getTideTime();
		secondDaySecondForecastTideTime = secondForecastTideTimes[1];

		Date secondForecastSecondTideTimePeriod = secondDaySecondForecastTideTime.getTime();
		
		secondDayCalendar = Calendar.getInstance();
		secondDayCalendar.setTime(secondForecastSecondTideTimePeriod);
	}

	@Test
	public void testGetFirstDayDate() {
		Calendar calendarFirstDay = Calendar.getInstance();
		calendarFirstDay.setTime(firstTideTable.getDate());
		assertEquals(27, calendarFirstDay.get(Calendar.DATE));
		assertEquals(3, calendarFirstDay.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarFirstDay.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetFirstTideTimeHour() {
		assertEquals(8, firstDayCalendar.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testGetHeightOfFirstTideTimeOfFirstDay() {
		Metres metres = new Metres(7.03);
		assertEquals(metres.getValue(), firstDayFirstForecastTideTime.getHeight().getValue(), 0.0);
	}

	@Test
	public void testGetTideTypeOfFirstTideTimeOfFirstDay() {
		assertEquals(TideType.HIGH, firstDayFirstForecastTideTime.getTideType());
	}
	
	@Test
	public void testGetSecondDayDate() {
		Calendar calendarSecondDay = Calendar.getInstance();
		calendarSecondDay.setTime(secondTideTable.getDate());
		assertEquals(28, calendarSecondDay.get(Calendar.DATE));
		assertEquals(3, calendarSecondDay.get(Calendar.MONTH)+1);
		assertEquals(2012, calendarSecondDay.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetSecondTideTimeHourOfSecondDay() {
		assertEquals(20, secondDayCalendar.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testGetSecondTideTimeMinuteOfSecondDay() {
		assertEquals(42, secondDayCalendar.get(Calendar.MINUTE));
	}

	@Test
	public void testGetHeightOfSecondTideTimeOfSecondDay() {
		Metres metres = new Metres(6.58);
		assertEquals(metres.getValue(), secondDaySecondForecastTideTime.getHeight().getValue(), 0.0);
	}

	@Test
	public void testGetTideTypeOfSecondTideTimeOfSecondDay() {
		assertEquals(TideType.LOW, secondDaySecondForecastTideTime.getTideType());
	}
}
