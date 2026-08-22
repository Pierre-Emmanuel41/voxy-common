package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamRemoveEffectRequest;

public class PlayerAudioStreamRemoveEffectWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamRemoveEffectRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamRemoveEffectWrapper] - The payload data type shall be PlayerAudioStreamRemoveEffectRequest");

		PlayerAudioStreamRemoveEffectRequest request = (PlayerAudioStreamRemoveEffectRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length and player name
		wrapper.putString(request.getPlayerName(), true);

		// Name's length and effect name
		wrapper.putString(request.getEffectName(), true);

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Player name's length
		int playerNameLength = wrapper.nextInt();

		// Player's name
		String playerName = wrapper.nextString(playerNameLength);

		// Effect name's length
		int effectNameLength = wrapper.nextInt();

		// Effect's name
		String effectName = wrapper.nextString(effectNameLength);

		return new PlayerAudioStreamRemoveEffectRequest(playerName, effectName);
	}

}
