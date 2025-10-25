package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerDeafRequest {
	private final String name;
	private final boolean isDeaf;

	/**
	 * Creates a request when the player's deaf status has changed.
	 * 
	 * @param name   The player's name.
	 * @param isDeaf The new player's deaf status.
	 */
	public PlayerDeafRequest(String name, boolean isDeaf) {
		this.name = name;
		this.isDeaf = isDeaf;
	}

	/**
	 * @return The player's name whose the deaf status has changed.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return True if the player is deaf, false otherwise.
	 */
	public boolean isDeaf() {
		return isDeaf;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("player=" + getName());
		joiner.add("isDeaf=" + isDeaf());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerDeafRequest))
			return false;

		PlayerDeafRequest other = (PlayerDeafRequest) obj;
		return name.equals(other.getName()) && isDeaf == other.isDeaf();
	}
}
