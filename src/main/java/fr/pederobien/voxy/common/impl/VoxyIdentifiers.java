package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.interfaces.IIdentifier;

public enum VoxyIdentifiers implements IIdentifier {

	/**
	 * Identifier to acknowledge a response.
	 */
	ACKOWLEDGEMENT(0, "Identifier used to acknowledge a response"),

	/**
	 * Identifier to gather the player properties.
	 */
	PLAYER_PROPERTIES(1, "Gathers the player properties"),

	/**
	 * Identifier to get the server's properties
	 */
	SERVER_PROPERTIES(2, "Gather server properties"),

	/**
	 * Identifier to add a room on a server.
	 */
	ADD_ROOM(3, "Adds a room on a server"),

	/**
	 * Identifier to remove a room from a server.
	 */
	REMOVE_ROOM(4, "Removes a room from a server"),

	/**
	 * Identifier to rename a room.
	 */
	RENAME_ROOM(5, "Renames a room"),

	/**
	 * Identifier to join a room to speak with other players.
	 */
	JOIN_ROOM(6, "Join a room");

	private int code;
	private String message;

	VoxyIdentifiers(int code, String message) {
		this.code = code;
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
}
