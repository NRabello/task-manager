package nrabello.back.core.usecase.user;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Role;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import nrabello.back.core.domain.entity.mapper.UserMapper;
import nrabello.back.core.repository.RoleRepository;
import nrabello.back.core.repository.UserRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements IUseCase<CreateUserDTO, String> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    private final AuthenticationManager authenticationManager;


    @Override
    public String execute(CreateUserDTO input) {

        validateUserExists(input.getUsername());

        String rawPassword = input.getPassword();
        input.setPassword(passwordEncoder.encode(rawPassword));

        User user = createUser(input);
        user = userRepository.save(user);

        return authenticateAndGenerateToken(input.getUsername(), rawPassword, user);
    }

    private void validateUserExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
    }

    private User createUser(CreateUserDTO input) {
        User user = mapper.toEntity(input);
        user.setRole(getRole(input.isAdmin()));
        user.setActive(true);
        return user;
    }

    private Role getRole(boolean isAdmin) {
        return isAdmin ? roleRepository.findByName("ROLE_ADMIN").orElseThrow(RuntimeException::new) : roleRepository.findByName("ROLE_USER").orElseThrow(RuntimeException::new);
    }

    private String authenticateAndGenerateToken(String username, String rawPassword, User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, rawPassword));

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Erro ao autenticar o usuário");
        }

        return "User created successfully";
    }
}
