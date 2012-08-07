package uk.co.ipponsolutions.surfapp.forecastTask;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.WaveData;
import uk.co.ipponsolutions.surfapp.exceptions.CacheException;
import uk.co.ipponsolutions.surfapp.loaders.XMLDocumentParser;
import uk.co.ipponsolutions.surfapp.loaders.XMLParserHelper;

public class ForecastWaveDataParser extends XMLDocumentParser {
	
	public void getForecastsForLocation(Location location) {
		location.setWaveData(getForecastsWaveDataForLocation());
		log.info("Wave data has been updated");
	}

	public String getForecastUri(Location location) {
		return getUriDetails().getWaveUri();
	}
	
	private WaveData[] getForecastsWaveDataForLocation() throws CacheException {
		NodeList beachParamNodeList = (NodeList) XMLParserHelper.getExpressionValue(xPath, domDocument, "/BeachSafetyList/BeachSafety", XPathConstants.NODESET); 

		WaveData[] waveDatas = new WaveData[beachParamNodeList.getLength()];
		
		for (int beachIndex = 0; beachIndex < beachParamNodeList.getLength(); beachIndex++) { 
			Element beachElement = (Element) beachParamNodeList.item(beachIndex);
			
			WaveData waveData = new WaveData();
			waveData.setWaveHeight(getMetresParameter(beachElement, "WaveHeight"));
			waveData.setWaveDirection(getDegreesParameter(beachElement, "WaveDirection"));
			waveData.setWavePeriod(getSecondsParameter(beachElement, "WavePeriod"));
			waveData.setDate(getFullDateAttributeValue(beachElement));

			waveDatas[beachIndex] = waveData;
		}
		return waveDatas;
	}
}
