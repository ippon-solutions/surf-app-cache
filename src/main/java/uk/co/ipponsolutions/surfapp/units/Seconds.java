package uk.co.ipponsolutions.surfapp.units;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlValue;

public class Seconds implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlValue
	final private double value;

	public Seconds() {
		value = 0;
	}

	public Seconds(final double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seconds other = (Seconds) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

}
