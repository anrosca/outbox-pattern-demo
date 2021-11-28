package inc.evil.outbox.shipment;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class OrderEventHandler {
	private final ShipmentService shipmentService;

	private final MessageLog messageLog;

	public OrderEventHandler(ShipmentService shipmentService, MessageLog messageLog) {
		this.shipmentService = shipmentService;
		this.messageLog = messageLog;
	}

	public void onOrderEvent(String eventType, String aggregateType, String aggregateId, JsonNode payload) {
		if (messageLog.alreadyProcessed(aggregateId)) {
			log.info("Event with id: {} already processed", aggregateId);
			return;
		}
		if (eventType.equals("OrderCreated")) {
			shipmentService.orderCreated(payload);
		}
		markAsProcessed(aggregateId);
	}

	private void markAsProcessed(String aggregateId) {
		ConsumedMessage consumedMessage = ConsumedMessage.builder()
				.messageId(aggregateId)
				.timeOfReceiving(Instant.now())
				.build();
		messageLog.processed(consumedMessage);
	}
}
