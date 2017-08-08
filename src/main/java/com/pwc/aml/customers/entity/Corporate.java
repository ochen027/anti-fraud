package com.pwc.aml.customers.entity;

import javax.persistence.Transient;

/**
 * Created by whuang072 on 8/8/2017.
 */
public class Corporate {

    @Transient
    private Representative representative;
}
