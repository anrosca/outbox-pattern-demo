package inc.evil.outbox.order.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public class AbstractEntity {
	@Id
	@GenericGenerator(name = "uuid-generator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid-generator")
	protected String id;

	public String getId() {
		return id;
	}
}
