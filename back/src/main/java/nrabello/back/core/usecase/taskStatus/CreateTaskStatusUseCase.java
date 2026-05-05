package nrabello.back.core.usecase.taskStatus;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.core.repository.TaskStatusRepository;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTaskStatusUseCase implements IUseCase<TaskStatus, TaskStatus> {

    private final UserService userService;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public TaskStatus execute(TaskStatus input) {
        userService.getUsuarioLogado();

        return taskStatusRepository.save(input);
    }
}
