package nrabello.back.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import nrabello.back.core.domain.entity.dto.user.LoginDTO;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserFacade facade;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseContent<String>> register(@RequestBody @Valid CreateUserDTO user) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseContent.<String>builder()
                        .data(facade.register(user))
                        .status(HttpStatus.CREATED.value())
                        .message("Usuário cadastrado com sucesso.")
                        .params(user)
                        .build());
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseContent<String>> login(@RequestBody @Valid LoginDTO login) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<String>builder()
                        .data(facade.login(login))
                        .status(HttpStatus.OK.value())
                        .message("Login bem-sucedido.")
                        .params(login)
                        .build());
    }


    @GetMapping("/dados-pessoais")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseContent<User>> dadosPessoais() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<User>builder()
                        .data(facade.buscarDadosUsuario())
                        .status(HttpStatus.OK.value())
                        .message("Dados obtidos com sucesso.")
                        .build());
    }
}
