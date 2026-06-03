package nrabello.back.core.usecase.project;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Project;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.repository.ProjectRepository;
import nrabello.back.core.service.UserOrganizationService;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListProjectUseCase implements IUseCase<Long, List<Project>> {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final UserOrganizationService userOrganizationService;

    @Override
    public List<Project> execute(Long organizationId) {
        User usuarioLogado = userService.getUsuarioLogado();

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), organizationId);

        return projectRepository.findAllByOrganizationIdAndActiveTrue(organizationId);
    }
}
