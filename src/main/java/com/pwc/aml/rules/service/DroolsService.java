package com.pwc.aml.rules.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.pwc.aml.entity.Message;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.util.ExecuteDrools;

@Service
public class DroolsService implements IDroolsService {

	@Override
	public Transactions fireTransRule(Transactions t) {
		// load up the knowledge base
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-trans");
		
		kSession.insert(t);
		kSession.fireAllRules();
		return t;
	}

	@Override
	public String fireRule() {
		// load up the knowledge base
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		// go !
		Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		kSession.insert(message);//插入
		kSession.fireAllRules();//执行规则
		kSession.dispose();
		return message.getMessage();
	}

	@Override
	public Transactions callStatic(Transactions t, String rules) {
		return ExecuteDrools.CallDrools(t, rules);
	}

}
