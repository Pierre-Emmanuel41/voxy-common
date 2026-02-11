package fr.pederobien.voxy.common.impl.requests;

import java.util.Arrays;
import java.util.StringJoiner;

public class PlayerAudioStreamContentRequest {
	private final String name;
	private final byte[] sample;
	private final byte algorithm;

	/**
	 * Creates a request to send to the remote when a player is speaking.
	 * 
	 * @param name      The name of the speaking player.
	 * @param sample    The bytes array that contains the audio sample.
	 * @param algorithm The code of the algorithm used to compress the audio sample.
	 */
	public PlayerAudioStreamContentRequest(String name, byte[] sample, byte algorithm) {
		this.name = name;
		this.sample = sample;
		this.algorithm = algorithm;
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

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("name=" + getName());
		joiner.add("sampleSize=" + getSample().length);
		joiner.add("algorithm=" + getAlgorithm());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamContentRequest))
			return false;

		PlayerAudioStreamContentRequest other = (PlayerAudioStreamContentRequest) obj;
		boolean equals = name.equals(other.getName());
		if (equals)
			equals = equals && Arrays.equals(sample, other.getSample());
		if (equals)
			equals = equals && (algorithm == other.getAlgorithm());

		return equals;
	}
}
