package nrabello.back.core.repository;

import nrabello.back.core.domain.entity.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {

    Optional<UserOrganization> findByUserIdAndOrganizationId(Long userId, Long organizationId);
}
