package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerPlaybackRequest {
	private final String name;
	private final boolean isPlayback;

	/**
	 * Creates a request to modify the playback status of a player.
	 * 
	 * @param name       The name of the player.
	 * @param isPlayback The new playback status.
	 */
	public PlayerPlaybackRequest(String name, boolean isPlayback) {
		this.name = name;
		this.isPlayback = isPlayback;
	}

	/**
	 * @return The name of the player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return True if the player shall hear its voice, false otherwise.
	 */
	public boolean isPlayback() {
		return isPlayback;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("player=" + getName());
		joiner.add("isPlayback=" + isPlayback());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerPlaybackRequest))
			return false;

		PlayerPlaybackRequest other = (PlayerPlaybackRequest) obj;
		return name.equals(other.getName()) && isPlayback == other.isPlayback();
	}
}
