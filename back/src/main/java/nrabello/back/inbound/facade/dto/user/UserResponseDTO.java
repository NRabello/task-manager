package nrabello.back.inbound.facade.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.inbound.facade.dto.AbstractDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponseDTO extends AbstractDTO {

    private String name;

    private String username;

    private String rolename;

}
