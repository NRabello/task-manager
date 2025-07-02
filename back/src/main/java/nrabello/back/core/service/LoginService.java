package nrabello.back.core.service;

import nrabello.back.core.domain.entity.User;
import nrabello.back.core.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    private final UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return user.get();

    }

}