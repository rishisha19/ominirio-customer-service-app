package org.omnirio.app.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerR {

    private String userId;
    private String userName;
    private String dateOfBirth;
    @NonNull
    private String gender;
    private String phoneNumber;

    public CustomerR (String userName, String dateOfBirth, String gender, String phoneNumber) {
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
}
