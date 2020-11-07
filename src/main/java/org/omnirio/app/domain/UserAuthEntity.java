package org.omnirio.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="user_auth")
public class UserAuthEntity {
    @Id
    private String userId;
    private String password;
    private String roleCode;
}
