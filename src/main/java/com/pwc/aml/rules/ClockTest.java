package com.pwc.aml.rules;

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

/**
 * Created by ochen027 on 6/29/2017.
 */
public class ClockTest {

    public static void main(String args[]){
        String clockRule = "package com.pwc.aml.rules\n" +
                "import com.pwc.aml.rules.Clock\n" +
                "\n" +
                "rule \"run\"\n" +
                "    salience 1\n" +
                "    when\n" +
                "        c: Clock(0 == 0)\n" +
                "    then\n" +
                "        System.out.println(c);\n" +
                "        Thread.sleep(10);\n" +
                "        c.setSecond(c.getSecond() + 1);\n" +
                "        update(c);\n" +
                "end\n" +
                "\n" +
                "rule \"second\"\n" +
                "    salience 2\n" +
                "    when\n" +
                "        c: Clock(second == 60);\n" +
                "    then\n" +
                "        c.setMinute(c.getMinute() + 1);\n" +
                "        c.setSecond(0);\n" +
                "        update(c);\n" +
                "end\n" +
                "\n" +
                "rule \"minute\"\n" +
                "    salience 3\n" +
                "    when\n" +
                "        c: Clock(minute == 60)\n" +
                "    then\n" +
                "        c.setHour(c.getHour() + 1);\n" +
                "        c.setMinute(0);\n" +
                "        update(c);\n" +
                "end";

        StatefulKnowledgeSession kSession = null;
        try {
            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            kb.add(ResourceFactory.newByteArrayResource(clockRule.getBytes("utf-8")), ResourceType.DRL);
            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kSession = kBase.newStatefulKnowledgeSession();
            Clock c = new Clock();
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
