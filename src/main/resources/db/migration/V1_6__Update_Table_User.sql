update app_user set account_non_expired = 1 where enabled=1;
update app_user set account_non_locked = 1 where enabled=1;
update app_user set credentials_non_expired = 1 where enabled=1;
