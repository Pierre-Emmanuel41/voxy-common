package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerPropertiesRequest;

public class PlayerPropertiesWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerPropertiesRequest))
			throw new IllegalArgumentException("[PlayerPropertiesWrapper] - The payload data type shall be PlayerPropertiesRequest");

		PlayerPropertiesRequest request = (PlayerPropertiesRequest) payload;

		ByteWrapper wrapper = ByteWrapper.create();
		if (request.isGetMode())
			return wrapper.get();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Player's mute status
		wrapper.put((byte) (request.isMute() ? 1 : 0));

		// Player's deaf status
		wrapper.put((byte) (request.isDeaf() ? 1 : 0));

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] bytes) {
		if (bytes.length == 0)
			return new PlayerPropertiesRequest();

		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(bytes);

		// Name's length
		int length = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(length);

		// Player's mute status
		boolean isMute = wrapper.next() == 1;

		// Player's deaf status
		boolean isDeaf = wrapper.next() == 1;

		return new PlayerPropertiesRequest(name, isMute, isDeaf);
	}
}
