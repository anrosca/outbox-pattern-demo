package inc.evil.outbox.shipment;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
public class Shipment {
	@Id
	@GenericGenerator(name = "uuid-generator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid-generator")
	private String id;

	private String orderId;

	private long customerId;

	private LocalDateTime orderDate;

	protected Shipment() {
	}

	private Shipment(ShipmentBuilder builder) {
		this.id = builder.id;
		this.orderId = builder.orderId;
		this.customerId = builder.customerId;
		this.orderDate = builder.orderDate;
	}

	public String getId() {
		return id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	@Override
	public String toString() {
		return "Shipment{" +
				"id='" + id + '\'' +
				", orderId='" + orderId + '\'' +
				", customerId=" + customerId +
				", orderDate=" + orderDate +
				'}';
	}

	public static ShipmentBuilder builder() {
		return new ShipmentBuilder();
	}

	public static class ShipmentBuilder {
		private String id;

		private String orderId;

		private long customerId;

		private LocalDateTime orderDate;

		public ShipmentBuilder id(String id) {
			this.id = id;
			return this;
		}

		public ShipmentBuilder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public ShipmentBuilder customerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public ShipmentBuilder orderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
			return this;
		}

		public Shipment build() {
			return new Shipment(this);
		}
	}
}
