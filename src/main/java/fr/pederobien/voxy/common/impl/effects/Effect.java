package fr.pederobien.voxy.common.impl.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import fr.pederobien.voxy.common.impl.effects.converters.IValueConverter;

public class Effect {
	private final String name;
	private final List<EffectParameter> parameters;

	/**
	 * Creates an effect.
	 * 
	 * @param name The effect name.
	 */
	public Effect(String name) {
		this.name = name;

		parameters = new ArrayList<EffectParameter>();
	}

	/**
	 * @return The name of the effect.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieve the EffectParameter associated to the given name.
	 * 
	 * @param name The name of the parameter to retrieve.
	 * @return The parameter associated to the given name if registered, null otherwise.
	 */
	public EffectParameter getParameter(String name) {
		for (EffectParameter parameter : parameters)
			if (parameter.getName().equals(name))
				return parameter;

		return null;
	}

	/**
	 * @return A map where each entry contains the parameter name and the parameter value.
	 */
	public Map<String, Object> getParametersMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (EffectParameter parameter : parameters)
			map.put(parameter.getName(), parameter.getValue());

		return map;
	}

	/**
	 * @return An unmodifiable list of parameters registered for this effect.
	 */
	public List<EffectParameter> getParameters() {
		return Collections.unmodifiableList(parameters);
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("name=" + getName());

		StringJoiner paramJoiner = new StringJoiner(",", "{", "}");
		for (EffectParameter parameter : parameters)
			paramJoiner.add(String.format("%s=%s", parameter.getName(), parameter.getValue()));

		joiner.add(paramJoiner.toString());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Effect))
			return false;

		Effect other = (Effect) obj;

		if (!name.equals(other.getName()))
			return false;

		return parameters.equals(other.getParameters());
	}

	/**
	 * Add a parameter to the underlying list of parameters.
	 * 
	 * @param name      The name of the parameter.
	 * @param clazz     The data type of the parameter.
	 * @param converter The converter to use for bytes array generation/parsing.
	 */
	protected void add(String name, Class<?> clazz, IValueConverter converter) {
		parameters.add(new EffectParameter(name, clazz, converter));
	}
}
