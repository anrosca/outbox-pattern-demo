package inc.evil.outbox.shipment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaEventConsumer {
	private final OrderEventHandler orderEventHandler;

	private final ObjectMapper objectMapper;

	public KafkaEventConsumer(OrderEventHandler orderEventHandler, ObjectMapper objectMapper) {
		this.orderEventHandler = orderEventHandler;
		this.objectMapper = objectMapper;
	}

	@KafkaListener(topics = "outbox_event")
	public void on(String value) {
		JsonNode jsonNode = deserialize(value);
		orderEventHandler.onOrderEvent(
				jsonNode.get("type").asText(),
				jsonNode.get("aggregate_type").asText(),
				jsonNode.get("aggregate_id").asText(),
				deserializePayload(jsonNode)
		);
	}

	private JsonNode deserializePayload(JsonNode jsonNode) {
		try {
			String unescaped = jsonNode.get("payload").asText();
			return objectMapper.readTree(unescaped);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private JsonNode deserialize(String value) {
		try {
			JsonNode jsonNode = objectMapper.readTree(value);
			return jsonNode.get("payload");
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
