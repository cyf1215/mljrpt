FROM moxm/java:1.8-full

RUN mkdir -p /mljrpt-codegen

WORKDIR /mljrpt-codegen

ARG JAR_FILE=target/mljrpt-codegen.jar

COPY ${JAR_FILE} app.jar

EXPOSE 5002

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 60; java $JAVA_OPTS -jar app.jar
