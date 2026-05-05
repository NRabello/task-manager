package nrabello.back.core.usecase.user;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.inbound.facade.dto.user.UserResponseDTO;
import nrabello.back.inbound.facade.mapper.UserMapper;
import nrabello.back.core.exception.DomainException;
import nrabello.back.core.repository.UserRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarDadosUsuarioUseCase implements IUseCase<Void, UserResponseDTO> {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserResponseDTO execute(Void input) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsernameAndActive(username, true).orElseThrow(() -> DomainException.userInvalid());

        return mapper.toResponseDTO(user);
    }
}
