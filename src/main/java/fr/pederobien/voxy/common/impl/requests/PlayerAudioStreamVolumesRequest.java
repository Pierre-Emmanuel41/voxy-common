package fr.pederobien.voxy.common.impl.requests;

import java.util.List;
import java.util.StringJoiner;

public class PlayerAudioStreamVolumesRequest {

	public record VolumeInfo(String name, float left, float right, float global) {

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
			if (!(obj instanceof VolumeInfo))
				return false;

			VolumeInfo other = (VolumeInfo) obj;
			boolean equals = name.equals(other.getName());
			if (equals)
				equals = equals && (left == other.getLeft()) && (right == other.getRight()) && (global == other.getGlobal());

			return equals;
		}
	}

	private List<VolumeInfo> volumes;

	/**
	 * Creates a request to send to the remote when the volumes of a player's audio stream has changed.
	 * 
	 * @param volumes The list that contains the new audio volumes to apply.
	 */
	public PlayerAudioStreamVolumesRequest(List<VolumeInfo> volumes) {
		this.volumes = volumes;
	}

	/**
	 * @return The list that contains the new audio volumes to apply.
	 */
	public List<VolumeInfo> getVolumes() {
		return volumes;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		for (VolumeInfo info : volumes)
			joiner.add(info.toString());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamVolumesRequest))
			return false;

		PlayerAudioStreamVolumesRequest other = (PlayerAudioStreamVolumesRequest) obj;

		boolean equals = volumes.size() == other.getVolumes().size();
		if (equals)
			for (int i = 0; i < volumes.size(); i++)
				equals = equals && volumes.get(i).equals(other.getVolumes().get(i));

		return equals;
	}
}
