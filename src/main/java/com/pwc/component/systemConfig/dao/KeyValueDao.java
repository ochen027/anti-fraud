package com.pwc.component.systemConfig.dao;


public interface KeyValueDao {
    String get(String key);
    void put(String Key,String value);

}
