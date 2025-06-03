package com.koleksiyoner.services.fabric.Impl;

import com.koleksiyoner.api.requests.fabric.FabricRequest;
import com.koleksiyoner.api.responses.fabric.FabricGroupByNameResponse;
import com.koleksiyoner.api.responses.fabric.FabricResponse;
import com.koleksiyoner.commons.mappers.ModelMapperService;
import com.koleksiyoner.commons.results.DataResult;
import com.koleksiyoner.commons.results.SuccessDataResult;
import com.koleksiyoner.entities.Fabric;
import com.koleksiyoner.enums.EStatus;
import com.koleksiyoner.repositories.FabricRepository;
import com.koleksiyoner.services.fabric.FabricService;
import com.koleksiyoner.validations.CheckFabricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FabricServiceImpl implements FabricService {

    private final FabricRepository fabricRepository;

    private final ModelMapperService modelMapperService;

    private final CheckFabricService checkFabricService;

    @Override
    public DataResult<FabricResponse> findById(FabricRequest fabricRequest) {
        Fabric fabric = checkFabricService.checkFindById(fabricRequest.getId());
        return new SuccessDataResult<>(
                prepareFabric(fabric),
                HttpStatus.OK);
    }

    private FabricResponse prepareFabric(Fabric fabric) {
        return (modelMapperService.forResponse().map(fabric, FabricResponse.class));
    }

    @Override
    public DataResult<List<FabricGroupByNameResponse>> getFabricsGroupByName() {
        return new SuccessDataResult<>(
                prepareFabricGroupByNameResponse(fabricRepository.getFabricsGroupByName()),
                HttpStatus.OK);
    }

    private List<FabricGroupByNameResponse> prepareFabricGroupByNameResponse(List<Object[]> fabrics) {
        List<FabricGroupByNameResponse> fabricGroupByNameResponses = new ArrayList<>();
        fabrics.forEach(fabric -> {
            fabricGroupByNameResponses.add(
                    new FabricGroupByNameResponse(
                            Arrays.stream(fabric).toList().get(0).toString(),
                            Arrays.stream(fabric).toList().get(1).toString()));
        });
        return fabricGroupByNameResponses;
    }


    @Override
    public DataResult<List<FabricResponse>> createFabrics(List<FabricRequest> fabricRequests) {
        checkFabricService.checkFabricExistsByCode(fabricRequests);
        List<Fabric> fabrics = new ArrayList<>();
        fabricRequests.forEach(fabricRequest -> {
            fabrics.add(modelMapperService.forRequest().map(fabricRequest, Fabric.class));
        });
        fabricRepository.saveAll(fabrics);
        return new SuccessDataResult<>(
                null,
                HttpStatus.CREATED);
    }

    @Override
    public DataResult<List<FabricResponse>> findAllByName(FabricRequest fabricRequest) {
        List<Fabric> fabrics = fabricRepository.findAllByName(fabricRequest.getName());
        return new SuccessDataResult<>(
                prepareFabricListByName(fabrics),
                HttpStatus.OK);
    }

    @Override
    public DataResult<List<FabricResponse>> findAll() {
        return null;
    }

    @Override
    public DataResult<List<FabricResponse>> changeFabricsEStatus(List<FabricRequest> fabricRequests) {
        fabricRepository.setEStatus(fabricRequests.get(0).getId(), fabricRequests.get(0).getStatus().toUpperCase());
        return new SuccessDataResult<>(
                null,
                HttpStatus.OK);
    }

    private List<FabricResponse> prepareFabricListByName(List<Fabric> fabrics) {
        List<FabricResponse> fabricResponses = new ArrayList<>();
        fabrics.forEach(fabric -> {
            fabricResponses.add(modelMapperService.forResponse().map(fabric, FabricResponse.class));
        });
        return fabricResponses;
    }

}
