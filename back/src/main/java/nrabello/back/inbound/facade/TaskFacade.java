package nrabello.back.inbound.facade;


import lombok.RequiredArgsConstructor;
import nrabello.back.inbound.facade.dto.task.CreateTaskDTO;
import nrabello.back.inbound.facade.dto.task.TaskResponseDTO;
import nrabello.back.core.usecase.task.CreateTaskUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TaskFacade {

    private final CreateTaskUseCase createTaskUseCase;

    @Transactional
    public TaskResponseDTO createTask(CreateTaskDTO dto){
        return createTaskUseCase.execute(dto);
    }
}
