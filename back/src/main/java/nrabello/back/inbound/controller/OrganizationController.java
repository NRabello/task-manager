package nrabello.back.inbound.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nrabello.back.inbound.controller.Handler.ResponseContent;
import nrabello.back.inbound.facade.OrganizationFacade;
import nrabello.back.inbound.facade.dto.organization.CreateOrganizationDTO;
import nrabello.back.inbound.facade.dto.organization.OrganizationResponseDTO;
import nrabello.back.inbound.facade.dto.task.CreateTaskDTO;
import nrabello.back.inbound.facade.dto.task.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Organization", description = "Operações relacionadas a organização")
public class OrganizationController {

    private final OrganizationFacade facade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<OrganizationResponseDTO>> createOrganization(@RequestBody @Valid CreateOrganizationDTO organization) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseContent.<OrganizationResponseDTO>builder()
                        .data(facade.createOrganization(organization))
                        .status(HttpStatus.CREATED.value())
                        .message("Organização cadastrada com sucesso.")
                        .params(organization)
                        .build());
    }

    @GetMapping("/my-organizations")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseContent<List<OrganizationResponseDTO>>> listOrganization() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseContent.<List<OrganizationResponseDTO>>builder()
                        .data(facade.listOrganization())
                        .status(HttpStatus.OK.value())
                        .message("Organizações obtidas com sucesso.")
                        .build());
    }
}
