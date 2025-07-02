package nrabello.back.core.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import nrabello.back.core.domain.enums.StatusTaskEnum;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task extends DomainEntity {

    @Column
    private String title;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer code;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusTaskEnum status;

    @Column
    private Double effort;

    @Column
    private Double completedWork;

    @Column
    private Double remainingWork;

    @ManyToOne
    private User user;

}
