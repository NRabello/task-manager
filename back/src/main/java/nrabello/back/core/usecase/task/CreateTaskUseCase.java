package nrabello.back.core.usecase.task;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Project;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.EntityNotFoundException;
import nrabello.back.core.repository.ProjectRepository;
import nrabello.back.core.repository.TaskRepository;
import nrabello.back.core.repository.TaskStatusRepository;
import nrabello.back.core.repository.UserRepository;
import nrabello.back.core.service.UserOrganizationService;
import nrabello.back.inbound.facade.dto.task.CreateTaskDTO;
import nrabello.back.inbound.facade.dto.task.TaskResponseDTO;
import nrabello.back.inbound.facade.mapper.TaskMapper;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateTaskUseCase implements IUseCase<CreateTaskDTO, TaskResponseDTO> {

    private final TaskMapper taskMapper;
    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserOrganizationService userOrganizationService;

    @Override
    public TaskResponseDTO execute(CreateTaskDTO input) {
        User usuarioLogado = userService.getUsuarioLogado();

        Project project = projectRepository.findById(input.getProjectId())
                .orElseThrow(() -> EntityNotFoundException.projetoNaoEncontrado(input.getProjectId()));

        userOrganizationService.getUserOrganization(usuarioLogado.getId(), project.getOrganization().getId());

        TaskStatus taskStatus = taskStatusRepository.findById(input.getTaskStatusId())
                .orElseThrow(() -> EntityNotFoundException.statusTaskNaoEncontrada(input.getTaskStatusId()));

        User user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> EntityNotFoundException.usuarioNaoEncontrado(input.getUserId()));

        Task task = taskMapper.toEntity(input);
        task.setStatus(taskStatus);
        task.setUser(user);
        task.setProject(project);

        return taskMapper.toResponseDTO(taskRepository.save(task));
    }
}
