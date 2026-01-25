package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerSpeakRequest;

public class PlayerSpeakWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerSpeakRequest))
			throw new IllegalArgumentException("[PlayerSpeakWrapper] - The payload data type shall be PlayerSpeakRequest");

		PlayerSpeakRequest request = (PlayerSpeakRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getName(), true);

		// Audio sample's size
		wrapper.putInt(request.getSample().length);

		// Audio sample's data
		wrapper.put(request.getSample());

		// Algorithm code
		wrapper.put(request.getAlgorithm());

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

		// Audio sample's size
		int dataSize = wrapper.nextInt();

		// Audio sample's data
		byte[] sample = wrapper.next(dataSize);

		// Algorithm code
		byte algorithm = wrapper.next();

		// Left side volume
		float left = wrapper.nextFloat();

		// Right side volume
		float right = wrapper.nextFloat();

		// Global volume
		float global = wrapper.nextFloat();

		return new PlayerSpeakRequest(name, sample, algorithm, left, right, global);
	}

}
