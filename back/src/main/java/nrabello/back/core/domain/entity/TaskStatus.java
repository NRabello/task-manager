package nrabello.back.core.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class TaskStatus extends DomainEntity {

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Color;
}
