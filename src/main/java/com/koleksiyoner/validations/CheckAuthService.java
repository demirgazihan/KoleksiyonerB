package com.koleksiyoner.validations;

import com.koleksiyoner.api.requests.auth.SignInRequest;
import com.koleksiyoner.entities.Role;
import com.koleksiyoner.entities.User;
import com.koleksiyoner.enums.ERole;
import org.springframework.security.core.Authentication;

public interface CheckAuthService {
    void checkUserExistsByEmail(String email);

    Role checkRoleFindByName(ERole eRole);

    Authentication checkUserIsAuthenticated(SignInRequest signInRequest);

    User checkAndGetUserExistsByEmail(String email);
}
