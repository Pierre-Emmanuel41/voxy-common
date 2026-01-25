package fr.pederobien.voxy.common.impl.requests;

import java.util.Arrays;
import java.util.StringJoiner;

public class PlayerSpeakRequest {
	private final String name;
	private final byte[] sample;
	private final byte algorithm;
	private final float left;
	private final float right;
	private final float global;

	/**
	 * Creates a request to send to the remote when a player is speaking.
	 * 
	 * @param name      The name of the speaking player.
	 * @param sample    The bytes array that contains the audio sample.
	 * @param algorithm The code of the algorithm used to compress the audio sample.
	 * @param left      The volume on the left side.
	 * @param right     The volume on the right side.
	 * @param global    The global volume on both side.
	 */
	public PlayerSpeakRequest(String name, byte[] sample, byte algorithm, float left, float right, float global) {
		this.name = name;
		this.sample = sample;
		this.algorithm = algorithm;
		this.left = left;
		this.right = right;
		this.global = global;
	}

	/**
	 * Creates a request to send to the remote when a player is speaking.
	 * 
	 * @param name      The name of the speaking player.
	 * @param sample    The bytes array that contains the audio sample.
	 * @param algorithm The code of the algorithm used to compress the audio sample.
	 */
	public PlayerSpeakRequest(String name, byte[] sample, byte algorithm) {
		this(name, sample, algorithm, -1, -1, -1);
	}

	/**
	 * @return The name of the speaking player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The bytes array that contains the audio sample.
	 */
	public byte[] getSample() {
		return sample;
	}

	/**
	 * @return The code of the algorithm used to compress the audio sample.
	 */
	public byte getAlgorithm() {
		return algorithm;
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
		joiner.add("sampleSize=" + getSample().length);
		joiner.add("algorithm=" + getAlgorithm());
		joiner.add("left=" + getLeft());
		joiner.add("right=" + getRight());
		joiner.add("global=" + getGlobal());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerSpeakRequest))
			return false;

		PlayerSpeakRequest other = (PlayerSpeakRequest) obj;
		boolean equals = name.equals(other.getName());
		if (equals)
			equals = equals && Arrays.equals(sample, other.getSample());
		if (equals)
			equals = equals && (algorithm == other.getAlgorithm());
		if (equals)
			equals = equals && (left == other.getLeft()) && (right == other.getRight()) && (global == other.getGlobal());

		return equals;
	}
}
