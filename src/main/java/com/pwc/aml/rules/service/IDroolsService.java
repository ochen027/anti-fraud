package com.pwc.aml.rules.service;

import com.pwc.aml.transation.entity.Transactions;

public interface IDroolsService {
	public Transactions fireTransRule(Transactions t);
	
	public String fireRule();
	
	public Transactions callStatic(Transactions t, String rules);
}
