package nrabello.back.inbound.facade.dto.workItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public abstract class CreateWorkItemDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Double effort;

    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime targetDate;

    @NotNull
    private Long userId;

    @NotNull
    private long organizationId;
}
