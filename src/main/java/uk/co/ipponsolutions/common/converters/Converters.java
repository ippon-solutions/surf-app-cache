package uk.co.ipponsolutions.common.converters;

public class Converters {

	public static final int KILO = 1000;
	public static final double FEET_TO_METRES=3.2808399f;
	public static final double NAUTICAL_MILE_TO_METRES = 1852.0;

	public static final float MPS_PER_KT = (float) NAUTICAL_MILE_TO_METRES / 3600;

	public static final int SECONDS_PER_MINUTE = 60;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int MINUTES_PER_DAY = MINUTES_PER_HOUR * 24;
	public static final int MILLISECONDS_PER_DAY = MINUTES_PER_DAY
			* SECONDS_PER_MINUTE * KILO;

	public static double feetToMetres(final double feet) {
		return feet / FEET_TO_METRES;
	}

	public static float knotsToMetersPerSecond(final float knot) {
		return knot * MPS_PER_KT;
	}

	public static float metersPerSecondToKnots(final float metersPerSecond) {
		return metersPerSecond / MPS_PER_KT;
	}
}