package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerMuteRequest {
	private final String name;
	private final boolean isMute;

	/**
	 * Creates a request when the player's mute status has changed.
	 * 
	 * @param name   The player's name.
	 * @param isMute The new player's mute status.
	 */
	public PlayerMuteRequest(String name, boolean isMute) {
		this.name = name;
		this.isMute = isMute;
	}

	/**
	 * @return The player's name whose the mute status has changed.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return True if the player is mute, false otherwise.
	 */
	public boolean isMute() {
		return isMute;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("player=" + getName());
		joiner.add("isMute=" + isMute());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerMuteRequest))
			return false;

		PlayerMuteRequest other = (PlayerMuteRequest) obj;
		return name.equals(other.getName()) && isMute == other.isMute();
	}
}
