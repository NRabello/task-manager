package nrabello.back.inbound.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.ProjectFacade;
import nrabello.back.inbound.facade.dto.project.CreateProjectDTO;
import nrabello.back.inbound.facade.dto.project.ProjectResponseDTO;
import nrabello.back.inbound.facade.dto.project.UpdateProjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Project", description = "Operações relacionadas a projetos")
public class ProjectController {

    private final ProjectFacade facade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<ProjectResponseDTO>> createProject(@RequestBody @Valid CreateProjectDTO project) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseContent.<ProjectResponseDTO>builder()
                        .data(facade.createProject(project))
                        .status(HttpStatus.CREATED.value())
                        .message("Projeto cadastrado com sucesso.")
                        .params(project)
                        .build());
    }

    @GetMapping("/organization/{organizationId}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<List<ProjectResponseDTO>>> listProjects(@PathVariable Long organizationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<List<ProjectResponseDTO>>builder()
                        .data(facade.listProjects(organizationId))
                        .status(HttpStatus.OK.value())
                        .message("Projetos obtidos com sucesso.")
                        .build());
    }

    @GetMapping("/{projectId}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<ProjectResponseDTO>> getProject(@PathVariable Long projectId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<ProjectResponseDTO>builder()
                        .data(facade.getProject(projectId))
                        .status(HttpStatus.OK.value())
                        .message("Projeto obtido com sucesso.")
                        .build());
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<ProjectResponseDTO>> updateProject(@RequestBody @Valid UpdateProjectDTO project) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<ProjectResponseDTO>builder()
                        .data(facade.updateProject(project))
                        .status(HttpStatus.OK.value())
                        .message("Projeto atualizado com sucesso.")
                        .params(project)
                        .build());
    }

    @DeleteMapping("/{projectId}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<Void>> deleteProject(@PathVariable Long projectId) {
        facade.deleteProject(projectId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Projeto excluído com sucesso.")
                        .build());
    }
}
