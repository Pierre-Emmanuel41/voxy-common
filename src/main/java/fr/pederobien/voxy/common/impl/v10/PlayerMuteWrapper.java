package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerMuteRequest;

public class PlayerMuteWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerMuteRequest))
			throw new IllegalArgumentException("[PlayerMuteWrapper] - The payload datatype shall be PlayerMuteRequest");

		PlayerMuteRequest request = (PlayerMuteRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Player's mute status
		wrapper.put((byte) (request.isMute() ? 1 : 0));

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int playerNameLength = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(playerNameLength);

		// Player's mute status
		boolean isMute = wrapper.next() == 1;

		return new PlayerMuteRequest(name, isMute);
	}

}
