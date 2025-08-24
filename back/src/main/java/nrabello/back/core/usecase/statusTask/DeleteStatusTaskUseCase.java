package nrabello.back.core.usecase.statusTask;

import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.domain.exception.EntityNotFoundException;
import nrabello.back.core.repository.StatusTaskRepository;
import nrabello.back.core.usecase.IUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteStatusTaskUseCase implements IUseCase<StatusTask, Void> {

    private final StatusTaskRepository statusTaskRepository;

    @Override
    public Void execute(StatusTask input) {

        if(statusTaskRepository.findByIdAndActive(input.getId(), true).isEmpty()){
            throw EntityNotFoundException.statusTaskNaoEncontrada(input.getId());
        }

        statusTaskRepository.deleteById(input.getId());

        return null;
    }
}
