package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerDeafRequest;

public class PlayerDeafWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerDeafRequest))
			throw new IllegalArgumentException("[PlayerDeafWrapper] - The payload datatype shall be PlayerDeafRequest");

		PlayerDeafRequest request = (PlayerDeafRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Player's deaf status
		wrapper.put((byte) (request.isDeaf() ? 1 : 0));

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int playerNameLength = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(playerNameLength);

		// Player's deaf status
		boolean isDeaf = wrapper.next() == 1;

		return new PlayerDeafRequest(name, isDeaf);
	}

}
