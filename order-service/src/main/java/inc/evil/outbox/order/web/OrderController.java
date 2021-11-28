package inc.evil.outbox.order.web;

import inc.evil.outbox.order.model.PurchaseOrder;
import inc.evil.outbox.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(params = "itemName")
	public ResponseEntity<List<OrderLineDto>> search(@RequestParam String itemName) {
		List<OrderLineDto> purchaseOrders = orderService.searchByItemName(itemName)
				.stream()
				.map(OrderLineDto::from)
				.toList();
		return ResponseEntity.ok(purchaseOrders);
	}

	@PostMapping
	public ResponseEntity<PurchaseOrderResponse> addOrder(@RequestBody CreateOrderRequest request) {
		PurchaseOrder order = request.toPurchaseOrder();
		PurchaseOrder createdOrder = orderService.addOrder(order);
		return ResponseEntity.ok()
				.body(PurchaseOrderResponse.from(createdOrder));
	}
}
