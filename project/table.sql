create table project_member(
	member_id varchar2(20 char) primary key,
	member_pw varchar2(15 char) not null,
	member_name varchar2(15 char) not null,
	member_gender varchar2(2 char) not null,
	member_birth varchar2(20 char) not null,
	member_mail varchar2(50 char) not null
);
drop table PROJECT_MEMBER;
select*from PROJECT_MEMBER;