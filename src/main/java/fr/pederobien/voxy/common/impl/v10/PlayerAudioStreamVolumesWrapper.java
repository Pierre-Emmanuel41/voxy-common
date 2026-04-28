package fr.pederobien.voxy.common.impl.v10;

import java.util.ArrayList;
import java.util.List;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamVolumesRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamVolumesRequest.VolumeInfo;

public class PlayerAudioStreamVolumesWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamVolumesRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamVolumesWrapper] - The payload data type shall be PlayerAudioStreamVolumesRequest");

		PlayerAudioStreamVolumesRequest request = (PlayerAudioStreamVolumesRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Number of volumes info
		wrapper.putInt(request.getVolumes().size());

		for (VolumeInfo info : request.getVolumes()) {

			// Name's length + player's name
			wrapper.putString(info.getName(), true);

			// Left side volume
			wrapper.putFloat(info.getLeft());

			// Right side volume
			wrapper.putFloat(info.getRight());

			// Global volume
			wrapper.putFloat(info.getGlobal());
		}

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Number of volumes info
		int size = wrapper.nextInt();

		List<VolumeInfo> volumes = new ArrayList<VolumeInfo>();

		for (int i = 0; i < size; i++) {

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

			volumes.add(new VolumeInfo(name, left, right, global));
		}

		return new PlayerAudioStreamVolumesRequest(volumes);
	}
}
