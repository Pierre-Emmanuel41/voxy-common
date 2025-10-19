package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class LeaveRoomRequest {
	private final String roomName;
	private final String playerName;

	/**
	 * Creates a request to leave a room.
	 *
	 * @param roomName   The name of the room to leave.
	 * @param playerName The name of the player that leave a room.
	 */
	public LeaveRoomRequest(String roomName, String playerName) {
		this.roomName = roomName;
		this.playerName = playerName;
	}

	/**
	 * @return The name of the room to leave.
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @return The name of the player that leave a room.
	 */
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("room=" + getRoomName());
		joiner.add("player=" + getPlayerName());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LeaveRoomRequest))
			return false;

		LeaveRoomRequest other = (LeaveRoomRequest) obj;
		return roomName.equals(other.getRoomName()) && playerName.equals(other.getPlayerName());
	}
}
