package nrabello.back.core.usecase.project;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Organization;
import nrabello.back.core.domain.entity.Project;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.EntityNotFoundException;
import nrabello.back.core.repository.OrganizationRepository;
import nrabello.back.core.repository.ProjectRepository;
import nrabello.back.core.service.UserOrganizationService;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import nrabello.back.inbound.facade.dto.project.CreateProjectDTO;
import nrabello.back.inbound.facade.mapper.ProjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProjectUseCase implements IUseCase<CreateProjectDTO, Project> {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;
    private final UserService userService;
    private final UserOrganizationService userOrganizationService;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public Project execute(CreateProjectDTO input) {
        User usuarioLogado = userService.getUsuarioLogado();

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), input.getOrganizationId());

        Organization organization = organizationRepository.findById(input.getOrganizationId())
                .orElseThrow(() -> EntityNotFoundException.organizacaoNaoEncontrada(input.getOrganizationId()));

        Project project = projectMapper.toEntity(input);
        project.setOrganization(organization);

        return projectRepository.save(project);
    }
}
