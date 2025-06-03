package com.koleksiyoner.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EProductType {
    SALON_SET, BEDROOM_SET,OTHER;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
