package nrabello.back.core.usecase.task;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.dto.task.CreateTaskDTO;
import nrabello.back.core.domain.entity.dto.task.TaskResponseDTO;
import nrabello.back.core.domain.entity.mapper.TaskMapper;
import nrabello.back.core.domain.exception.EntityNotFoundException;
import nrabello.back.core.repository.StatusTaskRepository;
import nrabello.back.core.repository.TaskRepository;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateTaskUseCase implements IUseCase<CreateTaskDTO, TaskResponseDTO> {

    private final TaskRepository taskRepository;
    private final StatusTaskRepository statusTaskRepository;
    private final TaskMapper mapper;
    private final UserService userService;

    @Override
    public TaskResponseDTO execute(CreateTaskDTO input) {
        Task task = mapper.toEntity(input);
        task.setCode(getNextCode());
        task.setUser(userService.getUsuarioLogado());
        task.setStatus(input.getStatus());
        task.setActive(true);

        validateStatusTask(task.getStatus());

        return mapper.toResponseDTO(taskRepository.save(task));
    }

    public Integer getNextCode(){
        return taskRepository.findTopByOrderByCodeDesc().map(Task::getCode).orElse(0) + 1;
    }

    public void validateStatusTask(StatusTask statusTask){
        if(statusTaskRepository.findByIdAndActive(statusTask.getId(), true).isEmpty()){
            throw EntityNotFoundException.statusTaskNaoEncontrada(statusTask.getId());
        }
    }
}
