package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

import fr.pederobien.voxy.common.impl.effects.Effect;

public class PlayerAudioStreamAddOrUpdateEffectRequest {
	private final String playerName;
	private final Effect effect;

	/**
	 * Creates a request in order to add an effect on the audio stream of a player.
	 * 
	 * @param playerName The name of the player whose the audio stream shall be modified.
	 * @param effect     The effect to apply.
	 */
	public PlayerAudioStreamAddOrUpdateEffectRequest(String playerName, Effect effect) {
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
		if (!(obj instanceof PlayerAudioStreamAddOrUpdateEffectRequest))
			return false;

		PlayerAudioStreamAddOrUpdateEffectRequest other = (PlayerAudioStreamAddOrUpdateEffectRequest) obj;

		if (!playerName.equals(other.getPlayerName()))
			return false;

		if (effect == null && other.getEffect() == null)
			return true;

		return effect.equals(other.getEffect());
	}
}
