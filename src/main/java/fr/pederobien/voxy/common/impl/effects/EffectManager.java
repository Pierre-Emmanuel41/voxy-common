package fr.pederobien.voxy.common.impl.effects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EffectManager {
	private Map<String, Supplier<Effect>> effects;

	/**
	 * Creates an effect manager that gather effects to be shared between a client and a server.
	 */
	public EffectManager() {
		effects = new HashMap<String, Supplier<Effect>>();
	}

	/**
	 * Register a object that contains the description of an effect.
	 * 
	 * @param name     The name of the effect.
	 * @param supplier The object that creates the effect.
	 * @return True if the parameters has been registered successfully, false otherwise.
	 */
	public boolean register(String name, Supplier<Effect> supplier) {
		Supplier<Effect> registered = effects.get(name);
		if (registered != null)
			return false;

		effects.put(name, supplier);
		return true;
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name   The name of the effect.
	 * @param values A map that gather effect parameter's name / parameter's value.
	 * @return The effect updated with the given values if registered, null otherwise.
	 */
	public Effect getEffect(String name, Map<String, Object> values) {
		Supplier<Effect> supplier = effects.get(name);
		if (supplier == null)
			return null;

		Effect effect = supplier.get();
		try {
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				EffectParameter parameter = effect.getParameter(entry.getKey());
				if (parameter == null)
					continue;

				parameter.setValue(entry.getValue());
			}
			return effect;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Check if there is an effect description registered for the given effect name.
	 * 
	 * @param name The name of the effect.
	 * @return The description updated with the given bytes array if registered, null otherwise.
	 */
	public Effect getEffect(String name) {
		Supplier<Effect> supplier = effects.get(name);
		if (supplier == null)
			return null;

		return supplier.get();
	}

	/**
	 * @return A list containing the name of each effect description registered for this manager.
	 */
	public List<String> getEffects() {
		return effects.keySet().stream().toList();
	}
}
