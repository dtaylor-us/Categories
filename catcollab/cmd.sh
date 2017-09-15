#!/bin/bash

ENV_NAME=$(heroku info -s | grep git_url | sed 's#.\+\.com/\(.\+\)\.git$#\1#g')
WAR_PATH=target/catcollab-1.0-SNAPSHOT.war

export DATABASE_URL=$(heroku config | grep CLEARDB_DATABASE_URL | sed 's/^.*: //')

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
    mvn initialize flyway:migrate
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