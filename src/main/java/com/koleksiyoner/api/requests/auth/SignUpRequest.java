package com.koleksiyoner.api.requests.auth;

import com.koleksiyoner.enums.EGender;
import com.koleksiyoner.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String email;
    private String password;
    private String firstname;
    private EGender gender;
    private boolean iWantToReceiveMail;
    private boolean iWantToReceiveSms;
    private String lastname;
    private String phone;
    private EStatus status;
}
