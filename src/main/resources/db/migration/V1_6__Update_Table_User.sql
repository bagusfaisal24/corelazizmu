update app_user set account_non_expired = true where enabled=true;
update app_user set account_non_locked = true where enabled=true;
update app_user set credentials_non_expired = true where enabled=true;
