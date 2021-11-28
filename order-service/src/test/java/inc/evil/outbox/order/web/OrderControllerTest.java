package inc.evil.outbox.order.web;

import inc.evil.outbox.order.model.OrderLine;
import inc.evil.outbox.order.model.PurchaseOrder;
import inc.evil.outbox.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@Execution(ExecutionMode.CONCURRENT)
public class OrderControllerTest {
	@MockBean
	private OrderService orderService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldBeAbleToCreateOrders() throws Exception {
		PurchaseOrder expectedOrder = PurchaseOrder.builder()
				.customerId(1)
				.orderDate(LocalDateTime.parse("2021-11-28T14:15:16"))
				.lineItems(List.of(
						OrderLine.builder()
								.item("Chocolate")
								.quantity(1)
								.totalPrice(new BigDecimal("1234.55"))
								.build()
				))
				.build();
		when(orderService.addOrder(any())).thenReturn(expectedOrder);
		String payload = """
				{
				  "customerId": "1",
				  "orderDate": "2021-11-28T14:15:16",
				  "lineItems": [
				    {
				      "item": "Chocolate",
				      "quantity": "1",
				      "totalPrice": "1234.55"
				    }
				  ]
				}
				""";

		mockMvc.perform(post("/api/v1/orders").contentType(MediaType.APPLICATION_JSON_VALUE).content(payload))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId", equalTo(1)))
				.andExpect(jsonPath("$.orderDate", equalTo("2021-11-28T14:15:16")))
				.andExpect(jsonPath("$.lineItems[0].item", equalTo("Chocolate")))
				.andExpect(jsonPath("$.lineItems[0].quantity", equalTo(1)))
				.andExpect(jsonPath("$.lineItems[0].totalPrice", equalTo(1234.55)));
	}
}
