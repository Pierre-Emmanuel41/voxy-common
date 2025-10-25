package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerMuteByRequest;

public class PlayerMuteByWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerMuteByRequest))
			throw new IllegalArgumentException("[PlayerMuteByWrapper] - the payload datatype shall be PlayerMuteByRequest");

		PlayerMuteByRequest request = (PlayerMuteByRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + target's name
		wrapper.putString(request.getTarget(), true);

		// Name's length + source's name
		wrapper.putString(request.getSource(), true);

		// Mute's status
		wrapper.put((byte) (request.isMute() ? 1 : 0));

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Target's name length
		int targetNameLength = wrapper.nextInt();

		// Target's name
		String target = wrapper.nextString(targetNameLength);

		// Source's name length
		int sourceNameLength = wrapper.nextInt();

		// Source's name
		String source = wrapper.nextString(sourceNameLength);

		// Mute's status
		boolean isMute = wrapper.next() == 1;

		return new PlayerMuteByRequest(target, source, isMute);
	}

}
