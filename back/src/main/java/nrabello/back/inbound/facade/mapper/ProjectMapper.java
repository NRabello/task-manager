package nrabello.back.inbound.facade.mapper;

import nrabello.back.core.domain.entity.Project;
import nrabello.back.inbound.facade.dto.project.CreateProjectDTO;
import nrabello.back.inbound.facade.dto.project.ProjectResponseDTO;
import nrabello.back.inbound.facade.dto.project.UpdateProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    @Mapping(target = "organizationId", source = "organization.id")
    ProjectResponseDTO toDto(Project project);

    @Mapping(target = "organization", ignore = true)
    Project toEntity(CreateProjectDTO dto);

    @Mapping(target = "organization", ignore = true)
    void updateEntity(UpdateProjectDTO dto, @MappingTarget Project project);
}
