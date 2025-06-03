package com.koleksiyoner.validations.Impl;

import com.koleksiyoner.api.requests.fabric.FabricRequest;
import com.koleksiyoner.entities.Fabric;
import com.koleksiyoner.exceptions.enums.ExceptionMessage;
import com.koleksiyoner.exceptions.exception.BaseException;
import com.koleksiyoner.exceptions.exception.ErrorMessage;
import com.koleksiyoner.repositories.FabricRepository;
import com.koleksiyoner.validations.CheckFabricService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckFabricServiceImpl implements CheckFabricService {

    private final FabricRepository fabricRepository;

    @Override
    public Fabric checkFindById(Long id) {
        return fabricRepository.findById(id).orElseThrow(() ->
                new BaseException(new ErrorMessage(ExceptionMessage.FABRIC_NOT_FOUND, null)));

    }

    @Override
    public void checkFabricExistsByCode(List<FabricRequest> fabricRequests) {
        List<Fabric> fabrics = fabricRepository.findAll();
        List<Fabric> matchingCodes = fabrics.stream().filter(element ->
                fabricRequests.stream().anyMatch(f -> f.getCode().equals(element.getCode()))).toList();
        List<String> errorMessageList = new ArrayList<>();
        matchingCodes.forEach(fabric -> {
            errorMessageList.add(fabric.getCode());
        });
        if (!matchingCodes.isEmpty()) {
            throw new BaseException(new ErrorMessage(ExceptionMessage.FABRICS_ARE_ALREADY_ADDED, String.join(" , ", errorMessageList)));
        }
    }
}
