package com.pwc.aml.util;

import java.io.UnsupportedEncodingException;

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
	
	
	public static Transactions CallDrools(Transactions t, String rules){
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
            kSession.insert(t);
            kSession.fireAllRules();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (kSession != null)
                kSession.dispose();
        }
		return t;
	}
	
	
	public static void main(String args[]){
		String rules = "package com.pwc.aml.rules.service\n"
				+ "import com.pwc.aml.transation.entity.Transactions\n"
				+"\n"
				+"rule \"run\"\n"
				+"salience 1\n"
				+"when\n"
				+" t: Transactions(transBaseAmt <= 500000);\n"
				+"then\n"
				+"t.setAlertType(\"Warning!\");\n"
				+"System.out.println(t.getAlertType());\n"
				+"end\n"
				+"\n"
				+"rule \"step\"\n"
				+"salience 2\n"
				+"when\n"
				+" t: Transactions(transBaseAmt > 500000);\n"
				+"then\n"
				+"t.setAlertType(\"Error!\");\n"
				+"System.out.println(t.getAlertType());\n"
				+"end\n";
		Transactions t = new Transactions();
		t.setTransBaseAmt(1009.0d);
		CallDrools(t, rules);
		System.out.println("sssss");
	}	



}
