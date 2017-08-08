package com.pwc.aml.watchlist.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by whuang072 on 7/21/2017.
 */
@Entity
@Table(name = "WatchList")
public class WatchList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWatchList() {
        return watchList;

    }

    public void setWatchList(String watchList) {
        this.watchList = watchList;
    }

    @Column(name = "WATCHLIST")
    private String watchList;


}
