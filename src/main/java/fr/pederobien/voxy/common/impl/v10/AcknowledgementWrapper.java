package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;

public class AcknowledgementWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		return new byte[0];
	}

	@Override
	public Object parse(byte[] data) {
		return null;
	}
}
