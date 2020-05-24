-- docker exec -it $CONTAINER_ID bash -c "source /home/oracle/.bashrc; sqlplus sys/Oradoc_db1@ORCLCDB as sysdba"
-- default SYS password: Oradoc_db1
alter session set "_ORACLE_SCRIPT"=true;
commit;
create user dev identified by "dev";
grant create session to dev;
grant create table to dev;
alter user dev quota unlimited on users;
grant create view, create procedure, create sequence to dev;