package fr.pederobien.voxy.common.impl.effects.converters;

public interface IValueConverter {

	/**
	 * @return The data type of the value.
	 */
	Class<?> getValueDataType();

	/**
	 * Checks if the input value is valid. The method shall throw an exception if the data type is incorrect.
	 * 
	 * @param name  The parameter's name.
	 * @param value The value to validate.
	 */
	void validate(String name, Object value);

	/**
	 * Convert the input value to bytes array.
	 * 
	 * @param The value of the parameter.
	 * @return The byte array corresponding to the value of the parameter.
	 */
	public byte[] toBytes(Object value);

	/**
	 * Parse the input bytes array to retrieve the value.
	 * 
	 * @param data The array to parse.
	 * @return The value of the parameter.
	 */
	public Object fromBytes(byte[] data);

	/**
	 * Parse the input string to retrieve the value.
	 * 
	 * @param value The value to parse.
	 * @return The value of the parameter.
	 */
	public Object fromString(String value);
}