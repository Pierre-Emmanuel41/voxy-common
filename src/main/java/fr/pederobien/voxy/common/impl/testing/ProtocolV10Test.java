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
import fr.pederobien.voxy.common.impl.requests.AddRoomRequest;
import fr.pederobien.voxy.common.impl.requests.JoinRoomRequest;
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
			IRequest request = getProtocolV10().get(VoxyIdentifiers.ACKOWLEDGEMENT, VoxyErrors.NO_ERROR, null);

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
