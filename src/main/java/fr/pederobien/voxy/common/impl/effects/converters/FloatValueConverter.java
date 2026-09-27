package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.Range;
import fr.pederobien.utils.ReadableByteWrapper;

public class FloatValueConverter implements IValueConverter {
	private final Range<Float> range;

	/**
	 * Creates a converter associated to a range.
	 * 
	 * @param range The range to use to validate the parameter's value.
	 */
	public FloatValueConverter(Range<Float> range) {
		this.range = range;
	}

	/**
	 * Creates a simple converter.
	 */
	public FloatValueConverter() {
		this(null);
	}

	@Override
	public Class<?> getValueDataType() {
		return Float.class;
	}

	@Override
	public void validate(String name, Object value) {
		if (!getValueDataType().isInstance(value)) {
			String format = "%s's value datatype should be %s";
			throw new IllegalArgumentException(String.format(format, name, getValueDataType().getSimpleName()));
		}

		float val = (float) value;
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

			float val = (float) value;
			if (!isValid(val))
				return null;

			return ByteWrapper.create().putFloat(val).get();
		} catch (ClassCastException e) {
			return null;
		}
	}

	@Override
	public Object fromBytes(byte[] data) {
		float value = ReadableByteWrapper.wrap(data).nextFloat();
		if (!isValid(value))
			return null;

		return value;
	}

	@Override
	public Object fromString(String value) {
		float val = Float.parseFloat(value);
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
	private boolean isValid(float value) {
		if (range == null)
			return true;

		return range.contains(value);
	}
}
