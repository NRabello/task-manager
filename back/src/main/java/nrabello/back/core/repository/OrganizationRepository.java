package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findAllByUsersUserId(Long userId);
}
