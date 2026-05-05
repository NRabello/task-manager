package nrabello.back.inbound.facade.mapper;

import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.inbound.facade.dto.taskStatus.CreateStatusTaskDTO;
import nrabello.back.inbound.facade.dto.taskStatus.TaskStatusResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskStatusMapper {

    TaskStatusResponseDTO toResponseDTO(TaskStatus taskStatus);

    TaskStatus toEntity(CreateStatusTaskDTO createStatusTaskDTO);
}
