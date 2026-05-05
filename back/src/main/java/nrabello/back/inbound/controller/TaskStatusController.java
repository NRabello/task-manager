package nrabello.back.inbound.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.TaskStatus;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.TaskStatusFacade;
import nrabello.back.inbound.facade.dto.taskStatus.CreateStatusTaskDTO;
import nrabello.back.inbound.facade.dto.taskStatus.TaskStatusResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-task")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "StatusTask", description = "Operações relacionadas a status de tarefas")
public class TaskStatusController {

    private final TaskStatusFacade facade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ResponseContent<TaskStatus>> createStatusTask(@RequestBody @Valid CreateStatusTaskDTO createStatusTaskDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<TaskStatus>builder()
                        .data(facade.createStatusTask(createStatusTaskDTO))
                        .status(HttpStatus.OK.value())
                        .message("Status de tarefa criado com sucesso.")
                        .build());
    }

    @GetMapping("/list")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ResponseContent<List<TaskStatusResponseDTO>>> getStatusTask() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<List<TaskStatusResponseDTO>>builder()
                        .data(facade.listTaskStatus())
                        .status(HttpStatus.OK.value())
                        .message("Lista de status de tarefa obtida com sucesso.")
                        .build());
    }
}
