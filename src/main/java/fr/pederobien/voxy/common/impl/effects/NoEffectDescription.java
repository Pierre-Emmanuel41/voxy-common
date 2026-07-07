package fr.pederobien.voxy.common.impl.effects;

public class NoEffectDescription extends EffectDescription {
	public static final String NAME = "NO EFFECT";

	/**
	 * Creates the description of no effect.
	 */
	public NoEffectDescription() {
		super(NAME);
	}

	@Override
	public void setValues(Object... params) {
		// Do nothing
	}

	@Override
	public Object[] getValues() {
		return new Object[0];
	}

	@Override
	public byte[] getBytes() {
		return new byte[0];
	}

	@Override
	public void fromBytes(byte[] data) {
		// Do nothing
	}

	@Override
	public String toString() {
		return String.format("{effectName=%s}", getEffectName());
	}
}
