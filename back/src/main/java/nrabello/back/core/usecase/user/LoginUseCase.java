package nrabello.back.core.usecase.user;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.LoginDTO;
import nrabello.back.core.domain.exception.DomainException;
import nrabello.back.core.repository.UserRepository;
import nrabello.back.core.service.JwtService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginUseCase implements IUseCase<LoginDTO, String> {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    public String execute(LoginDTO input) {

        Optional<User> user = userRepository.findByUsernameAndActive(input.getUsername(), true);

        if(user.isEmpty()){
            throw DomainException.usuarioInvalido();
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.get());
        }else{
            throw DomainException.usuarioInvalido();
        }
    }
}
