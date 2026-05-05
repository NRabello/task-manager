package nrabello.back.core.usecase.user;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Role;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.DomainException;
import nrabello.back.core.service.JwtService;
import nrabello.back.inbound.facade.dto.user.CreateUserDTO;
import nrabello.back.inbound.facade.mapper.UserMapper;
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
public class CreateUserUseCase implements IUseCase<CreateUserDTO, User> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    @Override
    public User execute(CreateUserDTO input) {

        validateUserExists(input.getUsername());

        String rawPassword = input.getPassword();
        input.setPassword(passwordEncoder.encode(rawPassword));

        User user = createUser(input);
        user = userRepository.save(user);

        return user;
    }

    private void validateUserExists(String username) {
        if (userRepository.findByUsernameAndActive(username, true).isPresent()) {
            throw DomainException.userAlreadyExists();
        }
    }

    private User createUser(CreateUserDTO input) {
        User user = mapper.toEntity(input);
        user.setRole(roleRepository.findByName("ROLE_USER").orElseThrow(DomainException::roleNotFound));
        return user;
    }

}
