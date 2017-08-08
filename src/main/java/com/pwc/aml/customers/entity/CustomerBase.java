package com.pwc.aml.customers.entity;

import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * table name: @Table(name = "CUSTOMERS")
 */
public class CustomerBase {




    @Transient
    private Individual individual;

    @Transient
    private Corporate corporate;

    @Transient
    private BigDecimal totalTransAmt;
    @Transient
    private int totalTransCount;
    @Transient
    private String transIdArray;
    @Transient
    private String accountIdArray;
    @Transient
    private String businessDate;
    @Transient
    private String alertCreationDate;
    @Transient
    private String alertId;
}
