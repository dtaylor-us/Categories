#!/bin/bash

ENV_NAME=frozen-coast-81302
WAR_PATH=target/catcollab-1.0-SNAPSHOT.war

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

migrate_schema() {
    clear
    echo Please enter the JDBC MySQL url:
    read url
    export MIGRATION_DATABASE_URL=$url
    clear

    echo Username:
    read user
    export MIGRATION_DATABASE_USERNAME=$user
    clear

    echo Password:
    read -s password
    export MIGRATION_DATABASE_PASSWORD=$password
    clear
    mvn flyway:migrate
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
        deploy_war
        ;;
    migrate)
        migrate_schema
        ;;
    
    esac
exit 0