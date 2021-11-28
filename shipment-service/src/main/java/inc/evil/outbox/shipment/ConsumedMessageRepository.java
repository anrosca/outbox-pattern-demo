package inc.evil.outbox.shipment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumedMessageRepository extends JpaRepository<ConsumedMessage, String> {
}
