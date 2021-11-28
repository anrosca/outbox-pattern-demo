package inc.evil.outbox.order.web;

import inc.evil.outbox.order.model.OrderLine;
import inc.evil.outbox.order.model.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderRequest {
	private long customerId;
	private LocalDateTime orderDate;
	private List<OrderLineDto> lineItems;

	public CreateOrderRequest() {
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderLineDto> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<OrderLineDto> lineItems) {
		this.lineItems = lineItems;
	}

	public PurchaseOrder toPurchaseOrder() {
		List<OrderLine> lines = lineItems.stream()
				.map(item -> OrderLine.builder()
						.item(item.getItem())
						.quantity(item.getQuantity())
						.status(item.getStatus())
						.totalPrice(item.getTotalPrice())
						.build())
				.collect(Collectors.toList());
		PurchaseOrder purchaseOrder = PurchaseOrder.builder()
				.customerId(customerId)
				.orderDate(orderDate)
				.lineItems(lines)
				.build();
		lines.forEach(item -> item.setPurchaseOrder(purchaseOrder));
		return purchaseOrder;
	}
}
