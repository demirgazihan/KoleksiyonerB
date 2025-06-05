package com.koleksiyoner.api.requests.fabric;

import com.koleksiyoner.api.requests.BaseListRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FabricListRequest extends BaseListRequest {
    String name;
}
