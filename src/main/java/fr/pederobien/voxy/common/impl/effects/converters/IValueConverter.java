package fr.pederobien.voxy.common.impl.effects.converters;

public interface IValueConverter {

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
}