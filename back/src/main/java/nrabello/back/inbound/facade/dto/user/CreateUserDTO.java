package nrabello.back.inbound.facade.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.inbound.facade.dto.AbstractDTO;

@Data
public class CreateUserDTO {

    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Formato de Email inválido.")
    @Schema(description = "Email do usuário.", example = "usuario@exemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",
            message = "Senha deve conter pelo menos uma letra maiúscula, pelo menos uma letra minúscula, pelo menos um número e pelo menos um caractere especial.")
    @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres.")
    @Schema(description = "Senha do usuário.", example = "Senha@123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres.")
    @Schema(description = "Nome completo do usuário.", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
