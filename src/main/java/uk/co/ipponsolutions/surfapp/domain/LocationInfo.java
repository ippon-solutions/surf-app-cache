package uk.co.ipponsolutions.surfapp.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationInfo {

    private String name;

    public LocationInfo(){}

    public LocationInfo(String name){
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getName() {
        return name;
    }

}