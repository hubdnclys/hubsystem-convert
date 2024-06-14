FROM mysql:latest

MAINTAINER hubdnclys@gmail.com

COPY init.sql /docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD=root

VOLUME /var/lib/mysql

EXPOSE 3307