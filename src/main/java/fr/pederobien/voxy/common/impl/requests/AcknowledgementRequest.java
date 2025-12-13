package fr.pederobien.voxy.common.impl.requests;

import java.util.StringJoiner;

import fr.pederobien.protocol.interfaces.IIdentifier;
import fr.pederobien.voxy.common.impl.VoxyIdentifiers;

public class AcknowledgementRequest {
	private IIdentifier identifier;

	/**
	 * Creating a request to acknowledge a received request.
	 * 
	 * @param identifier The identifier of the request to acknowledge.
	 */
	public AcknowledgementRequest(IIdentifier identifier) {
		this.identifier = identifier;
	}

	/**
	 * Creating a request to acknowledge a received request.
	 * 
	 * @param code The code of the identifier of the request to acknowledge.
	 */
	public AcknowledgementRequest(int code) {
		identifier = null;

		for (VoxyIdentifiers identifier : VoxyIdentifiers.values())
			if (identifier.getCode() == code)
				this.identifier = identifier;

		if (identifier == null)
			throw new IllegalArgumentException(String.format("The code %s does not correspond to an existing identifier", code));
	}

	/**
	 * @return The identifier of the request to acknowledge.
	 */
	public IIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "{", "}");
		joiner.add("code=" + identifier.getCode());
		joiner.add("message=" + identifier.getMessage());
		return joiner.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AcknowledgementRequest))
			return false;

		AcknowledgementRequest other = (AcknowledgementRequest) obj;
		return identifier.getCode() == other.getIdentifier().getCode();
	}
}
