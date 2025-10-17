package fr.pederobien.voxy.common.impl.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ServerPropertiesRequest {

	/**
	 * Creates a record to gather information about a player.
	 *
	 * @param name   The player's name.
	 * @param isMute The player's mute status.
	 * @param isDeaf The player's deaf status.
	 */
	public record PlayerInfo(String name, boolean isMute, boolean isDeaf) {

		@Override
		public String name() {
			return name;
		}

		@Override
		public boolean isMute() {
			return isMute;
		}

		@Override
		public boolean isDeaf() {
			return isDeaf;
		}

		@Override
		public String toString() {
			StringJoiner joiner = new StringJoiner(",", "{", "}");
			joiner.add("name=" + name());
			joiner.add("isMute=" + isMute());
			joiner.add("isDeaf=" + isDeaf());
			return joiner.toString();
		}

		@Override
		public final boolean equals(Object obj) {
			if (!(obj instanceof PlayerInfo))
				return false;

			PlayerInfo other = (PlayerInfo) obj;
			return name.equals(other.name()) && isMute == other.isMute() && isDeaf == other.isDeaf();
		}
	}

	/**
	 * Creates a record to gather information about a player.
	 *
	 * @param name    The room's name.
	 * @param port    The UDP port to communicate with other players
	 * @param players The list of players registered in the room.
	 */
	public record RoomInfo(String name, int port, List<PlayerInfo> players) {

		@Override
		public String name() {
			return name;
		}

		@Override
		public int port() {
			return port;
		}

		@Override
		public List<PlayerInfo> players() {
			return players;
		}

		@Override
		public String toString() {
			StringJoiner joiner = new StringJoiner(",", "{", "}");
			joiner.add("name=" + name());
			joiner.add("port=" + port());

			StringJoiner playersJoiner = new StringJoiner(",", "{", "}");
			for (PlayerInfo info : players)
				playersJoiner.add(info.toString());

			joiner.add("players=" + playersJoiner);
			return joiner.toString();
		}

		@Override
		public final boolean equals(Object obj) {
			if (!(obj instanceof RoomInfo))
				return false;

			RoomInfo other = (RoomInfo) obj;
			boolean equals = name.equals(other.name()) && port == other.port() && players.size() == other.players().size();

			for (int i = 0; i < players.size() && equals; i++)
				equals = equals && players.get(i).equals(other.players().get(i));

			return equals;
		}
	}

	private List<RoomInfo> rooms;
	private boolean getMode;

	/**
	 * Creates a request to send server properties.
	 *
	 * @param rooms The list of rooms and their properties.
	 */
	public ServerPropertiesRequest(List<RoomInfo> rooms) {
		this.rooms = rooms;
		getMode = false;
	}

	public ServerPropertiesRequest() {
		rooms = new ArrayList<RoomInfo>();
		getMode = true;
	}

	/**
	 * @return The list of rooms and their properties.
	 */
	public List<RoomInfo> getRooms() {
		return rooms;
	}

	/**
	 * @return true if the request direction is from Server to Client, false if the request direction is from Client to Server.
	 */
	public boolean isGetMode() {
		return getMode;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");

		if (isGetMode())
			joiner.add("GetMode - no payload");
		else {
			StringJoiner roomsJoiner = new StringJoiner(",", "{", "}");
			for (RoomInfo info : rooms)
				roomsJoiner.add(info.toString());

			joiner.add("rooms=" + roomsJoiner);
		}
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ServerPropertiesRequest))
			return false;

		ServerPropertiesRequest other = (ServerPropertiesRequest) obj;
		boolean equals = other.getRooms().size() == getRooms().size();

		for (int i = 0; i < getRooms().size() && equals; i++)
			equals = equals && getRooms().get(i).equals(other.getRooms().get(i));

		return equals;
	}
}
