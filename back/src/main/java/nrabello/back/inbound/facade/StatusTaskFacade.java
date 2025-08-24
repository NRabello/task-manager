package nrabello.back.inbound.facade;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.domain.entity.StatusTask;
import nrabello.back.core.usecase.statusTask.CreateStatusTaskUseCase;
import nrabello.back.core.usecase.statusTask.ListStatusTaskUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusTaskFacade {

    private final ListStatusTaskUseCase listStatusTaskUseCase;
    private final CreateStatusTaskUseCase createStatusTaskUseCase;

    public StatusTask createStatusTask(StatusTask statusTask){
        return createStatusTaskUseCase.execute(statusTask);
    }

    public List<StatusTask> listStatusTask(){
        return listStatusTaskUseCase.execute(null);
    }
}
