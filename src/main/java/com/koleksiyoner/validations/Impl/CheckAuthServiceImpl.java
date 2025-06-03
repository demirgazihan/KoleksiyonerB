package com.koleksiyoner.validations.Impl;

import com.koleksiyoner.api.requests.auth.SignInRequest;
import com.koleksiyoner.entities.Role;
import com.koleksiyoner.entities.User;
import com.koleksiyoner.enums.ERole;
import com.koleksiyoner.enums.EStatus;
import com.koleksiyoner.exceptions.enums.ExceptionMessage;
import com.koleksiyoner.exceptions.exception.BaseException;
import com.koleksiyoner.exceptions.exception.ErrorMessage;
import com.koleksiyoner.repositories.RoleRepository;
import com.koleksiyoner.repositories.UserRepository;
import com.koleksiyoner.validations.CheckAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CheckAuthServiceImpl implements CheckAuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    @Override
    public void checkUserExistsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.USER_EMAIL_ALREADY_EXISTS, email));
        }
    }

    @Override
    public Role checkRoleFindByName(ERole eRole) {
        return roleRepository.findByName(eRole).orElseThrow(() ->
                new BaseException(new ErrorMessage(ExceptionMessage.ROLE_IS_NOT_FOUND, eRole.toString())));
    }

    @Override
    public Authentication checkUserIsAuthenticated(SignInRequest signInRequest) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        } catch (Exception ex) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.USERNAME_OR_PASSWORD_IS_INCORRECT, signInRequest.getEmail()));
        }
    }

    @Override
    public User checkAndGetUserExistsByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (Objects.isNull(user)) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.USER_NOT_FOUND, email));
        } else if (user.getEStatus().equals(EStatus.PASSIVE)) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.USER_IS_PASSIVE, email));
        } else if (user.getEStatus().equals(EStatus.REMOVED)) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.USER_IS_REMOVED, email));
        }
        return user;
    }
}
