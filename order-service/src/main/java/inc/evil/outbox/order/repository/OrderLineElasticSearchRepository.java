package inc.evil.outbox.order.repository;

import inc.evil.outbox.order.model.search.OrderLineSearchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OrderLineElasticSearchRepository extends ElasticsearchRepository<OrderLineSearchModel, String> {
	List<OrderLineSearchModel> findAllByItemContaining(String item);
}
