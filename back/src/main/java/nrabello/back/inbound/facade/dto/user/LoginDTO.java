package nrabello.back.inbound.facade.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO{


    @NotBlank(message = "E-mail é obrigatório.")
    @Schema(description = "Email do usuário (usado como nome de usuário)", example = "usuario@exemplo.com")
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    @Schema(description = "Senha do usuário", example = "Senha@123")
    private String password;
}
