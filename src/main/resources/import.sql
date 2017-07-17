--initial user table
insert into users values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'sysadmin', 'sysadmin');
insert into users values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'guest', 'guest');
insert into users values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'admin', 'admin');
insert into users values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'l1', '123456');
insert into users values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'l2', '123456');
insert into users values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'qa', '123456');
insert into users values(7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'qc', '123456');
insert into users values(8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'ml', '123456');

--initial group table
insert into groups values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'User Group L1');
insert into groups values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'User Group L2');
insert into groups values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Quality Assurance');
insert into groups values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Quality Control');
insert into groups values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Money Laundry');
insert into groups values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'sysadmin');

--initial user group table
insert into usergroup values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 1);
insert into usergroup values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 2);
insert into usergroup values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 1);
insert into usergroup values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 2);
insert into usergroup values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 3);
insert into usergroup values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 4);
insert into usergroup values(7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 5);
insert into usergroup values(8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 6);
insert into usergroup values(9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 7);
insert into usergroup values(10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 8);

--initial role table
insert into roles values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Level 1');
insert into roles values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Level 2');
insert into roles values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Quality Assurance');
insert into roles values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Quality Control');
insert into roles values (5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Money Laundry');
insert into roles values (6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'sysadmin');

--initial group role table
insert into grouprole values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
insert into grouprole values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 2);
insert into grouprole values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 3);
insert into grouprole values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 4);
insert into grouprole values (5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 5);
insert into grouprole values (6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 5);
insert into grouprole values (7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 6);

