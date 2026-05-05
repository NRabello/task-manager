package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
