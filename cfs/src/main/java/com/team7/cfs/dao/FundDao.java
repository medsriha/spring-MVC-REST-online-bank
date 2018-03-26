package com.team7.cfs.dao;

import com.team7.cfs.domain.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
@Transactional
public interface FundDao extends JpaRepository<Fund, String>{
    Fund findFundByName(String name);
    Fund findFundBySymbol(String symbol);

}
