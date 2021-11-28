package inc.evil.outbox.order.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.lang.NonNull;

@Configuration(proxyBeanMethods = false)
public class AppConfig {

	@Configuration(proxyBeanMethods = false)
	public static class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
		@Override
		@Bean
		public @NonNull RestHighLevelClient elasticsearchClient() {
			final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
					.connectedTo("localhost:9200")
					.build();
			return RestClients.create(clientConfiguration).rest();
		}
	}
}
