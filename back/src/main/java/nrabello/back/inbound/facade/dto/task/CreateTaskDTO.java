package nrabello.back.inbound.facade.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.inbound.facade.dto.workItem.CreateWorkItemDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTaskDTO extends CreateWorkItemDTO {

    private boolean crudTested;

    private boolean multipleBrowsersTested;

    private boolean evidenceAttached;

    private String acceptanceCriteria;

    private String descriptionOfImpediment;

    @NotNull
    private Long taskStatusId;

}
