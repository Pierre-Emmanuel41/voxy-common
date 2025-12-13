package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.AcknowledgementRequest;

public class AcknowledgementWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof AcknowledgementRequest))
			throw new IllegalArgumentException("[AcknowledgementWrapper] - the payload datatype shall be AcknowledgementRequest");

		AcknowledgementRequest request = (AcknowledgementRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Acknowledgement's identifier
		wrapper.putInt(request.getIdentifier().getCode());

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Acknowledgement's identifier
		int code = wrapper.nextInt();

		return new AcknowledgementRequest(code);
	}
}
