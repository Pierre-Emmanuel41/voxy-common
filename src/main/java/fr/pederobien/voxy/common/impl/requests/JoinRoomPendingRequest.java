package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class JoinRoomPendingRequest {
	private final String roomName;
	private String playerName;

	/**
	 * Creates a request to notify that a player has joined the pending queue of a room.
	 * 
	 * @param room   The name of the room.
	 * @param player The name of the player.
	 */
	public JoinRoomPendingRequest(String room, String player) {
		this.roomName = room;
		this.playerName = player;
	}

	/**
	 * @return The name of the room a player is about to join.
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @return The name of the player that is about to join a room.
	 */
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("roomName=" + getRoomName());
		joiner.add("playerName=" + getPlayerName());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JoinRoomPendingRequest))
			return false;

		JoinRoomPendingRequest other = (JoinRoomPendingRequest) obj;
		return getRoomName().equals(other.getRoomName()) && getPlayerName().equals(other.getPlayerName());
	}
}
