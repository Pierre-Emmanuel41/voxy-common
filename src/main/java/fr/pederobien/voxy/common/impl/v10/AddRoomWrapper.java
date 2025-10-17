package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.AddRoomRequest;

public class AddRoomWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof AddRoomRequest))
			throw new IllegalArgumentException("The payload data type shall be AddRoomRequest");

		AddRoomRequest request = (AddRoomRequest) payload;

		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + room's name
		wrapper.putString(request.getName(), true);

		// Room's port number
		wrapper.putInt(request.getPort());

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] bytes) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(bytes);

		// Name's length
		int length = wrapper.nextInt();

		// Room's name
		String name = wrapper.nextString(length);

		// Room's port number
		int port = wrapper.nextInt();

		return new AddRoomRequest(name, port);
	}
}
