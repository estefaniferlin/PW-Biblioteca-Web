#!/bin/sh
mvn clean package && docker build -t br.edu.ifsul/PW-Biblioteca-Web .
docker rm -f PW-Biblioteca-Web || true && docker run -d -p 9080:9080 -p 9443:9443 --name PW-Biblioteca-Web br.edu.ifsul/PW-Biblioteca-Web