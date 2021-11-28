package inc.evil.outbox.order.model.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "purchase_orders")
public class PurchaseOrderSearchModel {
	@Id
	private String id;

	private long customerId;

	private LocalDateTime orderDate;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<OrderLineSearchModel> lineItems;

	protected PurchaseOrderSearchModel() {
	}

	private PurchaseOrderSearchModel(PurchaseOrderBuilder builder) {
		this.id = builder.id;
		this.customerId = builder.customerId;
		this.orderDate = builder.orderDate;
		this.lineItems = builder.lineItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<OrderLineSearchModel> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<OrderLineSearchModel> lineItems) {
		this.lineItems = lineItems;
	}

	public static PurchaseOrderBuilder builder() {
		return new PurchaseOrderBuilder();
	}

	public static class PurchaseOrderBuilder {
		private String id;

		private long customerId;

		private LocalDateTime orderDate;

		private List<OrderLineSearchModel> lineItems;

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

		public PurchaseOrderBuilder lineItems(List<OrderLineSearchModel> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public PurchaseOrderSearchModel build() {
			return new PurchaseOrderSearchModel(this);
		}
	}
}
