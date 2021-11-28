package inc.evil.outbox.order.event;

import com.fasterxml.jackson.databind.JsonNode;

public interface ExportedEvent {
	String getAggregateId();

	String getAggregateType();

	String getType();

	JsonNode getPayload();
}
