package inc.evil.outbox.shipment;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ShipmentService {
	private final ShipmentRepository shipmentRepository;

	public ShipmentService(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@Transactional
	public void orderCreated(JsonNode payload) {
		Shipment shipment = Shipment.builder()
				.customerId(payload.get("customerId").asLong())
				.orderDate(LocalDateTime.parse(payload.get("orderDate").asText()))
				.orderId(payload.get("id").asText())
				.build();
		log.info("Received shipment: {}", shipment);
		shipmentRepository.save(shipment);
	}
}
