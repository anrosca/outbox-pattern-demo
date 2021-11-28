package inc.evil.outbox.order.model;

import inc.evil.outbox.order.entity.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_lines")
public class OrderLine extends AbstractEntity {
	@ManyToOne
	private PurchaseOrder purchaseOrder;

	private String item;

	private int quantity;

	@Enumerated(EnumType.STRING)
	private OrderLineStatus status;

	private BigDecimal totalPrice;

	protected OrderLine() {
	}

	private OrderLine(OrderLineBuilder builder) {
		this.purchaseOrder = builder.purchaseOrder;
		this.item = builder.item;
		this.quantity = builder.quantity;
		this.status = builder.status;
		this.totalPrice = builder.totalPrice;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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

	public static OrderLineBuilder builder() {
		return new OrderLineBuilder();
	}

	public static class OrderLineBuilder {
		private PurchaseOrder purchaseOrder;

		private String item;

		private int quantity;

		private OrderLineStatus status;

		private BigDecimal totalPrice;

		public OrderLineBuilder purchaseOrder(PurchaseOrder purchaseOrder) {
			this.purchaseOrder = purchaseOrder;
			return this;
		}

		public OrderLineBuilder item(String item) {
			this.item = item;
			return this;
		}

		public OrderLineBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderLineBuilder status(OrderLineStatus status) {
			this.status = status;
			return this;
		}

		public OrderLineBuilder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public OrderLine build() {
			return new OrderLine(this);
		}
	}
}
