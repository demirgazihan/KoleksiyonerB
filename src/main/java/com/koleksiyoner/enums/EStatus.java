package com.koleksiyoner.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EStatus {
    ACTIVE, PASSIVE, REMOVED;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
