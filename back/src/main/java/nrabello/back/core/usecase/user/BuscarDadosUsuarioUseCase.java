package nrabello.back.core.usecase.user;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.repository.UserRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarDadosUsuarioUseCase implements IUseCase<Void, User> {

    private final UserRepository userRepository;

    @Override
    public User execute(Void input) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new RuntimeException("Usuário não encontrado");
        }

        return user;
    }
}
