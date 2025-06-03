package com.koleksiyoner.api.responses.fabric;

import com.koleksiyoner.api.responses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricResponse extends BaseResponse {

    private Long id;

    private String name;

    private String code;

}
