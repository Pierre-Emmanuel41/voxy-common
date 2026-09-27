package fr.pederobien.voxy.common.impl.effects;

import fr.pederobien.voxy.common.impl.effects.converters.IValueConverter;

public class EffectParameter {
	private final String name;
	private final String constraints;
	private final String unit;
	private final IValueConverter converter;
	private Object value;

	/**
	 * Creates a parameter, it is the association of a name, a value and a unit.
	 * 
	 * @param name        The parameter's name.
	 * @param constraints An explanation about the constraints the parameter's value shall meet.
	 * @param unit        The parameter's unit, can be null.
	 * @param converter   The object used to convert the value to bytes array or to parse string value.
	 */
	public EffectParameter(String name, String constraints, String unit, IValueConverter converter) {
		this.name = name;
		this.constraints = constraints;
		this.unit = unit;
		this.converter = converter;

		value = null;
	}

	/**
	 * Creates a parameter, it is the association of a name and a value.
	 * 
	 * @param name        The parameter's name.
	 * @param constraints An explanation about the constraints the parameter's value shall meet.
	 * @param converter   The object used to convert the value to bytes array or to parse string value.
	 */
	public EffectParameter(String name, String constraints, IValueConverter converter) {
		this(name, constraints, null, converter);
	}

	/**
	 * @return The name of the parameter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return An explanation about the constraints the parameter's value shall meet.
	 */
	public String getConstraints() {
		return constraints;
	}

	/**
	 * @return The value of the parameter.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @return The unit of the parameter.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @return The data type of the value.
	 */
	public Class<?> getValueDataType() {
		return converter.getValueDataType();
	}

	/**
	 * Set the value of this parameter.
	 * 
	 * @param value The new value of the parameter.
	 */
	public void setValue(Object value) {
		converter.validate(name, value);
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

	/**
	 * Parse the input string to retrieve the value. If the parameter's value becomes null then the value is a valid string
	 * representation of the parameter's value but the converted value does not match the constraints. Check the method getContraints
	 * to know what is incorrect.
	 * 
	 * @param value The string representation of the value.
	 */
	public void fromString(String value) {
		this.value = converter.fromString(value);
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
		return String.format("%s=%s%s (%s)", getName(), getValue(), getUnit(), getConstraints());
	}
}
