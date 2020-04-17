package com.revature.g2g.services.helpers;

public class BalanceConstants {
	private BalanceConstants() {}
	private static final float MIN_VALUE = 1;
	private static final float MAX_VALUE = 200;
	private static final float START_VALUE = 100;
	private static final float MAX_GAIN = 0.3f;
	private static final float MAX_LOSS = -MAX_GAIN;
	private static final float MAX_EXPERTISE = 1F;
	private static final float MIN_EXPERTISE = 0.00001F;
	private static final long DEFAULT_GAME_TIME = 3_600_000l;
	/**
	 * The minimum value possible for a skill in a SkillPlayerJT.
	 * @return float
	 */
	public static float getMinValue() {
		return MIN_VALUE;
	}
	/**
	 * The maximum value possible for a skill in a SkillPlayerJT.
	 * @return float
	 */
	public static float getMaxValue() {
		return MAX_VALUE;
	}
	/**
	 * The default start value for a skill when a SkillPlayerJT is first made.
	 * @return float
	 */
	public static float getStartValue() {
		return START_VALUE;
	}
	/**
	 * The maximum a SkillPlayerJT can be reduced by a new SkillPlayerChangeJT.
	 * @return float
	 */
	public static float getMaxLoss() {
		return MAX_LOSS;
	}
	/**
	 * The maximum a SkillPlayerJT can be increased by a new SkillPlayerChangeJT.
	 * @return
	 */
	public static float getMaxGain() {
		return MAX_GAIN;
	}
	/**
	 * The maximum modifier to a value of a SkillPlayerChangeJT based on how highly
	 * Skilled the player is with that skill. 1 = 100%, 0.5 = 50%;
	 * @return float
	 */
	public static float getMaxExpertise() {
		return MAX_EXPERTISE;
	}
	/**
	 * The minimum modifier to a value of a SkillPlayerChangeJT based on how
	 * poorly skilled the player is with that skill. 0 = no change is possible.
	 * @return float
	 */
	public static float getMinExpertise() {
		return MIN_EXPERTISE;
	}
	/**
	 * The time for a game that will give full credit for skill change calculations.
	 * Measured in milliseconds
	 * @return long
	 */
	public static long getDefaultGameTime() {
		return DEFAULT_GAME_TIME;
	}
}