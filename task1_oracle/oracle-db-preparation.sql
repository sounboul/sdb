alter session set "_ORACLE_SCRIPT"=true;
commit;
create user dev identified by "dev";
grant create session to dev;
grant create table to dev;
alter user dev quota unlimited on users;
grant create view, create procedure, create sequence to dev;