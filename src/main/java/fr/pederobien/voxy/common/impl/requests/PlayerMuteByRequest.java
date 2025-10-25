package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

public class PlayerMuteByRequest {
	private final String target;
	private final String source;
	private final boolean isMute;

	/**
	 * Creates a request when a player mutes/unmutes another player for himself.
	 * 
	 * @param target The player to mute/unmute.
	 * @param source The player for which the target player is muted/unmuted.
	 * @param isMute True if the player is mute, false otherwise.
	 */
	public PlayerMuteByRequest(String target, String source, boolean isMute) {
		this.target = target;
		this.source = source;
		this.isMute = isMute;
	}

	/**
	 * @return The player to mute/unmute.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return The player for which the target player is muted/unmuted.
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return True if the target player is muted byte the source player, false otherwise.
	 */
	public boolean isMute() {
		return isMute;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("target=" + getTarget());
		joiner.add("source=" + getSource());
		joiner.add("isMute=" + isMute());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlayerMuteByRequest))
			return false;

		PlayerMuteByRequest other = (PlayerMuteByRequest) obj;
		return target.equals(other.getTarget()) && source.equals(other.getSource()) && isMute == other.isMute();
	}
}
