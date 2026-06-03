package nrabello.back.core.usecase.project;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Project;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.EntityNotFoundException;
import nrabello.back.core.repository.ProjectRepository;
import nrabello.back.core.service.UserOrganizationService;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProjectUseCase implements IUseCase<Long, Project> {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final UserOrganizationService userOrganizationService;

    @Override
    public Project execute(Long projectId) {
        User usuarioLogado = userService.getUsuarioLogado();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> EntityNotFoundException.projetoNaoEncontrado(projectId));

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), project.getOrganization().getId());

        return project;
    }
}
