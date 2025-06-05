package com.koleksiyoner.api.controllers;

import com.koleksiyoner.api.requests.BaseListRequest;
import com.koleksiyoner.api.requests.fabric.FabricRequest;
import com.koleksiyoner.api.responses.fabric.FabricGroupByNameResponse;
import com.koleksiyoner.api.responses.fabric.FabricResponse;
import com.koleksiyoner.commons.results.DataResult;
import com.koleksiyoner.services.fabric.FabricService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.koleksiyoner.api.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(value = FABRIC)
@AllArgsConstructor
public class FabricController {

    private final FabricService fabricService;



    @GetMapping(FABRIC_FIND_GROUP_BY_NAME)
    public ResponseEntity<DataResult<List<FabricGroupByNameResponse>>> getFabricsGroupByName() {
        DataResult<List<FabricGroupByNameResponse>> result = fabricService.getFabricsGroupByName();
        return new ResponseEntity<>(result, result.getHttpStatus());
    }
    @GetMapping(FABRIC_FIND_ALL)
    public ResponseEntity<DataResult<List<FabricResponse>>> findAll(@RequestBody BaseListRequest baseListRequest) {
        DataResult<List<FabricResponse>> result = fabricService.findAll(baseListRequest);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @GetMapping(FABRIC_FIND_ALL_BY_NAME)
    public ResponseEntity<DataResult<List<FabricResponse>>> findAllByName(@RequestBody FabricRequest fabricRequest) {
        DataResult<List<FabricResponse>> result = fabricService.findAllByName(fabricRequest);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @GetMapping(FABRIC_FIND_BY_ID)
    public ResponseEntity<DataResult<FabricResponse>> findById(@RequestBody FabricRequest fabricRequest) {
        DataResult<FabricResponse> result = fabricService.findById(fabricRequest);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @PostMapping(FABRIC_CREATE)
    public ResponseEntity<DataResult<List<FabricResponse>>> createFabrics(@RequestBody List<FabricRequest> fabricRequests) {
        DataResult<List<FabricResponse>> result = fabricService.createFabrics(fabricRequests);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    @PostMapping(FABRIC_CHANGED_E_STATUS)
    public ResponseEntity<DataResult<List<FabricResponse>>> changeFabricsEStatus(@RequestBody List<FabricRequest> fabricRequests) {
        DataResult<List<FabricResponse>> result = fabricService.changeFabricsEStatus(fabricRequests);
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

}
