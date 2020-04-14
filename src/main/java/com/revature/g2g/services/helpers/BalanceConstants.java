package com.revature.g2g.services.helpers;

import org.springframework.stereotype.Service;

@Service
public class BalanceConstants {
	private static final float MIN_VALUE = 1;
	private static final float MAX_VALUE = 200;
	private static final float START_VALUE = 100;
	private static final float MIN_CHANGE = -0.1f;
	private static final float MAX_CHANGE = 0.1f;
	public static float getMinValue() {
		return MIN_VALUE;
	}
	public static float getMaxValue() {
		return MAX_VALUE;
	}
	public static float getStartValue() {
		return START_VALUE;
	}
	public static float getMinChange() {
		return MIN_CHANGE;
	}
	public static float getMaxChange() {
		return MAX_CHANGE;
	}
}
