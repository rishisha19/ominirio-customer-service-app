package org.omnirio.app.domain;


import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;


@Getter @Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "userId", updatable = false, nullable = false)
    private String userId;
    private String userName;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;

    public CustomerEntity (String userId, String userName, Date dateOfBirth, String gender, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerEntity)) {
            return false;
        }
        return userId != null && userId.equals(((CustomerEntity) o).userId);
    }

}
