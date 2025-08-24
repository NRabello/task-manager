package nrabello.back.core.domain.entity.mapper;

import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.dto.task.CreateTaskDTO;
import nrabello.back.core.domain.entity.dto.task.TaskResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    public abstract Task toEntity(CreateTaskDTO dto);

    @Mapping(target = "userName", source = "user.name")
    public abstract TaskResponseDTO toResponseDTO(Task entity);
}
