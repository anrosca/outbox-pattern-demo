package inc.evil.outbox.order.service;

import inc.evil.outbox.common.AbstractIntegrationTest;
import inc.evil.outbox.order.event.OrderCreatedEvent;
import inc.evil.outbox.order.model.*;
import inc.evil.outbox.order.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RecordApplicationEvents
public class OrderServiceTest extends AbstractIntegrationTest {
	@MockBean
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ApplicationEvents applicationEvents;

	@Test
	public void shouldBeAbleToCreateOrders() {
		PurchaseOrder order = PurchaseOrder.builder()
				.id("1111-2222-3333-4444")
				.customerId(1L)
				.orderDate(LocalDateTime.parse("2021-11-30T12:13:14"))
				.lineItems(List.of(OrderLine.builder()
						.item("Chocolate")
						.quantity(1)
						.totalPrice(new BigDecimal("10.5"))
						.status(OrderLineStatus.ENTERED)
						.build()))
				.build();
		Mockito.when(purchaseOrderRepository.save(any())).thenReturn(order);

		orderService.addOrder(order);

		assertThat(applicationEvents.stream(OrderCreatedEvent.class).count()).isEqualTo(1);
	}
}
