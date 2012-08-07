package uk.co.ipponsolutions.surfapp.units;

import javax.xml.bind.annotation.XmlValue;

public class MetresPerSecond {
	
	@XmlValue
    final private double metresPerSecond;
	
	@SuppressWarnings("unused")
	private MetresPerSecond() {
		metresPerSecond = 0;
	}
	
	public MetresPerSecond(double metresPerSecond) {
		this.metresPerSecond = metresPerSecond;
	}

	public MetresPerSecond(String metresPerSecond) {
		this.metresPerSecond = Double.parseDouble(metresPerSecond);
	}

	public double getValue() {
		return metresPerSecond;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(metresPerSecond);
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
		MetresPerSecond other = (MetresPerSecond) obj;
		if (Double.doubleToLongBits(metresPerSecond) != Double
				.doubleToLongBits(other.metresPerSecond))
			return false;
		return true;
	}
}
