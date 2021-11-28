package inc.evil.outbox.shipment;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageLog {
	private final ConsumedMessageRepository consumedMessageRepository;

	public MessageLog(ConsumedMessageRepository consumedMessageRepository) {
		this.consumedMessageRepository = consumedMessageRepository;
	}

	@Transactional(readOnly = true)
	public boolean alreadyProcessed(String messageId) {
		return consumedMessageRepository.findById(messageId)
				.isPresent();
	}

	@Transactional
	public void processed(ConsumedMessage consumedMessage) {
		consumedMessageRepository.save(consumedMessage);
	}
}
