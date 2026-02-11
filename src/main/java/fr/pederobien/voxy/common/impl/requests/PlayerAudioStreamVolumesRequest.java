package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerAudioStreamVolumesRequest {
	private final String name;
	private final float left;
	private final float right;
	private final float global;

	/**
	 * Creates a request to send to the remote when the volumes of a player's audio stream has changed.
	 * 
	 * @param name   The name of the player whose audio stream volumes shall change.
	 * @param left   The volume on the left side.
	 * @param right  The volume on the right side.
	 * @param global The global volume on both side.
	 */
	public PlayerAudioStreamVolumesRequest(String name, float left, float right, float global) {
		this.name = name;
		this.left = left;
		this.right = right;
		this.global = global;
	}

	/**
	 * @return The name of the player whose audio stream volumes shall change.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The volume on the left side.
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * @return The volume on the right side.
	 */
	public float getRight() {
		return right;
	}

	/**
	 * @return The volume on both side.
	 */
	public float getGlobal() {
		return global;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("name=" + getName());
		joiner.add("left=" + getLeft());
		joiner.add("right=" + getRight());
		joiner.add("global=" + getGlobal());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamVolumesRequest))
			return false;

		PlayerAudioStreamVolumesRequest other = (PlayerAudioStreamVolumesRequest) obj;
		boolean equals = name.equals(other.getName());
		if (equals)
			equals = equals && (left == other.getLeft()) && (right == other.getRight()) && (global == other.getGlobal());

		return equals;
	}
}
