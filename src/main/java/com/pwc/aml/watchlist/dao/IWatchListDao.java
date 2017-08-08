package com.pwc.aml.watchlist.dao;

import com.pwc.aml.watchlist.entity.WatchList;

import java.util.List;

public interface IWatchListDao {
    public WatchList save(WatchList watchList);

    WatchList update(WatchList watchList);

    void deleteById(int id);
    void delete(WatchList wl);
    List<WatchList> findAll();

    WatchList findById(int id);
    void removeAll();
}
