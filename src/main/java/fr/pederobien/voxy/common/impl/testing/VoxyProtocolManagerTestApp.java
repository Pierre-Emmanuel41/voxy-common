package fr.pederobien.voxy.common.impl.testing;

import fr.pederobien.utils.IExecutable;
import fr.pederobien.utils.event.Logger;

public class VoxyProtocolManagerTestApp {

	public static void main(String[] args) {
		Logger.instance().newLine(true).timeStamp(true).colorized(true).debug(true);

		runTest("runProtocolV10Test", () -> runProtocolV10Test());
	}

	private static void runProtocolV10Test() {
		ProtocolV10Test test = new ProtocolV10Test();

		test.checkVersion10Exist();
		test.acknowledgementTest();
		test.addRoomRequestTest();
		test.addRoomRequestWrongPayloadDatatypeTest();
		test.removeRoomRequestTest();
		test.removeRoomRequestWrongPayloadDatatypeTest();
		test.renameRoomRequestTest();
		test.renameRoomRequestWrongDatatypeTest();
		test.getPlayerPropertiesRequestTest();
		test.setPlayerPropertiesRequestTest();
		test.playerPropertiesRequestWrongDatatypeTest();
		test.getServerPropertiesTest();
		test.setServerPropertiesOneRoomNoPlayerTest();
		test.setServerPropertiesOneRoomTwoPlayersTest();
		test.setServerPropertiesTwoRoomsTwoPlayersTest();
		test.serverPropertiesWrongDatatypeTest();
	}

	private static void runTest(String testName, IExecutable test) {
		Logger.warning("Start of %s execution", testName);
		try {
			test.exec();
		} catch (Exception e) {
			Logger.error("Unexpected error: %s", e.getMessage());
			for (StackTraceElement trace : e.getStackTrace()) {
				Logger.error(trace.toString());
			}
		}

		sleep(1000);
		Logger.warning("End of %s execution", testName);
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
