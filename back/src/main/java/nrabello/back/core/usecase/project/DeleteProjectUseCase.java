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
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteProjectUseCase implements IUseCase<Long, Void> {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final UserOrganizationService userOrganizationService;

    @Override
    @Transactional
    public Void execute(Long projectId) {
        User usuarioLogado = userService.getUsuarioLogado();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> EntityNotFoundException.projetoNaoEncontrado(projectId));

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), project.getOrganization().getId());

        project.setActive(false);
        projectRepository.save(project);

        return null;
    }
}
