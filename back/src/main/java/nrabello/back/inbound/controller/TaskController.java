package nrabello.back.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.task.CreateTaskDTO;
import nrabello.back.core.domain.entity.dto.task.TaskResponseDTO;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.TaskFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade facade;

    @GetMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseContent<List<TaskResponseDTO>>> getTasks(@RequestParam(name = "userId", required = false) Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<List<TaskResponseDTO>>builder()
                .data(facade.listarTasks(userId))
                .status(HttpStatus.OK.value())
                .message("Lista de tarefas obtida com sucesso.")
                .params(userId)
                .build());
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseContent<TaskResponseDTO>> register(@RequestBody @Valid CreateTaskDTO task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseContent.<TaskResponseDTO>builder()
                        .data(facade.createTask(task))
                        .status(HttpStatus.CREATED.value())
                        .message("Tarefa cadastrada com sucesso.")
                        .params(task)
                        .build());
    }
}
