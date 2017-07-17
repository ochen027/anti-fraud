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

--initial user group table
insert into usergroup values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
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

--initial group role table
insert into grouprole values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
insert into grouprole values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 2);
insert into grouprole values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 3);
insert into grouprole values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 4);
insert into grouprole values (5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 5);


--initial menu table
insert into menus values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Dashboard', NULL, '/#!/dashboard');
insert into menus values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Alerts', NULL, NULL);
insert into menus values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'My Alerts', 2, '/#!/alert/myAlert');
insert into menus values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Available Alerts', 2, '/#!/alert/available');
insert into menus values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Suppressed Alerts', 2, '/#!/alert/suppress');
insert into menus values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Closed Alerts', 2, '/#!/alert/closed');
insert into menus values(7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Create Alert', 2, '/#!/alert/create');
insert into menus values(8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Query', NULL, '/#!/dashboard');
insert into menus values(9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Reports', NULL, '/#!/dashboard');
insert into menus values(10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Setting', NULL, NULL);
insert into menus values(11, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Scenario', 10, '/#!/dashboard');
insert into menus values(12, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Workflow', 10, '/#!/workflow/index');
insert into menus values(13, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Batch', 10, '/#!/batch');
insert into menus values(14, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Document', 10, '/#!/document/index');

--initial role menu table

insert into rolemenu values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 1, 1);
insert into rolemenu values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 2, 1);
insert into rolemenu values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 3, 1);
insert into rolemenu values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 4, 1);
insert into rolemenu values (5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 5, 1);
insert into rolemenu values (6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 6, 1);
insert into rolemenu values (7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 7, 1);
insert into rolemenu values (8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 8, 1);
insert into rolemenu values (9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 9, 1);
insert into rolemenu values (10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 10, 1);
insert into rolemenu values (11, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 11, 1);
insert into rolemenu values (12, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 12, 1);
insert into rolemenu values (13, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 13, 1);
insert into rolemenu values (14, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 14, 1);

--initial rule scenario
insert into rulescenario values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'Scenario 1');

--initial rule step
insert into rulestep values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'run', 1, 't: Transactions(transBaseAmt <= 20000);', 't.setAlertType("Warning!");', 1);
insert into rulestep values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'step', 2, 't: Transactions(transBaseAmt > 20000);', 't.setAlertType("Error!");', 1);

