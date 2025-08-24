package nrabello.back.core.domain.entity.mapper;

import nrabello.back.core.domain.entity.User;
import nrabello.back.core.domain.entity.dto.user.CreateUserDTO;
import nrabello.back.core.domain.entity.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    public abstract User toEntity(CreateUserDTO dto);

    @Mapping(target = "rolename", source = "role.name")
    public abstract UserResponseDTO toResponseDTO(User entity);
}
