FROM mysql/mysql-server:8.0.31

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./mljrpt.sql /docker-entrypoint-initdb.d

COPY ./mljrpt_codegen.sql /docker-entrypoint-initdb.d

COPY ./mljrpt_config.sql /docker-entrypoint-initdb.d

COPY ./mljrpt_job.sql /docker-entrypoint-initdb.d
