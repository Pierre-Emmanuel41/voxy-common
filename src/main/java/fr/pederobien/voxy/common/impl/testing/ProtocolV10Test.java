package fr.pederobien.voxy.common.impl.testing;

import java.util.ArrayList;
import java.util.List;

import fr.pederobien.protocol.interfaces.IProtocol;
import fr.pederobien.protocol.interfaces.IRequest;
import fr.pederobien.utils.IExecutable;
import fr.pederobien.utils.event.Logger;
import fr.pederobien.voxy.common.impl.VoxyErrors;
import fr.pederobien.voxy.common.impl.VoxyIdentifiers;
import fr.pederobien.voxy.common.impl.VoxyProtocolManager;
import fr.pederobien.voxy.common.impl.requests.AcknowledgementRequest;
import fr.pederobien.voxy.common.impl.requests.AddRoomRequest;
import fr.pederobien.voxy.common.impl.requests.JoinRoomPendingRequest;
import fr.pederobien.voxy.common.impl.requests.JoinRoomRequest;
import fr.pederobien.voxy.common.impl.requests.LeaveRoomRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamContentRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerAudioStreamVolumesRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerDeafRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerMuteByRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerMuteRequest;
import fr.pederobien.voxy.common.impl.requests.PlayerPropertiesRequest;
import fr.pederobien.voxy.common.impl.requests.RemoveRoomRequest;
import fr.pederobien.voxy.common.impl.requests.RenameRoomRequest;
import fr.pederobien.voxy.common.impl.requests.ServerPropertiesRequest;
import fr.pederobien.voxy.common.impl.requests.ServerPropertiesRequest.PlayerInfo;
import fr.pederobien.voxy.common.impl.requests.ServerPropertiesRequest.RoomInfo;

public class ProtocolV10Test {

	public void checkVersion10Exist() {
		IExecutable test = () -> {
			IProtocol protocol = getProtocolV10();
			if (protocol == null)
				Logger.error("The version 1.0 shall be supported by the VoxyProtocolManager");
			else if (protocol.getVersion() != 1.0f)
				Logger.error("The protocol associated to version 1.0 does not have the correct version: %s", protocol.getVersion());
			else
				Logger.info("VoxyProtocolManager supports version 1.0");
		};

		runTest("checkVersion10Exist", test);
	}

	public void acknowledgementTest() {
		IExecutable test = () -> {
			AcknowledgementRequest payload = new AcknowledgementRequest(VoxyIdentifiers.ADD_ROOM);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.ACKNOWLEDGEMENT, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to acknowledge a response");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to acknowledge a response");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.equals(request)) {
				Logger.error("Failure to acknowledge a response");
				return;
			}

