package com.pwc.aml.rules.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.pwc.aml.accounts.dao.IAccountDAO;
import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.ExecuteDrools;
import com.pwc.aml.customers.dao.CustomerDAO;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.alert.entity.Alerts;
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
        rulesDAO.createRules(rScenario);
    }

    @Override
    public void updateRuleScenario(Scenario rScenario) {
        rulesDAO.updateRules(rScenario);
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
    public void executeRuleEngine(int scenarioId) throws Exception {

        List<Customers> customerList = customerDAO.findAll();
        for (Customers c : customerList) {
            List<Accounts> accountList = accountDAO.findByCustId(c.getCustomerId());

            if (null == accountList || 0 == accountList.size()) {
                continue;
            }

            List<String> accountIdList = new Vector<String>();

            for (Accounts acct : accountList) {
                accountIdList.add(acct.getAccountId());
            }

            List<Transactions> transList = transactionDAO.getTransDataByAccount(accountIdList, keyValueDAO.get("RULES_DAY"), keyValueDAO.get("BUSINESS_DAY"));

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

            String ruleEngineScript = rulesDAO.findSingleScenario(scenarioId).getScenarioContent();

            String[] scriptArray = ruleEngineScript.split("\r\n");
            StringBuilder sbScript = new StringBuilder();
            for (String s : scriptArray) {
                sbScript.append(s).append("\r\n");
            }
            System.out.println(ruleEngineScript);

            ExecuteDrools.CallDrools(c, ruleEngineScript);


        }
    }

    @Override
    public Scenario saveOrUpdate(Scenario scenario, Users users) {


        if (scenario.getId() == 0) {
            scenario.setCreatedBy(users.getUserName());
            scenario.setCreationDate(new Date());
            rulesDAO.createRules(scenario);
        } else {
            scenario.setLastUpdatedBy(users.getUserName());
            scenario.setLastUpdateDate(new Date());
            rulesDAO.updateRules(scenario);
        }

        return scenario;
    }


}

