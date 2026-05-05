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
public class Task extends WorkItem {

    @Column
    private boolean crudTested = false;

    @Column
    private boolean multipleBrowsersTested = false;

    @Column
    private boolean evidenceAttached = false;

    @Column
    private String acceptanceCriteria;

    @Column
    private String descriptionOfImpediment;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatus status;


}
