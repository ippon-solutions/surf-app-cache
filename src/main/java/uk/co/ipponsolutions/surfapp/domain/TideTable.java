package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;

public class TideTable {

	private TideTime[] tideTime;
	private Date date;
		
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TideTime[] getTideTime() {
		return tideTime;
	}

	public void setTideTime(TideTime[] tideTime) {
		this.tideTime = tideTime;
	}
}
