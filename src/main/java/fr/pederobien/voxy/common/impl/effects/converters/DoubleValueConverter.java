package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.Range;
import fr.pederobien.utils.ReadableByteWrapper;

public class DoubleValueConverter implements IValueConverter {
	private final Range<Double> range;

	/**
	 * Creates a converter associated to a range.
	 * 
	 * @param range The range to use to validate the parameter's value.
	 */
	public DoubleValueConverter(Range<Double> range) {
		this.range = range;
	}

	/**
	 * Creates a simple converter.
	 */
	public DoubleValueConverter() {
		this(null);
	}

	@Override
	public Class<?> getValueDataType() {
		return Double.class;
	}

	@Override
	public void validate(String name, Object value) {
		if (!getValueDataType().isInstance(value)) {
			String format = "%s's value datatype should be %s";
			throw new IllegalArgumentException(String.format(format, name, getValueDataType().getSimpleName()));
		}

		double val = (double) value;
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

			double val = (double) value;
			if (!isValid(val))
				return null;

			return ByteWrapper.create().putDouble(val).get();
		} catch (ClassCastException e) {
			return null;
		}
	}

	@Override
	public Object fromBytes(byte[] data) {
		double value = ReadableByteWrapper.wrap(data).nextDouble();
		if (!isValid(value))
			return null;

		return value;
	}

	@Override
	public Object fromString(String value) {
		double val = Double.parseDouble(value);
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
	private boolean isValid(double value) {
		if (range == null)
			return true;

		return range.contains(value);
	}
}
