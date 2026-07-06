package fr.pederobien.voxy.common.impl.v10;

import fr.pederobien.protocol.interfaces.IProtocol;
import fr.pederobien.voxy.common.impl.VoxyIdentifiers;

public class ProtocolV10 {

	/**
	 * Fill the given protocol with all supported requests for version 1.0f
	 *
	 * @param protocol The protocol to update.
	 */
	public static final void update(IProtocol protocol) {
		float version = protocol.getVersion();
		if (version != 1.0f)
			throw new IllegalArgumentException(String.format("The protocol version shall be 1.0 instead of %s", version));

		// Registering requests for this protocol
		protocol.register(VoxyIdentifiers.ACKNOWLEDGEMENT, new AcknowledgementWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_PROPERTIES, new PlayerPropertiesWrapper());
		protocol.register(VoxyIdentifiers.SERVER_PROPERTIES, new ServerPropertiesWrapper());
		protocol.register(VoxyIdentifiers.ADD_ROOM, new AddRoomWrapper());
		protocol.register(VoxyIdentifiers.REMOVE_ROOM, new RemoveRoomWrapper());
		protocol.register(VoxyIdentifiers.RENAME_ROOM, new RenameRoomWrapper());
		protocol.register(VoxyIdentifiers.JOIN_ROOM_PENDING, new JoinRoomPendingWrapper());
		protocol.register(VoxyIdentifiers.JOIN_ROOM, new JoinRoomWrapper());
		protocol.register(VoxyIdentifiers.LEAVE_ROOM, new LeaveRoomWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_MUTE, new PlayerMuteWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_MUTE_BY, new PlayerMuteByWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_DEAF, new PlayerDeafWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_AUDIO_STREAM_CONTENT, new PlayerAudioStreamContentWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_AUDIO_STREAM_VOLUMES, new PlayerAudioStreamVolumesWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_AUDIO_STREAM_EFFECT, new PlayerAudioStreamEffectWrapper());
		protocol.register(VoxyIdentifiers.PLAYER_AUDIO_STREAM_EFFECT_UPDATE, new PlayerAudioStreamEffectWrapper());
	}
}
