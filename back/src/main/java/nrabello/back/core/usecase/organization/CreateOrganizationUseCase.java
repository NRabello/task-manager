package nrabello.back.core.usecase.organization;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Organization;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.UserOrganization;
import nrabello.back.core.domain.enums.OrganizationRoleEnum;
import nrabello.back.core.repository.OrganizationRepository;
import nrabello.back.core.repository.UserOrganizationRepository;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import nrabello.back.inbound.facade.dto.organization.CreateOrganizationDTO;
import nrabello.back.inbound.facade.mapper.OrganizationMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateOrganizationUseCase implements IUseCase<CreateOrganizationDTO, Organization> {

    private final OrganizationRepository organizationRepository;
    private final UserOrganizationRepository userOrganizationRepository;
    private final UserService userService;
    private final OrganizationMapper organizationMapper;

    @Override
    @Transactional
    public Organization execute(CreateOrganizationDTO input) {
        User usuarioLogado = userService.getUsuarioLogado();

        Organization organization = organizationMapper.toEntity(input);

        UserOrganization userOrganization = new UserOrganization();
        userOrganization.setOrganization(organization);
        userOrganization.setUser(usuarioLogado);
        userOrganization.setRole(OrganizationRoleEnum.OWNER);

        organization.getUsers().add(userOrganization);

        return organizationRepository.save(organization);
    }
}
