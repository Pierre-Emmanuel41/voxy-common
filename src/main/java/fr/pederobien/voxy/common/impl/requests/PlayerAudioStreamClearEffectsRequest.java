package fr.pederobien.voxy.common.impl.requests;

public class PlayerAudioStreamClearEffectsRequest {
	private final String name;

	/**
	 * Creates a request to remove all effects applied on an audio stream.
	 * 
	 * @param name The name of the stream.
	 */
	public PlayerAudioStreamClearEffectsRequest(String name) {
		this.name = name;
	}

	/**
	 * @return The name of the audio stream that shall not have effect anymore.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("{name=%s}", getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerAudioStreamClearEffectsRequest))
			return false;

		PlayerAudioStreamClearEffectsRequest other = (PlayerAudioStreamClearEffectsRequest) obj;
		return name.equals(other.getName());
	}
}
