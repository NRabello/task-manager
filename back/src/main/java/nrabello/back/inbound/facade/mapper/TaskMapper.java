package nrabello.back.inbound.facade.mapper;

import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.WorkItem;
import nrabello.back.inbound.facade.dto.task.CreateTaskDTO;
import nrabello.back.inbound.facade.dto.task.TaskResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    Task toEntity(CreateTaskDTO dto);

    @Mapping(target = "title", source = "task.title")
    @Mapping(target = "description", source = "task.description")
    @Mapping(target = "effort", source = "task.effort")
    @Mapping(target = "startDate", source = "task.startDate")
    @Mapping(target = "targetDate", source = "task.targetDate")
    @Mapping(target = "taskStatus", source = "task.status.name")
    @Mapping(target = "userId", source = "task.user.id")
    @Mapping(target = "acceptanceCriteria", source = "task.acceptanceCriteria")
    TaskResponseDTO toResponseDTO(Task task);
}
