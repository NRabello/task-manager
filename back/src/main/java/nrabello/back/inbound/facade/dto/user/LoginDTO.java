package nrabello.back.core.domain.entity.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO{


    @NotBlank(message = "E-mail é obrigatório.")
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    private String password;
}
