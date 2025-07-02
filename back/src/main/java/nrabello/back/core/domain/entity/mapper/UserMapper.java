package nrabello.back.core.domain.entity.mapper;

import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    public abstract User toEntity(CreateUserDTO dto);
}
