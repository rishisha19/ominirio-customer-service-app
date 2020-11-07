package org.omnirio.app.web.rest;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiError {

    private String message;
    private List<String> elseTry;
}
