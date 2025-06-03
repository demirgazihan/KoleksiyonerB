package com.koleksiyoner.api.responses.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koleksiyoner.enums.EGender;
import com.koleksiyoner.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private Long id;
    private String token;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private EGender eGender;
    private EStatus eStatus;
    private List<String> roles;
}
