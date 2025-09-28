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
	PLAYER_ALREADY_EXIST(1, "A player with the given name already exists");

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
