alter table app_user
add account_non_expired bit default 1;

alter table app_user
    add account_non_locked bit default 1;

alter table app_user
    add credentials_non_expired bit default 1;