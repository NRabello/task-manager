package nrabello.back.inbound.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.domain.entity.Task;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.StatusTaskFacade;
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
public class StatusTaskController {

    private final StatusTaskFacade facade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ResponseContent<StatusTask>> createStatusTask(@RequestBody @Valid StatusTask statusTask) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<StatusTask>builder()
                        .data(facade.createStatusTask(statusTask))
                        .status(HttpStatus.OK.value())
                        .message("Status de tarefa criado com sucesso.")
                        .build());
    }

    @GetMapping("/list")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<ResponseContent<List<StatusTask>>> getStatusTask() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<List<StatusTask>>builder()
                        .data(facade.listStatusTask())
                        .status(HttpStatus.OK.value())
                        .message("Lista de status de tarefa obtida com sucesso.")
                        .build());
    }
}
