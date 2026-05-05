package nrabello.back.core.usecase.taskStatus;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.core.repository.TaskStatusRepository;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListTaskStatusUseCase implements IUseCase<Void, List<TaskStatus>>{

    private final UserService userService;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public List<TaskStatus> execute(Void input) {
        userService.getUsuarioLogado();

        return taskStatusRepository.findAll();
    }
}
