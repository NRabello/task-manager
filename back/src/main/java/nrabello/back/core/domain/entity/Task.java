package nrabello.back.core.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import nrabello.back.core.domain.enums.StatusTaskEnum;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task extends DomainEntity {

    @Column
    private String title;

    @Column(unique = true)
    private Integer code;

    @Column
    private String description;

    @ManyToOne
    private StatusTask status;

    @Column
    private Double effort;

    @Column
    private Double completedWork = 0.0;

    @Column
    private Double remainingWork = 0.0;

    @ManyToOne
    private User user;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime targetDate;

    @Column
    private LocalDateTime endDate;

}
