package com.acm.rossword.repository;

import com.acm.rossword.entity.WordObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<WordObject,Long> {


}
