FROM accetto/ubuntu-vnc-xfce

USER root

RUN apt-get update && apt-get install -y openjdk-17-jdk && apt-get clean

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

COPY target/*.jar app.jar

RUN echo "java -jar /app/app.jar" >> /dockerstartup/custom_startup.sh

ENTRYPOINT ["/dockerstartup/vnc_startup.sh"]