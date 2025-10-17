package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class RemoveRoomRequest {
	private String name;

	/**
	 * Creates a request to remove a room from the server.
	 *
	 * @param name The name of the room to remove.
	 */
	public RemoveRoomRequest(String name) {
		this.name = name;
	}

	/**
	 * @return The name of the room to remove.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("name=" + getName());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RemoveRoomRequest))
			return false;

		RemoveRoomRequest other = (RemoveRoomRequest) obj;
		return name.equals(other.getName());
	}
}
