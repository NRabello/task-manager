package nrabello.back.inbound.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Validated
@RequiredArgsConstructor
public class TaskController {

    @GetMapping
    public ResponseEntity<String> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body("Task list");
    }
}
