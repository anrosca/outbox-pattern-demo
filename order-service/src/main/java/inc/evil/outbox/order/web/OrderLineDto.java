package inc.evil.outbox.order.web;

import inc.evil.outbox.order.model.OrderLine;
import inc.evil.outbox.order.model.OrderLineStatus;
import inc.evil.outbox.order.model.search.OrderLineSearchModel;

import java.math.BigDecimal;

public class OrderLineDto {
	private String item;

	private int quantity;

	private OrderLineStatus status = OrderLineStatus.ENTERED;

	private BigDecimal totalPrice;

	public OrderLineDto() {
	}

	public OrderLineDto(OrderLineDtoBuilder builder) {
		this.item = builder.item;
		this.quantity = builder.quantity;
		this.status = builder.status;
		this.totalPrice = builder.totalPrice;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderLineStatus getStatus() {
		return status;
	}

	public void setStatus(OrderLineStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static OrderLineDto from(OrderLine line) {
		return OrderLineDto.builder()
				.item(line.getItem())
				.quantity(line.getQuantity())
				.status(line.getStatus())
				.totalPrice(line.getTotalPrice())
				.build();
	}

	public static OrderLineDto from(OrderLineSearchModel line) {
		return OrderLineDto.builder()
				.item(line.getItem())
				.quantity(line.getQuantity())
				.status(line.getStatus())
				.totalPrice(line.getTotalPrice())
				.build();
	}

	public static OrderLineDtoBuilder builder() {
		return new OrderLineDtoBuilder();
	}

	public static class OrderLineDtoBuilder {

		private String item;

		private int quantity;

		private OrderLineStatus status;

		private BigDecimal totalPrice;

		public OrderLineDtoBuilder item(String item) {
			this.item = item;
			return this;
		}

		public OrderLineDtoBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderLineDtoBuilder status(OrderLineStatus status) {
			this.status = status;
			return this;
		}

		public OrderLineDtoBuilder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public OrderLineDto build() {
			return new OrderLineDto(this);
		}
	}
}
