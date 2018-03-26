package com.team7.cfs.dao;

import java.util.List;
import com.team7.cfs.domain.User;
import com.team7.cfs.domain.Position;
import com.team7.cfs.domain.PositionCompPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Repository
@Transactional
public interface PositionDao extends JpaRepository<Position, PositionCompPK> {

    List<Position> findPositionsById_User(User user);
    Position findPositionById(PositionCompPK pk);
}
