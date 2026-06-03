package nrabello.back.inbound.facade.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectDTO {

    @NotNull(message = "ID do projeto é obrigatório")
    @Schema(description = "ID do projeto", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Schema(description = "Nome do projeto", example = "TaskManager_Api", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Descrição do projeto", example = "Api do Projeto Task Manager")
    private String description;
}
