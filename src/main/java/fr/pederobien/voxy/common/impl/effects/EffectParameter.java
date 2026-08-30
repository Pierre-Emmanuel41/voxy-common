package fr.pederobien.voxy.common.impl.effects;

import fr.pederobien.voxy.common.impl.effects.converters.IValueConverter;

public class EffectParameter {

	private final String name;
	private final Class<?> clazz;
	private final IValueConverter converter;
	private Object value;

	/**
	 * Creates a parameter, it is the association of a name and a value.
	 * 
	 * @param name  The parameter's name.
	 * @param clazz The class of the parameter value.
	 */
	public EffectParameter(String name, Class<?> clazz, IValueConverter converter) {
		this.name = name;
		this.clazz = clazz;
		this.converter = converter;

		value = null;
	}

	/**
	 * @return The name of the parameter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The value of the parameter.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Set the value of this parameter.
	 * 
	 * @param value The new value of the parameter.
	 */
	public void setValue(Object value) {
		if (!clazz.isInstance(value)) {
			String format = "%s's value datatype should be %s";
			throw new IllegalArgumentException(String.format(format, name, clazz.getSimpleName()));
		}

		this.value = value;
	}

	/**
	 * @return The byte array corresponding to the value of the parameter.
	 */
	public byte[] toBytes() {
		return converter.toBytes(value);
	}

	/**
	 * Parse the input bytes array to retrieve the value.
	 * 
	 * @param data The bytes array to parse.
	 */
	public void fromBytes(byte[] data) {
		value = converter.fromBytes(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EffectParameter))
			return false;

		EffectParameter other = (EffectParameter) obj;
		return name.equals(other.getName()) && value.equals(other.getValue());
	}

	@Override
	public String toString() {
		return String.format("%s=%s", getName(), getValue());
	}
}
