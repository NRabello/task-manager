package nrabello.back.inbound.facade;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.usecase.project.*;
import nrabello.back.inbound.facade.dto.project.CreateProjectDTO;
import nrabello.back.inbound.facade.dto.project.ProjectResponseDTO;
import nrabello.back.inbound.facade.dto.project.UpdateProjectDTO;
import nrabello.back.inbound.facade.mapper.ProjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectFacade {

    private final CreateProjectUseCase createProjectUseCase;
    private final ListProjectUseCase listProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final ProjectMapper mapper;

    @Transactional
    public ProjectResponseDTO createProject(CreateProjectDTO dto) {
        return mapper.toDto(createProjectUseCase.execute(dto));
    }

    public List<ProjectResponseDTO> listProjects(Long organizationId) {
        return listProjectUseCase.execute(organizationId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ProjectResponseDTO getProject(Long projectId) {
        return mapper.toDto(getProjectUseCase.execute(projectId));
    }

    @Transactional
    public ProjectResponseDTO updateProject(UpdateProjectDTO dto) {
        return mapper.toDto(updateProjectUseCase.execute(dto));
    }

    @Transactional
    public void deleteProject(Long projectId) {
        deleteProjectUseCase.execute(projectId);
    }
}
