package com.pwc.component.systemConfig.dao;


public interface IKeyValueDao {
    String get(String key);
    void put(String key,String value, String userName);
    void remove(String key);
}
