package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {

    List<StatusTask> findAllByActive(Boolean active);

    Optional<StatusTask> findByIdAndActive(Long id, Boolean active);
}
