package uk.co.ipponsolutions.surfapp.domain;

public enum TimePeriod {
	ZERO_HUNDRED_HOURS,
	THREE_HUNDRED_HOURS,
	SIX_HUNDRED_HOURS,
	NINE_HUNDRED_HOURS,
	TWELVE_HUNDRED_HOURS,
	FIFTEEN_HUNDRED_HOURS,
	EIGHTEEN_HUNDRED_HOURS,
	TWENTYONE_HUNDRED_HOURS;

	public int getValue(TimePeriod timePeriod) {
		int value = 0;
		switch (timePeriod) {
		case ZERO_HUNDRED_HOURS:
			value = 0;
			break;
		case THREE_HUNDRED_HOURS:
			value = 3;
			break;
		case SIX_HUNDRED_HOURS:
			value = 6;
			break;
		case NINE_HUNDRED_HOURS:
			value = 9;
			break;
		case TWELVE_HUNDRED_HOURS:
			value = 12;
			break;
		case FIFTEEN_HUNDRED_HOURS:
			value = 15;
			break;
		case EIGHTEEN_HUNDRED_HOURS:
			value = 18;
			break;
		case TWENTYONE_HUNDRED_HOURS:
			value = 21;
			break;
		default:
			break;
		}
		return value;
	}
}

