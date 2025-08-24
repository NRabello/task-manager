package nrabello.back.core.usecase.statusTask;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.repository.StatusTaskRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListStatusTaskUseCase implements IUseCase<Void, List<StatusTask>> {

    private final StatusTaskRepository statusTaskRepository;

    @Override
    public List<StatusTask> execute(Void input) {
        return statusTaskRepository.findAllByActive(true);
    }
}
