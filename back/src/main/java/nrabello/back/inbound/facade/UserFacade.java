package nrabello.back.inbound.facade;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.LoginDTO;
import nrabello.back.core.domain.entity.dto.user.UserResponseDTO;
import nrabello.back.core.usecase.user.BuscarDadosUsuarioUseCase;
import nrabello.back.core.usecase.user.LoginUseCase;
import org.springframework.stereotype.Component;
import nrabello.back.core.usecase.user.CreateUserUseCase;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;

    private final LoginUseCase loginUseCase;

    private final BuscarDadosUsuarioUseCase buscarDadosUsuarioUseCase;

    @Transactional
    public String register(CreateUserDTO dto) {
        return createUserUseCase.execute(dto);
    }

    public String login(LoginDTO dto) {
        return loginUseCase.execute(dto);
    }

    public UserResponseDTO buscarDadosUsuario(){
        return buscarDadosUsuarioUseCase.execute(null);
    }
}
