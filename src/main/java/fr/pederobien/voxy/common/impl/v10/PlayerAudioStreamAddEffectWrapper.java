package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.VoxyManagers;
import fr.pederobien.voxy.common.impl.effects.EffectDescription;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamAddEffectRequest;

public class PlayerAudioStreamAddEffectWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamAddEffectRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamAddEffectWrapper] - The payload data type shall be PlayerAudioStreamAddEffectRequest");

		PlayerAudioStreamAddEffectRequest request = (PlayerAudioStreamAddEffectRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getPlayerName(), true);

		// Effect's index
		wrapper.put(request.getIndex());

		// Name's length + effect's name
		wrapper.putString(request.getDescription().getEffectName(), true);

		// Effect's parameters
		wrapper.put(request.getDescription().getBytes());

		return wrapper.get();
	}

	@Override
	public Object parse(byte[] data) {
		ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(data);

		// Name's length
		int playerNameLength = wrapper.nextInt();

		// Player's name
		String playerName = wrapper.nextString(playerNameLength);

		// Name's length
		int effectNameLength = wrapper.nextInt();

		// Effect's index
		byte index = wrapper.next();

		// Effect's name
		String effectName = wrapper.nextString(effectNameLength);

		EffectDescription parameters = VoxyManagers.getEffectDescription(effectName, wrapper.next(-1));
		return new PlayerAudioStreamAddEffectRequest(playerName, index, parameters);
	}
}
