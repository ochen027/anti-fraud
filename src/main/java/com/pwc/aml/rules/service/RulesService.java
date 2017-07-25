package com.pwc.aml.rules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.pwc.aml.accounts.dao.IAccountDAO;
import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.common.util.ExecuteDrools;
import com.pwc.aml.customers.dao.CustomerDAO;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.transation.dao.ITransactionDAO;
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
public class RulesService implements IRulesService{

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
	@Override
	public String getRuleScript(int scenarioId) {
		List<RuleStep> rList = this.listStepByRule(scenarioId);
		if(null == rList || 0 == rList.size()){
			return null;
		}
		StringBuffer sb = new StringBuffer("package com.pwc.aml.rules.service\n"
				+ "import com.pwc.aml.transation.entity.Transactions\n");
		for(RuleStep rs : rList){
			sb.append("\n");
			sb.append("rule \"").append(rs.getStepName()).append("\"\n");
			sb.append("  salience ").append(rs.getStepOrder()).append("\n");
			sb.append("  when\n");
			sb.append("    ").append(rs.getStepWhen()).append("\n");
			sb.append("  then\n");
			sb.append("    ").append(rs.getStepThen()).append("\n");
			sb.append("end\n");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	**/

	@Override
	public void executeRuleEngine(int scenarioId) throws Exception{

		List<Customers> customerList = customerDAO.findAll();
		for(Customers c : customerList){
			List<Accounts> accountList = accountDAO.findByCustId(c.getCustomerId());
			List<String> accountIdList = new Vector<String>();
			for(Accounts acct : accountList){
				accountIdList.add(acct.getAccountId());
			}

			String businessDate = keyValueDAO.get("BUSINESS_DAY");
			String ruleDays = keyValueDAO.get("RULES_DAY");

			List<Transactions> transList = transactionDAO.getTransDataByAccount(accountIdList, ruleDays, businessDate);



		}




		List<Transactions> tList = new ArrayList<Transactions>();
		try {
			tList = transactionDAO.getAllTransData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//String ruleScript = this.getRuleScript(scenarioId);
		
		/**
		String ruleScript = "package com.pwc.aml.rules.service\n"
				+ "import com.pwc.aml.transation.entity.Transactions\n"
				+"\n"
				+"rule \"run\"\n"
				+"salience 1\n"
				+"when\n"
				+" t: Transactions(transBaseAmt <= 500000);\n"
				+"then\n"
				+"t.setAlertType(\"Warning!\");\n"
				+"end\n"
				+"\n"
				+"rule \"step\"\n"
				+"salience 2\n"
				+"when\n"
				+" t: Transactions(transBaseAmt > 500000);\n"
				+"then\n"
				+"t.setAlertType(\"Error!\");\n"
				+"end\n";
		**/
		
		//List<Alerts> aList = new ArrayList<Alerts>();
		for(Transactions t : tList){
			//Transactions tResult = ExecuteDrools.CallDrools(t, ruleScript);

			Transactions tResult = null;

			if(StringUtils.isNotEmpty(tResult.getAlertType())){
				Alerts a = new Alerts();
				a.setAlterId("ALT"+tResult.getTransId());
				a.setAlertName(tResult.getAlertType());
				a.setAlertContents(tResult.getAlertType()+"==>Amount is:"+tResult.getTransBaseAmt());
				//a.setAlertCreatedDate(LocalDateTime.now().toString());
				//a.setTransId(tResult.getTransId());

				HbaseDaoImp hdao = new HbaseDaoImp();
				HTable table = hdao.getTable("aml:alerts");
				hdao.putData(table, a.getAlterId(), "f1", "alertName", a.getAlertName());
				hdao.putData(table, a.getAlterId(), "f1", "alertContents", a.getAlertContents());
				//hdao.putData(table, a.getAlterId(), "f1", "alertCreatedDate", a.getAlertCreatedDate());
				//hdao.putData(table, a.getAlterId(), "f1", "transId", a.getTransId());

				//aList.add(a);
			}
			
		}

	}
	

}
