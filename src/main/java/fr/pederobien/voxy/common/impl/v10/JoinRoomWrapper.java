package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.JoinRoomRequest;

public class JoinRoomWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof JoinRoomRequest))
			throw new IllegalArgumentException("[JoinRoomWrapper] - The payload data type shall be JoinRoomRequest");

		JoinRoomRequest request = (JoinRoomRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + room's name
		wrapper.putString(request.getRoomName(), true);

		// Name's length + player's name
		wrapper.putString(request.getPlayerName(), true);

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int roomNameLength = wrapper.nextInt();

		// Room's name
		String roomName = wrapper.nextString(roomNameLength);

		// Name's length
		int playerNameLength = wrapper.nextInt();

		// Player's name
		String playerName = wrapper.nextString(playerNameLength);

		return new JoinRoomRequest(roomName, playerName);
	}

}
