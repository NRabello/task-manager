package nrabello.back.core.domain.entity.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.core.domain.entity.Role;
import nrabello.back.core.domain.entity.dto.AbstractDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponseDTO extends AbstractDTO {

    private String name;

    private String username;

    private String rolename;

}
