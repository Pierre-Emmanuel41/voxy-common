package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class AddRoomRequest {
	private final String name;
	private final int port;

	/**
	 * Creates a request to add a room on a server.
	 *
	 * @param name The name of the room to add.
	 * @param port The UDP port number used for the players to communicate.
	 */
	public AddRoomRequest(String name, int port) {
		this.name = name;
		this.port = port;
	}

	/**
	 * @return The name of the room to add.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The UDP port number used for the players to communicate.
	 */
	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("name=" + getName());
		joiner.add("port=" + getPort());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AddRoomRequest))
			return false;

		AddRoomRequest other = (AddRoomRequest) obj;
		return name.equals(other.getName()) && port == other.getPort();
	}
}
