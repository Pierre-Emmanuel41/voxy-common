package fr.pederobien.voxy.common.impl.requests;

import java.util.List;

public class ServerPropertiesRequest {

    /**
     * Creates a record to gather information about a player.
     *
     * @param name   The player's name.
     * @param isMute The player's mute status.
     * @param isDeaf The player's deaf status.
     */
    public record PlayerInfo(String name, boolean isMute, boolean isDeaf) {

        @Override
        public String name() {
            return name;
        }

        @Override
        public boolean isMute() {
            return isMute;
        }

        @Override
        public boolean isDeaf() {
            return isDeaf;
        }
    }

    /**
     * Creates a record to gather information about a player.
     *
     * @param name    The room's name.
     * @param port    The UDP port to communicate with other players
     * @param players The list of players registered in the room.
     */
    public record RoomInfo(String name, int port, List<PlayerInfo> players) {

        @Override
        public String name() {
            return name;
        }

        @Override
        public int port() {
            return port;
        }

        @Override
        public List<PlayerInfo> players() {
            return players;
        }
    }

    private List<RoomInfo> rooms;

    /**
     * Creates a request to send server properties.
     *
     * @param rooms The list of rooms and their properties.
     */
    public ServerPropertiesRequest(List<RoomInfo> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return The list of rooms and their properties.
     */
    public List<RoomInfo> getRooms() {
        return rooms;
    }
}
