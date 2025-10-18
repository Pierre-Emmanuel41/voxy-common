package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class JoinRoomRequest {
	private final String roomName;
	private final String playerName;

	public JoinRoomRequest(String roomName, String playerName) {
		this.roomName = roomName;
		this.playerName = playerName;
	}

	public String getRoomName() {
		return roomName;
	}

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
		if (!(obj instanceof JoinRoomRequest))
			return false;

		JoinRoomRequest other = (JoinRoomRequest) obj;
		return roomName.equals(other.getRoomName()) && playerName.equals(other.getPlayerName());
	}
}
