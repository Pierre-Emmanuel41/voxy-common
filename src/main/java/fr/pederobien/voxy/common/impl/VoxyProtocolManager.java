package fr.pederobien.voxy.common.impl;

import fr.pederobien.protocol.impl.ProtocolManager;
import fr.pederobien.protocol.interfaces.IProtocolManager;
import fr.pederobien.voxy.common.impl.v10.ProtocolV10;

public class VoxyProtocolManager {

    private static class Singleton {
        private static final IProtocolManager INSTANCE;

        static {
            INSTANCE = new ProtocolManager();

            // Registering errors
            INSTANCE.registerErrors(VoxyErrors.values());

            // Adding requests for version 1.0
            ProtocolV10.update(INSTANCE.getOrCreate(1.0f));
        }
    }

    /**
     * @return The protocol manager dedicated for a voxy client-server application.
     */
    public static IProtocolManager instance() {
        return Singleton.INSTANCE;
    }
}
