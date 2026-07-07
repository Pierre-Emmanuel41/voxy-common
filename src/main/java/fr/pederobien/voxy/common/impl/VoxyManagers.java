package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.impl.ProtocolManager;
import fr.pederobien.protocol.interfaces.IProtocolManager;
import fr.pederobien.voxy.common.impl.effects.EchoEffectDescription;
import fr.pederobien.voxy.common.impl.effects.EffectDescription;
import fr.pederobien.voxy.common.impl.effects.EffectManager;
import fr.pederobien.voxy.common.impl.effects.NoEffectDescription;
import fr.pederobien.voxy.common.impl.v10.ProtocolV10;

public class VoxyManagers {
	private IProtocolManager protocolManager;
	private EffectManager effectManager;

	/**
	 * Creates a VoxyProtocolManager.
	 */
	private VoxyManagers() {
		protocolManager = new ProtocolManager();
		effectManager = new EffectManager();
	}

	private static class Singleton {
		private static final VoxyManagers INSTANCE;

		static {
			INSTANCE = new VoxyManagers();

			// Registering errors
			INSTANCE.getProtocolManager().registerErrors(VoxyErrors.values());

			// Adding requests for version 1.0
			ProtocolV10.update(INSTANCE.getProtocolManager().getOrCreate(1.0f));

			// Adding native effects
			INSTANCE.getEffectManager().register(NoEffectDescription.NAME, () -> new NoEffectDescription());
			INSTANCE.getEffectManager().register(EchoEffectDescription.NAME, () -> new EchoEffectDescription());
		}
	}

	/**
	 * @return The manager responsible create requests or parse bytes array to create requests.
	 */
	public IProtocolManager getProtocolManager() {
		return protocolManager;
	}

	/**
	 * @return The manager responsible to gather effect property to be shared between a client and a server.
	 */
	public EffectManager getEffectManager() {
		return effectManager;
	}

	/**
	 * @return The protocol manager dedicated for a voxy client-server application.
	 */
	public static VoxyManagers instance() {
		return Singleton.INSTANCE;
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name   The name of the effect.
	 * @param params The array that contains effect parameters value.
	 * @return The description updated with the given values if registered, null otherwise.
	 */
	public static EffectDescription getEffectDescription(String effectName, Object... params) {
		return instance().getEffectManager().getEffectDescription(effectName, params);
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name The name of the effect.
	 * @param data The bytes array that contains effect parameters value.
	 * @return The description updated with the given bytes array if registered, null otherwise.
	 */
	public static EffectDescription getEffectDescription(String effectName, byte[] data) {
		return instance().getEffectManager().getEffectDescription(effectName, data);
	}
}
