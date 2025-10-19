package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class JoinRoomRequest {
	private final String roomName;
	private final String playerName;
	private final boolean isMute;
	private final boolean isDeaf;

	/**
	 * Creates a request when a player joins a room.
	 * 
	 * @param roomName   The room's name.
	 * @param playerName The player's name.
	 * @param isMute     The player's mute status.
	 * @param isDeaf     The player's deaf status.
	 */
	public JoinRoomRequest(String roomName, String playerName, boolean isMute, boolean isDeaf) {
		this.roomName = roomName;
		this.playerName = playerName;
		this.isMute = isMute;
		this.isDeaf = isDeaf;
	}

	/**
	 * @return The name of the room to join.
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @return The name of the player.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return The player's mute status.
	 */
	public boolean isMute() {
		return isMute;
	}

	/**
	 * @return The player's deaf status.
	 */
	public boolean isDeaf() {
		return isDeaf;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("roomName=" + getRoomName());
		joiner.add("playerName=" + getPlayerName());
		joiner.add("isMute=" + isMute());
		joiner.add("isDeaf=" + isDeaf());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JoinRoomRequest))
			return false;

		JoinRoomRequest other = (JoinRoomRequest) obj;
		return roomName.equals(other.getRoomName()) && playerName.equals(other.getPlayerName()) && isMute == other.isMute() && isDeaf == other.isDeaf();
	}
}
