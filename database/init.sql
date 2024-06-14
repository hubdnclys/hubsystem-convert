alter user 'root'@'localhost' identified with caching_sha2_password by 'root';
flush privileges;

create database if not exists hubsystem_convert;

use hubsystem_convert;