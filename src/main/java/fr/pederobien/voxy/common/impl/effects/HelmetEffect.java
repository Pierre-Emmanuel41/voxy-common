package fr.pederobien.voxy.common.impl.effects;

import fr.pederobien.utils.Range;
import fr.pederobien.voxy.common.impl.effects.converters.FloatValueConverter;

public class HelmetEffect extends Effect {

	/**
	 * The name of the effect
	 */
	public static final String NAME = "HELMET_EFFECT";

	/**
	 * The name of the center frequency. The value shall be of type Float.
	 */
	public static final String FREQUENCY = "frequency";

	/**
	 * The name of the quality factor. The value shall be of type Float.
	 */
	public static final String QUALITY_FACTOR = "factor";

	/**
	 * Creates a HELMET_EFFECT effect.
	 */
	public HelmetEffect() {
		super(NAME);

		add(FREQUENCY, "The value shall be in range [500, 3000]", "Hz", new FloatValueConverter(Range.of(500.0f, 3000.0f)));
		add(QUALITY_FACTOR, "The value shall be in range [0.5, 3]", "", new FloatValueConverter(Range.of(0.5f, 3.0f)));
	}
}
