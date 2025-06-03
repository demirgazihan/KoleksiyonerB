package com.koleksiyoner.exceptions.enums;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    // GENERAL
    GENERAL_EXCEPTION("GENERAL_EXCEPTION", "Genel Hata."),

    // USER && AUTH
    USER_EMAIL_ALREADY_EXISTS("USER_EMAIL_ALREADY_EXISTS", "Bu E-posta kullanılıyor."),
    ROLE_IS_NOT_FOUND("ROLE_IS_NOT_FOUND", "Rol bulunamadı."),
    USERNAME_OR_PASSWORD_IS_INCORRECT("USERNAME_OR_PASSWORD_IS_INCORRECT", "Kullanıcı adı yada parola yanlış."),
    USER_NOT_FOUND("USER_NOT_FOUND", "Kullanıcı bulunamadı."),
    USER_IS_PASSIVE("USER_IS_PASSIVE", "Kullanıcı pasiftir."),
    USER_IS_REMOVED("USER_IS_REMOVED", "Kullanıcı silinmiştir."),

    // FABRIC
    FABRIC_NOT_FOUND("FABRIC_NOT_FOUND", "Kumaş Bulunamadı."),
    FABRICS_ARE_ALREADY_ADDED("FABRICS_ARE_ALREADY_ADDED", "Kumaşlar Zaten Eklenmiş.");

    private String code;
    private String message;

    ExceptionMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
