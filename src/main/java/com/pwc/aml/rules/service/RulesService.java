package com.pwc.aml.rules.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.pwc.aml.accounts.dao.IAccountDAO;
import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.common.util.ExecuteDrools;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.Rules;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.aml.workflow.dao.IWorkflowExDao;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import com.pwc.component.workflow.entity.FlowEvent;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.rules.dao.IRulesDAO;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.transation.entity.Transactions;

@Service
public class RulesService implements IRulesService {


    @Autowired
    private IRulesDAO rulesDAO;

    @Autowired
    private ITransactionDAO transactionDAO;

    @Autowired
    private ICustomerDAO customerDAO;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IKeyValueDao keyValueDAO;

    @Autowired
    private IAlertDAO alertDAO;

    @Autowired
    private IWorkflowExDao workflowExDao;

    @Autowired
    private IWorkObjService workObjService;

    private static String DEFAULT_RULE="DEFAULT_RULE";
    private static String DEFAULT_WORKFLOW = "DEFAULT_WORKFLOW";

    @Override
    public List<Scenario> listAllRuleScenario() {
        return rulesDAO.listAllRuleScenario();
    }

    @Override
    public Scenario findSingleRuleScenario(int scenarioId) {
        return rulesDAO.findSingleScenario(scenarioId);
    }

    @Override
    public void createRuleScenario(Scenario rScenario) {
        rulesDAO.createScenario(rScenario);
    }

    @Override
    public void updateRuleScenario(Scenario rScenario) {
        rulesDAO.updateScenario(rScenario);
    }

    @Override
    public void deleteRuleScenario(int scenarioId) {
        rulesDAO.deleteRules(scenarioId);
    }

    /**
     * @Override public String getRuleScript(int scenarioId) {
     * List<RuleStep> rList = this.listStepByRule(scenarioId);
     * if(null == rList || 0 == rList.size()){
     * return null;
     * }
     * StringBuffer sb = new StringBuffer("package com.pwc.aml.rules.service\n"
     * + "import com.pwc.aml.transation.entity.Transactions\n");
     * for(RuleStep rs : rList){
     * sb.append("\n");
     * sb.append("rule \"").append(rs.getStepName()).append("\"\n");
     * sb.append("  salience ").append(rs.getStepOrder()).append("\n");
     * sb.append("  when\n");
     * sb.append("    ").append(rs.getStepWhen()).append("\n");
     * sb.append("  then\n");
     * sb.append("    ").append(rs.getStepThen()).append("\n");
     * sb.append("end\n");
     * }
     * //System.out.println(sb.toString());
     * return sb.toString();
     * }
     **/


    @Override
    public Scenario saveOrUpdate(Scenario scenario, Users users) {


        if (scenario.getId() == 0) {
            scenario.setCreatedBy(users.getUserName());
            scenario.setCreationDate(new Date());
            rulesDAO.createScenario(scenario);
        } else {
            scenario.setLastUpdatedBy(users.getUserName());
            scenario.setLastUpdateDate(new Date());
            rulesDAO.updateScenario(scenario);
        }

        return scenario;
    }

    @Override
    public Rules saveOrUpdateRules(Rules rules, Users users) {

        if (rules.getId() == 0) {
            rules.setCreatedBy(users.getUserName());
            rules.setCreationDate(new Date());
            rulesDAO.createRules(rules);

            //insert relations
            this.saveOrUpdateRuleScenario(rules, users);

        } else {
            rules.setLastUpdatedBy(users.getUserName());
            rules.setLastUpdateDate(new Date());
            rulesDAO.updateRules(rules);

            //insert relations
            rulesDAO.deleteRuleScenarioByRuleId(rules.getId());
            this.saveOrUpdateRuleScenario(rules, users);
        }

        return rules;
    }

    @Override
    public List<Rules> listAllRules() {
        return rulesDAO.listAllRules();
    }

    @Override
    public List<RuleScenario> findRuleScenarioByRuleId(int ruleId) {

        return rulesDAO.findRuleScenarioByRuleId(ruleId);
    }

    @Override
    public String getDefaultRuleId() {
        return keyValueDAO.get(DEFAULT_RULE);
    }

    @Override
    public void setDefaultRuleId(String ruleId, String userName) {
        keyValueDAO.put(DEFAULT_RULE,ruleId, userName);
    }

    public void saveOrUpdateRuleScenario(Rules rules, Users users) {
        for (int id : rules.getScenarios()) {
            RuleScenario ruleScenario = new RuleScenario();
            ruleScenario.setCreatedBy(users.getUserName());
            ruleScenario.setCreationDate(new Date());
            ruleScenario.setRuleId(rules.getId());
            ruleScenario.setScenarioId(id);
            rulesDAO.createRuleScenario(ruleScenario);
        }
    }

