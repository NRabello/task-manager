package nrabello.back.inbound.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.inbound.facade.dto.task.CreateTaskDTO;
import nrabello.back.inbound.facade.dto.task.TaskResponseDTO;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.TaskFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task", description = "Operações relacionadas a tarefas")
public class TaskController {

    private final TaskFacade facade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<TaskResponseDTO>> criarTask(@RequestBody @Valid CreateTaskDTO task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseContent.<TaskResponseDTO>builder()
                        .data(facade.createTask(task))
                        .status(HttpStatus.CREATED.value())
                        .message("Tarefa cadastrada com sucesso.")
                        .params(task)
                        .build());
    }
}
