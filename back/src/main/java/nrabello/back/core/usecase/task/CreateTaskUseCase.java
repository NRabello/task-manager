package nrabello.back.core.usecase.task;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.UserOrganization;
import nrabello.back.core.exception.EntityNotFoundException;
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

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CreateTaskUseCase implements IUseCase<CreateTaskDTO, TaskResponseDTO> {

    private final TaskMapper taskMapper;
    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserRepository userRepository;
    private final UserOrganizationService userOrganizationService;

    @Override
    public TaskResponseDTO execute(CreateTaskDTO input) {
        userService.getUsuarioLogado();
        UserOrganization userOrganization = userOrganizationService.getUserOrganization(input.getUserId(), input.getOrganizationId());

        TaskStatus taskStatus = taskStatusRepository.findById(input.getTaskStatusId())
                .orElseThrow(() -> EntityNotFoundException.statusTaskNaoEncontrada(input.getTaskStatusId()));

        User user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> EntityNotFoundException.usuarioNaoEncontrado(input.getUserId()));

        Task task = taskMapper.toEntity(input);
        task.setStatus(taskStatus);
        task.setUser(user);
        task.setOrganization(userOrganization.getOrganization());

        return taskMapper.toResponseDTO(taskRepository.save(task));
    }
}
