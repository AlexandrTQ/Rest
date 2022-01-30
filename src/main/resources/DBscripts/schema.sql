drop table if exists worker;
drop table if exists Department;

 CREATE TABLE department
 (
     department_id integer NOT NULL PRIMARY KEY,
     name character varying(200)
 );

CREATE TABLE worker
 (
     Personal_Id bigint NOT NULL PRIMARY KEY,
     firstname character varying(200),
     surname character varying(200),
     phone character varying(20),
     department_id int,
     CONSTRAINT fk_workers FOREIGN KEY (department_id)
         REFERENCES department (department_id)
 );
