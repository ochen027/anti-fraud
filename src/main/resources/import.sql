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



insert into rolemenu values (22, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
insert into rolemenu values (23, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 2);
insert into rolemenu values (24, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 3);
insert into rolemenu values (25, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 4);
insert into rolemenu values (26, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 5);


--initial rule scenario
insert into rulescenario values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Scenario 1');

--initial rule step
insert into rulestep values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'run', 1, 't: Transactions(transBaseAmt <= 20000);', 't.setAlertType("Warning!");', 1);
insert into rulestep values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'step', 2, 't: Transactions(transBaseAmt > 20000);', 't.setAlertType("Error!");', 1);

