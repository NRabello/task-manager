package nrabello.back.inbound.facade;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.usecase.organization.CreateOrganizationUseCase;
import nrabello.back.core.usecase.organization.ListOrganizationUseCase;
import nrabello.back.inbound.facade.dto.organization.CreateOrganizationDTO;
import nrabello.back.inbound.facade.dto.organization.OrganizationResponseDTO;
import nrabello.back.inbound.facade.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganizationFacade {

    private final CreateOrganizationUseCase createOrganizationUseCase;
    private final ListOrganizationUseCase listOrganizationUseCase;
    private final OrganizationMapper mapper;

    public OrganizationResponseDTO createOrganization(CreateOrganizationDTO createOrganizationDTO) {
        return mapper.toDto(createOrganizationUseCase.execute(createOrganizationDTO));
    }

    public List<OrganizationResponseDTO> listOrganization ()
    {
        return listOrganizationUseCase.execute(null)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
