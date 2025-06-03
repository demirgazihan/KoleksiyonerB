package com.koleksiyoner.api.responses.fabric;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricGroupByNameResponse {

    private String name;

    private String count;

}
