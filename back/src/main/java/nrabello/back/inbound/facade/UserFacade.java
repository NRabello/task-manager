package nrabello.back.inbound.facade;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.inbound.facade.dto.user.LoginDTO;
import nrabello.back.inbound.facade.dto.user.UserResponseDTO;
import nrabello.back.core.usecase.user.BuscarDadosUsuarioUseCase;
import nrabello.back.core.usecase.user.LoginUseCase;
import org.springframework.stereotype.Component;
import nrabello.back.core.usecase.user.CreateUserUseCase;
import nrabello.back.inbound.facade.dto.user.CreateUserDTO;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;

    private final LoginUseCase loginUseCase;

    private final BuscarDadosUsuarioUseCase buscarDadosUsuarioUseCase;

    @Transactional
    public String register(CreateUserDTO dto) {
        createUserUseCase.execute(dto);
        return "Usuario cadastrado com sucesso.";
    }

    public String login(LoginDTO dto) {
        return loginUseCase.execute(dto);
    }

    public UserResponseDTO buscarDadosUsuario(){
        return buscarDadosUsuarioUseCase.execute(null);
    }
}
