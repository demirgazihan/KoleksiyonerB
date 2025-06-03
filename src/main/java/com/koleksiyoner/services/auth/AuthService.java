package com.koleksiyoner.services.auth;

import com.koleksiyoner.api.requests.auth.SignInRequest;
import com.koleksiyoner.api.requests.auth.SignUpRequest;
import com.koleksiyoner.api.responses.auth.SignInResponse;
import com.koleksiyoner.api.responses.auth.SignUpResponse;
import com.koleksiyoner.commons.results.DataResult;

public interface AuthService {

    DataResult<SignInResponse> signIn(SignInRequest signInRequest);

    DataResult<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
