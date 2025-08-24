package nrabello.back.core.domain.entity.dto.task;

import lombok.Data;

@Data
public class TaskResponseDTO {

    private String title;

    private Integer code;

    private String description;

    private Double effort;

    private Double completedWork;

    private Double remainingWork;

    private String userName;
}
