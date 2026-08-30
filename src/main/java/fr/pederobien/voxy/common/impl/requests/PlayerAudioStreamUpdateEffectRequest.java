package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

import fr.pederobien.voxy.common.impl.effects.Effect;

public class PlayerAudioStreamUpdateEffectRequest {
	private final String playerName;
	private final Effect effect;

	/**
	 * Creates a request in order to modify the parameters of an effect.
	 * 
	 * @param playerName The name of the player whose the audio stream shall be modified.
	 * @param effect     The effect whose the parameters shall be updated.
	 */
	public PlayerAudioStreamUpdateEffectRequest(String playerName, Effect effect) {
		this.playerName = playerName;
		this.effect = effect;
	}

	/**
	 * @return The name of player whose the audio stream should be modified by an effect.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return The description of the effect.
	 */
	public Effect getEffect() {
		return effect;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("playerName=" + getPlayerName());
		joiner.add("effect=" + getEffect());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamUpdateEffectRequest))
			return false;

		PlayerAudioStreamUpdateEffectRequest other = (PlayerAudioStreamUpdateEffectRequest) obj;

		if (!playerName.equals(other.getPlayerName()))
			return false;

		if (effect == null && other.getEffect() == null)
			return true;

		return effect.equals(other.getEffect());
	}
}
