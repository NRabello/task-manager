package nrabello.back.inbound.facade.mapper;

import nrabello.back.core.domain.entity.User;
import nrabello.back.inbound.facade.dto.user.CreateUserDTO;
import nrabello.back.inbound.facade.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity(CreateUserDTO dto);

    @Mapping(target = "rolename", source = "role.name")
    UserResponseDTO toResponseDTO(User entity);
}
