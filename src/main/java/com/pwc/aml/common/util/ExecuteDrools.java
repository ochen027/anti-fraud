package com.pwc.aml.common.util;

import java.io.UnsupportedEncodingException;

import com.pwc.aml.customers.entity.Customers;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.pwc.aml.transation.entity.Transactions;

public class ExecuteDrools {

	public static void CallDrools(Customers c, String rules){
		StatefulKnowledgeSession kSession = null;
        try {
            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            kb.add(ResourceFactory.newByteArrayResource(rules.getBytes("utf-8")), ResourceType.DRL);
            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kSession = kBase.newStatefulKnowledgeSession();
            kSession.insert(c);
            kSession.fireAllRules();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (kSession != null)
                kSession.dispose();
        }
	}

}
