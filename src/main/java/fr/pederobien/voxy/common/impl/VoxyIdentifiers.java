package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.interfaces.IIdentifier;

public enum VoxyIdentifiers implements IIdentifier {
    /**
     * Identifier to add a room on a server.
     */
    ADD_ROOM(1, "Adds a room on a server"),

    /**
     * Identifier to remove a room from a server.
     */
    REMOVE_ROOM(2, "Removes a room from a server"),

    /**
     * Identifier to rename a room.
     */
    RENAME_ROOM(3, "Rename a room"),

    /**
     * Identifier to gather the player properties.
     */
    PLAYER_PROPERTIES(4, "Gather the player properties"),

    /**
     *
     */
    SERVER_PROPERTIES(5, "Gather server properties");


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
