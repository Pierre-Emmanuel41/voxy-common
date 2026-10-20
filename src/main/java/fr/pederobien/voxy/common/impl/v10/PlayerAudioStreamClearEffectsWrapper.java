package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamClearEffectsRequest;

public class PlayerAudioStreamClearEffectsWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamClearEffectsRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamClearEffectsRequest] - The payload data type shall be PlayerAudioStreamClearEffectsRequest");

		PlayerAudioStreamClearEffectsRequest request = (PlayerAudioStreamClearEffectsRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int length = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(length);

		return new PlayerAudioStreamClearEffectsRequest(name);
	}

}
