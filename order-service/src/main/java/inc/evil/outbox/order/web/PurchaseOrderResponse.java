package inc.evil.outbox.order.web;

import inc.evil.outbox.order.model.PurchaseOrder;
import inc.evil.outbox.order.model.search.PurchaseOrderSearchModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseOrderResponse {
	private long customerId;

	private LocalDateTime orderDate;

	private List<OrderLineDto> lineItems;

	public PurchaseOrderResponse() {
	}

	public PurchaseOrderResponse(OrderOperationResponseBuilder builder) {
		this.customerId = builder.customerId;
		this.orderDate = builder.orderDate;
		this.lineItems = builder.lineItems;
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

	public static PurchaseOrderResponse from(PurchaseOrder order) {
		List<OrderLineDto> lineItems = order.getLineItems()
				.stream()
				.map(OrderLineDto::from)
				.collect(Collectors.toList());
		return PurchaseOrderResponse.builder()
				.customerId(order.getCustomerId())
				.orderDate(order.getOrderDate())
				.lineItems(lineItems)
				.build();
	}

	public static PurchaseOrderResponse from(PurchaseOrderSearchModel order) {
		List<OrderLineDto> lineItems = order.getLineItems()
				.stream()
				.map(OrderLineDto::from)
				.collect(Collectors.toList());
		return PurchaseOrderResponse.builder()
				.customerId(order.getCustomerId())
				.orderDate(order.getOrderDate())
				.lineItems(lineItems)
				.build();
	}

	public static OrderOperationResponseBuilder builder() {
		return new OrderOperationResponseBuilder();
	}

	public static class OrderOperationResponseBuilder {
		private long customerId;

		private LocalDateTime orderDate;

		private List<OrderLineDto> lineItems;

		public OrderOperationResponseBuilder customerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public OrderOperationResponseBuilder orderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
			return this;
		}

		public OrderOperationResponseBuilder lineItems(List<OrderLineDto> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public PurchaseOrderResponse build() {
			return new PurchaseOrderResponse(this);
		}
	}
}
