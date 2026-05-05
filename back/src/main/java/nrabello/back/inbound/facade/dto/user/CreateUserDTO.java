package nrabello.back.core.domain.entity.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.core.domain.entity.dto.AbstractDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserDTO extends AbstractDTO {

    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Formato de Email inválido.")
    private String username;

    @NotBlank(message = "Senha é obrigatória.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",
            message = "Senha deve conter pelo menos uma letra maiúscula, pelo menos uma letra minúscula, pelo menos um número e pelo menos um caractere especial.")
    @Size(min = 8, max = 20, message = "Senha deve ter entre 8 e 20 caracteres.")
    private String password;

    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    private boolean admin;
}
