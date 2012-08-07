package uk.co.ipponsolutions.surfapp.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import uk.co.ipponsolutions.surfapp.units.Degrees;
import uk.co.ipponsolutions.surfapp.units.Metres;
import uk.co.ipponsolutions.surfapp.units.Seconds;

@XmlRootElement
public class WaveData {
	
	private Date date;
	private Metres waveHeight;
	private Seconds wavePeriod;
	private Degrees waveDirection;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Metres getWaveHeight() {
		return waveHeight;
	}
	public void setWaveHeight(Metres waveHeight) {
		this.waveHeight = waveHeight;
	}
	public Seconds getWavePeriod() {
		return wavePeriod;
	}
	public void setWavePeriod(Seconds wavePeriod) {
		this.wavePeriod = wavePeriod;
	}
	public Degrees getWaveDirection() {
		return waveDirection;
	}
	public void setWaveDirection(Degrees waveDirection) {
		this.waveDirection = waveDirection;
	}
}
