package inc.evil.outbox.order.model;

import inc.evil.outbox.order.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder extends AbstractEntity {
	private long customerId;

	private LocalDateTime orderDate;

	@OneToMany(mappedBy = "purchaseOrder", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<OrderLine> lineItems;

	protected PurchaseOrder() {
	}

	private PurchaseOrder(PurchaseOrderBuilder builder) {
		this.id = builder.id;
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

	public List<OrderLine> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<OrderLine> lineItems) {
		this.lineItems = lineItems;
	}

	public static PurchaseOrderBuilder builder() {
		return new PurchaseOrderBuilder();
	}

	public static class PurchaseOrderBuilder {
		private String id;

		private long customerId;

		private LocalDateTime orderDate;

		private List<OrderLine> lineItems;

		public PurchaseOrderBuilder id(String id) {
			this.id = id;
			return this;
		}

		public PurchaseOrderBuilder customerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public PurchaseOrderBuilder orderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
			return this;
		}

		public PurchaseOrderBuilder lineItems(List<OrderLine> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public PurchaseOrder build() {
			return new PurchaseOrder(this);
		}
	}
}
