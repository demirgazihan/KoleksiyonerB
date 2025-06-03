package com.koleksiyoner.services.auth.impl;


import com.koleksiyoner.api.requests.auth.SignInRequest;
import com.koleksiyoner.api.requests.auth.SignUpRequest;
import com.koleksiyoner.api.responses.auth.SignInResponse;
import com.koleksiyoner.api.responses.auth.SignUpResponse;
import com.koleksiyoner.commons.mappers.ModelMapperService;
import com.koleksiyoner.commons.results.DataResult;
import com.koleksiyoner.commons.results.SuccessDataResult;
import com.koleksiyoner.entities.Role;
import com.koleksiyoner.entities.User;
import com.koleksiyoner.enums.ERole;
import com.koleksiyoner.enums.EStatus;
import com.koleksiyoner.repositories.RoleRepository;
import com.koleksiyoner.repositories.UserRepository;
import com.koleksiyoner.services.auth.AuthService;
import com.koleksiyoner.validations.CheckAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtServiceImpl jwtService;

    private final CheckAuthService checkAuthService;

    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<SignInResponse> signIn(SignInRequest signInRequest) {
        Authentication authentication = checkAuthService.checkUserIsAuthenticated(signInRequest);
        User user = checkAuthService.checkAndGetUserExistsByEmail(signInRequest.getEmail());
        return new SuccessDataResult<>(prepareSignInResponse(user, authentication), HttpStatus.OK);
    }

    private SignInResponse prepareSignInResponse(User user, Authentication authentication) {
        SignInResponse signInResponse = this.modelMapperService.forResponse().map(user, SignInResponse.class);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        signInResponse.setToken(jwtService.generateJwtToken(authentication));
        signInResponse.setRoles(roles);
        return signInResponse;

    }

    @Override
    public DataResult<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        checkAuthService.checkUserExistsByEmail(signUpRequest.getEmail());
        User user = prepareUser(signUpRequest);
        userRepository.save(user);
        return new SuccessDataResult<>(prepareSignUpResponse(user), HttpStatus.CREATED);
    }

    /* */
    private User prepareUser(SignUpRequest signUpRequest) {
        User user = modelMapperService.forRequest().map(signUpRequest, User.class);
        user.setUsername(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(prepareRole());
        user.setEStatus(EStatus.ACTIVE);
        return user;
    }

    /* The role must be user when registering. */
    private Set<Role> prepareRole() {
        Set<Role> roles = new HashSet<>();
        roles.add(checkAuthService.checkRoleFindByName(ERole.ROLE_USER));
        return roles;
    }

    private SignUpResponse prepareSignUpResponse(User user) {
        return this.modelMapperService.forResponse().map(user, SignUpResponse.class);
    }

    private Authentication getAuthenticate(SignInRequest signInRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }
}
