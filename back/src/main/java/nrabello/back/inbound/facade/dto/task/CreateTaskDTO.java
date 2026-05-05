package nrabello.back.core.domain.entity.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nrabello.back.core.domain.entity.dto.WorkItem.CreateWorkItemDTO;


@Data
public class CreateTaskDTO {

    @NotNull(message = "WorkItem é obrigatório.")
    CreateWorkItemDTO workItem;

}
