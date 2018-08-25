FROM openjdk:8-jdk

ARG PGX_VERSION=2.7.0
ENV PGX_HOME=/opt/pgx \
    INSTALL_FILE=pgx-${PGX_VERSION}-server.zip

COPY $INSTALL_FILE $PGX_HOME/

WORKDIR $PGX_HOME
RUN apt-get update && apt-get install -y \
    unzip \
    vim

RUN unzip $INSTALL_FILE \
 && rm $INSTALL_FILE

ENV PATH=$PATH:/opt/pgx/pgx-${PGX_VERSION}/bin
RUN mkdir /shared
WORKDIR /shared
