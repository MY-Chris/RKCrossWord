package com.acm.rossword.repository;

import com.acm.rossword.entity.ScoreEntity;
import com.acm.rossword.entity.WordObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {

    List<ScoreEntity> findAll();

}
