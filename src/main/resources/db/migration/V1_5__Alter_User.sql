alter table app_user
add account_non_expired boolean default true;

alter table app_user
    add account_non_locked boolean default true;

alter table app_user
    add credentials_non_expired boolean default true;