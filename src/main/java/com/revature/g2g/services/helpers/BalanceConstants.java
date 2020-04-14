package com.revature.g2g.services.helpers;

public class BalanceConstants {
	private BalanceConstants() {}
	private static final float MIN_VALUE = 1;
	private static final float MAX_VALUE = 200;
	private static final float START_VALUE = 100;
	private static final float MAX_LOSS = -0.1f;
	private static final float MAX_GAIN = 0.1f;
	private static final float MAX_EXPERTISE = 1F;
	private static final float MIN_EXPERTISE = 0.00001F;
	public static float getMinValue() {
		return MIN_VALUE;
	}
	public static float getMaxValue() {
		return MAX_VALUE;
	}
	public static float getStartValue() {
		return START_VALUE;
	}
	public static float getMaxLoss() {
		return MAX_LOSS;
	}
	public static float getMaxGain() {
		return MAX_GAIN;
	}
	public static float getMaxExpertise() {
		return MAX_EXPERTISE;
	}
	public static float getMinExpertise() {
		return MIN_EXPERTISE;
	}
}
