FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y \
    unzip \
    vim

# PGX
WORKDIR /opt
ARG PGX_VERSION=19.1.0
ARG PGX_FILE=pgx-$PGX_VERSION-server.zip
COPY $PGX_FILE /opt
RUN unzip $PGX_FILE \
 && rm $PGX_FILE
ENV PATH=$PATH:/opt/pgx-$PGX_VERSION/bin

# Groovy
WORKDIR /opt
ARG GROOVY_VERSION=2.5.6
ARG GROOVY_FILE=apache-groovy-binary-$GROOVY_VERSION.zip
RUN wget https://bintray.com/artifact/download/groovy/maven/$GROOVY_FILE \
 && unzip $GROOVY_FILE \
 && rm $GROOVY_FILE
ENV GROOVY_HOME=/opt/groovy-$GROOVY_VERSION

# For avoiding worning
ENV HADOOP_HOME=/opt/haoop

WORKDIR /work
