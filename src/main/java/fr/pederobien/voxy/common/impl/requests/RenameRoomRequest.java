package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class RenameRoomRequest {
	private final String oldName;
	private final String newName;

	/**
	 * Creates a request to rename a room.
	 *
	 * @param oldName The old room's name.
	 * @param newName The new room's name.
	 */
	public RenameRoomRequest(String oldName, String newName) {
		this.oldName = oldName;
		this.newName = newName;
	}

	/**
	 * @return The old room's name.
	 */
	public String getOldName() {
		return oldName;
	}

	/**
	 * @return The new room's name.
	 */
	public String getNewName() {
		return newName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("oldName=" + getOldName());
		joiner.add("newName=" + getNewName());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RenameRoomRequest))
			return false;

		RenameRoomRequest other = (RenameRoomRequest) obj;
		return oldName.equals(other.getOldName()) && newName.equals(other.getNewName());
	}
}
