package nrabello.back.core.service;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.UserOrganization;
import nrabello.back.core.exception.DomainException;
import nrabello.back.core.repository.UserOrganizationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrganizationService {

    private final UserOrganizationRepository repository;

    public UserOrganization getUserOrganization(Long userId, Long organizationId) {
        return repository
                .findByUserIdAndOrganizationId(userId, organizationId)
                .orElseThrow(DomainException::userOrganizationNotFound);
    }
}
