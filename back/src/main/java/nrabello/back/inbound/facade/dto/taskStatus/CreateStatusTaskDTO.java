package nrabello.back.inbound.facade.dto.taskStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStatusTaskDTO {

    @NotBlank
    private String Name;

    @NotBlank
    private String Color;
}
