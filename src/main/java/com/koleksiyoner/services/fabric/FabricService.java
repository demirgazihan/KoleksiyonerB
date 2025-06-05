package com.koleksiyoner.services.fabric;

import com.koleksiyoner.api.requests.BaseListRequest;
import com.koleksiyoner.api.requests.fabric.FabricRequest;
import com.koleksiyoner.api.responses.fabric.FabricGroupByNameResponse;
import com.koleksiyoner.api.responses.fabric.FabricResponse;
import com.koleksiyoner.commons.results.DataResult;
import com.koleksiyoner.entities.Fabric;

import java.util.List;

public interface FabricService {

    DataResult<FabricResponse> findById(FabricRequest fabricRequest);

    DataResult<List<FabricGroupByNameResponse>> getFabricsGroupByName();

    DataResult<List<FabricResponse>> createFabrics(List<FabricRequest> fabricRequests);

    DataResult<List<FabricResponse>> findAllByName(FabricRequest fabricRequest);

    DataResult<List<FabricResponse>> findAll(BaseListRequest baseListRequest);

    DataResult<List<FabricResponse>> changeFabricsEStatus(List<FabricRequest> fabricRequests);
}
