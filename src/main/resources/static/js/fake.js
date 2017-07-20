Mock.mock(/\/alert\/myalert$|\/alert\/availableAlert$|\/alert\/suppressedAlert$|\/alert\/closeAlert$/, {
        "result|35": [{
            "alertId|+1": 1,
            "accNumber": /\d{5}/,
            "custName": "@first @last",
            "createDate": "@date('yyyy-MM-dd')",
            "scenario|1": ["level-1", "level-2", "level-3", "level-4", "level-5"],
            "description": "@sentence(3,10)",
            "triggeringValues|1-10.2-7": 1.00,
            "status|1": ["Active", "Closed", "Deleted", "Canceled"],
            "age|1-100": 100,
            "days|1-3":1,
            "counterparty": "@last",
            "frequency|1-10": 1
        }],
    "total|1-10.2" : 1.00
    });
Mock.mock(/\/alert\/myAlertInfo\/12345$|\/alert\/availableInfo\/12345$|\/alert\/suppressedInfo\/12345$|\/alert\/closedAlertInfo\/12345$/, {
    "summary": {
        "alertId": 1,
        "alertDate": "@date('yyyy-MM-dd')",
        "customerId|1-10": 1,
        "custName": "@first @last",
        "customerType|1": ["type-1", "type-2", "type-3", "type-4", "type-5"]
    },
    "individual": {
        "brithdate": "@date('yyyy-MM-dd')",
        "ID": "@id()",
        "occupation|1": ["teacher", "farmer", "doctor", "clerk"],
        "city": "shanghai",
        "country": "CN",
        "phone": /\d{11}/,
        "isPEP": "@boolean()",
        "isNonResident": "@boolean()",
        "isAMLSuspect": "@boolean()"
    },
    "corporate": {
        "registrationNo": /\d{5}/,
        "lineOfBusiness|1":  ["line-1","line-2","line-3"],
        "phone": /\d{11}/,
        "location": "shanghai",
        "country": "CN",
        "isAMLSuspect": "@boolean()"
    },
    "legalRepresentative": {
        "dateofbith": "@date('yyyy-MM-dd')",
        "ID": "@id()",
        "occupation|1": ["teacher", "farmer", "doctor", "clerk"],
        "City": "shanghai",
        "country": "CN",
        "phone": /\d{11}/,
        "isPEP": "@boolean()",
        "isNonResident": "@boolean()",
        "isAMLSuspect": "@boolean()"
    },
    "tableRecords|15": [
        {
            "transactionDate": "@date('yyyy-MM-dd')",
            "accountType|1": ["type-1","type-2","type-3"],
            "accountNum":/\d{5}/,
            "currency": "USD",
            "transactionAmount|1-10.2": 1.00,
            "TransactionType|1": ["trans-1","trans-2","trans-3"],
            "fundReceivePay|1": ["receive","pay"],
            "transactionPurpose": "@sentence(1)",
            "accountOpenDate": "@date('yyyy-MM-dd')",
            "accountCloseDate": "@date('yyyy-MM-dd')",
            "Counterparty": "@last",
            "counterbank": "ICBC Bank",
            "counterBankLocation": "shanghai",
            "representative": "@last",
            "representativeId": "@id()",
            "representativeType|1": ["teacher", "farmer", "doctor", "clerk"]
        }

    ],
    "total|1-10.2" : 1.00,
    "comments": "this is comments"
})
