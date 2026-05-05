package nrabello.back.inbound.facade.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrganizationDTO {

    @NotBlank(message= "Nome não pode estar vazio")
    @Schema(description = "Nome da organização", example = "Time Solutions", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

}
