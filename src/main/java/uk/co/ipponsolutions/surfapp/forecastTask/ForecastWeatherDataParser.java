package uk.co.ipponsolutions.surfapp.forecastTask;

import java.util.Date;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uk.co.ipponsolutions.surfapp.domain.ForecastDay;
import uk.co.ipponsolutions.surfapp.domain.ForecastTimestep;
import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.WeatherParameters;
import uk.co.ipponsolutions.surfapp.exceptions.CacheException;
import uk.co.ipponsolutions.surfapp.loaders.XMLDocumentParser;
import uk.co.ipponsolutions.surfapp.loaders.XMLParserHelper;

public class ForecastWeatherDataParser extends XMLDocumentParser {
	
	public void getForecastsForLocation(Location location) {
		location.setForecastTimesteps(getForecastWeather());
		log.info("Weather data has been updated");
	}

	public String getForecastUri(Location location) {
		return getUriDetails().getWeatherUri();
	}
	
	private ForecastTimestep[] getForecastWeather() throws CacheException {
		NodeList timeStepsNodeList = (NodeList) XMLParserHelper.getExpressionValue(xPath, domDocument, "/BestFcst/Forecast/Location/Day/TimeSteps/TimeStep", XPathConstants.NODESET);
		ForecastTimestep[] forecastTimesteps =  new ForecastTimestep[timeStepsNodeList.getLength()];
		int forecastCounter = 0;
		
		NodeList dayNodeList = (NodeList) XMLParserHelper.getExpressionValue(xPath, domDocument, "/BestFcst/Forecast/Location/Day", XPathConstants.NODESET);
		
		for (int dayIndex = 0; dayIndex < dayNodeList.getLength(); dayIndex++) {
			
			Element dayElement = (Element) dayNodeList.item(dayIndex);
			NodeList timeNodeList = (NodeList) XMLParserHelper.getExpressionValueForXmlSnippet(dayElement, "./TimeSteps/TimeStep", XPathConstants.NODESET);
			
			Date dayDate = getDateAttributeValue(dayElement);
			
			for (int timeIndex = 0; timeIndex < timeNodeList.getLength(); timeIndex++) {

				ForecastTimestep forecastTimestep = new ForecastTimestep();
				
				Element timestepElement = (Element) timeNodeList.item(timeIndex);
				
				String timeStepDate = timestepElement.getAttribute("time");
				
				WeatherParameters weatherParameters = new WeatherParameters();
				weatherParameters.setWindDirection(getStringParameter(timestepElement, "WindDirection"));
				weatherParameters.setWindGust(getMetresPerSecondParameter(timestepElement, "WindGust"));
				weatherParameters.setWindSpeed(getMetresPerSecondParameter(timestepElement, "WindSpeed"));

				forecastTimestep.setTimePeriod(addTimestepTimeToDayDate(dayDate, timeStepDate));
				forecastTimestep.setWeatherParameters(weatherParameters);
				
				forecastTimesteps[forecastCounter] = forecastTimestep;
				forecastCounter += 1;
			}
		}
		
		return forecastTimesteps;
	}
}
