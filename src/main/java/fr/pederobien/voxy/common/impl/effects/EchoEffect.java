package fr.pederobien.voxy.common.impl.effects;

import fr.pederobien.voxy.common.impl.effects.converters.FloatValueConverter;
import fr.pederobien.voxy.common.impl.effects.converters.IntValueConverter;

public class EchoEffect extends Effect {

	/**
	 * The name of this effect.
	 */
	public static final String NAME = "ECHO";

	/**
	 * Name of the delay parameter. The value data type shall be Integer.
	 */
	public static final String DELAY = "delay";

	/**
	 * Name of the feedback parameter. The value data type shall be Float.
	 */
	public static final String FEEDBACK = "feedback";

	/**
	 * Name of the gain parameter. The value data type shall be Float.
	 */
	public static final String GAIN = "gain";

	/**
	 * Creates a ECHO effect.
	 */
	public EchoEffect() {
		super(NAME);

		add(DELAY, Integer.class, new IntValueConverter());
		add(FEEDBACK, Float.class, new FloatValueConverter());
		add(GAIN, Float.class, new FloatValueConverter());
	}
}
