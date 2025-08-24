package nrabello.back.core.usecase.statusTask;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.repository.StatusTaskRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateStatusTaskUseCase implements IUseCase<StatusTask, StatusTask> {

    private final StatusTaskRepository statusTaskRepository;

    @Override
    public StatusTask execute(StatusTask input) {
        input.setActive(true);
        return statusTaskRepository.save(input);
    }
}
