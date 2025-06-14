package com.koleksiyoner.services.fabric.Impl;

import com.koleksiyoner.api.requests.BaseListRequest;
import com.koleksiyoner.api.requests.fabric.FabricListRequest;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public DataResult<List<FabricGroupByNameResponse>> getFabricsGroupByName(BaseListRequest baseListRequest) {
        return new SuccessDataResult<>(
                prepareFabricGroupByNameResponse(fabricRepository.getFabricsGroupByName(preparePageable(baseListRequest.getPageNo(), baseListRequest.getPageSize()))),
                HttpStatus.OK);
    }


    @Override
    public DataResult<List<FabricResponse>> findAllByName(FabricListRequest fabricListRequest) {
        return new SuccessDataResult<>(
                prepareFabricListByName(fabricRepository.findAllByName(fabricListRequest.getName(),
                        preparePageable(fabricListRequest.getPageNo(), fabricListRequest.getPageSize()))),
                HttpStatus.OK);
    }

    @Override
    public DataResult<List<FabricResponse>> findAll(BaseListRequest baseListRequest) {
        List<Fabric> fabrics = fabricRepository.findAll(preparePageable(baseListRequest.getPageNo(),
                baseListRequest.getPageSize())).getContent();
        return new SuccessDataResult<>(fabrics.stream()
                .map(fabric -> this.modelMapperService.forResponse()
                        .map(fabric, FabricResponse.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public DataResult<List<FabricResponse>> createFabrics(List<FabricRequest> fabricRequests) {
        checkFabricService.checkFabricsExistsByCode(fabricRequests);
        List<Fabric> fabrics = new ArrayList<>();
        fabricRequests.forEach(fabricRequest -> fabrics.add(modelMapperService.forRequest().map(fabricRequest, Fabric.class)));
        fabrics.forEach(fabric -> fabric.setEStatus(EStatus.ACTIVE));
        fabricRepository.saveAll(fabrics);
        return new SuccessDataResult<>(
                null,
                HttpStatus.CREATED);
    }

    @Override
    public DataResult<List<FabricResponse>> changeFabricsEStatus(List<FabricRequest> fabricRequests) {
        fabricRequests.forEach(fabricRequest -> {
            checkFabricService.checkFindById(fabricRequest.getId());
            fabricRepository.setEStatus(fabricRequest.getId(), fabricRequest.getStatus().toUpperCase());
        });
        return new SuccessDataResult<>(
                null,
                HttpStatus.OK);
    }

    private FabricResponse prepareFabric(Fabric fabric) {
        return (modelMapperService.forResponse().map(fabric, FabricResponse.class));
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


    private List<FabricResponse> prepareFabricListByName(List<Fabric> fabrics) {
        List<FabricResponse> fabricResponses = new ArrayList<>();
        fabrics.forEach(fabric -> {
            fabricResponses.add(modelMapperService.forResponse().map(fabric, FabricResponse.class));
        });
        return fabricResponses;
    }

    private Pageable preparePageable(int pageNo, int pageSize) {
        return PageRequest.of(pageNo, pageSize);
    }
}
