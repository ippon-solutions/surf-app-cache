package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;

import uk.co.ipponsolutions.surfapp.units.Metres;

public class TideTime {

	private Metres height;
	private Date time;
	private TideType tideType;
	
	public Metres getHeight() {
		return height;
	}
	public void setHeight(Metres height) {
		this.height = height;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public TideType getTideType() {
		return tideType;
	}
	public void setTideType(TideType tideType) {
		this.tideType = tideType;
	}
}
