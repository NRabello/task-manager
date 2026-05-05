package nrabello.back.inbound.facade;


import lombok.RequiredArgsConstructor;
import nrabello.back.core.usecase.statusWorkItem.CreateStatusWorkItemUseCase;
import nrabello.back.core.usecase.statusWorkItem.ListStatusWorkItemUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusTaskFacade {

    private final ListStatusWorkItemUseCase listStatusWorkItemUseCase;
    private final CreateStatusWorkItemUseCase createStatusWorkItemUseCase;

    public StatusWorkItem createStatusTask(StatusWorkItem statusWorkItem){
        return createStatusWorkItemUseCase.execute(statusWorkItem);
    }

    public List<StatusWorkItem> listStatusTask(){
        return listStatusWorkItemUseCase.execute(null);
    }
}
