package tuyen.bui.sms.infrastructure.persistance.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tuyen.bui.sms.infrastructure.persistance.entity.OrderOutboxEntity;

public interface OrderOutboxRepositoryJpa extends JpaRepository<OrderOutboxEntity, Long> {
}
