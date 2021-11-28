package inc.evil.outbox.order.service;

import inc.evil.outbox.order.event.OrderCreatedEvent;
import inc.evil.outbox.order.model.PurchaseOrder;
import inc.evil.outbox.order.model.search.OrderLineSearchModel;
import inc.evil.outbox.order.repository.OrderLineElasticSearchRepository;
import inc.evil.outbox.order.repository.PurchaseOrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
	private final PurchaseOrderRepository purchaseOrderRepository;

	private final OrderLineElasticSearchRepository orderLineRepository;

	private final ApplicationEventPublisher eventPublisher;

	public OrderService(PurchaseOrderRepository purchaseOrderRepository,
			OrderLineElasticSearchRepository orderLineRepository, ApplicationEventPublisher eventPublisher) {
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.orderLineRepository = orderLineRepository;
		this.eventPublisher = eventPublisher;
	}

	@Transactional
	public PurchaseOrder addOrder(PurchaseOrder order) {
		PurchaseOrder createdOrder = purchaseOrderRepository.save(order);
		eventPublisher.publishEvent(OrderCreatedEvent.of(createdOrder));
		return createdOrder;
	}

	public List<OrderLineSearchModel> searchByItemName(String itemName) {
		return orderLineRepository.findAllByItemContaining(itemName);
	}
}
