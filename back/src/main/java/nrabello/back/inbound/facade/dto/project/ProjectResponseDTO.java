package nrabello.back.inbound.facade.dto.project;

import lombok.Data;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Long organizationId;
}
