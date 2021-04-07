create table user_order_status
(
id int(11) not null primary key
,title varchar(30)
)engine=innodb;

insert into user_order_status(id,title) values (1,'Confermato')
,(2,'Preso in carico')
,(3,'Spedito')
,(4,'Consegnato');

alter table user_order add uosid int(11) not null;
update user_order set uosid=1;

alter table user_order add foreign key (uosid) references user_order_status(id);


alter table user_order add updated_at datetime;

