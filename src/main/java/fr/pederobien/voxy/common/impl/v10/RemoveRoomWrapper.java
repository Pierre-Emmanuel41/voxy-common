package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.RemoveRoomRequest;

public class RemoveRoomWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof RemoveRoomRequest))
			return null;

		RemoveRoomRequest request = (RemoveRoomRequest) payload;

		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + room name
		wrapper.putString(request.getName(), true);
		return wrapper.get();
	}

	@Override
	public Object parse(byte[] bytes) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(bytes);

		// Name's length
		int length = wrapper.nextInt();

		// Room's name
		String name = wrapper.nextString(length);

		return new RemoveRoomRequest(name);
	}
}
