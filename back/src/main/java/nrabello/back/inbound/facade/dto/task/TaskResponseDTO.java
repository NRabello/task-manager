package nrabello.back.inbound.facade.dto.task;

import lombok.Data;
import nrabello.back.inbound.facade.dto.taskStatus.TaskStatusResponseDTO;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {

    private String title;

    private String description;

    private Double effort;

    private LocalDateTime startDate;

    private LocalDateTime targetDate;

    private Long userId;

    private boolean crudTested;

    private boolean multipleBrowsersTested;

    private boolean evidenceAttached;

    private String acceptanceCriteria;

    private String descriptionOfImpediment;

    private String taskStatus;
}
