package nrabello.back.core.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDetails extends WorkItemDetails {

    @Column
    private boolean CRUDTested = false;

    @Column
    private boolean MultipleBrowsersTested = false;

    @Column
    private boolean EvidenceAttached = false;
}
