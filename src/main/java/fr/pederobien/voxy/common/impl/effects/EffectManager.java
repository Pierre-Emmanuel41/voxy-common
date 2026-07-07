package fr.pederobien.voxy.common.impl.effects;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EffectManager {
	private Map<String, Supplier<EffectDescription>> descriptions;

	/**
	 * Creates an effect manager that gather effect descriptions to be shared between a client and a server.
	 */
	public EffectManager() {
		descriptions = new HashMap<String, Supplier<EffectDescription>>();
	}

	/**
	 * Register a object that contains the description of an effect.
	 * 
	 * @param name     The name of the effect.
	 * @param supplier The object that creates the description of an effect.
	 * @return True if the parameters has been registered successfully, false otherwise.
	 */
	public boolean register(String name, Supplier<EffectDescription> supplier) {
		Supplier<EffectDescription> registered = descriptions.get(name);
		if (registered != null)
			return false;

		descriptions.put(name, supplier);
		return true;
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name   The name of the effect.
	 * @param values The array that contains effect parameters value.
	 * @return The description updated with the given values if registered, null otherwise.
	 */
	public EffectDescription getEffectDescription(String name, Object... values) {
		Supplier<EffectDescription> supplier = descriptions.get(name);
		if (supplier == null)
			return null;

		EffectDescription description = supplier.get();
		try {
			description.setValues(values);
			return description;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name The name of the effect.
	 * @param data The bytes array that contains effect parameters value.
	 * @return The description updated with the given bytes array if registered, null otherwise.
	 */
	public EffectDescription getEffectDescription(String name, byte[] data) {
		Supplier<EffectDescription> supplier = descriptions.get(name);
		if (supplier == null)
			return null;

		EffectDescription p = supplier.get();
		p.fromBytes(data);
		return p;
	}
}
