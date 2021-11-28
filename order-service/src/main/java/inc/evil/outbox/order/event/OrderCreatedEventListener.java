package inc.evil.outbox.order.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener {
	private final OutboxEventRepository eventRepository;

	public OrderCreatedEventListener(OutboxEventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@EventListener(OrderCreatedEvent.class)
	public void on(OrderCreatedEvent event) {
		OutboxEvent outboxEvent = OutboxEvent.builder()
				.aggregateType(event.getAggregateType())
				.payload(event.getPayload())
				.aggregateId(event.getAggregateId())
				.type(event.getType())
				.build();
		eventRepository.save(outboxEvent);
		eventRepository.delete(outboxEvent);
	}
}
