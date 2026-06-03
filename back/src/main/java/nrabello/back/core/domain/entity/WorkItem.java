package nrabello.back.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WorkItem extends DomainEntity {

    @Column
    private String title;

    @Column(unique = true)
    private Integer code;

    @Column
    private String description;

    @Column
    private Double effort = 0.0;

    @ManyToOne
    private User user;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime targetDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private Double completedWork = 0.0;

    @Column
    private Double remainingWork = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
