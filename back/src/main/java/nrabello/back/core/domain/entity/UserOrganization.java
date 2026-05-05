package nrabello.back.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nrabello.back.core.domain.enums.OrganizationRoleEnum;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserOrganization extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private OrganizationRoleEnum role;
}
