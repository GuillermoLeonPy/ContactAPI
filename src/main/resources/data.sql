insert into security_users (username, password, enabled)
values ('guest','guest',true);

insert into security_authorities (username, authority)
values ('guest','ROLE_GUEST');