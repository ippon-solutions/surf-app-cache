package uk.co.ipponsolutions.surfapp.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import uk.co.ipponsolutions.surfapp.domain.Location;
import uk.co.ipponsolutions.surfapp.domain.LocationsList;
import uk.co.ipponsolutions.surfapp.domain.UriDetails;
import uk.co.ipponsolutions.surfapp.exceptions.CacheException;
import uk.co.ipponsolutions.surfapp.units.Degrees;
import uk.co.ipponsolutions.surfapp.units.Metres;
import uk.co.ipponsolutions.surfapp.units.MetresPerSecond;
import uk.co.ipponsolutions.surfapp.units.Seconds;

public abstract class XMLDocumentParser {

	private static final String ERR_HTTP_TEXT_FAILED = "Text could not be retrieved: ";
	private static final String ERR_MALFORMED_URL = "Malformed URL: ";
	
	protected LocationsList locationsList;
	protected UriDetails uriDetails;
	
	protected transient Logger log = LoggerFactory.getLogger(XMLDocumentParser.class);
	protected Document domDocument;
	protected XPath xPath;

	public void getForecastsForLocations()
	{
		for(Location location : locationsList.getLocationsList()) {
			String forecastUri = getForecastUri(location) + location.getLocationId();
			if (checkUriETag(forecastUri, location) == true) {
				InputStream forecastXml = getForecastDataStream(forecastUri);
				initializeDocumentAndXpath(forecastXml);
				getForecastsForLocation(location);
			} else {
				log.info("ETag is the same for: " + forecastUri);
			}
		}
	}
	
	protected abstract void getForecastsForLocation(Location location);
	
	protected abstract String getForecastUri(Location location);
	
	private String getLastForecastUriETag(Location location, String url) {
		log.debug("Old eTag: " + url + " " + location.geteTagMapItem(url));
		return location.geteTagMapItem(url);
	}
	
	private void setLastForecastUriETag(Location location, String url, String eTag) {
		location.setETagMapItem(url, eTag);
	}
	
	public void initializeDocumentAndXpath(InputStream capabilitiesXml) throws CacheException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			domDocument = domFactory.newDocumentBuilder().parse(capabilitiesXml);
			xPath = XPathFactory.newInstance().newXPath();
		} catch (ParserConfigurationException e) {
			throw new CacheException(e.getMessage(), e);
		} catch (SAXException e) {
			throw new CacheException(e.getMessage(), e);
		} catch (IOException e) {
			throw new CacheException(e.getMessage(), e);
		}
	}

	private Boolean checkUriETag(String url, Location location) throws CacheException {
		Boolean isNew = false;
		URLConnection textConnection = getUrlConnection(url);
		String newETag = textConnection.getHeaderField("ETag");
		String existingETag = getLastForecastUriETag(location, url);
		if ((existingETag == null && newETag != null) || ((existingETag != null) && (newETag != null) && (!(existingETag.equals(newETag))) ) ) {
			isNew = true;
			setLastForecastUriETag(location, url, newETag);
		}
		log.debug("New eTag: " + url + " " + newETag);
		return isNew;
	
	}
	
	protected InputStream getForecastDataStream(String url) throws CacheException {
		try {
			URLConnection textConnection = getUrlConnection(url);
			return textConnection.getInputStream();
		} catch (java.io.IOException e) {
			throw new CacheException(ERR_HTTP_TEXT_FAILED + url, e);
		}
	}

	private URLConnection getUrlConnection(String url) {
		try {
			URL textURL = new URL(url);
			URLConnection textConnection = textURL.openConnection();
			textConnection.setUseCaches(false);
		
			return textConnection;
		} catch (java.net.MalformedURLException e) {
			throw new CacheException(ERR_MALFORMED_URL + url, e);
		} catch (java.io.IOException e) {
			throw new CacheException(ERR_HTTP_TEXT_FAILED + url, e);
		}
	}
	
	protected Date addTimestepTimeToDayDate(Date dayDate, String timeStepDate) {
		Date timePeriod = new Date();
		
		int timeStepHour = Integer.parseInt(timeStepDate.substring(0, 2));
		int timeStepMinute = Integer.parseInt(timeStepDate.substring(3, 5));
		int timeStepSeconds = Integer.parseInt(timeStepDate.substring(6, 8));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dayDate);
		calendar.set(Calendar.HOUR_OF_DAY, timeStepHour);
		calendar.set(Calendar.MINUTE, timeStepMinute);
		calendar.set(Calendar.SECOND, timeStepSeconds);
		timePeriod = calendar.getTime();
		
		return timePeriod;
	}
	
	protected Date getDateAttributeValue(Element element) {
		SimpleDateFormat forecastDayFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = forecastDayFormat.parse(element.getAttribute("date"));
		} catch (ParseException e) {
			throw new CacheException(e.getMessage(), e);
		}
		return date;
	}

	protected Date getFullDateAttributeValue(Element element) {
		SimpleDateFormat forecastDayFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date();
		try {
			date = forecastDayFormat.parse(element.getAttribute("time"));
		} catch (ParseException e) {
			throw new CacheException(e.getMessage(), e);
		}
		return date;
	}
	
	protected String getAttributeValue(Element element, String attributeName) {
		String output = element.getAttribute(attributeName);
		return output;
	}
	
	protected Metres getMetresAttributeValue(Element element, String attributeName) {
		String output = getAttributeValue(element, attributeName);
		double metres = Double.parseDouble(output);
		return new Metres(metres);
	}

	protected Degrees getDegreesAttributeValue(Element element, String attributeName) {
		String output = getAttributeValue(element, attributeName);
		double degrees = Double.parseDouble(output);
		return new Degrees(degrees);
	}

	protected Seconds getSecondsAttributeValue(Element element, String attributeName) {
		String output = getAttributeValue(element, attributeName);
		double seconds = Double.parseDouble(output);
		return new Seconds(seconds);
	}
	
	protected MetresPerSecond getMetresPerSecondParameter(Element element, String parameter) {
		String output = getElementValue(element, parameter);
		return new MetresPerSecond(output);
	}

	protected Metres getMetresParameter(Element element, String parameter) {
		String output = getElementValue(element, parameter);
		double metres = Double.parseDouble(output);
		return new Metres(metres);
	}

	protected Degrees getDegreesParameter(Element element, String parameter) {
		String output = getElementValue(element, parameter);
		double degrees = Double.parseDouble(output);
		return new Degrees(degrees);
	}

	protected Seconds getSecondsParameter(Element element, String parameter) {
		String output = getElementValue(element, parameter);
		double seconds = Double.parseDouble(output);
		return new Seconds(seconds);
	}
	
	protected String getElementValue(Element element, String parameter) {
		return element.getElementsByTagName(parameter).item(0).getTextContent();
	}
	
	protected String getStringParameter(Element element, String parameter) {
		return getParameterElement(element, parameter);
	}
	
	private String getParameterElement(Element element, String parameter) {
		return element.getElementsByTagName(parameter).item(0).getTextContent();
	}
	
	public LocationsList getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(LocationsList locationsList) {
		this.locationsList = locationsList;
	}	

	public UriDetails getUriDetails() {
		return uriDetails;
	}

	public void setUriDetails(UriDetails uriDetails) {
		this.uriDetails = uriDetails;
	}
}
