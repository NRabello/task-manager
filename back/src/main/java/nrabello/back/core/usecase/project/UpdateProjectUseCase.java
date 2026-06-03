package nrabello.back.core.usecase.project;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Project;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.EntityNotFoundException;
import nrabello.back.core.repository.ProjectRepository;
import nrabello.back.core.service.UserOrganizationService;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import nrabello.back.inbound.facade.dto.project.UpdateProjectDTO;
import nrabello.back.inbound.facade.mapper.ProjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateProjectUseCase implements IUseCase<UpdateProjectDTO, Project> {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final UserOrganizationService userOrganizationService;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public Project execute(UpdateProjectDTO input) {
        User usuarioLogado = userService.getUsuarioLogado();

        Project project = projectRepository.findById(input.getId())
                .orElseThrow(() -> EntityNotFoundException.projetoNaoEncontrado(input.getId()));

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), project.getOrganization().getId());

        projectMapper.updateEntity(input, project);

        return projectRepository.save(project);
    }
}
