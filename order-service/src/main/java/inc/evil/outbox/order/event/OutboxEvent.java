package inc.evil.outbox.order.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import inc.evil.outbox.order.entity.AbstractEntity;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "outbox_event")
@TypeDef(name = "PayloadType", typeClass = JsonNodeBinaryType.class)
public class OutboxEvent extends AbstractEntity {
	@Type(type = "PayloadType")
	private JsonNode payload;

	private String type;

	private String aggregateType;

	private String aggregateId;

	protected OutboxEvent() {
	}

	private OutboxEvent(OutboxEventBuilder builder) {
		this.payload = builder.payload;
		this.type = builder.type;
		this.aggregateType = builder.aggregateType;
		this.aggregateId = builder.aggregateId;
	}

	public JsonNode getPayload() {
		return payload;
	}

	public void setPayload(JsonNode payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public static OutboxEventBuilder builder() {
		return new OutboxEventBuilder();
	}

	public static class OutboxEventBuilder {
		private JsonNode payload;

		private String type;

		private String aggregateType;

		private String aggregateId;

		OutboxEventBuilder() {
		}

		public OutboxEventBuilder payload(JsonNode payload) {
			this.payload = payload;
			return this;
		}

		public OutboxEventBuilder type(String type) {
			this.type = type;
			return this;
		}

		public OutboxEventBuilder aggregateType(String aggregateType) {
			this.aggregateType = aggregateType;
			return this;
		}

		public OutboxEventBuilder aggregateId(String aggregateId) {
			this.aggregateId = aggregateId;
			return this;
		}

		public OutboxEvent build() {
			return new OutboxEvent(this);
		}
	}
}
