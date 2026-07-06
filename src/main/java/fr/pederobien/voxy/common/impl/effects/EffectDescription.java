package fr.pederobien.voxy.common.impl.effects;

public abstract class EffectDescription {
	private final String effectName;

	/**
	 * Creates a default parameters of an effect.
	 * 
	 * @param effectName The name of the effect associated to this parameters list.
	 */
	public EffectDescription(String effectName) {
		this.effectName = effectName;
	}

	/**
	 * @return The name of the effect associated to this parameters list.
	 */
	public String getEffectName() {
		return effectName;
	}

	/**
	 * Set the parameters of an effect.
	 * 
	 * @param params The array that contains effect parameters value.
	 */
	public abstract void setValues(Object... params);

	/**
	 * @return A list of parameters value.
	 */
	public abstract Object[] getValues();

	/**
	 * @return The bytes array that contains effect parameters value. The name is already managed.
	 */
	public abstract byte[] getBytes();

	/**
	 * Parse the content of the bytes array to update effect parameters value.
	 * 
	 * @param data The bytes array that contains effect parameters. The name is already managed.
	 */
	public abstract void fromBytes(byte[] data);
}
