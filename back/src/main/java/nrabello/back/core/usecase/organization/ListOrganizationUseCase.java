package nrabello.back.core.usecase.organization;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.Organization;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.repository.OrganizationRepository;
import nrabello.back.core.service.UserService;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListOrganizationUseCase implements IUseCase<Void, List<Organization>> {

    private final OrganizationRepository repository;
    private final UserService userService;

    @Override
    public List<Organization> execute(Void input) {
        User user = userService.getUsuarioLogado();
        List<Organization> organizations = repository.findAllByUsersUserId(user.getId());
        return organizations;
    }
}
