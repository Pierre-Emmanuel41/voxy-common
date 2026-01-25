package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.interfaces.IIdentifier;

public enum VoxyIdentifiers implements IIdentifier {

	/**
	 * Identifier to acknowledge a response.
	 */
	ACKNOWLEDGEMENT("Identifier used to acknowledge a response"),

	/**
	 * Identifier to gather the player properties.
	 */
	PLAYER_PROPERTIES("Gathers the player properties"),

	/**
	 * Identifier to get the server's properties
	 */
	SERVER_PROPERTIES("Gather server properties"),

	/**
	 * Identifier to add a room on a server.
	 */
	ADD_ROOM("Adds a room on a server"),

	/**
	 * Identifier to remove a room from a server.
	 */
	REMOVE_ROOM("Removes a room from a server"),

	/**
	 * Identifier to rename a room.
	 */
	RENAME_ROOM("Renames a room"),

	/**
	 * Identifier to join a room to speak with other players.
	 */
	JOIN_ROOM("Join a room"),

	/**
	 * Identifier to leave a room.
	 */
	LEAVE_ROOM("Leave a room"),

	/**
	 * Identifier to change the mute status of a player.
	 */
	PLAYER_MUTE("Change the player's mute status"),

	/**
	 * Identifier to change the mute status of a player regarding another player.
	 */
	PLAYER_MUTE_BY("Change the player's mute status for another player"),

	/**
	 * Identifier to change the deaf status of a player.
	 */
	PLAYER_DEAF("Change the player's deaf status"),

	/**
	 * Identifier that indicates that a player is speaking.
	 */
	PLAYER_SPEAK("Player's audio sample");

	private static int codeGenerator = 0;
	private int code;
	private String message;

	/**
	 * Creates a request identifier shared by a voxy client and a voxy server.
	 * 
	 * @param message The message associated to the identifier.
	 */
	VoxyIdentifiers(String message) {
		this.code = generateCode();
		this.message = message;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @return Increments the static code generator and returns the result.
	 */
	private int generateCode() {
		return codeGenerator++;
	}
}
