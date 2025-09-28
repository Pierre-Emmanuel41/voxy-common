package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IWrapper;
import fr.pederobien.utils.ByteWrapper;
import fr.pederobien.utils.ReadableByteWrapper;
import fr.pederobien.voxy.common.impl.requests.ServerPropertiesRequest;

import java.util.ArrayList;
import java.util.List;

public class ServerPropertiesWrapper implements IWrapper {

    @Override
    public byte[] getBytes(Object payload) {
        if (!(payload instanceof ServerPropertiesRequest))
            return null;

        ServerPropertiesRequest request = (ServerPropertiesRequest) payload;
        ByteWrapper wrapper = ByteWrapper.create();

        // Number of rooms
        wrapper.putInt(request.getRooms().size());

        // For each room
        for (ServerPropertiesRequest.RoomInfo room : request.getRooms()) {

            // Name's length + room's name
            wrapper.putString(room.name(), true);

            // Room's port number
            wrapper.putInt(room.port());

            // Number of players
            wrapper.putInt(room.players().size());

            // For each player
            for (ServerPropertiesRequest.PlayerInfo player : room.players()) {

                // Name's length + player's name
                wrapper.putString(player.name(), true);

                // Player's mute status
                wrapper.put((byte) (player.isMute() ? 1 : 0));

                // Player's deaf status
                wrapper.put((byte) (player.isDeaf() ? 1 : 0));
            }
        }

        return wrapper.get();
    }

    @Override
    public Object parse(byte[] bytes) {
        ReadableByteWrapper wrapper = ReadableByteWrapper.wrap(bytes);

        // Number of rooms
        int roomSize = wrapper.nextInt();

        List<ServerPropertiesRequest.RoomInfo> rooms = new ArrayList<ServerPropertiesRequest.RoomInfo>();

        // For each room
        for (int i = 0; i < roomSize; i++) {

            // Name's length
            int roomNameLength = wrapper.nextInt();

            // Room's name
            String roomName = wrapper.nextString(roomNameLength);

            // Room's port number
            int port = wrapper.nextInt();

            // Number of players
            int playerSize = wrapper.nextInt();

            List<ServerPropertiesRequest.PlayerInfo> players = new ArrayList<ServerPropertiesRequest.PlayerInfo>();

            // For each player
            for (int j = 0; j < playerSize; j++) {

                // Name's length
                int playerNameLength = wrapper.nextInt();

                // Player's name
                String playerName = wrapper.nextString(playerNameLength);

                // Player's mute status
                boolean isMute = wrapper.next() == 1;

                // Player's deaf status
                boolean isDeaf = wrapper.next() == 1;

                players.add(new ServerPropertiesRequest.PlayerInfo(playerName, isMute, isDeaf));
            }

            rooms.add(new ServerPropertiesRequest.RoomInfo(roomName, port, players));
        }

        return new ServerPropertiesRequest(rooms);
    }
}
