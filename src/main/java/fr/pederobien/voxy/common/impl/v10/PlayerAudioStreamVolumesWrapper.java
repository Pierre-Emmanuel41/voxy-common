package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamVolumesRequest;

public class PlayerAudioStreamVolumesWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamVolumesRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamVolumesWrapper] - The payload data type shall be PlayerAudioStreamVolumesRequest");

		PlayerAudioStreamVolumesRequest request = (PlayerAudioStreamVolumesRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Left side volume
		wrapper.putFloat(request.getLeft());

		// Right side volume
		wrapper.putFloat(request.getRight());

		// Global volume
		wrapper.putFloat(request.getGlobal());

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int nameLength = wrapper.nextInt();

		// Player's name
		String name = wrapper.nextString(nameLength);

		// Left side volume
		float left = wrapper.nextFloat();

		// Right side volume
		float right = wrapper.nextFloat();

		// Global volume
		float global = wrapper.nextFloat();

		return new PlayerAudioStreamVolumesRequest(name, left, right, global);
	}
}
