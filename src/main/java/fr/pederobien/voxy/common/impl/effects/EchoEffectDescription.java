package fr.pederobien.voxy.common.impl.effects;

import java.util.StringJoiner;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;

public class EchoEffectDescription extends EffectDescription {
	public static final String NAME = "ECHO";

	private int delay;
	private float feedback;
	private float gain;

	/**
	 * Creates a description of an echo effect.
	 */
	public EchoEffectDescription() {
		super(NAME);
	}

	@Override
	public void setValues(Object... params) {
		delay = (int) params[0];
		feedback = (float) params[1];
		gain = (float) params[2];
	}

	@Override
	public Object[] getValues() {
		return new Object[] { delay, feedback, gain };
	}

	@Override
	public byte[] getBytes() {
		ByteWrapper wrapper = ByteWrapper.create();

		// Byte 0 -> 3: delay
		wrapper.putInt(delay);

		// Bytes 4 -> 7: feedback;
		wrapper.putFloat(feedback);

		// Bytes 8 -> 11: gain
		wrapper.putFloat(gain);

		return wrapper.get();
	}

	@Override
	public void fromBytes(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Bytes 0 -> 3: delay
		delay = wrapper.nextInt();

		// Bytes 4 -> 7: feedback
		feedback = wrapper.nextFloat();

		// Bytes 8 -> 11: gain
		gain = wrapper.nextFloat();
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("effectName=" + getEffectName());
		joiner.add("delay=" + getDelay());
		joiner.add("feedback=" + getFeedback());
		joiner.add("gain=" + getGain());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EchoEffectDescription))
			return false;

		EchoEffectDescription other = (EchoEffectDescription) obj;
		return delay == other.getDelay() && feedback == other.getFeedback() && gain == other.getGain();
	}

	/**
	 * @return The time, in ms, before repeating previous sample.
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return The value that controls how much of the delayed signal is sent back into the delay line to create subsequent
	 *         repetitions. It determines the number of repeats and the decay rate.
	 */
	public float getFeedback() {
		return feedback;
	}

	/**
	 * @return The value that controls the volume of the first echo repetition relative to the original (dry) sound. It determines how
	 *         loud the echo is when it first becomes audible.
	 */
	public float getGain() {
		return gain;
	}
}