    @Override
    public void runRule(int ruleId,Users users){
        List<RuleScenario> list = rulesDAO.findRuleScenarioByRuleId(ruleId);
        for(RuleScenario rs : list){
            try {
                this.executeRuleEngine(rs.getScenarioId(),users);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executeRuleEngine(int scenarioId,Users users) throws Exception {

        String businessDay = keyValueDAO.get("BUSINESS_DAY");

        List<Customers> customerList = customerDAO.findAll();
        for (Customers c : customerList) {
            List<Accounts> accountList = accountDAO.findByCustId(c.getCustomerId());

            if (null == accountList || 0 == accountList.size()) {
                continue;
            }

            List<String> accountIdList = new Vector<String>();
            StringBuilder sbAccountArray = new StringBuilder();
            for (Accounts acct : accountList) {
                accountIdList.add(acct.getAccountId());
                sbAccountArray.append(acct.getAccountId()+",");
            }

            List<Transactions> transList = new ArrayList<Transactions>();

            if(1==scenarioId){
                transList = transactionDAO.getTransDataByAccount(accountIdList, keyValueDAO.get("RULES_DAY"), businessDay);
            }else if(2==scenarioId){
                String shortTermDays = keyValueDAO.get("SHORT_TERMS_DAY");
                String longTermDays = keyValueDAO.get("LONG_TERMS_DAY");
                LocalDate date = FormatUtils.StringToLocalDate(businessDay).minusDays(Long.parseLong(shortTermDays));
                String days = String.valueOf(Integer.parseInt(longTermDays)-Integer.parseInt(shortTermDays));
                transList = transactionDAO.getTransDataByAccount(accountIdList, days, FormatUtils.LocalDateToString(date));
                if(null == transList || 0 == transList.size()){
                    transList = transactionDAO.getTransDataByAccount(accountIdList, shortTermDays, businessDay);
                }else{
                    continue;
                }
            }else{
                //TODO
                System.out.println();
            }


            if (null == transList || 0 == transList.size()) {
                continue;
            }

            BigDecimal tAmt = new BigDecimal("0");
            StringBuilder sbTransId = new StringBuilder();
            for (Transactions t : transList) {
                tAmt = tAmt.add(t.getTransBaseAmt());
                sbTransId.append(t.getTransId() + ",");
            }

            c.setTotalTransAmt(tAmt);
            c.setTotalTransCount(transList.size());
            c.setTransIdArray(sbTransId.substring(0, sbTransId.length() - 1));
            c.setBusinessDate(keyValueDAO.get("BUSINESS_DAY").replace("-", ""));
            c.setAccountIdArray(sbAccountArray.substring(0, sbAccountArray.length()-1));
            c.setAlertCreationDate(FormatUtils.LocalDateToString(LocalDate.now()));

            String ruleEngineScript = rulesDAO.findSingleScenario(scenarioId).getScenarioContent();

            String case1 = "package com.pwc.aml.rules.service;\n" +
                    "import com.pwc.aml.customers.entity.Customers;\n" +
                    "import com.pwc.aml.common.hbase.HbaseDaoImp;\n" +
                    "import com.pwc.aml.common.hbase.IHbaseDao;\n" +
                    "import org.apache.hadoop.hbase.client.HTable;\n" +
                    "import com.pwc.common.util.FormatUtils;\n"+
                    "\n" +
                    "rule \"CaseTest\"\n" +
                    "    salience 1\n" +
                    "    when\n" +
                    "        $customer : Customers(totalTransAmt >= 30000 && totalTransCount >=3);\n" +
                    "    then\n" +
                    "        IHbaseDao hBaseDAO = new HbaseDaoImp();\n" +
                    "        HTable table = hBaseDAO.getTable(\"aml:alerts\");\n" +
                    "        String alertId = FormatUtils.GenerateId();\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"alertName\", \"Scenario 1 Conflict\");\n" +
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"alertContent\", \"Conflict with Transactions Amount > 30000 and in the last 3 days\");\n" +
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"customerId\", $customer.getCustomerId());\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"accountId\", $customer.getAccountIdArray());\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"transIdArray\", $customer.getTransIdArray());\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"scenarioId\", \"1\");\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"businessDate\", $customer.getBusinessDate());\n" +
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"totalAmt\", $customer.getTotalTransAmt().toString());\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"createdDate\", $customer.getAlertCreationDate());\n"+
                    "        hBaseDAO.putData(table, alertId, \"f1\", \"alertDesc\", \"Alert Desc\");\n"+
                    "        $customer.setAlertId(alertId);\n"+
                    "end";

            String alertId = ExecuteDrools.CallDrools(c, StringEscapeUtils.unescapeJava(ruleEngineScript)).getAlertId();
            if(StringUtils.isNotBlank(alertId)){
                workObjService.attach(alertDAO.getSingleAlert(alertId),
                        workflowExDao.getWorkflowByFlowId(keyValueDAO.get(DEFAULT_WORKFLOW)),users);
            }
        }
    }


}

