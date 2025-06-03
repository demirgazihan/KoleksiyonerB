package com.koleksiyoner.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EGender {
    MALE, FEMALE, UNSPECIFIED;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
