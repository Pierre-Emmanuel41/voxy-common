package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.VoxyManagers;
import fr.pederobien.voxy.common.impl.effects.Effect;
import fr.pederobien.voxy.common.impl.effects.EffectParameter;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamUpdateEffectRequest;

public class PlayerAudioStreamUpdateEffectWrapper implements IWrapper {

	@Override
	public byte[] getBytes(Object payload) {
		if (!(payload instanceof PlayerAudioStreamUpdateEffectRequest))
			throw new IllegalArgumentException("[PlayerAudioStreamUpdateEffectWrapper] - The payload data type shall be PlayerAudioStreamUpdateEffectRequest");

		PlayerAudioStreamUpdateEffectRequest request = (PlayerAudioStreamUpdateEffectRequest) payload;
		ByteWrapper wrapper = ByteWrapper.create();

		// Name's length + player's name
		wrapper.putString(request.getPlayerName(), true);

		// Name's length + effect's name
		wrapper.putString(request.getEffect().getName(), true);

		// Number of parameters
		wrapper.putInt(request.getEffect().getParameters().size());

		// Effect's parameters
		for (EffectParameter parameter : request.getEffect().getParameters()) {

			// Name's length and parameter's name
			wrapper.putString(parameter.getName(), true);

			byte[] valueBytes = parameter.toBytes();

			// Parameter's value length
			wrapper.putInt(valueBytes.length);

			// Parameter's value
			wrapper.put(valueBytes);
		}

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

		// Effect's name
		String effectName = wrapper.nextString(effectNameLength);

		Effect effect = VoxyManagers.getEffect(effectName);

		// Effect not supported
		if (effect == null)
			return null;

		// Number of parameters
		int count = wrapper.nextInt();

		for (int i = 0; i < count; i++) {

			// Name's length
			int parameterNameLength = wrapper.nextInt();

			// Parameter's name
			String parameterName = wrapper.nextString(parameterNameLength);

			EffectParameter parameter = effect.getParameter(parameterName);

			if (parameter == null)
				continue;

			// Parameter's value length
			int parameterValueLength = wrapper.nextInt();

			parameter.fromBytes(wrapper.next(parameterValueLength));
		}

		return new PlayerAudioStreamUpdateEffectRequest(playerName, effect);
	}
}
