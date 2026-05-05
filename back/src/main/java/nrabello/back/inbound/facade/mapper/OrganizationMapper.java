package nrabello.back.inbound.facade.mapper;

import nrabello.back.core.domain.entity.Organization;
import nrabello.back.inbound.facade.dto.organization.CreateOrganizationDTO;
import nrabello.back.inbound.facade.dto.organization.OrganizationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {
    OrganizationResponseDTO toDto(Organization organization);

    Organization toEntity(CreateOrganizationDTO createOrganizationDTO);
}
