package nrabello.back.inbound.facade;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.dto.task.CreateTaskDTO;
import nrabello.back.core.domain.entity.dto.task.TaskResponseDTO;
import nrabello.back.core.usecase.task.CreateTaskUseCase;
import nrabello.back.core.usecase.task.ListarTasksUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskFacade {

    private final CreateTaskUseCase createTaskUseCase;

    private final ListarTasksUseCase listarTasksUseCase;

    @Transactional
    public TaskResponseDTO createTask(CreateTaskDTO dto){
        return createTaskUseCase.execute(dto);
    }


    public List<TaskResponseDTO> listarTasks(Long userId){
        return listarTasksUseCase.execute(userId);
    }
}
