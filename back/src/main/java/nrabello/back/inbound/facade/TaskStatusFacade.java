package nrabello.back.inbound.facade;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.core.usecase.taskStatus.CreateTaskStatusUseCase;
import nrabello.back.core.usecase.taskStatus.ListTaskStatusUseCase;
import nrabello.back.inbound.facade.dto.taskStatus.CreateStatusTaskDTO;
import nrabello.back.inbound.facade.dto.taskStatus.TaskStatusResponseDTO;
import nrabello.back.inbound.facade.mapper.TaskStatusMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskStatusFacade {

    private final ListTaskStatusUseCase listTaskStatusUseCase;
    private final CreateTaskStatusUseCase createTaskStatusUseCase;
    private final TaskStatusMapper taskStatusMapper;

    public TaskStatus createStatusTask(CreateStatusTaskDTO createStatusTaskDTO){
        TaskStatus taskStatus = taskStatusMapper.toEntity(createStatusTaskDTO);
        return createTaskStatusUseCase.execute(taskStatus);
    }

    public List<TaskStatusResponseDTO> listTaskStatus(){
        return listTaskStatusUseCase.execute(null)
                .stream()
                .map(taskStatusMapper::toResponseDTO)
                .toList();
    }
}
