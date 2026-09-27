package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.Range;
import fr.pederobien.utils.ReadableByteWrapper;

public class ByteValueConverter implements IValueConverter {
	private final Range<Byte> range;

	/**
	 * Creates a converter associated to a range.
	 * 
	 * @param range The range to use to validate the parameter's value.
	 */
	public ByteValueConverter(Range<Byte> range) {
		this.range = range;
	}

	/**
	 * Creates a simple converter.
	 */
	public ByteValueConverter() {
		this(null);
	}

	@Override
	public Class<?> getValueDataType() {
		return Byte.class;
	}

	@Override
	public void validate(String name, Object value) {
		if (!getValueDataType().isInstance(value)) {
			String format = "%s's value datatype should be %s";
			throw new IllegalArgumentException(String.format(format, name, getValueDataType().getSimpleName()));
		}

		byte val = (byte) value;
		if (!isValid(val)) {
			String format = "%s's value shall be in range %s";
			throw new IllegalArgumentException(String.format(format, name, range));
		}
	}

	@Override
	public byte[] toBytes(Object value) {
		try {
			if (value == null)
				return null;

			byte val = (byte) value;
			if (!isValid(val))
				return null;

			return ByteWrapper.create().putInt(val).get();
		} catch (ClassCastException e) {
			return null;
		}
	}

	@Override
	public Object fromBytes(byte[] data) {
		byte value = ReadableByteWrapper.wrap(data).next();
		if (!isValid(value))
			return null;

		return value;
	}

	@Override
	public Object fromString(String value) {
		byte val = Byte.parseByte(value);
		if (!isValid(val))
			return null;

		return val;
	}

	/**
	 * Check if the value is valid.
	 * 
	 * @param value The value to validate.
	 * @return True if the value is valid, false otherwise.
	 */
	private boolean isValid(byte value) {
		if (range == null)
			return true;

		return range.contains(value);
	}
}
