package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.interfaces.IError;

public enum VoxyErrors implements IError {
	/**
	 * Error code corresponding to no error.
	 */
	NO_ERROR(0, "No Error"),

	/**
	 * Error code to indicate that a player with a specific name already exists on the server.
	 */
	PLAYER_ALREADY_EXIST(1, "A player with the given name already exists"),

	/**
	 * Error code to indicate that no player with the given name exists.
	 */
	PLAYER_DOES_NOT_EXIST(2, "There no player registered with the given name"),

	/**
	 * Error code to indicate that a room is already registered for a specific name.
	 */
	ROOM_ALREADY_REGISTERED(3, "A room is already registered"),

	/**
	 * Error code to indicate that a room does not exist.
	 */
	ROOM_DOES_NOT_EXIST(4, "The room does not exist"),

	/**
	 * Error code to indicate that the player's name is incorrect.
	 */
	PLAYER_NAME_INCORRECT(5, "The player's name is incorrect"),

	/**
	 * Error code to indicate that a player with the same name is already registered in a room.
	 */
	PLAYER_ALREADY_REGISTERED(6, "The player is already registered in a room");

	private int code;
	private String message;

	VoxyErrors(int code, String message) {
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
