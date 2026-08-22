package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

import fr.pederobien.voxy.common.impl.effects.EffectDescription;

public class PlayerAudioStreamUpdateEffectRequest {
	private final String playerName;
	private final EffectDescription description;

	/**
	 * Creates a request in order to modify the parameters of an effect.
	 * 
	 * @param playerName  The name of the player whose the audio stream shall be modified.
	 * @param description The description of the effect to apply.
	 */
	public PlayerAudioStreamUpdateEffectRequest(String playerName, EffectDescription description) {
		this.playerName = playerName;
		this.description = description;
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
	public EffectDescription getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("playerName=" + getPlayerName());
		joiner.add("description=" + getDescription());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamUpdateEffectRequest))
			return false;

		PlayerAudioStreamUpdateEffectRequest other = (PlayerAudioStreamUpdateEffectRequest) obj;

		if (!playerName.equals(other.getPlayerName()))
			return false;

		if (description == null && other.getDescription() == null)
			return true;

		return description.equals(other.getDescription());
	}
}
