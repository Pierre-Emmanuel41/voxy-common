package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.Range;
import fr.pederobien.utils.ReadableByteWrapper;

public class ShortValueConverter implements IValueConverter {
	private final Range<Short> range;

	/**
	 * Creates a converter associated to a range.
	 * 
	 * @param range The range to use to validate the parameter's value.
	 */
	public ShortValueConverter(Range<Short> range) {
		this.range = range;
	}

	/**
	 * Creates a simple converter.
	 */
	public ShortValueConverter() {
		this(null);
	}

	@Override
	public Class<?> getValueDataType() {
		return Short.class;
	}

	@Override
	public void validate(String name, Object value) {
		if (!getValueDataType().isInstance(value)) {
			String format = "%s's value datatype should be %s";
			throw new IllegalArgumentException(String.format(format, name, getValueDataType().getSimpleName()));
		}

		short val = (short) value;
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

			short val = (short) value;
			if (!isValid(val))
				return null;

			return ByteWrapper.create().putShort(val).get();
		} catch (ClassCastException e) {
			return null;
		}
	}

	@Override
	public Object fromBytes(byte[] data) {
		short value = ReadableByteWrapper.wrap(data).nextShort();
		if (!isValid(value))
			return null;

		return value;
	}

	@Override
	public Object fromString(String value) {
		short val = Short.parseShort(value);
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
	private boolean isValid(short value) {
		if (range == null)
			return true;

		return range.contains(value);
	}
}
