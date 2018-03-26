package com.team7.cfs.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Position {

    @EmbeddedId
    @Column(nullable = false)
    private PositionCompPK id;

    @Column(nullable = false)
    private String shares;

    public PositionCompPK getId() {
        return id;
    }

    public void setId(PositionCompPK id) {
        this.id = id;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
