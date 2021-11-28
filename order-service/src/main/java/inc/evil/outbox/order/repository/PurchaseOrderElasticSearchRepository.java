package inc.evil.outbox.order.repository;

import inc.evil.outbox.order.model.search.PurchaseOrderSearchModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PurchaseOrderElasticSearchRepository
		extends ElasticsearchRepository<PurchaseOrderSearchModel, String> {
	@Query("""
			{
				"bool": {
					"must": [
						{
							"match": {"lineItems.item": "?0"}
						}
					]
				}
			}
			""")
	List<PurchaseOrderSearchModel> findAllContainingLineItem(String itemName);
}
