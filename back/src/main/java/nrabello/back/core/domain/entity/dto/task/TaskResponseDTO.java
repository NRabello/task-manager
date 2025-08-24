package nrabello.back.core.domain.entity.dto.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {

    private String title;

    private Integer code;

    private String description;

    private Double effort;

    private Double completedWork;

    private Double remainingWork;

    private String userName;

    private LocalDateTime startDate;

    private LocalDateTime targetDate;

    private LocalDateTime endDate;
}
