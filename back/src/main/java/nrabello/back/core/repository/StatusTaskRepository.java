package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {
}
