package nrabello.back.core.domain.entity.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nrabello.back.core.domain.entity.dto.AbstractDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserDTO extends AbstractDTO {

    private String username;

    private String password;

    private boolean admin;
}
