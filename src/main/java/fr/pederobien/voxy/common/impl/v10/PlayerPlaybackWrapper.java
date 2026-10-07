package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerPlaybackRequest;

public class PlayerPlaybackWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerPlaybackRequest))
			throw new IllegalArgumentException("[PlayerPlaybackWrapper] - the payload datatype shall be PlayerPlaybackRequest");

		PlayerPlaybackRequest request = (PlayerPlaybackRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Playback status
		wrapper.put((byte) (request.isPlayback() ? 1 : 0));

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int length = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(length);

		// Playback status
		boolean isPlayback = wrapper.next() == 1;

		return new PlayerPlaybackRequest(name, isPlayback);
	}
}