			Logger.info("Acknowledge response supported successfully");
		};

		runTest("acknowledgementTest", test);
	}

	public void acknowledgementWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.ACKNOWLEDGEMENT, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("AddRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("AddRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("acknowledgementWrongPayloadDatatypeTest", test);
	}

	public void addRoomRequestTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.ADD_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to add a room");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to add a room");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("AddRoomRequest bytes array generated and parsed successfully");
		};

		runTest("addRoomRequestTest", test);
	}

	public void addRoomRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.ADD_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("AddRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("AddRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("addRoomRequestWrongPayloadDatatypeTest", test);
	}

	public void removeRoomRequestTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.REMOVE_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to remove a room");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to remove a room");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("RemoveRoomRequest bytes array generated and parsed successfully");
		};

		runTest("removeRoomRequestTest", test);
	}

	public void removeRoomRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.REMOVE_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("RemoveRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("RemoveRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("removeRoomRequestWrongPayloadDatatypeTest", test);
	}

	public void renameRoomRequestTest() {
		IExecutable test = () -> {
			RenameRoomRequest payload = new RenameRoomRequest("general", "caporal");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.RENAME_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to rename a room");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to rename a room");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("RenameRoomRequest bytes array generated and parsed successfully");
		};

		runTest("renameRoomRequestTest", test);
	}

	public void renameRoomRequestWrongDatatypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.RENAME_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("RenameRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("RenameRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("renameRoomRequestWrongDatatypeTest", test);
	}

	public void getPlayerPropertiesRequestTest() {
		IExecutable test = () -> {
			PlayerPropertiesRequest payload = new PlayerPropertiesRequest();
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to get player's properties");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to get player's properties");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("GetPlayerProperties bytes array generated and parsed successfully");
		};

		runTest("getPlayerPropertiesRequestTest", test);
	}

	public void setPlayerPropertiesRequestTest() {
		IExecutable test = () -> {
			PlayerPropertiesRequest payload = new PlayerPropertiesRequest("Player 1", true, false);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to set player's properties");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to set player's properties");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("SetPlayerProperties bytes array generated and parsed successfully");
		};

		runTest("setPlayerPropertiesRequestTest", test);
	}

	public void playerPropertiesRequestWrongDatatypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerPropertiesWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerPropertiesWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerPropertiesRequestWrongDatatypeTest", test);
	}

	public void getServerPropertiesTest() {
		IExecutable test = () -> {
			ServerPropertiesRequest payload = new ServerPropertiesRequest();
			IRequest request = getProtocolV10().get(VoxyIdentifiers.SERVER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to get server's properties");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("GetServerProperties bytes array generated and parsed successfully");
		};

		runTest("getServerPropertiesTest", test);
	}

	public void setServerPropertiesOneRoomNoPlayerTest() {
		IExecutable test = () -> {
			List<RoomInfo> rooms = new ArrayList<ServerPropertiesRequest.RoomInfo>();
			rooms.add(new RoomInfo("Room 1", 12345, new ArrayList<ServerPropertiesRequest.PlayerInfo>()));

			ServerPropertiesRequest payload = new ServerPropertiesRequest(rooms);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.SERVER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to get server's properties");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("GetServerProperties bytes array generated and parsed successfully");
		};

		runTest("setServerPropertiesOneRoomNoPlayerTest", test);
	}

	public void setServerPropertiesOneRoomTwoPlayersTest() {
		IExecutable test = () -> {
			List<RoomInfo> rooms = new ArrayList<ServerPropertiesRequest.RoomInfo>();
			List<PlayerInfo> players = new ArrayList<ServerPropertiesRequest.PlayerInfo>();
			players.add(new PlayerInfo("Player 1", false, false));
			players.add(new PlayerInfo("Player 2", true, true));
			rooms.add(new RoomInfo("Room 1", 12345, players));

			ServerPropertiesRequest payload = new ServerPropertiesRequest(rooms);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.SERVER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to get server's properties");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("GetServerProperties bytes array generated and parsed successfully");
		};

		runTest("setServerPropertiesOneRoomTwoPlayersTest", test);
	}

	public void setServerPropertiesTwoRoomsTwoPlayersTest() {
		IExecutable test = () -> {
			List<RoomInfo> rooms = new ArrayList<ServerPropertiesRequest.RoomInfo>();
			List<PlayerInfo> room1players = new ArrayList<ServerPropertiesRequest.PlayerInfo>();
			room1players.add(new PlayerInfo("Player 1", false, false));
			room1players.add(new PlayerInfo("Player 2", true, true));
			rooms.add(new RoomInfo("Room 1", 12345, room1players));

			List<PlayerInfo> room2players = new ArrayList<ServerPropertiesRequest.PlayerInfo>();
			room2players.add(new PlayerInfo("Player 3", false, false));
			room2players.add(new PlayerInfo("Player 4", true, true));
			rooms.add(new RoomInfo("Room 2", 12345, room2players));

			ServerPropertiesRequest payload = new ServerPropertiesRequest(rooms);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.SERVER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to get server's properties");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("GetServerProperties bytes array generated and parsed successfully");
		};

		runTest("setServerPropertiesTwoRoomsTwoPlayersTest", test);
	}

	public void serverPropertiesWrongDatatypeTest() {
		IExecutable test = () -> {
			PlayerPropertiesRequest payload = new PlayerPropertiesRequest();
			IRequest request = getProtocolV10().get(VoxyIdentifiers.SERVER_PROPERTIES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("ServerPropertiesWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("ServerPropertiesWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("serverPropertiesWrongDatatypeTest", test);
	}

	public void joinRoomPendingRequestTest() {
		IExecutable test = () -> {
			JoinRoomPendingRequest payload = new JoinRoomPendingRequest("general", "player");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.JOIN_ROOM_PENDING, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to join the pending queue of a room");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("JoinRoomPendingRequest bytes array generated and parsed successfully");
		};

		runTest("joinRoomPendingRequestTest", test);
	}

	public void joinRoomPendingRequestWrongDataTypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.JOIN_ROOM_PENDING, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("JoinRoomPendingWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("JoinRoomPendingWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("joinRoomPendingRequestWrongDataTypeTest", test);
	}

	public void joinRoomRequestTest() {
		IExecutable test = () -> {
			JoinRoomRequest payload = new JoinRoomRequest("general", "player", true, true);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.JOIN_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to join a room");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("JoinRoomRequest bytes array generated and parsed successfully");
		};

		runTest("joinRoomRequestTest", test);
	}

	public void joinRoomRequestWrongDataTypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.JOIN_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("JoinRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("JoinRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("joinRoomRequestWrongDataTypeTest", test);
	}

	public void leaveRoomRequestTest() {
		IExecutable test = () -> {
			LeaveRoomRequest payload = new LeaveRoomRequest("general", "player");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.LEAVE_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to leave a room");
				return;
			}

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("LeaveRoomRequest bytes array generated and parsed successfully");
		};

		runTest("leaveRoomRequestTest", test);
	}

	public void leaveRoomRequestWrongDataTypeTest() {
		IExecutable test = () -> {
			AddRoomRequest payload = new AddRoomRequest("general", 12345);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.LEAVE_ROOM, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {
				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("LeaveRoomWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("LeaveRoomWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("leaveRoomRequestWrongDataTypeTest", test);
	}

	public void playerMuteRequestTest() {
		IExecutable test = () -> {
			PlayerMuteRequest payload = new PlayerMuteRequest("Player 1", true);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_MUTE, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to mute/unmute a player");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to mute/unmute a player");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("PlayerMuteRequest bytes array generated and parsed successfully");
		};

		runTest("playerMuteRequestTest", test);
	}

	public void playerMuteRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_MUTE, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerMuteWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerMuteWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerMuteRequestWrongPayloadDatatypeTest", test);
	}

	public void playerMuteByRequestTest() {
		IExecutable test = () -> {
			PlayerMuteByRequest payload = new PlayerMuteByRequest("Player 1", "Player 2", true);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_MUTE_BY, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to mute/unmute a player for another player");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to mute/unmute a player for another player");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("playerMuteByRequestTest bytes array generated and parsed successfully");
		};

		runTest("playerMuteByRequestTest", test);
	}

	public void playerMuteByRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_MUTE_BY, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerMuteByWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerMuteByWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerMuteByRequestWrongPayloadDatatypeTest", test);
	}

	public void playerDeafRequestTest() {
		IExecutable test = () -> {
			PlayerDeafRequest payload = new PlayerDeafRequest("Player 1", true);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_DEAF, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to deaf/undeaf a player");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to deaf/undeaf a player");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("PlayerDeafRequest bytes array generated and parsed successfully");
		};

		runTest("playerDeafRequestTest", test);
	}

	public void playerDeafRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_DEAF, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerDeafWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerDeafWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerDeafRequestWrongPayloadDatatypeTest", test);
	}

	public void playerAudioStreamContentRequestTest() {
		IExecutable test = () -> {
			PlayerAudioStreamContentRequest payload = new PlayerAudioStreamContentRequest("Player 1", new byte[15], (byte) 1);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_AUDIO_STREAM_CONTENT, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to send player's audio sample");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to send player's audio sample");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("PlayerSpeakRequest bytes array generated and parsed successfully");
		};

		runTest("playerAudioStreamContentRequestTest", test);
	}

	public void playerAudioStreamContentRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_AUDIO_STREAM_CONTENT, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerSpeakWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerSpeakWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerAudioStreamContentRequestWrongPayloadDatatypeTest", test);
	}

	public void playerAudioStreamVolumesRequestTest() {
		IExecutable test = () -> {
			PlayerAudioStreamVolumesRequest payload = new PlayerAudioStreamVolumesRequest("Player 1", 1.5678f, 0.65432f, 0.45632f);
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_AUDIO_STREAM_VOLUMES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request == null) {
				Logger.error("The protocol 1.0 shall support the request to send player's audio stream volumes");
				return;
			}

			Logger.info("The protocol 1.0 supports the request to send player's audio stream volumes");

			// Step 2: Verifying bytes generation
			IRequest parsed = VoxyProtocolManager.instance().parse(request.getBytes());
			if (!parsed.getPayload().equals(payload)) {
				Logger.error("Failure to parse the payload");
				return;
			}

			Logger.info("PlayerAudioStreamVolumesRequest bytes array generated and parsed successfully");
		};

		runTest("playerAudioStreamVolumesRequestTest", test);
	}

	public void playerAudioStreamVolumesRequestWrongPayloadDatatypeTest() {
		IExecutable test = () -> {
			RemoveRoomRequest payload = new RemoveRoomRequest("general");
			IRequest request = getProtocolV10().get(VoxyIdentifiers.PLAYER_AUDIO_STREAM_VOLUMES, VoxyErrors.NO_ERROR, payload);

			// Step 1: Verifying the request is supported
			if (request != null) {

				// Step 2: Verifying bytes generation
				try {
					VoxyProtocolManager.instance().parse(request.getBytes());
					Logger.error("PlayerAudioStreamVolumesWrapper did not throw the IllegalArgumentException");
				} catch (Exception e) {
					Logger.info("PlayerAudioStreamVolumesWrapper threw an expected Exception: %s", e.getMessage());
				}
			}
		};

		runTest("playerAudioStreamVolumesRequestWrongPayloadDatatypeTest", test);
	}

	private void runTest(String testName, IExecutable test) {
		Logger.warning("Begin %s", testName);
		try {
			test.exec();
		} catch (Exception e) {
			Logger.error("Unexpected error: %s", e.getMessage());
			for (StackTraceElement trace : e.getStackTrace()) {
				Logger.error(trace.toString());
			}
		}
		Logger.warning("End %s", testName);
	}

	/**
	 * @return The protocol associated to version 1.0.
	 */
	private IProtocol getProtocolV10() {
		return VoxyProtocolManager.instance().getOrCreate(1.0f);
	}
}
