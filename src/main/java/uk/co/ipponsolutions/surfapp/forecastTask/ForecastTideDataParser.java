package uk.co.ipponsolutions.surfapp.forecastTask;

import java.util.Date;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.TideTable;
import uk.co.ipponsolutions.surfapp.domain.TideTime;
import uk.co.ipponsolutions.surfapp.domain.TideType;
import uk.co.ipponsolutions.surfapp.exceptions.CacheException;
import uk.co.ipponsolutions.surfapp.loaders.XMLDocumentParser;
import uk.co.ipponsolutions.surfapp.loaders.XMLParserHelper;

public class ForecastTideDataParser extends XMLDocumentParser {
	
	public void getForecastsForLocation(Location location) {
		location.setTideTables(getForecastsTideDataForLocation());
		log.info("Tide data has been updated");
	}
	
	public String getForecastUri(Location location) {
		return getUriDetails().getTidalUri();
	}
	
	private TideTable[] getForecastsTideDataForLocation() throws CacheException {
		NodeList dailyTideNodeList = (NodeList) XMLParserHelper.getExpressionValue(xPath, domDocument, "/TideTableList/TideTable", XPathConstants.NODESET); 

		TideTable[] tideTables = new TideTable[dailyTideNodeList.getLength()];
		
		for (int dayIndex = 0; dayIndex < dailyTideNodeList.getLength(); dayIndex++) { 
			Element dailyTidesElement = (Element) dailyTideNodeList.item(dayIndex);

			TideTable tideTable = new TideTable();
			Date dayDate = getDateAttributeValue(dailyTidesElement);

			int numberOfTideTimes = dailyTidesElement.getElementsByTagName("TideTime").getLength();
			TideTime[] tideTimes =  new TideTime[numberOfTideTimes];
			
			for (int tideIndex = 0; tideIndex < numberOfTideTimes; tideIndex++) {
				Element tideElement = (Element) dailyTidesElement.getElementsByTagName("TideTime").item(tideIndex);
				
				TideTime tideTime = new TideTime();
				tideTime.setHeight(getMetresAttributeValue(tideElement, "height"));
				tideTime.setTime(addTimestepTimeToDayDate(dayDate, tideElement.getTextContent()));
				tideTime.setTideType(getTideType(tideElement));
				
				tideTimes[tideIndex] = tideTime;
			}

			tideTable.setDate(dayDate);
			tideTable.setTideTime(tideTimes);
			
			tideTables[dayIndex] = tideTable;
		}
		return tideTables; 
	}
	
	private TideType getTideType(Element tideElement) {
		String tideTypeAttr = getAttributeValue(tideElement, "type");
		TideType tideType = TideType.LOW;
		if (tideTypeAttr.equalsIgnoreCase("HW")) {
			tideType = TideType.HIGH;
		}
		return tideType;
	}
}
