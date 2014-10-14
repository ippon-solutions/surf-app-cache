package uk.co.ipponsolutions.surfapp.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationInfo {

    private String name;
	private double longitude;
	private double latitude;

    public LocationInfo(){}

    public LocationInfo(String name, double latitude, double longitude){
        this.setName(name);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getName() {
        return name;
    }

	public double getLatitude() {
		return latitude;
	}
    @XmlElement
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
    @XmlElement
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}