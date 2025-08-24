package nrabello.back.core.domain.entity.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nrabello.back.core.domain.entity.StatusTask;

import java.time.LocalDateTime;

@Data
public class CreateTaskDTO {

    @NotBlank(message = "Título é obrigatório.")
    private String title;

    @NotBlank(message = "Descrição é obrigatória.")
    private String description;

    @NotNull(message = "Prazo é obrigatório.")
    private Double effort;

    @NotNull(message = "Status é obrigatório.")
    private StatusTask status;

    private LocalDateTime startDate;

    @NotNull(message = "Data esperada é obrigatória.")
    private LocalDateTime targetDate;

    private LocalDateTime endDate;
}
