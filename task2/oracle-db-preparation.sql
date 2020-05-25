-- docker exec -it $CONTAINER_ID bash -c "source /home/oracle/.bashrc; sqlplus sys/oracle@ORCL as sysdba"
-- default SYS password: oracle
alter session set "_ORACLE_SCRIPT"=true;
commit;
create user dev identified by "dev";
grant create session to dev;
grant create table to dev;
alter user dev quota unlimited on users;
grant create view, create procedure, create sequence to dev;
grant olap_user to dev;
grant olap_dba to dev;
grant olap_xs_admin to dev;
grant create any materialized view to dev;
grant create any dimension to dev;
grant advisor to dev;
