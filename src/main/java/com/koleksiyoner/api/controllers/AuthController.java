package com.koleksiyoner.api.controllers;

import com.koleksiyoner.api.requests.auth.SignInRequest;
import com.koleksiyoner.api.requests.auth.SignUpRequest;
import com.koleksiyoner.api.responses.auth.SignInResponse;
import com.koleksiyoner.api.responses.auth.SignUpResponse;
import com.koleksiyoner.commons.results.DataResult;
import com.koleksiyoner.services.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.koleksiyoner.api.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(value = AUTH)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(SIGN_IN)
    public ResponseEntity<DataResult<SignInResponse>> signIn(@RequestBody SignInRequest signInRequest) {
        DataResult<SignInResponse> result = authService.signIn(signInRequest);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<DataResult<SignUpResponse>> signUp(@RequestBody SignUpRequest signUpRequest) {
        DataResult<SignUpResponse> result = authService.signUp(signUpRequest);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }
}
