package fr.pederobien.voxy.common.impl.effects;

import fr.pederobien.utils.Range;
import fr.pederobien.voxy.common.impl.effects.converters.FloatValueConverter;
import fr.pederobien.voxy.common.impl.effects.converters.IntValueConverter;

public class EchoEffect extends Effect {

	/**
	 * The name of this effect.
	 */
	public static final String NAME = "ECHO_EFFECT";

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

		add(DELAY, "The value shall be in range [0, 2000]", "ms", new IntValueConverter(Range.of(0, 2000)));
		add(FEEDBACK, "The value shall be in range [0,1]", "%", new FloatValueConverter(Range.of(0.0f, 1.0f)));
		add(GAIN, "The value shall be in range [0,1]", "%", new FloatValueConverter(Range.of(0.0f, 1.0f)));
	}
}
