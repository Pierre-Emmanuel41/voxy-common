package fr.pederobien.voxy.common.impl.requests;

public class PlayerPropertiesRequest {
    private final String name;
    private final boolean isMute;
    private final boolean isDeaf;
    private final boolean getMode;

    /**
     * Creates a request to send back to the server the player's properties.
     *
     * @param name   The player's name.
     * @param isMute The player's mute status.
     * @param isDeaf The player's deaf status.
     */
    public PlayerPropertiesRequest(String name, boolean isMute, boolean isDeaf) {
        this.name = name;
        this.isMute = isMute;
        this.isDeaf = isDeaf;

        getMode = false;
    }

    /**
     * Creates a request to send to the client to get the player's properties
     */
    public PlayerPropertiesRequest() {
        name = "";
        isMute = false;
        isDeaf = false;
        getMode = true;
    }

    /**
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The player's mute status.
     */
    public boolean isMute() {
        return isMute;
    }

    /**
     * @return The player's deaf status.
     */
    public boolean isDeaf() {
        return isDeaf;
    }

    /**
     * @return true if the request direction is from Server to Client, false if the request direction is from Client to Server.
     */
    public boolean isGetMode() {
        return getMode;
    }
}
