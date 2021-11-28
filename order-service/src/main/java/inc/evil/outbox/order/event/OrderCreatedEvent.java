package inc.evil.outbox.order.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inc.evil.outbox.order.model.OrderLine;
import inc.evil.outbox.order.model.PurchaseOrder;

public class OrderCreatedEvent implements ExportedEvent {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private final String id;
	private final JsonNode order;

	private OrderCreatedEvent(String id, JsonNode order) {
		this.id = id;
		this.order = order;
	}

	public static OrderCreatedEvent of(PurchaseOrder order) {
		ObjectNode asJson = objectMapper.createObjectNode()
				.put("id", order.getId())
				.put("customerId", order.getCustomerId())
				.put("orderDate", order.getOrderDate().toString());

		ArrayNode items = asJson.putArray("lineItems");

		for (OrderLine orderLine : order.getLineItems()) {
			items.add(
					objectMapper.createObjectNode()
							.put("id", orderLine.getId())
							.put("item", orderLine.getItem())
							.put("quantity", orderLine.getQuantity())
							.put("totalPrice", orderLine.getTotalPrice())
							.put("status", orderLine.getStatus().name())
			);
		}

		return new OrderCreatedEvent(order.getId(), asJson);
	}

	@Override
	public String getAggregateId() {
		return String.valueOf(id);
	}

	@Override
	public String getAggregateType() {
		return "Order";
	}

	@Override
	public String getType() {
		return "OrderCreated";
	}

	@Override
	public JsonNode getPayload() {
		return order;
	}
}
