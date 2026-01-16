package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.interfaces.IError;

public enum VoxyErrors implements IError {
	/**
	 * Error code corresponding to no error.
	 */
	NO_ERROR("No Error"),

	/**
	 * Error code to indicate that a server plugin cancelled the request.
	 */
	CANCELLED("Operation cancelled by external source"),

	/**
	 * Error code to indicate that a player with a specific name already exists on the server.
	 */
	PLAYER_ALREADY_EXIST("A player with the given name already exists"),

	/**
	 * Error code to indicate that no player with the given name exists.
	 */
	PLAYER_DOES_NOT_EXIST("There is no player registered with the given name"),

	/**
	 * Error code to indicate that the player's name is incorrect.
	 */
	PLAYER_NAME_INCORRECT("The player's name is incorrect"),

	/**
	 * Error code to indicate that a player with the same name is already registered in a room.
	 */
	PLAYER_ALREADY_REGISTERED("The player is already registered in a room"),

	/**
	 * Error code to indicate that a player is not registered in a room.
	 */
	PLAYER_NOT_REGISTERED("The player is not registered in a room"),

	/**
	 * Error code to indicate that a source player already muted the target player.
	 */
	PLAYER_ALREADY_MUTED("The source player already muted the target player"),

	/**
	 * Error code to indicate that a source player did not mute the target player.
	 */
	PLAYER_NOT_MUTED("The source player did not mute the target player"),

	/**
	 * Error code to indicate that a room is already registered for a specific name.
	 */
	ROOM_ALREADY_REGISTERED("A room is already registered"),

	/**
	 * Error code to indicate that a room does not exist.
	 */
	ROOM_DOES_NOT_EXIST("The room does not exist");

	private static int codeGenerator = 0;
	private int code;
	private String message;

	/**
	 * Creates a error code shared between a voxy client and a voxy server.
	 * 
	 * @param message The message associated to the error.
	 */
	VoxyErrors(String message) {
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
