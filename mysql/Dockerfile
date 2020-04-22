FROM mysql:5.7.28

#设置免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

#将所需文件放到容器中
COPY my.cnf /etc/mysql/conf.d/mysqlutf8.cnf
COPY privileges.sql /docker-entrypoint-initdb.d
COPY yun-admin.sql /docker-entrypoint-initdb.d
COPY nacos.sql /docker-entrypoint-initdb.d