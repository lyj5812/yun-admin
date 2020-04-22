FROM fabletang/jre8-alpine
#指定维护者信息
MAINTAINER lyj5812@foxmail.com
VOLUME /tmp
ADD target/auth-server-1.0-SNAPSHOT.jar ./app.jar
ADD wait-for-it.sh /wait-for-it.sh
RUN bash -c 'touch /app.jar'
RUN chmod +x /wait-for-it.sh
#ENTRYPOINT 为容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]