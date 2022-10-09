@echo off
call mvn clean package
call docker build -t br.edu.ifsul/PW-Biblioteca-Web .
call docker rm -f PW-Biblioteca-Web
call docker run -d -p 9080:9080 -p 9443:9443 --name PW-Biblioteca-Web br.edu.ifsul/PW-Biblioteca-Web