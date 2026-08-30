package fr.pederobien.voxy.common.impl;

import java.util.Map;

import fr.pederobien.protocol.impl.ProtocolManager;
import fr.pederobien.protocol.interfaces.IProtocolManager;
import fr.pederobien.voxy.common.impl.effects.EchoEffect;
import fr.pederobien.voxy.common.impl.effects.Effect;
import fr.pederobien.voxy.common.impl.effects.EffectManager;
import fr.pederobien.voxy.common.impl.effects.NoEffect;
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
			INSTANCE.getEffectManager().register(NoEffect.NAME, () -> new NoEffect());
			INSTANCE.getEffectManager().register(EchoEffect.NAME, () -> new EchoEffect());
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
	 * @param name The name of the effect.
	 * @param data The bytes array that contains effect parameters value.
	 * @return The description updated with the given bytes array if registered, null otherwise.
	 */
	public static Effect getEffect(String effectName) {
		return instance().getEffectManager().getEffect(effectName);
	}

	/**
	 * Check if there is an effect registered for the given effect name.
	 * 
	 * @param effectName The name of the effect to retrieve.
	 * @param values     A map that contains the values of the parameters of the effect.
	 * @return The effect updated with the parameters updated if registered, null otherwise.
	 */
	public static Effect getEffect(String effectName, Map<String, Object> values) {
		return instance().getEffectManager().getEffect(effectName, values);
	}
}
