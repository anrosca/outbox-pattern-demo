package inc.evil.outbox.order.model.search;

import inc.evil.outbox.order.model.OrderLineStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;

@Document(indexName = "order_lines")
public class OrderLineSearchModel {
	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String item;

	private int quantity;

	private OrderLineStatus status;

	@Field(name = "total_price")
	private BigDecimal totalPrice;

	protected OrderLineSearchModel() {
	}

	private OrderLineSearchModel(OrderLineBuilder builder) {
		this.id = builder.id;
		this.item = builder.item;
		this.quantity = builder.quantity;
		this.status = builder.status;
		this.totalPrice = builder.totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		private String id;

		private String item;

		private int quantity;

		private OrderLineStatus status;

		private BigDecimal totalPrice;

		public OrderLineBuilder id(String id) {
			this.id = id;
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

		public OrderLineSearchModel build() {
			return new OrderLineSearchModel(this);
		}
	}
}
