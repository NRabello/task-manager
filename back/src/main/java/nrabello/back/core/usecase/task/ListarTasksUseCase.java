package nrabello.back.core.usecase.task;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.dto.task.TaskResponseDTO;
import nrabello.back.core.domain.entity.mapper.TaskMapper;
import nrabello.back.core.repository.TaskRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ListarTasksUseCase implements IUseCase<Long, List<TaskResponseDTO>> {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Override
    public List<TaskResponseDTO> execute(Long input) {

        if (input == null) {
            return taskRepository.findAll().stream().map(mapper::toResponseDTO).toList();
        } else {
            return taskRepository.findAllByUser_Id(input).stream().map(mapper::toResponseDTO).toList();
        }
    }
}
