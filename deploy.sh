#!/bin/sh

server=172.30.6.35
pkg=cms-0.0.1-SNAPSHOT.jar
scp $PWD/target/$pkg mark@$server:/home/mark/

ssh mark@172.30.6.35 "source ~/.zshrc; echo $JAVA_HOME"
ssh mark@$server "$JAVA_HOME/bin/java -jar $pkg"

