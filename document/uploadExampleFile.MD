#  using FileZilla upload example file

## connection server with sftp

1. setting user name and password 

sftp:// sinl069076.mso.net  
user name/password: hadoop/Welcome1

folder:/data/Hadoop/sampleData


2. file template

structure like:
```
trans_id	as_of_date	acct_id	trans_seq	trans_chanel	trans_cdt_cd	currency_cd	trans_base_amt	trans_desc	trans_dt	counterparty_id_1	trans_br	trans_by
900000000001	1/1/2008	acct0000001	00001	1	c	USD	10000.99	ajsdkfjskdljflkdsjfds	1/1/2008	acct0000002	100034	20001
900000000002	1/1/2008	acct0000001	00002	1	d	USD	500000	ajsdkfjskdljflkdsjfds	1/1/2008	acct0000003	100034	20001
900000000003	1/1/2008	acct0000001	00003	1	c	USD	100	ajsdkfjskdljflkdsjfds	1/1/2008	acct0000004	100034	20001
900000000004	1/1/2008	acct0000001	00004	2	c	USD	300000	ajsdkfjskdljflkdsjfds	1/1/2008	acct0000005	100034	20001
```

which contains meta data.

