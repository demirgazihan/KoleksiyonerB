package com.koleksiyoner.api.constants;

import org.springframework.http.MediaType;

public class ApiEndpointConstants {
    public static final String API = "/api";
    // AUTH
    public static final String AUTH = API + "/auth";
    public static final String SIGN_IN = "/signIn";
    public static final String SIGN_UP = "/signUp";
    // Material
    public static final String MATERIAL = API + "/material";
    public static final String FABRIC = MATERIAL + "/fabric";
    public static final String FABRIC_CREATE = "/create";
    public static final String FABRIC_FIND_GROUP_BY_NAME = "/findFabricsGroupByName";
    public static final String FABRIC_FIND_ALL = "/findAll";
    public static final String FABRIC_FIND_ALL_BY_NAME = "/findAllByName";
    public static final String FABRIC_FIND_BY_ID = "/findById";
    public static final String FABRIC_CHANGED_E_STATUS = "/changeStatus";

    public static final String RESPONSE_CONTENTTYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";
    public static final String REGISTER_URL = "http://user-service/users/register";
    public static final String LOGIN_URL = "http://user-service/users/login";
    public static final String TOKEN_URL = "http://user-service/token/createToken";


}
