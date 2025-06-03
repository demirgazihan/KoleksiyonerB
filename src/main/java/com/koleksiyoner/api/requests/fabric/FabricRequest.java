package com.koleksiyoner.api.requests.fabric;

import com.koleksiyoner.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricRequest {

    private Long id;

    private String name;

    private String code;

    private String status;

}
