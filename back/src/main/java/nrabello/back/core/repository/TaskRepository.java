package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTopByOrderByCodeDesc();

    List<Task> findAllByUser_Id(Long userId);
}
