package com.koleksiyoner.repositories;

import com.koleksiyoner.entities.Fabric;
import com.koleksiyoner.enums.EStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {

    @Query(value = "SELECT name, COUNT(name) FROM fabrics  GROUP BY name ORDER BY name DESC", nativeQuery = true)
    List<Object[]> getFabricsGroupByName();

    List<Fabric> findAllByName(String name);

    Fabric findByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE fabrics SET e_status = :status WHERE id = :id", nativeQuery = true)
    void setEStatus(Long id, String status);


}
