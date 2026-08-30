package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;

public class LongValueConverter implements IValueConverter {

	@Override
	public byte[] toBytes(Object value) {
		return ByteWrapper.create().putLong((long) value).get();
	}

	@Override
	public Object fromBytes(byte[] data) {
		return ReadableByteWrapper.wrap(data).nextLong();
	}
}
