package nrabello.back.inbound.facade.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFilterDTO {

    private String title;
    private Long statusId;
    private Long userId;
}