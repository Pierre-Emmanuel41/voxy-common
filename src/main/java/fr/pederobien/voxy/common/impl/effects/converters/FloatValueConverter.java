package fr.pederobien.voxy.common.impl.effects.converters;

import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;

public class FloatValueConverter implements IValueConverter {

	@Override
	public byte[] toBytes(Object value) {
		return ByteWrapper.create().putFloat((float) value).get();
	}

	@Override
	public Object fromBytes(byte[] data) {
		return ReadableByteWrapper.wrap(data).nextFloat();
	}
}
