package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

import fr.pederobien.voxy.common.impl.effects.EffectDescription;

public class PlayerAudioStreamAddEffectRequest {
	private final String playerName;
	private final byte index;
	private final EffectDescription description;

	/**
	 * Creates a request in order to add an effect on the audio stream of a player.
	 * 
	 * @param playerName  The name of the player whose the audio stream shall be modified.
	 * @param index       The index at which the effect shall be added.
	 * @param description The description of the effect to apply.
	 */
	public PlayerAudioStreamAddEffectRequest(String playerName, byte index, EffectDescription description) {
		this.playerName = playerName;
		this.index = index;
		this.description = description;
	}

	/**
	 * @return The name of player whose the audio stream should be modified by an effect.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return The index at which the effect shall be added.
	 */
	public byte getIndex() {
		return index;
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
		joiner.add("index=" + getIndex());
		joiner.add("description=" + getDescription());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamAddEffectRequest))
			return false;

		PlayerAudioStreamAddEffectRequest other = (PlayerAudioStreamAddEffectRequest) obj;

		if (!playerName.equals(other.getPlayerName()))
			return false;

		if (index != other.getIndex())
			return false;

		if (description == null && other.getDescription() == null)
			return true;

		return description.equals(other.getDescription());
	}
}
