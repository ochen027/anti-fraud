insert into  articles values (1,'test1','title1');
insert into  articles values (2,'test2','title2');
insert into  articles values (3,'test3','title3');
insert into  articles values (4,'test4','title4');
insert into  articles values (5,'test5','title5');

--initial user table
insert into users values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'sysadmin', 'sysadmin');
insert into users values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 0, 'sysadmin1', 'sysadmin1');
insert into users values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 0, 'sysadmin2', 'sysadmin2');
insert into users values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'sysadmin3', 'sysadmin3');
insert into users values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'sysadmin4', 'sysadmin4');

--initial group table
insert into groups values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'User Group L1');
insert into groups values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, 'User Group L2');

--initial user group table
insert into usergroup values(1, 1);
insert into usergroup values(1, 2);
insert into usergroup values(2, 1);
insert into usergroup values(2, 2);

--initial role table
insert into roles values (1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Role 1');
insert into roles values (2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Role 2');
insert into roles values (3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Role 3');
insert into roles values (4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1,'Role 4');

--initial group role table
insert into grouprole values(1, 2);
insert into grouprole values(3, 2);
insert into grouprole values(2, 1);
insert into grouprole values(4, 1);

--initial menu table
insert into menus values(1, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu1', NULL, '/m1.jsp');
insert into menus values(2, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu2', NULL, '/m2.jsp');
insert into menus values(3, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu3', NULL, '/m3.jsp');
insert into menus values(4, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu4', NULL, '/m4.jsp');
insert into menus values(5, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu5', NULL, '/m5.jsp');
insert into menus values(6, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu1-1', 1, '/m1-1.jsp');
insert into menus values(7, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu1-2', 1, '/m1-2.jsp');
insert into menus values(8, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu1-3', 1, '/m1-3.jsp');
insert into menus values(9, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu1-4', 1, '/m1-4.jsp');
insert into menus values(10, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu2-1', 2, '/m2-1.jsp');
insert into menus values(11, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu3-1', 3, '/m3-1.jsp');
insert into menus values(12, 'sysadmin', sysdate, sysdate, 'sysadmin', 1, NULL , NULL , 'Menu4-1', 4, '/m4-1.jsp');

--initial role menu table
insert into rolemenu values (1, 1);
insert into rolemenu values (2, 1);
insert into rolemenu values (6, 1);
insert into rolemenu values (7, 1);
insert into rolemenu values (8, 1);
insert into rolemenu values (10, 2);