package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.RenameRoomRequest;

public class RenameRoomWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof RenameRoomRequest))
			throw new IllegalArgumentException("[RenameRoomWrapper] - The payload data type shall be RenameRoomRequest");

		RenameRoomRequest request = (RenameRoomRequest) payload;

		ByteWrapper wrapper = ByteWrapper.create();

		// Old name's length + old room's name
		wrapper.putString(request.getOldName(), true);

		// New name's length + new room's name
		wrapper.putString(request.getNewName(), true);
		return wrapper.get();
	}

	@Override
	public Object parse(byte[] bytes) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(bytes);

		// Old name's length
		int oldLength = wrapper.nextInt();

		// Old room's name
		String oldName = wrapper.nextString(oldLength);

		// New name's length
		int newLength = wrapper.nextInt();

		// New room's name
		String newName = wrapper.nextString(newLength);
		return new RenameRoomRequest(oldName, newName);
	}
}
