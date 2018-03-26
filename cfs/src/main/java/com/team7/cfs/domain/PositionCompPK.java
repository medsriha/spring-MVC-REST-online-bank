package com.team7.cfs.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PositionCompPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Fund.class)
    @JoinColumn(name="symbol")
    private Fund fund;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name="id")
    private User user;

    public PositionCompPK() {
    }

    public PositionCompPK(Fund fund, User user) {
        this.fund = fund;
        this.user = user;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