--initial menu table
insert into menus values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Dashboard', NULL, '/#!/dashboard');
insert into menus values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Alerts', NULL, NULL);
insert into menus values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'My Alerts', 2, '/#!/alert/myAlert');
insert into menus values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Available Alerts', 2, '/#!/alert/available');
insert into menus values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Suppressed Alerts', 2, '/#!/alert/suppress');
insert into menus values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Closed Alerts', 2, '/#!/alert/closed');
insert into menus values(7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Create Alert', 2, '/#!/alert/create');
insert into menus values(8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Case', NULL, NULL);
insert into menus values(9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Query', NULL, NULL);
insert into menus values(10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Reports', NULL, NULL);
insert into menus values(11, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Setting', NULL, NULL);
insert into menus values(12, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Scenario', 11, '/#!/dashboard');
insert into menus values(13, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Workflow', 11, '/#!/workflow/index');
insert into menus values(14, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Batch', 11, '/#!/batch');
insert into menus values(15, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Document', 11, '/#!/document/index');
insert into menus values(16, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Report Summary', 10, '/#!/report/summary');
insert into menus values(17, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Report Detail', 10, '/#!/report/detail');
insert into menus values(18, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Alert Query', 9, '/#!/query/alert');
insert into menus values(19, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Case Query', 9, '/#!/query/case');
insert into menus values(20, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'My Case', 8, '/#!/case/myCase');
insert into menus values(21, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Closed Case', 8, '/#!/case/closed');


--initial role menu table

insert into rolemenu values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 6);
insert into rolemenu values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 6);
insert into rolemenu values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 6);
insert into rolemenu values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 6);
insert into rolemenu values (5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 6);
insert into rolemenu values (6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 6);
insert into rolemenu values (7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 6);
insert into rolemenu values (8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 6);
insert into rolemenu values (9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 6);
insert into rolemenu values (10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 6);
insert into rolemenu values (11, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 6);
insert into rolemenu values (12, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 6);
insert into rolemenu values (13, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 6);
insert into rolemenu values (14, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 6);
insert into rolemenu values (15, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 6);
insert into rolemenu values (16, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 6);
insert into rolemenu values (17, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 6);
insert into rolemenu values (18, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 6);
insert into rolemenu values (19, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 6);
insert into rolemenu values (20, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 6);
insert into rolemenu values (21, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 6);


---l1 menu
insert into rolemenu values (100001, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
insert into rolemenu values (100002, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 1);
insert into rolemenu values (100003, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 1);
insert into rolemenu values (100004, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 1);
insert into rolemenu values (100005, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 1);
insert into rolemenu values (100006, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 1);
insert into rolemenu values (100007, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 1);
insert into rolemenu values (100008, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 1);
insert into rolemenu values (100009, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 1);
insert into rolemenu values (100010, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 1);
insert into rolemenu values (100011, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 1);
insert into rolemenu values (100012, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 1);
insert into rolemenu values (100013, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 1);
insert into rolemenu values (100014, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 1);
insert into rolemenu values (100015, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 1);
insert into rolemenu values (100016, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 1);
insert into rolemenu values (100017, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 1);
insert into rolemenu values (100018, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 1);
insert into rolemenu values (100019, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 1);
insert into rolemenu values (100020, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 1);
insert into rolemenu values (100021, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 1);

---l2 menu
insert into rolemenu values (110001, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 2);
insert into rolemenu values (110002, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 2);
insert into rolemenu values (110003, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 2);
insert into rolemenu values (110004, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 2);
insert into rolemenu values (110005, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 2);
insert into rolemenu values (110006, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 2);
insert into rolemenu values (110007, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 2);
insert into rolemenu values (110008, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 2);
insert into rolemenu values (110009, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 2);
insert into rolemenu values (110010, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 2);
insert into rolemenu values (110011, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 2);
insert into rolemenu values (110012, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 2);
insert into rolemenu values (110013, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 2);
insert into rolemenu values (110014, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 2);
insert into rolemenu values (110015, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 2);
insert into rolemenu values (110016, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 2);
insert into rolemenu values (110017, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 2);
insert into rolemenu values (110018, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 2);
insert into rolemenu values (110019, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 2);
insert into rolemenu values (110020, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 2);
insert into rolemenu values (110021, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 2);

---qa menu
insert into rolemenu values (120001, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 3);
insert into rolemenu values (120002, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 3);
insert into rolemenu values (120003, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 3);
insert into rolemenu values (120004, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 3);
insert into rolemenu values (120005, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 3);
insert into rolemenu values (120006, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 3);
insert into rolemenu values (120007, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 3);
insert into rolemenu values (120008, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 3);
insert into rolemenu values (120009, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 3);
insert into rolemenu values (120010, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 3);
insert into rolemenu values (120011, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 3);
insert into rolemenu values (120012, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 3);
insert into rolemenu values (120013, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 3);
insert into rolemenu values (120014, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 3);
insert into rolemenu values (120015, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 3);
insert into rolemenu values (120016, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 3);
insert into rolemenu values (120017, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 3);
insert into rolemenu values (120018, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 3);
insert into rolemenu values (120019, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 3);
insert into rolemenu values (120020, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 3);
insert into rolemenu values (120021, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 3);


---qc menu
insert into rolemenu values (130001, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 4);
insert into rolemenu values (130002, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 4);
insert into rolemenu values (130003, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 4);
insert into rolemenu values (130004, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 4);
insert into rolemenu values (130005, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 4);
insert into rolemenu values (130006, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 4);
insert into rolemenu values (130007, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 4);
insert into rolemenu values (130008, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 4);
insert into rolemenu values (130009, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 4);
insert into rolemenu values (130010, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 4);
insert into rolemenu values (130011, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 4);
insert into rolemenu values (130012, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 4);
insert into rolemenu values (130013, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 4);
insert into rolemenu values (130014, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 4);
insert into rolemenu values (130015, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 4);
insert into rolemenu values (130016, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 4);
insert into rolemenu values (130017, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 4);
insert into rolemenu values (130018, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 4);
insert into rolemenu values (130019, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 4);
insert into rolemenu values (130020, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 4);
insert into rolemenu values (130021, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 4);


---ml menu
insert into rolemenu values (140001, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 5);
insert into rolemenu values (140002, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 5);
insert into rolemenu values (140003, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 5);
insert into rolemenu values (140004, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 5);
insert into rolemenu values (140005, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 5);
insert into rolemenu values (140006, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 5);
insert into rolemenu values (140007, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 5);
insert into rolemenu values (140008, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 5);
insert into rolemenu values (140009, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 5);
insert into rolemenu values (140010, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 5);
insert into rolemenu values (140011, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 5);
insert into rolemenu values (140012, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 5);
insert into rolemenu values (140013, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 5);
insert into rolemenu values (140014, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 5);
insert into rolemenu values (140015, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 15, 5);
insert into rolemenu values (140016, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 16, 5);
insert into rolemenu values (140017, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 17, 5);
insert into rolemenu values (140018, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 18, 5);
insert into rolemenu values (140019, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 19, 5);
insert into rolemenu values (140020, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 20, 5);
insert into rolemenu values (140021, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 21, 5);



--initial rule scenario
insert into rulescenario values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Scenario 1');

--initial rule step
insert into rulestep values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'run', 1, 't: Transactions(transBaseAmt <= 20000);', 't.setAlertType("Warning!");', 1);
insert into rulestep values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'step', 2, 't: Transactions(transBaseAmt > 20000);', 't.setAlertType("Error!");', 1);

