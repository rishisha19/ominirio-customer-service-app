package org.omnirio.app.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "roleId", updatable = false, nullable = false)
    private String roleId;
    @Column(unique = true)
    private String roleName;
    @Column(unique = true)
    private String roleCode;


}
