#!/bin/bash

ENV_NAME=frozen-coast-81302
WAR_PATH=target/catcollab.war
 
clean() {
    mvn clean
}
 
compile() {
    mvn compile
}
 
install() {
    mvn install
}
 
deploy_war() {
    heroku war:deploy $WAR_PATH --app $ENV_NAME
}
 
case $1 in
    clean)
        clean
        ;;
    compile)
        compile
        ;;
    install)
        install
        ;;
    deploy)
        clean
        install
        deploy_war
        ;;
    
    esac
exit 0