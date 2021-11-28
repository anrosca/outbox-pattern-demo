package inc.evil.outbox.shipment;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "consumed_messages")
public class ConsumedMessage {
	@Id
	private String messageId;

	private Instant timeOfReceiving;

	protected ConsumedMessage() {
	}

	private ConsumedMessage(ConsumedMessageBuilder builder) {
		this.messageId = builder.messageId;
		this.timeOfReceiving = builder.timeOfReceiving;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Instant getTimeOfReceiving() {
		return timeOfReceiving;
	}

	public void setTimeOfReceiving(Instant timeOfReceiving) {
		this.timeOfReceiving = timeOfReceiving;
	}

	public static ConsumedMessageBuilder builder() {
		return new ConsumedMessageBuilder();
	}

	public static class ConsumedMessageBuilder {

		private String messageId;

		private Instant timeOfReceiving;

		public ConsumedMessageBuilder messageId(String messageId) {
			this.messageId = messageId;
			return this;
		}

		public ConsumedMessageBuilder timeOfReceiving(Instant timeOfReceiving) {
			this.timeOfReceiving = timeOfReceiving;
			return this;
		}

		public ConsumedMessage build() {
			return new ConsumedMessage(this);
		}
	}
}
