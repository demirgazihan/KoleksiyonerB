package com.koleksiyoner.validations;

import com.koleksiyoner.api.requests.fabric.FabricRequest;
import com.koleksiyoner.entities.Fabric;

import java.util.List;

public interface CheckFabricService {

    Fabric checkFindById(Long id);

    void checkFabricExistsByCode(List<FabricRequest> fabricRequests);
}
