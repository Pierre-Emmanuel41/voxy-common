package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerAudioStreamRemoveEffectRequest {
	private final String playerName;
	private final String effectName;

	/**
	 * Creates a request to remove an effect on the audio stream of a player.
	 * 
	 * @param playerName The name of the player for which an effect shall be removed.
	 * @param effectName The name of the effect to remove.
	 */
	public PlayerAudioStreamRemoveEffectRequest(String playerName, String effectName) {
		this.playerName = playerName;
		this.effectName = effectName;
	}

	/**
	 * @return The name of the player for which an effect shall be removed.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return The name of the effect to remove.
	 */
	public String getEffectName() {
		return effectName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("playerName=" + getPlayerName());
		joiner.add("effectName=" + getEffectName());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamRemoveEffectRequest))
			return false;

		PlayerAudioStreamRemoveEffectRequest other = (PlayerAudioStreamRemoveEffectRequest) obj;
		return playerName.equals(other.getPlayerName()) && effectName.equals(other.getEffectName());
	}
}
