package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrganizationId(Long organizationId);
    List<Project> findAllByOrganizationIdAndActiveTrue(Long organizationId);
}
